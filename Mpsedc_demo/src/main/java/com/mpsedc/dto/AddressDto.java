package com.mpsedc.dto;

import jakarta.validation.constraints.Pattern;

public class AddressDto {
	private Long addressId;
	private String state;
	private String district;
	private String city;

	@Pattern(regexp = "^[0-9]{5}$", message = "Pin code must be 5 digits")
	private String pinCode;
	private Long userId;

	public AddressDto() {
		super();
	}

	public AddressDto(String state, String district, String city, String pinCode, Long userId) {
		super();
		this.state = state;
		this.district = district;
		this.city = city;
		this.pinCode = pinCode;
		this.userId = userId;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getPinCode() {
		return pinCode;
	}

	public void setPinCode(String pinCode) {
		this.pinCode = pinCode;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getAddressId() {
		return addressId;
	}

	public void setAddressId(Long addressId) {
		this.addressId = addressId;
	}

}
