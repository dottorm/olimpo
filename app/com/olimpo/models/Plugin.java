package com.olimpo.models;

public class Plugin {

	private String name;
	
	public Plugin(String name){
		this.name = name;
	}
	
	public void setName(String name){
		this.name = name;
	}
	
	public String getName(){
		return this.name;
	}
	
	public String toString(){
		return this.name;
	}
}
