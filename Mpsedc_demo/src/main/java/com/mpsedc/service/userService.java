package com.mpsedc.service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;

import com.mpsedc.dto.UserDto;
import com.mpsedc.entity.User;

public interface userService {
	public List<UserDto> getAllUsers();
	
	public UserDto createUser(UserDto userDto);

	public Optional<UserDto> getUserById(Long id);

	public UserDto updateUser(Long id, UserDto userDto);

	
	List<UserDto> getUsersByCastCatagory(String castCatagory);
	public List<UserDto> getUsersByDateOfBirthBetween(Date startDate, Date endDate);
	
//	List<UserDto> searchUsersByFirstName(String query);
	 public UserDto createUser1(UserDto userDto);
	 public UserDto updateUser1(Long id, UserDto userDto);
	 public UserDto getUserWithAddresses(Long userId);
	 
	 public boolean deleteUserById(Long userId);
	 public Page<UserDto> getAllUsers(int page, int size);

}
