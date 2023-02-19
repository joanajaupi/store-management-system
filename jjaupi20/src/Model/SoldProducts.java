package Model;

import java.io.Serializable;

public class SoldProducts implements Serializable {

	private String soldProductName;
	private double productUnitPrice;
	private int quantity;
	private String genre;

	public SoldProducts(String n, double p, int q) {
		this.soldProductName = n;
		this.productUnitPrice = p;
		this.quantity = q;
	}

	public String getSoldProductName() {
		return soldProductName;
	}

	public void setSoldProductName(String soldProductName) {
		this.soldProductName = soldProductName;
	}

	public double getProductUnitPrice() {
		return productUnitPrice;
	}

	public void setProductUnitPrice(double productUnitPrice) {
		this.productUnitPrice = productUnitPrice;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public void addQuantity(int quant) {
		this.quantity += quant;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

}
