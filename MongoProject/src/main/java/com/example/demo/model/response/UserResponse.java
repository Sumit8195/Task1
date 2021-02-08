package com.example.demo.model.response;

import org.springframework.web.bind.annotation.ResponseBody;

@ResponseBody
public class UserResponse {
	private long uid;
	private String uemail;
	public String fname;
	
	public UserResponse() {
		super();
		// TODO Auto-generated constructor stub
	}
	public UserResponse(long uid, String uemail, String fname) {
		super();
		this.uid = uid;
		this.uemail = uemail;
		this.fname = fname;
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

}
