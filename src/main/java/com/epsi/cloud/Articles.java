package com.epsi.cloud;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "articles")
public class Articles {
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private int user_id;
	private String title;
	private String description;
	private String slug;
	private String body;
	public Articles() {
		super();
	}		
	public Articles(int user_id, String title, String description, String slug, String body) {
		super();
		this.user_id = user_id;
		this.title = title;
		this.description = description;
		this.slug = slug;
		this.body = body;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getSlug() {
		return slug;
	}
	public void setSlug(String slug) {
		this.slug = slug;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}	
}
