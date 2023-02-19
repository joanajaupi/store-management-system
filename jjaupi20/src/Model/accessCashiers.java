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

public class accessCashiers {
	public static final String filename = "sources/data/shitesit.ser";
	InputStream file, Buffer;
	OutputStream bf, fl;
	ObjectInput input;
	ObjectOutput output;
	File uf = new File(filename);

	private ArrayList<Cashier> cashiers = new ArrayList<>();

	public accessCashiers() {
		if (uf.length() > 0) {
			readFile();
			//System.out.println(">>>>Reading file");
		} else
			System.out.println("File empty");

	}

	public ArrayList<Cashier> getCashiers() {
		return cashiers;
	}

	public void setCashiers(ArrayList<Cashier> cashiers) {
		this.cashiers = cashiers;
		writeFile();
	}

	public void writeFile() {
		try {
			fl = new FileOutputStream(uf);
			bf = new BufferedOutputStream(fl);
			output = new ObjectOutputStream(bf);
			output.writeObject(cashiers);
		} catch (IOException ex) {
			System.out.println("Ups cant performe output" + ex.toString());
		} finally {
			closeFile();
		}
	}

	@SuppressWarnings("unchecked")
	public void readFile() {
		try {
			file = new FileInputStream(uf);
			Buffer = new BufferedInputStream(file);
			input = new ObjectInputStream(Buffer);
			if (input != null) {
				cashiers = (ArrayList<Cashier>) input.readObject();
			} else
				System.out.println("input is null");
		} catch (EOFException a) {
			System.out.println("Nothing in file");
		} catch (ClassNotFoundException | IOException e) {
			// TODO Auto-generated catch block
			System.out.println("error @ reading");
			e.printStackTrace();
		} finally {
			closeFile();
		}
	}

	public void closeFile() {
		try {
			if (input != null) {
				input.close();
				Buffer.close();
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

	public void addCashier(Cashier c) {
		cashiers.add(c);
		this.writeFile();
	}

	public void removeCashier(Cashier c) {
		cashiers.remove(c);
		this.writeFile();
	}

	public void editCashier(int pos, Cashier c) {
		System.out.println(">>>> " + pos);
		if (pos < 0 || pos >= cashiers.size()) {
			System.out.println("Cannot find User");
			return;
		} else {
			cashiers.set(pos, c);
			this.writeFile();
			System.out.println("cd edited");
		}
	}

	public int getPosition(Cashier c) {
		// this.readF();
		System.out.println("--------------");
		System.out.println(c);
		System.out.println("--------------");
		for (int i = 0; i < cashiers.size(); i++) {
			if (cashiers.get(i).toString().equals(c.toString()))
				return i;
		}

		return -1;
	}
}
