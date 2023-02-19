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

public class AccessUsers {

	public static final String filename = "sources/data/perdorues.ser";

	private ArrayList<User> users = new ArrayList<User>();
	InputStream file, buffer;
	OutputStream bf, fl;
	ObjectInput input;
	ObjectOutput output;
	File uf = new File(filename);

	public AccessUsers() {
		if (uf.length() > 0) {
			readFile();
			//System.out.println(">>>>Reading file");
		}
	}

	public void addUser(User user) {
		users.add(user);
		writeFile();
	}

	public User checkUser(String username, String password) {
		for (User x : users)
			if (x.getUsername().equals(username) && x.getPassword().equals(password))
				return x;
		return null;
	}

	public String readS() {
		readFile();
		String read = "";
		for (User x : users) {
			read += "---User" + x.getUserId() + "\n" + x.toString();

		}
		return read;
	}

	public ArrayList<User> getUsers() {
		return users;
	}

	public void remove(User u) {
		users.remove(u);
		this.writeFile();
	}

	public int getPosition(User u) {
		for (int i = 0; i < users.size(); i++) {
			if (users.get(i).toString().equals(u.toString()))
				return i;
		}
		return -1;
	}

	public void setSalary(User u, double salary) {
		int pos = getPosition(u);
		users.get(pos).setSalary(salary);
		this.writeFile();
	}

	public void editUser(int pos, User u) {
		users.set(pos, u);
		this.writeFile();
	}

	@SuppressWarnings("unchecked")
	private void readFile() {
		try {
			file = new FileInputStream(uf);
			buffer = new BufferedInputStream(file);
			input = new ObjectInputStream(buffer);
			users = (ArrayList<User>) input.readObject();
			/*
			 * for(User u: users) { System.out.println("Data: " + u.toString()); }
			 */
		} catch (EOFException e) {
			System.out.println("End of file");
		} catch (Exception e) {
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
			output.writeObject(users);
		} catch (IOException ex) {
			System.out.println("Cannot perform output." + ex.toString());
		}
		closeFile();

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

	public int getUserCount() {
		readFile();
		System.out.println("User count : " + users.size());
		return users.size();

	}
}
