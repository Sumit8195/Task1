package com.example.demo.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="users")
public class User 
{
	@Id
	private Long userId;
	private String firstName;
	private String lastName;
	private String userEmail;
	private Long userContact;
	
	
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	public Long getUserContact() {
		return userContact;
	}
	public void setUserContact(Long userContact) {
		this.userContact = userContact;
	}
	public User() {
		super();
	}
	
	public User(Long userId, String firstName, String lastName, String userEmail, Long userContact) {
		super();
		this.userId = userId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.userEmail = userEmail;
		this.userContact = userContact;
	}
	@Override
	public String toString() {
		return "User [userId=" + userId + ", firstName=" + firstName + ", lastName=" + lastName + ", userEmail="
				+ userEmail + ", userContact=" + userContact + "]";
	}
	
	
}
