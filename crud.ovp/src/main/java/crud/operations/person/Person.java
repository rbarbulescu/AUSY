package crud.operations.person;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="persons")
public class Person {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id")
	private int id;
	
	@Column(name="email")
	private String email;
	
	@Column(name="username")
	private String username;
	
	@Column(name="password")
	private String password;
	
	@Column(name="typeofuser")
	private String typeofuser;
	
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

	public String getTypeofuser() {
		return typeofuser;
	}

	public void setTypeofuser(String typeofuser) {
		this.typeofuser = typeofuser;
	}

	public String getAdminsId() {
		return adminsId;
	}

	public void setAdminsId(String adminsId) {
		this.adminsId = adminsId;
	}

	@Override
	public String toString() {
		return "Persons [id=" + id + ", email=" + email + ", username=" + username + ", password=" + password
				+ ", typeofuser=" + typeofuser + ", adminsId=" + adminsId + "]";
	}
	
	
	
}
