package mq;

import java.util.Date;

public class MovieQuote {
	
	private int id;
	private String movie;
	private String quote;
	private Date lastTouch;

	
	public MovieQuote(int id, String movie, String quote) {
		super();
		this.id = id;
		this.movie = movie;
		this.quote = quote;
		this.lastTouch = new Date();
	}

	public MovieQuote(int id, String movie, String quote, Date lastTouch) {
		super();
		this.id = id;
		this.movie = movie;
		this.quote = quote;
		this.lastTouch = lastTouch;
	}
	
	public String getMovie() {
		return movie;
	}


	public void setMovie(String movie) {
		this.movie = movie;
	}


	public String getQuote() {
		return quote;
	}


	public void setQuote(String quote) {
		this.quote = quote;
	}


	public Date getLastTouch() {
		return lastTouch;
	}


	public void setLastTouch(Date lastTouch) {
		this.lastTouch = lastTouch;
	}


	public int getId() {
		return id;
	}

	
}
