package Model;

import java.io.Serializable;
import java.time.LocalDate;

public class BoughtProducts implements Serializable{
	private String name;
	private String barcode;
	private int quantity;
	private double boughtPrice;
	private double total;
	private String supplier;
	private LocalDate date;
	
	public BoughtProducts(String name, String barcode, double boughtPrice, String supplier, int quantity) {
		
		this.setName(name);
		this.setBarcode(barcode);
		this.setSupplier(supplier);
		this.setQuantity(quantity);
		this.setBoughtPrice(boughtPrice);
		this.setDate(LocalDate.now());
		this.setTotal();
	}

	public String getSupplier() {
		return supplier;
	}

	public void setSupplier(String supplier) {
		this.supplier = supplier;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBarcode() {
		return barcode;
	}

	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public double getBoughtPrice() {
		return boughtPrice;
	}

	public void setBoughtPrice(double boughtPrice) {
		this.boughtPrice = boughtPrice;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal() {
		this.total = this.boughtPrice * this.quantity;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}
	

}
