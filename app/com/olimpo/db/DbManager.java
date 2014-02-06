package com.olimpo.db;

import java.net.UnknownHostException;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import com.olimpo.exceptions.DbLoginException;

public class DbManager {
	
	private static DB db;
	private static String host;
	private static int port;
	
	private DbManager(){}
	
	public static DB getDb(String dbname, String host, int port, String user, char[] pass) throws UnknownHostException, DbLoginException{
		if (host == null){ 
			DbManager.host = "localhost";
		}else{
			DbManager.host = host;
		}
		if(port == 0){
			DbManager.port = 27017;
		}else{
			DbManager.port = port;
		}
		MongoClient mongoClient = new MongoClient(DbManager.host,DbManager.port);
		if(db == null){
			db = mongoClient.getDB(dbname);
			if(user!=null && pass.length > 0){
				boolean connected = db.authenticate(user, pass);
				if (!connected){
					System.out.println(connected);
					throw new DbLoginException("Error during DB authentication");
				}
			}
		}
		return db;
	}
	/*
	public static void main (String [] a) throws UnknownHostException, DbLoginException{
    	char[] c = {};
		DB db = DbManager.getDb("prova",null,0,null,c);
		DBCollection coll = db.getCollection("collection_prova");
		coll.insert(new BasicDBObject("nome","prova"));
	}*/

}
