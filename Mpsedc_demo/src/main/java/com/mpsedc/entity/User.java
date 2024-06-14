package com.mpsedc.entity;

import java.sql.Date;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.NamedStoredProcedureQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.ParameterMode;
import jakarta.persistence.StoredProcedureParameter;
import jakarta.persistence.Table;



@Entity
@NamedStoredProcedureQuery(
	    name = "DeleteUserById",
	    procedureName = "DeleteUserById",
	    parameters = {
	        @StoredProcedureParameter(mode = ParameterMode.IN, name = "userId", type = Long.class)
	    }
	)
@Table(name = "users")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "userid")
	private Long id;

	@Column(name = "first_name")
	private String firstName;

	@Column(name = "middle_name")
	private String middleName;

	@Column(name = "last_name")
	private String lastname;

	@Column(name = "mobile_no")
	private String mobileNo;

	@Column(name = "date_of_birth")
	private Date dateOfBirth;

	@Column(name = "email")
	private String email;

	@Column(name = "cast_catagory")
	private String castCatagory;

	@Column(name = "specially_abled")
	private String speciallyAbled;

	@Column(name = "kind_disability")
	private String kindDisability;

	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Address> addresses;

	public User() {
		super();
	}

	public User(Long id, String firstName, String middleName, String lastname, String mobileNo, Date dateOfBirth,
			String email, String castCatagory, String speciallyAbled, String kindDisability, List<Address> addresses) {
		super();
		this.id = id;
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
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public List<Address> getAddresses() {
		return addresses;
	}

	public void setAddresses(List<Address> addresses) {
		this.addresses = addresses;
	}

}
