package com.javaex.vo;

public class UserVo {
	private int no;
	private String names;
	private String email;
	private String passwords;
	private String gender;
	public UserVo() {
		super();
	}
	
	
	public UserVo(int no, String names, String email, String passwords, String gender) {
		super();
		this.no = no;
		this.names = names;
		this.email = email;
		this.passwords = passwords;
		this.gender = gender;
	}
	

	public UserVo(String names, String email, String passwords, String gender) {
		super();
		this.names = names;
		this.email = email;
		this.passwords = passwords;
		this.gender = gender;
	}


	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getNames() {
		return names;
	}
	public void setNames(String names) {
		this.names = names;
	}
	public String getPasswords() {
		return passwords;
	}
	public void setPasswords(String passwords) {
		this.passwords = passwords;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	@Override
	public String toString() {
		return "UserVo [no=" + no + ", email=" + email + ", names=" + names + ", passwords=" + passwords + ", gender="
				+ gender + "]";
	}
	
	
}
