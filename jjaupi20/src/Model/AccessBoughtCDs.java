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

public class AccessBoughtCDs {
	public static final String filename = "sources/data/bought.ser";
	InputStream file, Buffer;
	OutputStream bf, fl;
	ObjectInput input;
	ObjectOutput output;
	File uf = new File(filename);

	private ArrayList<BoughtProducts> boughtProd = new ArrayList<>();

	public AccessBoughtCDs() {
		if (uf.length() > 0)
			readFile();
	}

	public ArrayList<BoughtProducts> get() {
		return boughtProd;
	}

	public void setProd(ArrayList<BoughtProducts> b) {
		this.boughtProd = b;
		writeFile();
	}

	public void writeFile() {
		try {
			fl = new FileOutputStream(uf);
			bf = new BufferedOutputStream(fl);
			output = new ObjectOutputStream(bf);
			output.writeObject(boughtProd);
		} catch (IOException ex) {
			System.out.println("Ups cant performe output" + ex.toString());
		} finally {
			closeFile();
		}
	}

	public void readFile() {
		try {
			file = new FileInputStream(uf);
			Buffer = new BufferedInputStream(file);
			input = new ObjectInputStream(Buffer);
			if (input != null) {
				boughtProd = (ArrayList<BoughtProducts>) input.readObject();
			} else
				System.out.println("input is null");
		} catch (EOFException a) {
			System.out.println("EOF");
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

	public void addProd(BoughtProducts c) {
		boughtProd.add(c);
		this.writeFile();
	}

	public void removeProd(BoughtProducts c) {
		boughtProd.remove(c);
		this.writeFile();
	}

	public void editProd(int pos, BoughtProducts c) {
		System.out.println(">>>> " + pos);
		if (pos < 0 || pos >= boughtProd.size()) {
			System.out.println("Cannot find User");
			return;
		} else {
			boughtProd.set(pos, c);
			this.writeFile();
			System.out.println("cd edited");
		}
	}

	public int getPosition(BoughtProducts c) {
		// this.readF();
		System.out.println("--------------");
		System.out.println(c);
		System.out.println("--------------");
		for (int i = 0; i < boughtProd.size(); i++) {
			if (boughtProd.get(i).toString().equals(c.toString()))
				return i;
		}

		return -1;
	}
}
