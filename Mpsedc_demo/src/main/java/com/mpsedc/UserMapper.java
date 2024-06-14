package com.mpsedc;

import java.util.List;
import java.util.stream.Collectors;

import com.mpsedc.dto.AddressDto;
import com.mpsedc.dto.UserDto;
import com.mpsedc.entity.Address;
import com.mpsedc.entity.User;

public class UserMapper {

	public static User toEntity(UserDto userDto) {
		User user = new User();
		user.setFirstName(userDto.getFirstName());
		user.setMiddleName(userDto.getMiddleName());
		user.setLastname(userDto.getLastname());
		user.setMobileNo(userDto.getMobileNo());
		user.setDateOfBirth(userDto.getDateOfBirth());
		user.setEmail(userDto.getEmail());
		user.setCastCatagory(userDto.getCastCatagory());
		user.setSpeciallyAbled((userDto.getSpeciallyAbled()));
		user.setKindDisability(userDto.getKindDisability());

		List<Address> addresses = userDto.getAddresses().stream().map(addressDto -> {
			Address address = new Address();
			address.setDistrict(addressDto.getDistrict());
			address.setState(addressDto.getState());
			address.setCity(addressDto.getCity());
			address.setPinCode(addressDto.getPinCode());
			address.setUser(user);
			return address;
		}).collect(Collectors.toList());

		user.setAddresses(addresses);

		return user;
	}

	public static UserDto toDTO(User user) {
		UserDto userDto = new UserDto();
		userDto.setUserId(user.getId());
		userDto.setFirstName(user.getFirstName());
		userDto.setMiddleName(user.getMiddleName());
		userDto.setLastname(user.getLastname());
		userDto.setMobileNo(user.getMobileNo());
		userDto.setDateOfBirth(user.getDateOfBirth());
		userDto.setEmail(user.getEmail());
		userDto.setCastCatagory(user.getCastCatagory());
		userDto.setSpeciallyAbled(user.getSpeciallyAbled());
		userDto.setKindDisability(user.getKindDisability());

		List<AddressDto> addressDTOs = user.getAddresses().stream().map(address -> {
			AddressDto addressDto = new AddressDto();
			addressDto.setAddressId(address.getAddressId());
			addressDto.setState(address.getState());
			addressDto.setDistrict(address.getDistrict());
			addressDto.setCity(address.getCity());
			addressDto.setPinCode(address.getPinCode());
			return addressDto;
		}).collect(Collectors.toList());

		userDto.setAddresses(addressDTOs);

		return userDto;
	}
}
