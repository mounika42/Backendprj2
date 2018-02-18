package com.niit.model;


import javax.persistence.*;


@Entity
@Table(name="User_S18250")
public class User {
@Id
private String username;
private String password;
private int phoneno;
private String role;
@Column(name="online_status")
private boolean online;

	private  String email;
	public String getEmail() {
	return email;
}

public void setEmail(String email) {
	this.email = email;
}

public String getUsername() {
	return username;
}

public void setUsername(String username) {
	this.username = username;
}

public String getPassword() {
	return password;
}

public void setPassword(String password) {
	this.password = password;
}

public int getPhoneno() {
	return phoneno;
}

public void setPhoneno(int phoneno) {
	this.phoneno = phoneno;
}

public String getRole() {
	return role;
}

public void setRole(String role) {
	this.role = role;
}

public boolean isOnline() {
	return online;
}

public void setOnline(boolean online) {
	this.online = online;
}
@Override
public String toString(){
	return this.email+""+this.username+""+this.password+""+this.phoneno+""+this.role;
}
}

