package Model;

import java.io.Serializable;

public class ProductOnCart implements Serializable{
	String name;
	String productID;
	double price;//unit price
	int quantity;
	double totalPrice;//unit price * quantity
	CD c;

	public ProductOnCart(CD prod) {
		this.name = prod.getCdName();
		this.productID = prod.getCdID();
		this.price = prod.getCdPrice();

		c = prod;

	}

	public String getName() {
		return name;
	}

	public String getProductID() {
		return productID;
	}

	public double getPrice() {
		return price;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		if (quantity <= c.getStockQuantity()) {
			this.quantity = quantity;
			c.setStockQuantity(c.getStockQuantity() - quantity);

		}else System.out.println("not enough stock");

	}

	public double getTotalPrice() {
		totalPrice = quantity * price;
		return totalPrice;
	}
}
