package com.epsi.cloud;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "users")
public class Users {
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String username;
	private String email;
	private String password;
	private String bio;
	private String image;
	private Date created_at;
	private Date updated_at;
	private boolean enabled;
	public Users() {
		super();
		// TODO Auto-generated constructor stub
	}	
	public Users(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}
	public Users(String username, String email, String password, String bio, String image, Date created_at,
			Date updated_at) {
		super();
		this.username = username;
		this.email = email;
		this.password = password;
		this.bio = bio;
		this.image = image;
		this.created_at = created_at;
		this.updated_at = updated_at;
	}
	

	public Users(String username, String email, String password, String bio, String image, Date created_at,
			Date updated_at, boolean enabled) {
		super();
		this.username = username;
		this.email = email;
		this.password = password;
		this.bio = bio;
		this.image = image;
		this.created_at = created_at;
		this.updated_at = updated_at;
		this.enabled = enabled;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getBio() {
		return bio;
	}
	public void setBio(String bio) {
		this.bio = bio;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
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
	public boolean isEnabled() {
		return enabled;
	}
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}	
	
}
