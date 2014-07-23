package dataset.dao;

import java.util.ArrayList;
import java.util.List;

import dataset.models.Artist;
import dataset.models.Rating;
import dataset.models.User;

public class DAORaiting implements IntfDAO<Rating> {

	List<Rating> raitings;
	
	public DAORaiting() {
		this.raitings = new ArrayList<Rating>();
		raitings.add(new Rating(1, 1, 3));
		raitings.add(new Rating(1, 3, 3));
		raitings.add(new Rating(1, 4, 2)); // FIXME alterar para 2.5
		raitings.add(new Rating(1, 5, 4));
		
		raitings.add(new Rating(2, 1, 1)); // FIXME alterar para 1.5
		raitings.add(new Rating(2, 3, 4));
		raitings.add(new Rating(2, 5, 5));
		
		raitings.add(new Rating(3, 2, 1));
		raitings.add(new Rating(3, 3, 1)); // FIXME alterar para 1.5
		raitings.add(new Rating(3, 4, 1));
		
		raitings.add(new Rating(4, 1, 4));
		raitings.add(new Rating(4, 2, 3));
		raitings.add(new Rating(4, 4, 1)); // FIXME alterar para 1.5
		raitings.add(new Rating(4, 5, 4)); // FIXME alterar para 4.5 
		
		raitings.add(new Rating(5, 1, 2));
		raitings.add(new Rating(5, 2, 2)); // FIXME alterar para 2.5
		raitings.add(new Rating(5, 4, 2));
		raitings.add(new Rating(5, 5, 4));
		
		raitings.add(new Rating(6, 1, 5));
		raitings.add(new Rating(6, 3, 4)); // FIXME alterar para 4.5
		
		raitings.add(new Rating(7, 1, 1));
		raitings.add(new Rating(7, 2, 2));
		raitings.add(new Rating(7, 3, 1));
		raitings.add(new Rating(7, 5, 3)); // FIXME alterar para 3.5
		
		raitings.add(new Rating(8, 2, 5));
		raitings.add(new Rating(8, 4, 1));
		raitings.add(new Rating(8, 5, 4));
	}
	
	public List<Rating> getUserRaiting(int userID) {
		List<Rating> userRaitings = new ArrayList<Rating>();
		
		for(Rating raiting : this.raitings) {
			if(raiting.getUserID() == userID) {
				userRaitings.add(raiting);
			}
		}
		
		return userRaitings;
	}
	
	public List<Rating> getArtistRaiting(int artistID) {
		List<Rating> userRaitings = new ArrayList<Rating>();
		
		for(Rating raiting : this.raitings) {
			if(raiting.getArtistID() == artistID) {
				userRaitings.add(raiting);
			}
		}
		
		return userRaitings;
	}
	
	public Rating getUserToArtistRaiting(int userID, int artistID) {
		Rating rait = null;
		
		for(Rating raiting : this.raitings) {
			if(raiting.getUserID() == userID && raiting.getArtistID() == artistID) {
				rait = raiting;
			}
		}
		
		return rait;
	}

	public void add(User user, Artist artist, int rait) {
		this.add(new Rating(user.getId(), artist.getId(), rait));
	}
	
	@Override
	public void add(Rating raiting) {
		this.raitings.add(raiting);
	}

	@Override
	public void delete(Rating e) {
		// delete
	}

	@Override
	public void update(Rating raiting) {
		// find raiting
		int index = this.raitings.indexOf(this.getUserToArtistRaiting(raiting.getUserID(), raiting.getArtistID()));
		this.raitings.set(index, raiting);
	}

	@Override
	public List<Rating> findAll() {
		return this.raitings;
	}

	@Override
	@Deprecated
	public Rating find(int id) {
		return null;
	}
}
