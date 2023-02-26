package org.example.model;

public class Audio {
	private String id;
	private String name;
	private String track_title;
	private String album_title;
	private String track_number;
	private int year;
	private String reviews;
	private int sold;
	
	public Audio(String id, String name, String track_title, String album_title, String track_number,int year, String reviews,int sold) {
		this.id =id;
		this.name=name;
		this.track_title=track_title;
		this.album_title=album_title;
		this.track_number=track_number;
		this.year=year;
		this.reviews=reviews;
		this.sold=sold;
		
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String getTitle() {
		return track_title;
	}
	public void setTitle(String track_title) {
		this.track_title = track_title;
	}
	
	public String getAlbum_Title() {
		return album_title;
	}
	public void setAlbum_title(String album_title) {
		this.album_title = album_title;
	}
	
	public String getTrack_Number() {
		return track_number;
	}
	public void setTrack_Number(String track_number) {
		this.track_number = track_number;
	}
	
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	
	public String getReviews() {
		return reviews;
	}
	public void setReviews(String reviews) {
		this.reviews = reviews;
	}
	
	public int getSold() {
		return sold;
	}
	public void setSold(int sold) {
		this.sold = sold;
	}
	
	@Override
	public String toString() {
		return "Audio [id=" + id + ", artistName=" + name + ", trackTitle=" + track_title + ", albumTitle="
				+ album_title + ", trackNumber=" + track_number + ", year=" + year + ", reviewsNum=" + reviews
				+ ", copiesSoldNum=" + sold + "]";
	}
		
}
