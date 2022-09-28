package com.dh.movieservice.domain.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;


@Entity
public class Movie  {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private String genre;
	private String urlStream;

	public Movie(){
		//No-args constructor
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public String getUrlStream() {
		return urlStream;
	}

	public void setUrlStream(String urlStream) {
		this.urlStream = urlStream;
	}

	@Override
	public String toString() {
		return "MovieDTO{" +
				"id=" + id +
				", name='" + name + '\'' +
				", genre='" + genre + '\'' +
				", urlStream='" + urlStream + '\'' +
				'}';
	}
}
