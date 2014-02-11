package com.olimpo.models;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Admin{
	
	private String username;
	private String password;
	private String email;
	private List<Project> projects;
	private Date created;
	private boolean isStaff;
	
	
	public Admin(){}
	
	public Admin(String username, String password, String email){
		projects = new ArrayList<Project>();
		this.username = username;
		this.password = password;
		this.email = email;
		this.created = new Date();
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
	
	public void setStaff(boolean isStaff){
		this.isStaff = isStaff;
	}
	
	public void setDate(Date created){
		this.created = created;
	}
	
	public void addProject(Project project){
		this.projects.add(project);
	}
	
	public boolean removeProject(Project project){
		boolean result = false;
		if(this.projects.size()>0 && this.projects.contains(project)){
			result = this.projects.remove(project);
		}
		return result;
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
	
	public boolean getStaff(){
		return this.isStaff;
	}
	
	public Date getCreated(){
		return this.created;
	}
	
	public String toString(){
		return "Username: "+this.username;
	}

}
