package com.olimpo.models;

import java.util.List;

public class Project {
	
	private String name;
	private List<Plugin> plugins;
	
	public Project(String name){
		this.name = name;
	}
	
	public void setName(String name){
		this.name = name;
	}
	
	public void addPlugin(Plugin plugin){
		plugins.add(plugin);
	}
	
	public String getName(){
		return this.name;
	}
	
	public List<Plugin> allPlugins(){
		return this.plugins;
	}
	
	public String toString(){
		return this.name;
	}

}
