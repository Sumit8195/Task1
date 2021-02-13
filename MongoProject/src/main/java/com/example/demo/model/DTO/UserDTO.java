package com.example.demo.model.DTO;


import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;


import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.web.bind.annotation.ResponseBody;

@ResponseBody
public class UserDTO extends RepresentationModel<UserDTO> {
	
	@NotNull
	private long uid;
	@NotNull
	public String fname;
	@NotNull
	private String lname;
	@NotNull
	private long ucontact;
	
	@NotNull
	@Email
	@Indexed(unique=true)
	private String uemail;
	
	public String getLname() {
		return lname;
	}
	public void setLname(String lname) {
		this.lname = lname;
	}
	public long getUcontact() {
		return ucontact;
	}
	public void setUcontact(long ucontact) {
		this.ucontact = ucontact;
	}
	
	public long getUid() {
		return uid;
	}
	public void setUid(long uid) {
		this.uid = uid;
	}
	public String getUemail() {
		return uemail;
	}
	public void setUemail(String uemail) {
		this.uemail = uemail;
	}
	public String getFname() {
		return fname;
	}
	public void setFname(String fname) {
		this.fname = fname;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((fname == null) ? 0 : fname.hashCode());
		result = prime * result + ((lname == null) ? 0 : lname.hashCode());
		result = prime * result + (int) (ucontact ^ (ucontact >>> 32));
		result = prime * result + ((uemail == null) ? 0 : uemail.hashCode());
		result = prime * result + (int) (uid ^ (uid >>> 32));
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserDTO other = (UserDTO) obj;
		if (fname == null) {
			if (other.fname != null)
				return false;
		} else if (!fname.equals(other.fname))
			return false;
		if (lname == null) {
			if (other.lname != null)
				return false;
		} else if (!lname.equals(other.lname))
			return false;
		if (ucontact != other.ucontact)
			return false;
		if (uemail == null) {
			if (other.uemail != null)
				return false;
		} else if (!uemail.equals(other.uemail))
			return false;
		if (uid != other.uid)
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "UserResponse [uid=" + uid + ", uemail=" + uemail + ", fname=" + fname + ", lname=" + lname
				+ ", ucontact=" + ucontact + "]";
	}
	
	

}
