package com.cybage.jiraservice.model;

import java.util.ArrayList;
import java.util.List;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class DataProviderRequest{
	
	private List<ProjectRequest> projects;

	public DataProviderRequest(List<ProjectRequest> projects) {
		super();
		this.projects = projects;
	}
	
	public DataProviderRequest() {
		super();
		this.projects = new ArrayList<>();
	}

	public List<ProjectRequest> getProjects() {
		return projects;
	}

	public void setProjects(List<ProjectRequest> projects) {
		this.projects = projects;
	}

	@Override
	public String toString() {
		return "DPRequest [projects=" + projects + "]";
	}
}
