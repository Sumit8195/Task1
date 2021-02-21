package com.example.demo.model.dto;


import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;


import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.web.bind.annotation.ResponseBody;

@ResponseBody
public class UserDTO extends RepresentationModel<UserDTO> {
	
	@NotNull
	private Long userId;
	@NotNull
	public String firstName;
	@NotNull
	private String lastName;
	@NotNull
	private Long userContact;
	
	@NotNull
	@Email
	@Indexed(unique=true)
	private String userEmail;
	
	
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
	public Long getUserContact() {
		return userContact;
	}
	public void setUserContact(Long userContact) {
		this.userContact = userContact;
	}
	
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	@Override
	public String toString() {
		return "UserDTO [userId=" + userId + ", firstName=" + firstName + ", lastName=" + lastName + ", userContact="
				+ userContact + ", uesrEmail=" + userEmail + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
		result = prime * result + ((userContact == null) ? 0 : userContact.hashCode());
		result = prime * result + ((userEmail == null) ? 0 : userEmail.hashCode());
		result = prime * result + ((userId == null) ? 0 : userId.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserDTO other = (UserDTO) obj;
		if (firstName == null) {
			if (other.firstName != null)
				return false;
		} else if (!firstName.equals(other.firstName))
			return false;
		if (lastName == null) {
			if (other.lastName != null)
				return false;
		} else if (!lastName.equals(other.lastName))
			return false;
		if (userContact == null) {
			if (other.userContact != null)
				return false;
		} else if (!userContact.equals(other.userContact))
			return false;
		if (userEmail == null) {
			if (other.userEmail != null)
				return false;
		} else if (!userEmail.equals(other.userEmail))
			return false;
		if (userId == null) {
			if (other.userId != null)
				return false;
		} else if (!userId.equals(other.userId))
			return false;
		return true;
	}

	
	
	
	

}
