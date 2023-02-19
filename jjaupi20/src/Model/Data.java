package Model;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;



public class Data {
	
	/*static String cashiersPath = "resources/data/cashiers.dat";
	static String managersPath = "resources/data/managers.dat";
	static String suppliersPath = "resources/data/suppliers.dat";
	static String CDspath = "resources/data/CDs.dat";
	private static ArrayList<Cashier> cashierList;
	private static ArrayList<Menager> menager;
	private static ArrayList<CD> CDs;
	private static ArrayList<Supplier>suppliers;
	
	
	public static void openFiles() {
		try {
			FileInputStream CashierFile = new FileInputStream(cashiersPath);
			ObjectInputStream inptCashiers = new ObjectInputStream(CashierFile);
			FileInputStream ManagersFile = new FileInputStream(managersPath);
			ObjectInputStream inptManagers = new ObjectInputStream(ManagersFile);
			FileInputStream SuppliersFile =new FileInputStream(suppliersPath);
			ObjectInputStream inptSuppliers = new ObjectInputStream(SuppliersFile);
			FileInputStream CdsFile = new FileInputStream(CDspath);
			ObjectInputStream inptCDs = new ObjectInputStream(CdsFile);
			
			 cashierList = (ArrayList<Cashier>)inptCashiers.readObject();
			 menager = (ArrayList<Menager>)inptManagers.readObject();
			 CDs =(ArrayList<CD>)inptCDs.readObject();
			 suppliers = (ArrayList<Supplier>)inptSuppliers.readObject();
			
			CashierFile.close();
			inptCashiers.close();
			ManagersFile.close();
			inptManagers.close();
			SuppliersFile.close();
			inptSuppliers.close();
			CdsFile.close();
			inptCDs.close();
		}catch(Exception e) {
			System.out.println("Exception caught");
		}
	}

	public static void save() {
		 
		try {
			FileOutputStream fCashiers = new FileOutputStream(cashiersPath);
			ObjectOutputStream outCashiers = new ObjectOutputStream(fCashiers);
			
			FileOutputStream fManagers = new FileOutputStream(managersPath);
			ObjectOutputStream outManagers =new ObjectOutputStream(fManagers);
			
			FileOutputStream fSuppliers = new FileOutputStream(suppliersPath);
			ObjectOutputStream outSuppliers =new ObjectOutputStream(fSuppliers);
			
			FileOutputStream fCds = new FileOutputStream(CDspath);
			ObjectOutputStream outCds =new ObjectOutputStream(fCds);
			
			outCashiers.writeObject(cashierList);
			outManagers.writeObject(menager);
			outSuppliers.writeObject(suppliers);
			outCds.writeObject(CDs);
			
			fCashiers.close();
			outCashiers.close();
			fManagers.close();
			outManagers.close();
			fCds.close();
			outCds.close();
			fSuppliers.close();
			outSuppliers.close();
			
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
         

	}
	public static ArrayList<Cashier> getCashierList() {
		return cashierList;
	}

	public static void setCashierList(ArrayList<Cashier> cashierList) {
		Data.cashierList = cashierList;
	}

	public static ArrayList<Menager> getMenager() {
		return menager;
	}

	public static void setMenager(ArrayList<Menager> menager) {
		Data.menager = menager;
	}

	public static ArrayList<CD> getCDs() {
		return CDs;
	}

	public static void setCDs(ArrayList<CD> cDs) {
		CDs = cDs;
	}

	public static ArrayList<Supplier> getSuppliers() {
		return suppliers;
	}

	public static void setSuppliers(ArrayList<Supplier> suppliers) {
		Data.suppliers = suppliers;
	}
	*/
	
}