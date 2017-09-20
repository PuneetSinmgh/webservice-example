package com.cybage.jiraservice.common;

public enum JiraURLConstants {
	
	CUSTOMFIELD("/rest/api/2/field"),
	CUSTOMFIELDSEARCH("/rest/api/2/search?jql=project="),
	ISSUETYPE(" AND issuetype=Story"),
	UPDATED(" AND updated >= -"),
	FIELDS("&fields="),
	ISSUE("/rest/api/2/search?jql="),
	MAXRESULTS("&maxResults=1000"),
	MINRESULTS("&maxResults=100");
	
	private String url;
	
	private JiraURLConstants(String url) {
		this.url = url;
	}
	
	public String getValue() {
	      return this.url;
	}
}
