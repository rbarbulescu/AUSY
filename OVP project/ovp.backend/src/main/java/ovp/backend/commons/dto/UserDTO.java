package ovp.backend.commons.dto;

import java.io.Serializable;

import ovp.backend.persistence.model.User;

public class UserDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1001720607109291674L;
	
	//private fields
	private String email;
	private String userName;
	private int userTypeId;
	
	public UserDTO() {
		super();
	}
	
	public UserDTO(User user) {
		this.email = user.getEmail();
		this.userName = user.getUserName();
		this.userTypeId = user.getUserTypeId();
	}
	
	public UserDTO(String email, String userName, int userTypeId) {
		// TODO Auto-generated constructor stub
		this.email = email;
		this.userName = userName;
		this.userTypeId = userTypeId;
	}

	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public int getUserTypeId() {
		return userTypeId;
	}
	public void setUserTypeId(int userTypeId) {
		this.userTypeId = userTypeId;
	}

	@Override
	public String toString() {
		return "UserDTO [email=" + email + ", userName=" + userName + ", userTypeId=" + userTypeId + "]";
	}
	
}