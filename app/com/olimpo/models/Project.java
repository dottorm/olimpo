package com.olimpo.models;

import java.util.ArrayList;
import java.util.List;

public class Project {
	
	private String name;
	private List<Plugin> plugins;
	private String identifier;
	
	public Project(String name){
		plugins = new ArrayList<Plugin>();
		this.name = name;
		this.identifier = null;
	}
	
	public void setName(String name){
		this.name = name;
	}
	
	public void addPlugin(Plugin plugin){
		plugins.add(plugin);
	}
	
	public void setIdentifier(String identifier){
		this.identifier = identifier;
	}
	
	public String getName(){
		return this.name;
	}
	
	public List<Plugin> allPlugins(){
		return this.plugins;
	}
	
	
	public String getIdentifier(){
		return this.identifier;
	}
	
	public String toString(){
		return this.name;
	}

}
