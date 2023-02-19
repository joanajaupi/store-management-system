package Model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;

public class Cashier extends User implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ArrayList<Bill> bills; 
	private int numberOfBills;

	public Cashier(String name, String surname, LocalDate birthday, String phoneNr, String id, String email, int l,
			String username, String password, double salary) {
		super(name, surname, birthday, phoneNr, id, email, l, username, password, salary);
		numberOfBills = 0;
		bills = new ArrayList<Bill>();
		

	}

	public ArrayList<Bill> getBills() {
		return bills;
	}

	public void setBills(ArrayList<Bill> bills) {
		this.bills = bills;
	}

	public int getNumberOfBills() {
		numberOfBills = bills.size();
		return this.numberOfBills;
	}

	public void addNewBill(Bill b) {
		bills.add(b);
		accessCashiers ac = new accessCashiers();
		ac.editCashier(ac.getPosition(this), this);
	}

	public String toString() {
		return "Cashier: " + this.getName() + " " + this.getSurname() + " " + this.getLvl() + " " + this.getID();
	}



}
