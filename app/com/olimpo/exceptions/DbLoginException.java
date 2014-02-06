package com.olimpo.exceptions;

public class DbLoginException extends Exception{
	
	private static final long serialVersionUID = 2953637268456640175L;
	
	public DbLoginException(String messaggio){
		super(messaggio);
	}

}
