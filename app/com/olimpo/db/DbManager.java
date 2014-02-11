package com.olimpo.db;

import java.net.UnknownHostException;

import play.Play;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import com.olimpo.exceptions.DbLoginException;

public class DbManager {
	
	private static DB db;
	private static MongoClient mongoClient;
	private static String host = Play.application().configuration().getString("olimpo.db.host");
	private static int port = Integer.parseInt(Play.application().configuration().getString("olimpo.db.port"));
	private static String user = Play.application().configuration().getString("olimpo.db.user");
	private static char[] pass = Play.application().configuration().getString("olimpo.db.pass").toCharArray();
	private static String dbname = Play.application().configuration().getString("olimpo.db.dbname");
	 
	
	private DbManager(){}
	
	public static DBCollection getDb() throws UnknownHostException, DbLoginException{
		mongoClient = new MongoClient(DbManager.host,DbManager.port);
		if(db == null){
			if (!mongoClient.getDatabaseNames().contains(dbname)){
				firstLaunch();
			}else{
				db = mongoClient.getDB(dbname);
			}
				boolean connected = db.authenticate(user,pass);
				if (!connected){
					throw new DbLoginException("Error during DB authentication");
				}
		}
		return db.getCollection("olimpo");
	}
	
	private static void firstLaunch(){
		db = mongoClient.getDB("admin");
		createUser(db);
		db = mongoClient.getDB(dbname);
		createUser(db);
		db.getCollection("olimpo");
	}
	
	private static void createUser(DB db){
		db.addUser(DbManager.user, DbManager.pass);
	}

}
