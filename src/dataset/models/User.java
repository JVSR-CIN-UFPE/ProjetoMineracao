package dataset.models;

import java.util.List;

public class User {
	
	public Integer id;
	
	String name;
	
	List<User> friends;
	
	public User(String name) {
		this.name = name;
	}
	
	public User(int id, String name) {
		this.id = id;
		this.name = name;
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
