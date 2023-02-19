package Model;

import java.io.Serializable;

public class Genres implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String genreName;

	public Genres(String nameOfGenre) {
		this.setGenreName(nameOfGenre);
		
	}
	public String getGenreName() {
		return genreName;
	}

	public void setGenreName(String genreName) {
		this.genreName = genreName;
	}
	public String toString() {
		return this.genreName;
	}

}
