package dataset.models;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class User {
	
	public Integer id;
	
	String name;
	
	List<User> friends;
	
	public Map<Artist, Integer> ratings;
	
	public User(String name) {
		this.name = name;
		this.ratings = new HashMap<Artist, Integer>();
	}
	
	public User(int id, String name) {
		this.id = id;
		this.name = name;
		this.ratings = new HashMap<Artist, Integer>();
	}
	
	public Integer getId() {
		return id;
	}

	@Override
	public boolean equals(Object obj) {
		
		if(obj instanceof User) {
			return ((User) obj).id==this.id;
		}
		
		return false;
	}
}
