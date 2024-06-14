package com.mpsedc.controller;

import java.sql.Date;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mpsedc.dto.ApiResponse;
import com.mpsedc.dto.Constants;
import com.mpsedc.dto.UserDto;
import com.mpsedc.service.userService;

import jakarta.validation.Valid;

/*

 Author: Rajkumar saad
 date 16/05/2024

*/

@RestController
@RequestMapping("/api")
@Validated
@CrossOrigin("*")
public class UserController {

	@Autowired
	private userService userService1;

	// create user by stored procedure
	@PostMapping("/create")
	public ResponseEntity<ApiResponse> createUser1(@RequestBody UserDto userDto) {

		UserDto createdUser = userService1.createUser1(userDto);
		return ResponseEntity.ok(new ApiResponse(true, "User created successfully", createdUser));

	}

	// get user by stored procedure
	@GetMapping("/get/user/{id}")
	public ResponseEntity<ApiResponse> getUserWithAddresses(@PathVariable Long id) {
		UserDto userDto = userService1.getUserWithAddresses(id);
		if (userDto != null) {

			return ResponseEntity.ok(new ApiResponse(Constants.SUCCESS, "User Data", userDto));
		} else  

			return ResponseEntity.ok(new ApiResponse(Constants.SUCCESS, "User not found !", userDto));
		} 

	// update user by stored procedure
	@PutMapping("user/update/{id}")
	public ResponseEntity<ApiResponse> updateUser1(@PathVariable Long id, @RequestBody UserDto userDto) {
		userDto.setUserId(id); // Ensure the user ID is set from the path variable
		UserDto updatedUser = userService1.updateUser1(id, userDto);

		if (updatedUser != null) {
			return ResponseEntity.ok(new ApiResponse(true, "User updated successfully", updatedUser));
		} else {
			return ResponseEntity.status(400).body(new ApiResponse(true, "Null ", userDto.getUserId()));
		}

	}

	// delete user by stored procedure
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<ApiResponse> deleteUserById(@PathVariable Long id) {
		boolean isDeleted = userService1.deleteUserById(id);
		if (isDeleted) {
			return ResponseEntity.ok(new ApiResponse(isDeleted, "User deleted successfully", id));
		} else {
			return ResponseEntity.ok(new ApiResponse(true, "User not found", null));
		}

	}

	@PostMapping("/user")
	public ResponseEntity<Map<String, Object>> createUser(@Valid @RequestBody UserDto userDto) {
		UserDto createdUser = userService1.createUser(userDto);
		Map<String, Object> response = new HashMap<>();
		response.put("status", true);
		response.put("data", createdUser);
		return new ResponseEntity<>(response, HttpStatus.OK);

	}

	@GetMapping("/alluser")
	public ResponseEntity<List<UserDto>> getAllUsers() {
		return new ResponseEntity<>(userService1.getAllUsers(), HttpStatus.OK);
	}

	// get all users with pagination
	@GetMapping("/allusers")
	public ResponseEntity<Page<UserDto>> getAllUsers(@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "10") int size) {
		Page<UserDto> usersPage = userService1.getAllUsers(page, size);
		return new ResponseEntity<>(usersPage, HttpStatus.OK);
	}

	@GetMapping("/get/{id}")
	public ResponseEntity<Map<String, Object>> getUserById(@PathVariable Long id) {
		Optional<UserDto> user = userService1.getUserById(id);

		Map<String, Object> response = new HashMap<>();
		response.put("status", true);
		response.put("data", user);
		return new ResponseEntity<>(response, HttpStatus.OK);

	}

	@PutMapping("/update/{id}")
	public ResponseEntity<UserDto> updateUser(@PathVariable Long id, @Valid @RequestBody UserDto userDto) {

		UserDto updatedUser = userService1.updateUser(id, userDto);

		return ResponseEntity.ok(updatedUser);

	}


	@GetMapping("/users")
	public List<UserDto> getUsersByCastCatagory(@RequestParam String castCatagory) {
		return userService1.getUsersByCastCatagory(castCatagory);
	}

	@GetMapping("/by-date-of-birth")
	public ResponseEntity<List<UserDto>> getUsersByDateOfBirthBetween(@RequestParam Date startDate,
			@RequestParam Date endDate) {
		List<UserDto> users = userService1.getUsersByDateOfBirthBetween(startDate, endDate);
		return new ResponseEntity<>(users, HttpStatus.OK);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<?> handleValidationExceptions(MethodArgumentNotValidException ex) {
		String errorMessage = ex.getBindingResult().getFieldErrors().stream()
				.map(DefaultMessageSourceResolvable::getDefaultMessage).findFirst().orElse("Invalid input");
		return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
	}

}
