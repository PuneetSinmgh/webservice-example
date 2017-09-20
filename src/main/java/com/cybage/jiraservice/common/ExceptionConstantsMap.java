package com.cybage.jiraservice.common;

import java.util.HashMap;
import java.util.Map;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
@Scope("singleton")
public class ExceptionConstantsMap {
	
	private static Map<Integer, String> mapException = new HashMap<Integer, String>();
	
	private ExceptionConstantsMap() {
		ExceptionConstantsMap.mapException.put(
				HttpStatus.UNAUTHORIZED.value(), 
				"UNAUTHORIZED (INVALID CREDENTIALS)");
		ExceptionConstantsMap.mapException.put(
				HttpStatus.FORBIDDEN.value(), 
				"FORBIDDEN");
		ExceptionConstantsMap.mapException.put(
				HttpStatus.NOT_FOUND.value(), 
				"RESOURCE NOT FOUND");
		ExceptionConstantsMap.mapException.put(
				HttpStatus.BAD_GATEWAY.value(), 
				"BAD GATWAY");
		ExceptionConstantsMap.mapException.put(
				HttpStatus.BAD_REQUEST.value(), 
				"BAD REQUEST");
	}

	public static String getMessage(int status) {
		return mapException.get(status);
	}
}
