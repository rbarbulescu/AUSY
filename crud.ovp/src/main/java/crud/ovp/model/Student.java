package crud.ovp.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="students")
public class Student {
	
	@Column(name="firstName")
	private String firstName;
	
	@Column(name="lastName")
	private String lastName;
	
	@Column(name="phone")
	private String phone;
	
	@Column(name="address")
	private String address;
	
	@Id
	@Column(name="CNP")
	private String CNP;
	
	@Column(name="birthday")
	private String birthday;
	
	@Column(name="trips")
	private int trips;
	
	@Column(name="personsId")
	private String personsId;
	
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
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getCNP() {
		return CNP;
	}
	public void setCNP(String CNP) {
		this.CNP = CNP;
	}
	public String getBirthday() {
		return birthday;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	public int getTrips() {
		return trips;
	}
	public void setTrips(int trips) {
		this.trips = trips;
	}
	public String getPersonsId() {
		return personsId;
	}
	public void setPersonsId(String personsId) {
		this.personsId = personsId;
	}
	
	@Override
	public String toString() {
		return "Student [firstName=" + firstName + ", lastName=" + lastName + ", phone=" + phone + ", address="
				+ address + ", CNP=" + CNP + ", birthday=" + birthday + ", trips=" + trips + ", personsId=" + personsId
				+ "]";
	}
	
}
