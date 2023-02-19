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

public class AccessSoldProducts {

	public static final String filename = "sources/data/sold.ser";
	InputStream file, Buffer;
	OutputStream bf, fl;
	ObjectInput input;
	ObjectOutput output;
	File uf = new File(filename);
	private ArrayList<SoldProducts> cdsSold = new ArrayList<SoldProducts>();

	public AccessSoldProducts() {
		if (uf.length() > 0) {
			this.readFile();
			//System.out.println(">>>>Reading file");
		}
	}

	public ArrayList<SoldProducts> getCdsSold() {
		this.readFile();
		return cdsSold;
	}

	public void setCdsSold(ArrayList<SoldProducts> cdsSold) {
		this.cdsSold = cdsSold;
		this.write();
	}

	public void write() {
		try {
			fl = new FileOutputStream(uf);
			bf = new BufferedOutputStream(fl);
			output = new ObjectOutputStream(bf);
			output.writeObject(cdsSold);
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
				cdsSold = (ArrayList<SoldProducts>) input.readObject();
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

	public void add(SoldProducts toAdd) {
		cdsSold.add(toAdd);
		this.write();
	}

}
