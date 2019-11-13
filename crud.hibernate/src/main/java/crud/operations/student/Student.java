package crud.operations.student;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="students")
public class Student {
	
	@Column(name="firstname")
	private String firstname;
	
	@Column(name="lastname")
	private String lastname;
	
	@Column(name="phone")
	private double phone;
	
	@Column(name="address")
	private String address;
	
	@Id
	@Column(name="CNP")
	private double CNP;
	
	@Column(name="birthday")
	private Date birthday;
	
	@Column(name="trips")
	private int trips;
	
	@Column(name="personsId")
	private String personsId;
	
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public double getPhone() {
		return phone;
	}
	public void setPhone(double phone) {
		this.phone = phone;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public double getCNP() {
		return CNP;
	}
	public void setCNP(double cNP) {
		CNP = cNP;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
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
		return "Student [firstname=" + firstname + ", lastname=" + lastname + ", phone=" + phone + ", address="
				+ address + ", CNP=" + CNP + ", birthday=" + birthday + ", trips=" + trips + ", personsId=" + personsId
				+ "]";
	}
	
}
