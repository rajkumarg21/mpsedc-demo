package com.mpsedc.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "login")
public class Login {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long LonginId;

    private String username;
    
    private String password;

	public Login() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Login(Long longinId, String username, String password) {
		super();
		LonginId = longinId;
		this.username = username;
		this.password = password;
	}

	public Long getLonginId() {
		return LonginId;
	}

	public void setLonginId(Long longinId) {
		LonginId = longinId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
