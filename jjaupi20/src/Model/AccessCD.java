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

public class AccessCD {

	public static final String filename = "sources/data/vinyls.ser";

	private ArrayList<CD> cds = new ArrayList<CD>();;
	InputStream file, Buffer;
	OutputStream bf, fl;
	ObjectInput input;
	ObjectOutput output;
	File uf = new File(filename);

	public AccessCD() {
		if (uf.length() > 0) {
			readFile();
			//System.out.println(">>>>Reading file");
		}else
			System.out.println("File empty");

	}

	public void addCD(CD cd) {
		cds.add(cd);
		this.writeFile();
	}

	public ArrayList<CD> getCds() {
		return cds;
	}

	public void setCds(ArrayList<CD> cds) {
		this.cds = cds;
		this.writeFile();
	}

	@SuppressWarnings("unchecked")
	public void readFile() {
		try {
			file = new FileInputStream(uf);
			Buffer = new BufferedInputStream(file);
			input = new ObjectInputStream(Buffer);
			if (input != null) {
				cds = (ArrayList<CD>) input.readObject();
			} else
				System.out.println("input is null");
		} catch (EOFException a) {
			System.out.println("File empty");
		} catch (ClassNotFoundException | IOException e) {
			// TODO Auto-generated catch block
			System.out.println("error @ reading");
			e.printStackTrace();
		} finally {
			closeFile();
		}
	}

	private void writeFile() {
		try {
			fl = new FileOutputStream(uf);
			bf = new BufferedOutputStream(fl);
			output = new ObjectOutputStream(bf);
			output.writeObject(cds);
		} catch (IOException ex) {
			System.out.println("Ups cant performe output" + ex.toString());
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

	public void remove(CD cd) {
		cds.remove(cd);
		this.writeFile();
	}

	public void editCD(int pos, CD u) {
		System.out.println(">>>>" + pos);
		if (pos < 0 || pos >= cds.size()) {
			System.out.println("Cannot find CD");
			return;
		} else {
			cds.set(pos, u);
			this.writeFile();
			System.out.println("cd edited");
		}
	}

	public int getPosition(CD u) {
		// this.readF();
		System.out.println("--------------");
		System.out.println(u);
		System.out.println("--------------");
		for (int i = 0; i < cds.size(); i++) {
			if (cds.get(i).toString().equals(u.toString()))
				return i;
		}

		return -1;
	}

	public void tog() {
		for (CD c : cds) {
			System.out.println(c.getCdName() + " " + c.getCdGenre() + "\n");
		}
	}
}
