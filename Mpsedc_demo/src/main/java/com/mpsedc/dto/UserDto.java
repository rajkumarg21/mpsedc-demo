package com.mpsedc.dto;

import java.sql.Date;
import java.util.List;

//import com.mpsedc.entity.Address;
//
//import jakarta.validation.constraints.Pattern;

public class UserDto {

	private Long userId;
	private String firstName;
	private String middleName;
	private String lastname;

	//@Pattern(regexp = "^[0-9]{10}$", message = "Mobile number must be 10 digits")
	private String mobileNo;
	private Date dateOfBirth;
	private String email;
	private String castCatagory;
	private String speciallyAbled;
	private String kindDisability;
	private List<AddressDto> addresses;

	public UserDto() {
		super();
	}

	public UserDto(Long userId,String firstName, String middleName, String lastname, String mobileNo, Date dateOfBirth,
			String email, String castCatagory, String speciallyAbled, String kindDisability,
			List<AddressDto> addresses) {

		this.firstName = firstName;
		this.middleName = middleName;
		this.lastname = lastname;
		this.mobileNo = mobileNo;
		this.dateOfBirth = dateOfBirth;
		this.email = email;
		this.castCatagory = castCatagory;
		this.speciallyAbled = speciallyAbled;
		this.kindDisability = kindDisability;
		this.addresses = addresses;
		this.userId= userId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCastCatagory() {
		return castCatagory;
	}

	public void setCastCatagory(String castCatagory) {
		this.castCatagory = castCatagory;
	}

	public String getSpeciallyAbled() {
		return speciallyAbled;
	}

	public void setSpeciallyAbled(String speciallyAbled) {
		this.speciallyAbled = speciallyAbled;
	}

	public String getKindDisability() {
		return kindDisability;
	}

	public void setKindDisability(String kindDisability) {
		this.kindDisability = kindDisability;
	}

	public List<AddressDto> getAddresses() {
		return addresses;
	}

	public void setAddresses(List<AddressDto> addresses) {
		this.addresses = addresses;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	

}
