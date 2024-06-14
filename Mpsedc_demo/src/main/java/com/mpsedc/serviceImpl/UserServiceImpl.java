package com.mpsedc.serviceImpl;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.mpsedc.UserMapper;
import com.mpsedc.dto.AddressDto;
import com.mpsedc.dto.UserDto;
import com.mpsedc.entity.Address;
import com.mpsedc.entity.User;
import com.mpsedc.repository.AddressRepository;
import com.mpsedc.repository.UserRepository;
import com.mpsedc.service.userService;

import jakarta.transaction.Transactional;

import com.mpsedc.exception.MobileNoFormateException;
import com.mpsedc.exception.ResourceNotFoundException;
import com.mpsedc.exception.UserAlreadyExistsException;

@Service
public class UserServiceImpl implements userService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private AddressRepository addressRepository;

	UserMapper userMapper = new UserMapper();

	@Transactional
	public UserDto createUser1(UserDto userDto) {
		if (userRepository.existsByEmail(userDto.getEmail())) {
			throw new UserAlreadyExistsException("Email already exists");
		}
		if (!isValidMobileNumber(userDto.getMobileNo())) {

			throw new MobileNoFormateException("Mobile number format is invalid: " + userDto.getMobileNo());
		}
		Long userId = userRepository.insertUser(userDto.getFirstName(), userDto.getMiddleName(), userDto.getLastname(),
				userDto.getMobileNo(), userDto.getDateOfBirth(), userDto.getEmail(), userDto.getCastCatagory(),
				userDto.getSpeciallyAbled(), userDto.getKindDisability());

		for (AddressDto addressDto : userDto.getAddresses()) {
			addressRepository.insertAddress(userId, addressDto.getState(), addressDto.getDistrict(),
					addressDto.getCity(), addressDto.getPinCode());
		}

		userDto.setUserId(userId);
		return userDto;
	}

	@Transactional
	public UserDto getUserWithAddresses(Long userId) {
		List<Object[]> results = userRepository.getUserWithAddresses(userId);
		if (results.isEmpty()) {
			return null; // or throw an exception if user not found
		}

		UserDto userDto = new UserDto();
		List<AddressDto> addressDtoList = new ArrayList<>();

		for (Object[] result : results) {
			if (userDto.getUserId() == null) {
				userDto.setUserId((Long) result[0]);
				userDto.setCastCatagory((String) result[1]);
				userDto.setDateOfBirth((Date) result[2]);
				userDto.setEmail((String) result[3]);
				userDto.setFirstName((String) result[4]);
				userDto.setKindDisability((String) result[5]);
				userDto.setLastname((String) result[6]);
				userDto.setMiddleName((String) result[7]);
				userDto.setMobileNo((String) result[8]);
				userDto.setSpeciallyAbled((String) result[9]);

			}

			if (result[10] != null) {
				AddressDto addressDto = new AddressDto();
				addressDto.setAddressId((Long) result[10]);
				addressDto.setState((String) result[11]);
				addressDto.setDistrict((String) result[12]);
				addressDto.setCity((String) result[13]);
				addressDto.setPinCode((String) result[14]);
				addressDtoList.add(addressDto);
			}
		}

		userDto.setAddresses(addressDtoList);
		return userDto;
	}

	@Transactional
	public UserDto updateUser1(Long id, UserDto userDto) {
		User existingUser = userRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("User", "id", id));

		if (!isValidMobileNumber(userDto.getMobileNo())) {
			throw new MobileNoFormateException("Mobile number format is invalid: " + userDto.getMobileNo());
		}

		// Update user information using stored procedure
		userRepository.updateUser(userDto.getUserId(), userDto.getFirstName(), userDto.getMiddleName(),
				userDto.getLastname(), userDto.getMobileNo(), userDto.getDateOfBirth(), userDto.getEmail(),
				userDto.getCastCatagory(), userDto.getSpeciallyAbled(), userDto.getKindDisability());

		// Fetch existing addresses from the database
		List<Address> existingAddresses = addressRepository.findByUserId(id);
		Map<Long, Address> addressMap = existingAddresses.stream()
				.collect(Collectors.toMap(Address::getAddressId, Function.identity()));

		// Update existing addresses and add new ones if necessary
		List<Address> updatedAddresses = userDto.getAddresses().stream().map(addressDto -> {
			Address address;
			if (addressDto.getAddressId() != null && addressMap.containsKey(addressDto.getAddressId())) {
				// Update existing address
				address = addressMap.get(addressDto.getAddressId());
				address.setDistrict(addressDto.getDistrict());
				address.setState(addressDto.getState());
				address.setCity(addressDto.getCity());
				address.setPinCode(addressDto.getPinCode());
				addressRepository.updateAddress(addressDto.getAddressId(), id, addressDto.getState(),
						addressDto.getDistrict(), addressDto.getCity(), addressDto.getPinCode());
			} else {
				// Create new address
				address = new Address();
				address.setUser(existingUser);
				address.setDistrict(addressDto.getDistrict());
				address.setState(addressDto.getState());
				address.setCity(addressDto.getCity());
				address.setPinCode(addressDto.getPinCode());
				addressRepository.save(address); // Save new address
			}
			return address;
		}).collect(Collectors.toList());

		existingUser.setAddresses(updatedAddresses);

		return UserMapper.toDTO(existingUser);
	}

	@Override
	public UserDto createUser(UserDto userDto) {
		if (userRepository.existsByEmail(userDto.getEmail())) {
			throw new UserAlreadyExistsException("Email already exists");
		}
		if (!isValidMobileNumber(userDto.getMobileNo())) {

			throw new MobileNoFormateException("Mobile number format is invalid: " + userDto.getMobileNo());
		}
		User user = UserMapper.toEntity(userDto);
		User savedUser = userRepository.save(user);
		return UserMapper.toDTO(savedUser);
	}

	public Optional<UserDto> getUserById(Long id) {
		Optional<User> user = userRepository.findById(id);
		return user.map(UserMapper::toDTO);
	}

	public List<UserDto> getAllUsers() {
		List<User> users = userRepository.findAll();
		return users.stream().map(user -> userMapper.toDTO(user)).collect(Collectors.toList());
	}

	public Page<UserDto> getAllUsers(int page, int size) {
		Pageable pageable = PageRequest.of(page, size);
		return userRepository.findAll(pageable).map(UserMapper::toDTO);
	}

	public UserDto updateUser(Long id, UserDto userDto) {
		User existingUser = userRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("User", "id", id));
		if (!isValidMobileNumber(userDto.getMobileNo())) {

			throw new MobileNoFormateException("Mobile number format is invalid: " + userDto.getMobileNo());
		}

		existingUser.setFirstName(userDto.getFirstName());
		existingUser.setMiddleName(userDto.getMiddleName());
		existingUser.setLastname(userDto.getLastname());
		existingUser.setMobileNo(userDto.getMobileNo());
		existingUser.setDateOfBirth(userDto.getDateOfBirth());
		existingUser.setEmail(userDto.getEmail());
		existingUser.setCastCatagory(userDto.getCastCatagory());
		existingUser.setSpeciallyAbled(userDto.getSpeciallyAbled());
		existingUser.setKindDisability(userDto.getKindDisability());

		List<Address> existingAddresses = existingUser.getAddresses();
		Map<Long, Address> addressMap = existingAddresses.stream()
				.collect(Collectors.toMap(Address::getAddressId, Function.identity()));

		// Update existing addresses and add new ones
		List<Address> updatedAddresses = userDto.getAddresses().stream().map(addressDto -> {
			Address address;
			if (addressDto.getAddressId() != null && addressMap.containsKey(addressDto.getAddressId())) {
				address = addressMap.get(addressDto.getAddressId());
			} else {
				address = new Address();
				address.setUser(existingUser);
			}
			address.setDistrict(addressDto.getDistrict());
			address.setState(addressDto.getState());
			address.setCity(addressDto.getCity());
			address.setPinCode(addressDto.getPinCode());
			return address;
		}).collect(Collectors.toList());

		existingUser.setAddresses(updatedAddresses);

		// Save the updated user
		User updatedUser = userRepository.save(existingUser);
		return UserMapper.toDTO(updatedUser);
	}

	private boolean isValidMobileNumber(String mobileNo) {
		// Define your mobile number format validation logic here
		return mobileNo.matches("\\d{10}");
	}

	@Transactional
	public boolean deleteUserById(Long userId) {
		if (userRepository.existsById(userId)) {
			addressRepository.deleteAddressById(userId);
			userRepository.deleteUserById(userId);
			return true;
		} else {
			return false;
		}

	}

	public List<UserDto> getUsersByCastCatagory(String castCatagory) {

		List<User> users = userRepository.findByCastCatagory(castCatagory);
		return users.stream().map(user -> userMapper.toDTO(user)).collect(Collectors.toList());

	}

	public List<UserDto> getUsersByDateOfBirthBetween(Date startDate, Date endDate) {
		List<User> users = userRepository.findUsersByDateOfBirthBetween(startDate, endDate);
		return users.stream().map(user -> userMapper.toDTO(user)).collect(Collectors.toList());
	}

}
