package Model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

@SuppressWarnings("serial")
public class User extends person implements Serializable{
	
	private static int userCount = -1;
	private final int UserId; 
	
	private String username;
	private String password;
	private int lvl; //1 = admin 2 = menager 3= cashier
	private double salary;

	public User(String name, String surname, LocalDate birthday, String phoneNr, String id, String email, int l, String username, String password, double salary) {
		
		super(name, surname, birthday, phoneNr, id, email);
		this.UserId = new AccessUsers().getUserCount()+1;
		// TODO Auto-generated constructor stub
		/*if(userCount < 0) {
			userCount = 1;
		}
		else 
			userCount++;
		setUserId(userCount);*/
		setUsername(username);
		setPassword(password);
		setLvl(l);
		setSalary(salary);
		
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

	public int getLvl() {
		return lvl;
	}

	public void setLvl(int lvl) {
		this.lvl = lvl;
	}


	public String toString() {
		return "Name: " + super.getName() + "surname: " + super.getSurname() +"\nusername: " + getUsername() +
				" password: " + getPassword();
	}

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

	public int getUserId() {
		return UserId;
	}
}
