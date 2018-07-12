package com.epsi.cloud;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "comments")
public class Comments {
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private int user_id;
	private String article;
	private String body;
	private Date created_at;
	private Date updated_at;
	public Comments() {
		super();
		// TODO Auto-generated constructor stub
	}	
	public Comments(int id, int user_id, String article, String body, Date created_at, Date updated_at) {
		super();
		this.id = id;
		this.user_id = user_id;
		this.article = article;
		this.body = body;
		this.created_at = created_at;
		this.updated_at = updated_at;
	}	
	public Comments(int user_id, String article, String body, Date created_at, Date updated_at) {
		super();
		this.user_id = user_id;
		this.article = article;
		this.body = body;
		this.created_at = created_at;
		this.updated_at = updated_at;
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
	public String getArticle() {
		return article;
	}
	public void setArticle(String article) {
		this.article = article;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	public Date getCreated_at() {
		return created_at;
	}
	public void setCreated_at(Date created_at) {
		this.created_at = created_at;
	}
	public Date getUpdated_at() {
		return updated_at;
	}
	public void setUpdated_at(Date updated_at) {
		this.updated_at = updated_at;
	}	
}
