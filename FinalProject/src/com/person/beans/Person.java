package com.person.beans;

public class Person {
	private long id;
	private String email;
	private String username;
	private String password;
	private String typeofuser;
	private String adminsId;
	
	public Person() {
		
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String usernamne) {
		this.username = usernamne;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getTypeofuser() {
		return typeofuser;
	}
	public void setTypeofuser(String typeofuser) {
		this.typeofuser = typeofuser;
	}
	public String getAdminsId() {
		return adminsId;
	}
	public void setAdminsId(String adminId) {
		this.adminsId = adminId;
	}
}
