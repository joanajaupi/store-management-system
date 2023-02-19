package Model;

import java.io.Serializable;
import java.time.LocalDate;

public class Sold implements Serializable {
    private String name;
    private int productID;
    private int billNumber;
    private int quantity;
    private double price;
    private LocalDate soldDate;
    private double totalPrice;
    
    public Sold() {
    	
    	this.soldDate = LocalDate.now();
    }

}
