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

public class AccessGenres {

	public static final String genrefile = "sources/data/zhanre.ser";
	private ArrayList<Genres> genreList = new ArrayList<Genres>();
	InputStream file, Buffer;
	OutputStream bf, fl;
	ObjectInput input;
	ObjectOutput output;
	File uf = new File(genrefile);

	public AccessGenres() {
		if (uf.length() > 0) {
			readFile();
			//System.out.println(">>>>>Reading file");
		} else {
			System.out.println("File empty");
		}
	}

	@SuppressWarnings("unchecked")
	public void readFile() {
		try {
			file = new FileInputStream(uf);
			Buffer = new BufferedInputStream(file);
			input = new ObjectInputStream(Buffer);
			if (input != null) {
				genreList = (ArrayList<Genres>) input.readObject();
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

	private void writeFile() {
		try {
			fl = new FileOutputStream(uf);
			bf = new BufferedOutputStream(fl);
			output = new ObjectOutputStream(bf);
			output.writeObject(genreList);
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

	public ArrayList<Genres> getGenreList() {
		return genreList;
	}

	public void setGenreList(ArrayList<Genres> genreList) {
		this.genreList = genreList;
	}

	public void addGenre(Genres gen) {
		genreList.add(gen);
		this.writeFile();
	}

	public void removeGenre(Genres gen) {
		genreList.remove(gen);
		this.writeFile();
	}

	public Genres checkGenre(String g) {
		for (Genres gen : genreList) {
			if (gen.getGenreName().matches(g)) {
				return gen;
			}
		}
		return null;
	}

	public ArrayList<String> toStringArrayList() {
		ArrayList<String> list = new ArrayList<String>();
		for (Genres g : genreList) {
			list.add(g.getGenreName());
		}
		return list;
	}

}
