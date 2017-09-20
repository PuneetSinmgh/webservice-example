package com.cybage.jiraservice.model;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Component
@Scope("prototype")
public class StoryPoint {

@SerializedName("expand")
@Expose
private String expand;
@SerializedName("startAt")
@Expose
private Integer startAt;
@SerializedName("maxResults")
@Expose
private Integer maxResults;
@SerializedName("total")
@Expose
private Integer total;
@SerializedName("issues")
@Expose
private List<Issue> issues = null;


public StoryPoint() {
	super();
}

public String getExpand() {
return expand;
}

public void setExpand(String expand) {
this.expand = expand;
}

public Integer getStartAt() {
return startAt;
}

public void setStartAt(Integer startAt) {
this.startAt = startAt;
}

public Integer getMaxResults() {
return maxResults;
}

public void setMaxResults(Integer maxResults) {
this.maxResults = maxResults;
}

public Integer getTotal() {
return total;
}

public void setTotal(Integer total) {
this.total = total;
}

public List<Issue> getIssues() {
return issues;
}

public void setIssues(List<Issue> issues) {
this.issues = issues;
}

}