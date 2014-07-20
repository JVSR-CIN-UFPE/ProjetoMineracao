package dataset.dao;

import java.util.ArrayList;
import java.util.List;

import dataset.models.Artist;
import dataset.models.Band;
import dataset.models.Singer;

public class DAOArtist implements IntfDAO<Artist> {

	List<Artist> artists;
	
	public DAOArtist() {
		this.artists = new ArrayList<Artist>();
		
		artists.add(new Band(1, "Band 1"));
		artists.add(new Band(2, "Band 2"));
		artists.add(new Band(3, "Band 3"));
		artists.add(new Singer(4, "Band 4"));
		artists.add(new Singer(5, "Band 5"));
		artists.add(new Singer(6, "Band 6"));
		
	}

	@Override
	public void add(Artist artist) {
		this.artists.add(artist.getId(), artist);
	}

	@Override
	public void delete(Artist artist) {
		this.artists.remove(artist.getId());
	}

	@Override
	public void update(Artist artist) {
		this.artists.set(artist.getId(), artist);
	}

	@Override
	public List<Artist> findAll() {
		return this.artists;
	}

	@Override
	public Artist find(int idArtist) {
		return this.artists.get(idArtist);
	}
}
