package dataset.models;

import java.util.List;

import dataset.Music;

public class Album {

	Long id;
	
	String name;
	int year;
	
	List<Music> album_musics;
	
	public Album(String name) {
		this.name = name;
	}
}
