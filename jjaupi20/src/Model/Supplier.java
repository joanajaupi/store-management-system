package Model;

import java.io.Serializable;
import java.util.ArrayList;

public class Supplier implements Serializable{
	
	private String name;
	//AccessCD ac = new AccessCD();
	public Supplier(String supplierName) {
		name = supplierName;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	

}
