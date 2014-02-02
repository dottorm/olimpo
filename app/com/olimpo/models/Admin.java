package com.olimpo.models;

import java.util.List;

public class Admin {
	
	private String username;
	private String password;
	private String email;
	private List<Project> projects;
	
	public Admin(String username, String password, String email){
		this.username = username;
		this.password = password;
		this.email = email;
	}
	
	public Admin(String username, String password){
		this.username = username;
		this.password = password;
	}
	
	public void setUsername(String username){
		this.username = username;
	}
	
	public void setPassword(String password){
		this.password = password;
	}
	
	public void setEmail(String email){
		this.email = email;
	}
	
	public void addProject(Project name){
		this.projects.add(name);
	}
	
	public String getUsername(){
		return this.username;
	}
	
	public String getPassword(){
		return this.password;
	}
	
	public String getEmail(){
		return this.email;
	}
	
	public List<Project> allProjects(){
		return this.projects;
	}
	
	public String toString(){
		return "Username: "+this.username;
	}

}
