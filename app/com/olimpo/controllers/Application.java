package com.olimpo.controllers;

import java.util.HashMap;
import java.util.Map;

import play.*;
import play.mvc.*;
import views.html.*;

public class Application extends Controller {

    public static Result index() {
        return ok(index.render("Funziona!!!"));
    }
    
    public static Result provaJson(){
    	Map<String, String> messaggio = new HashMap<String, String>();
    	messaggio.put("message", "Funziona!");
    	return ok(play.libs.Json.toJson(messaggio));
    }

}
