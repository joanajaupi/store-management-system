package Model;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;

public class AccessSuppliers {
	public static String filename = "sources/data/furnitor.ser";
	private ArrayList<Supplier> suppliers = new ArrayList<Supplier>();
	InputStream file, buffer;
	OutputStream bf, fl;
	ObjectInput input;
	ObjectOutput output;
	File uf = new File(filename);

	public AccessSuppliers() {
		if (uf.length() > 0) {
			readFile();
			//System.out.println(">>>>Reading file");
		}else
			System.out.println("File empty");
	}

	public ArrayList<Supplier> getSuppliers() {
		return suppliers;
	}

	public void setSuppliers(ArrayList<Supplier> suppliers) {
		this.suppliers = suppliers;
		this.writeFile();
	}

	@SuppressWarnings("unchecked")
	public void readFile() {
		try {
			file = new FileInputStream(uf);
			buffer = new BufferedInputStream(file);
			input = new ObjectInputStream(buffer);
			suppliers = (ArrayList<Supplier>) input.readObject();
			/*
			 * for(User u: users) { System.out.println("Data: " + u.toString()); }
			 */
		} catch (EOFException e) {
			System.out.println("Suppliers End of file");
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeFile();
		}

	}

	public void closeFile() {
		try {
			if (input != null) {
				input.close();
				buffer.close();
				file.close();
			}
			if (output != null) {
				output.close();
				bf.close();
				fl.close();
			}
		} catch (IOException e) {
			System.out.println(e.toString());
		}
	}

	private void writeFile() {
		try {
			fl = new FileOutputStream(uf);
			bf = new BufferedOutputStream(fl);
			output = new ObjectOutputStream(bf);
			output.writeObject(suppliers);
		} catch (IOException ex) {
			System.out.println("Cannot perform output." + ex.toString());
		}
		closeFile();

	}

	public void addSup(Supplier a) {
		suppliers.add(a);
		this.writeFile();
	}

	public void remove(Supplier a) {
		suppliers.remove(a);
		this.writeFile();
	}

	public ArrayList<String> toStringArrayList() {
		ArrayList<String> list = new ArrayList<String>();
		for (Supplier s : suppliers) {
			list.add(s.getName());
		}
		return list;
	}

}
