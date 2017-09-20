package com.cybage.jiraservice.common;

public enum DataPointConstant {

	STORYPOINTS("Story+Points"),
	TOTALSTORYPOINTS("Total+Story+Points"),
	UPDATEDSTORYPOINTS("Updated+Story+Points"),
	NEWSTORYPOINTS("New+Story+Points"),
	CLOSEDSTORYPOINTS("Closed+Story+Points");
	
	private String name;
	
	private DataPointConstant(String name){
		this.name=name;
	}
	
	public String getValue() {
	      return this.name;
	}
	
}
