package com.nazi.model;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
public class Book {
	public static final int READ = 0;
	public static final int CURRENTLYREADING = 1;
	public static final int WANTTOREAD = 2;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "name")
	@NotEmpty(message = "Enter a name for book")
	@NotBlank(message = "you can't Enter just space")
	private String name;

	@Column(name = "author")
	@NotNull
	@NotEmpty(message = "Enter author name")
	@NotBlank(message = "you can't Enter just space")
	private String author;

	@Column(name = "readStatus")
	private int readStatus;

	@ManyToOne
	private Friend owner;

	@ManyToOne
	private User user;

	@Column
	private Date date;

	@Column
	private double lon;

	@Column
	private double lat;

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getFormatedDate() {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy/dd/MM HH:mm");
		return date == null ? "" : formatter.format(date);

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public int getReadStatus() {
		return readStatus;
	}

	public void setReadStatus(int readStatus) {
		this.readStatus = readStatus;
	}

	public Friend getOwner() {
		return owner;
	}

	public void setOwner(Friend owner) {
		this.owner = owner;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public double getLon() {
		return lon;
	}

	public void setLon(double lon) {
		this.lon = lon;
	}

	public double getLat() {
		return lat;
	}

	public void setLat(double lat) {
		this.lat = lat;
	}

	@Override
	public String toString() {
		return this.name + " " + this.author;
	}
}
