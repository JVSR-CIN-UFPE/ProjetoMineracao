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
	
	public DataBaseFacade() {
		daoUser = new DAOUser();
		daoArtist = new DAOArtist();
		
		DAORaiting daoRaiting = new DAORaiting();
		
		for(Rating rating : daoRaiting.findAll()) {
			int userId = rating.getUserID();
			int artistId = rating.getArtistID();
			int rate = rating.rate;
			
			daoUser.find(userId).ratings.put(daoArtist.find(artistId), rate);
		}
		
		for(User user : daoUser.findAll()) {
			System.out.println(user.ratings);
		}
	}

	public void add(User user) {
		this.daoUser.add(user);
	}

	public void add(Artist artist) {
		this.daoArtist.add(artist);
	}
	
	public void delete(User user) {
		this.daoUser.delete(user);
	}
	
	public void delete(Artist artist) {
		this.daoArtist.delete(artist);
	}
	
	public void update(User user) {
		this.daoUser.update(user);
	}
	
	public void update(Artist artist) {
		this.daoArtist.update(artist);
	}
	
	public List<User> findAllUsers() {
		return this.daoUser.findAll();
	}
	
	public List<Artist> findAllArtists() {
		return this.daoArtist.findAll();
	}
	
	public User findUser(int userID) {
		return this.daoUser.find(userID);
	}
	
	public Artist findArtist(int artistID) {
		return this.daoArtist.find(artistID);
	}

	public static void main(String[] args) {
		DataBaseFacade baseFacade = new DataBaseFacade();
	}
	
}
