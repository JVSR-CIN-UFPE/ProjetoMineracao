package dataset.models;

import java.util.List;


public abstract class Artist {

	public Integer id;
	
	String name;
	String style;
	
	List<Album> albuns;
	
	List<Tag> tags;
	
	public Artist(String name) {
		this.name = name;
	}

	public Artist(int id, String name) {
		this.id = id;
		this.name = name;
	}
	
	public Integer getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}
}
