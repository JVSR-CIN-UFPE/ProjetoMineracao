package dataset.dao;

import java.util.ArrayList;
import java.util.List;

import dataset.models.User;

public class DAOUser implements IntfDAO<User> {

	List<User> users;
	
	public DAOUser() {
		users = new ArrayList<User>();
		
		users.add(new User(1, "user 1"));
		users.add(new User(2, "user 2"));
		users.add(new User(3, "user 3"));
		users.add(new User(4, "user 4"));
		users.add(new User(5, "user 5"));
		users.add(new User(6, "user 6"));
		users.add(new User(7, "user 7"));
		users.add(new User(8, "user 8"));
		
	}

	@Override
	public void add(User user) {
		this.users.add(user.getId(), user);
	}

	@Override
	public void delete(User user) {
		this.users.remove(user.getId());
	}

	@Override
	public void update(User user) {
		this.users.set(user.getId(), user);
	}

	@Override
	public List<User> findAll() {
		return this.users;
	}

	@Override
	public User find(int idUser) {
		return this.users.get(idUser);
	}

}
