package com.example.demo.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="user")
public class User 
{
	@Id
	private long uid;
	private String fname;
	private String lname;
	private String uemail;
	private long ucontact;
	
	public String getFname() {
		return fname;
	}
	public void setFname(String fname) {
		this.fname = fname;
	}
	public String getLname() {
		return lname;
	}
	public void setLname(String lname) {
		this.lname = lname;
	}
	public String getUemail() {
		return uemail;
	}
	public void setUemail(String uemail) {
		this.uemail = uemail;
	}
	public long getUid() {
		return uid;
	}
	public void setUid(long uid) {
		this.uid = uid;
	}
	public long getUcontact() {
		return ucontact;
	}
	public void setUcontact(long ucontact) {
		this.ucontact = ucontact;
	}
	public User() {
		super();
	}
	public User(String fname, String lname, String uemail, long ucontact,long uid) {
		super();
		this.fname = fname;
		this.lname = lname;
		this.uemail = uemail;
		this.ucontact = ucontact;
		this.uid = uid;
	}
	@Override
	public String toString() {
		return "User [fname=" + fname + ", lname=" + lname + ", uemail=" + uemail + ", uid=" + uid + ", ucontact="
				+ ucontact + "]";
	}
	
}
