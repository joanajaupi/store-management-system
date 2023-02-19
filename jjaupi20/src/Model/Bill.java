package Model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;

public class Bill implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	int billNo;
	double price;
	LocalDate datePrinted;
	private ArrayList<ProductOnCart> products = new ArrayList<ProductOnCart>();

	public Bill() {

		billNo = getTotalNrOfBills() + 1;
		price = 0.0;
		datePrinted = LocalDate.now();
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public LocalDate getDatePrinted() {
		return datePrinted;
	}

	public int getBillNo() {
		return billNo;
	}

	public int getTotalNrOfBills() {
		int nrOfBills = 0;
		accessCashiers acCash = new accessCashiers();
		for (Cashier c : acCash.getCashiers())
			nrOfBills += c.getNumberOfBills();
		return nrOfBills;
	}

	public ArrayList<ProductOnCart> getProducts() {
		return products;
	}

	public void setProducts(ArrayList<ProductOnCart> products) {
		this.products = products;
	}
	public void addProduct(ProductOnCart pinCart) {
		this.products.add(pinCart);
	} 

}
