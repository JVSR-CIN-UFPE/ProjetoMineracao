package dataset;

import java.util.List;

import dataset.dao.DAOArtist;
import dataset.dao.DAORaiting;
import dataset.dao.DAOUser;
import dataset.models.Artist;
import dataset.models.Rating;
import dataset.models.User;

public class DataBaseFacade {

	DAOUser daoUser;
	DAOArtist daoArtist;
	DAORaiting daoRaiting;
	
	public DataBaseFacade() {
		daoUser = new DAOUser();
		daoArtist = new DAOArtist();
		daoRaiting = new DAORaiting();
	}

	public void add(User user) {
		this.daoUser.add(user);
	}

	public void add(Artist artist) {
		this.daoArtist.add(artist);
	}
	
	public void add(Rating raiting) {
		this.daoRaiting.add(raiting);
	}
	
	public void delete(User user) {
		this.daoUser.delete(user);
	}
	
	public void delete(Artist artist) {
		this.daoArtist.delete(artist);
	}
	
	public void delete(Rating raiting) {
		this.daoRaiting.delete(raiting);
	}
	
	public void update(User user) {
		this.daoUser.update(user);
	}
	
	public void update(Artist artist) {
		this.daoArtist.update(artist);
	}
	
	public void update(Rating raiting) {
		this.daoRaiting.update(raiting);
	}

	public List<User> findAllUsers() {
		return this.daoUser.findAll();
	}
	
	public List<Artist> findAllArtists() {
		return this.daoArtist.findAll();
	}
	
	public List<Rating> findAllRaitings() {
		return this.daoRaiting.findAll();
	}
	
	public User findUser(int userID) {
		return this.daoUser.find(userID);
	}
	
	public Artist findArtist(int artistID) {
		return this.daoArtist.find(artistID);
	}

	public List<Rating> userRatings(int userid) {
		return this.daoRaiting.getUserRaiting(userid);
	}
	
}
