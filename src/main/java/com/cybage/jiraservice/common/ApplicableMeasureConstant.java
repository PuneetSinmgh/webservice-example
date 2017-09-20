package com.cybage.jiraservice.common;

public enum ApplicableMeasureConstant {

	COUNT("count"),
	SIZE("size"),
	FREQUENCY("frequency"),
	EFFORTS("efforts"),
	EXECUTIONUNIT("executionUnit"),
	EXISTS("efforts"),
	ACTOR("actor"),
	USER("user");
	
	private String measureName;
	
	private ApplicableMeasureConstant(String measureName){
		this.measureName = measureName;
	}
	
	public String getValue() {
	      return this.measureName;
	   }
}