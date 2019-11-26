package crud.ovp.persistence.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="persons")
public class Person {

	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id")
	private int id;
	
	@Column(name="email")
	private String email;
	
	@Id
	@Column(name="userName")
	private String userName;
	
	@Column(name="password")
	private String password;
	
	@Column(name="typeOfUser")
	private String typeOfUser;
	
	@Column(name="adminsId")
	private String adminsId;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getTypeOfUser() {
		return typeOfUser;
	}

	public void setTypeOfUser(String typeOfUser) {
		this.typeOfUser = typeOfUser;
	}

	public String getAdminsId() {
		return adminsId;
	}

	public void setAdminsId(String adminsId) {
		this.adminsId = adminsId;
	}

	@Override
	public String toString() {
		return "Persons [id=" + id + ", email=" + email + ", userName=" + userName + ", password=" + password
				+ ", typeOfUser=" + typeOfUser + ", adminsId=" + adminsId + "]";
	}
	
	
	
}
