package dataset.models;

public class Rating {

//	User user;
//	Artist artist;
	
	Integer userID;
	public Integer artistID;
	
	public int rate;
	
	public Rating(int userid, int artistid, int rait) {
		this.userID = userid;
		this.artistID = artistid;
		this.rate = rait;
	}
	
//	public Raiting(User user, Artist artist, int nota) {
//		this.user = user;
//		this.artist = artist;
//		this.rait = nota;
//	}

	public Integer getUserID() {
		return userID;
	}
	
	public Integer getArtistID() {
		return artistID;
	}
	
//	public User getUser() {
//		return this.user;
//	}

//	public Artist getArtist() {
//		return this.artist;
//	}
}
