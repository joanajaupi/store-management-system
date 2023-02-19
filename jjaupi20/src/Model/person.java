package Model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

abstract class person implements Serializable{
	private String name;
	private String surname;
	private LocalDate birthday;
	private String phoneNumber;
	private String iNo;
	private String email;
	// private static int noOfPeople = 0;
	public person() {
		
	}
	
	public person(String name, String surname, LocalDate birthday, String phoneNr, String id, String email) {
		setName(name);
		setSurname(surname);
		setBirthday(birthday);
		setPhoneNumber(phoneNr);
		setID(id);
		setEmail(email);
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	public LocalDate getBirthday() {
		return birthday;
	}
	public void setBirthday(LocalDate birthday) {
		this.birthday = birthday;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getID() {
		return iNo;
	}
	public void setID(String iD) {
		iNo = iD;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}


}
