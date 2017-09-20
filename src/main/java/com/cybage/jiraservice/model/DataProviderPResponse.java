package com.cybage.jiraservice.model;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class DataProviderPResponse {
	
	private List<ProjectResponse> projects;

	public DataProviderPResponse(List<ProjectResponse> projects) {
		super();
		this.projects = projects;
	}

	public DataProviderPResponse() {
		super();
		this.projects = new ArrayList<>();
	}

	public List<ProjectResponse> getProjects() {
		return projects;
	}

	public void setProjects(List<ProjectResponse> projects) {
		this.projects = projects;
	}

	@Override
	public String toString() {
		return "DPResponse [projects=" + projects + "]";
	}
}
