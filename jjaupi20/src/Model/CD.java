package Model;

import java.io.Serializable;

public class CD implements Serializable {
	
	private String cdID;
	private String cdName;
	private String cdGenre;
	private Double cdPrice;
	private String cdSinger;
	private String supplier;
	private int stockQuantity;
	private Double byingPrice;
	private Double totalBuyingPrice;

	public CD(String id, String name, String genre, double cdPrice, int quant, String supplier, String singer, double buyingP) {
		this.setCdID(id);
		this.setCdName(name);
		this.setCdGenre(genre);
		this.setCdPrice(cdPrice);
		this.setStockQuantity(quant);
		this.setSupplier(supplier);
		this.setCdSinger(singer);
		this.setByingPrice(buyingP);
		getTotalBuyingPrice();
	}

	public CD() {
	}

	public String getCdID() {
		return cdID;
	}

	public void setCdID(String cdID) {
		this.cdID = cdID;
	}

	public String getCdName() {
		return cdName;
	}

	public void setCdName(String cdName) {
		this.cdName = cdName;
	}

	public String getCdGenre() {
		return cdGenre;
	}

	public void setCdGenre(String cdGenre) {
		this.cdGenre = cdGenre;
	}

	public double getCdPrice() {
		return cdPrice;
	}

	public void setCdPrice(double cdPrice) {
		this.cdPrice = cdPrice;
	}

	public String getSupplier() {
		return supplier;
	}

	public void setSupplier(String supplier) {
		this.supplier = supplier;
	}

	public void setStockQuantity(int quant) {
		this.stockQuantity = quant;
	}

	public int getStockQuantity() {
		return stockQuantity;
	}

	public void addQuantityBack(int quantity) {
		this.stockQuantity += quantity;
	}

	public String getCdSinger() {
		return cdSinger;
	}

	public void setCdSinger(String cdSinger) {
		this.cdSinger = cdSinger;
	}

	public String toString() {
		return this.cdName + " " + this.cdID + " " + this.cdGenre + " " + this.cdPrice + " " + this.supplier + " "
				+ this.stockQuantity + " " + this.cdSinger;
	}

	public Double getByingPrice() {
		return byingPrice;
	}

	public void setByingPrice(Double byingPrice) {
		this.byingPrice = byingPrice;
	}

	public Double getTotalBuyingPrice() {
		this.totalBuyingPrice = this.stockQuantity * this.byingPrice;
		return totalBuyingPrice;
	}




	/*public void removeFromStock(int quantity) {
		if (this.stockQuantity > quantity)
			this.stockQuantity -= quantity;
		else System.out.println("not enough in stock");
	}*/

}
