package com.olimpo.dao;

import java.net.UnknownHostException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.WriteResult;
import com.olimpo.db.DbManager;
import com.olimpo.exceptions.DbLoginException;
import com.olimpo.models.Admin;
import com.olimpo.models.BasicObject;
import com.olimpo.models.Project;
import com.olimpo.util.Utils;

public class AdminDAO {
	
	DBCollection coll;
	BasicObject administrator;
	
	public AdminDAO() throws UnknownHostException, DbLoginException{
		coll = DbManager.getDb();
		administrator = null;
	}
		
	public void createAdmin(Admin admin) throws UnknownHostException, DbLoginException, NoSuchAlgorithmException{
		Utils util = new Utils();
		admin.setPassword(util.convertToMD5(admin.getPassword()));
		if (!admin.getUsername().equals(findAdmin(admin).getUsername()) || findAdmin(admin) == null){
			administrator = new BasicObject();
			administrator.put("username", admin.getUsername());
			administrator.put("password", admin.getPassword());
			administrator.put("email", admin.getEmail());
			administrator.put("created", admin.getCreated());
			coll.insert(administrator);
		}else{
			System.out.println("User alredy exists");
		}
	}
	
	public Admin findAdmin(Admin admin){
		administrator = new BasicObject();
		administrator.put("username", admin.getUsername());
		
		Admin ad = new Admin();
		
		if(coll.count(administrator)>0){
		DBObject obj = coll.findOne(administrator);
		ad = new Admin(obj.get("username").toString(),obj.get("password").toString(),obj.get("email").toString());
		ad.setDate((Date) obj.get("created"));
		}
		return ad;
	}
	
	public boolean deleteAdmin(Admin admin){
		administrator = new BasicObject();
		administrator.put("username", admin.getUsername());
		
		boolean result = false;
		
		if(coll.count(administrator)>0){
			WriteResult obj = coll.remove(administrator);
			if(obj.getField("err") == null){
				result =  true;
			}
		}
		
		return result;
	}
	
	public boolean modifyAdmin(Admin old_admin, Admin new_admin){
		administrator = new BasicObject();
		administrator.put("username", old_admin.getUsername());
		DBObject obj = coll.findOne(administrator);
		
		BasicDBObject administrator_new = new BasicDBObject();
		administrator_new.put("username", new_admin.getUsername());
		administrator_new.put("password", new_admin.getPassword());
		administrator_new.put("email", new_admin.getEmail());
		administrator_new.put("created", old_admin.getCreated());
		
		int len_projects = new_admin.allProjects().size();
		
		List<BasicDBObject> p = new ArrayList<>();
		if(len_projects > 0){
			p = new ArrayList<>();
			for(int i = 0 ; i<len_projects;i++){
				BasicDBObject proj = new BasicDBObject();
				proj.put("name", new_admin.allProjects().get(i).getName());
				proj.put("identifier", new_admin.allProjects().get(i).getIdentifier());
				p.add(proj);
			}
		}
		administrator_new.put("projects", p);
				
		boolean result = false;
		
		WriteResult res = coll.update(obj, administrator_new);
		
		if(res.getField("err") == null) result =  true;
		
		return result;
	}
	
	//TODO: example method. 
	public List<Project> readProjects(Admin admin){
		administrator = new BasicObject();
		administrator.put("username", admin.getUsername());
		DBCursor cursor = coll.find(administrator);
		BasicDBObject p = null;
		List<Project> prjs = new ArrayList<Project>();
		while(cursor.hasNext()){
			p = (BasicDBObject)cursor.next();
			BasicDBList projs = (BasicDBList)p.get("projects");
			for(Iterator<Object> it = projs.iterator();it.hasNext();){
				BasicDBObject dbo = (BasicDBObject) it.next();
				Project prj = new Project(dbo.getString("name"));
				prj.setIdentifier(dbo.getString("identifier"));
				prjs.add(prj);
			}
		}
	
		return prjs;
	}
	
	public boolean addProject(Admin admin, Project project){
		boolean result = false;
		administrator = new BasicObject();
		administrator.put("username", admin.getUsername());
		DBObject adm = coll.findOne(administrator);
		
		List<Project> prjs = readProjects(admin);
		
		prjs.add(project);
		int len_projects = prjs.size();
		
		List<BasicDBObject> p = new ArrayList<>();
		if(len_projects > 0){
			p = new ArrayList<>();
			for(int i = 0 ; i<len_projects;i++){
				BasicDBObject proj = new BasicDBObject();
				proj.put("name", prjs.get(i).getName());
				proj.put("identifier", prjs.get(i).getIdentifier());
				p.add(proj);
			}
		}
		adm.put("projects", p);
		
		WriteResult res = coll.update(administrator, adm);
		
		if(res.getField("err") == null) result =  true;
		
		return result;
	}
	
	public boolean removeProject(Admin admin, Project project){
		boolean result = false;
		administrator = new BasicObject();
		administrator.put("username", admin.getUsername());
		DBObject adm = coll.findOne(administrator);
		
		List<Project> prjs = readProjects(admin);
		prjs.remove(project);
		int len_projects = prjs.size();
		
		List<BasicDBObject> p = new ArrayList<>();
		if(len_projects > 0){
			p = new ArrayList<>();
			for(int i = 0 ; i<len_projects;i++){
				BasicDBObject proj = new BasicDBObject();
				proj.put("name", prjs.get(i).getName());
				proj.put("identifier", prjs.get(i).getIdentifier());
				p.add(proj);
			}
		}
		adm.put("projects", p);
		
		WriteResult res = coll.update(administrator, adm);
		
		if(res.getField("err") == null) result =  true;
		
		return result;
	}

}
