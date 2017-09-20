package com.cybage.jiraservice.util;

import java.lang.reflect.Type;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import com.google.gson.Gson;

@Component
@Scope("singleton")
public class JsonFactory {

	//private final Logger logger = Logger.getLogger(this.getClass());
	
	@Autowired
	private Gson gson;
	
	public JsonFactory() {
		super();
	}
	
	public Object[] getFromJsonArray(String key, Type className){
		return gson.fromJson(key, className);
	}
	
	public Object getFromJsonObject(String key, Type className){
		return gson.fromJson(key, className);
	}
}