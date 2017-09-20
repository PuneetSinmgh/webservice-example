/*
 * Copyright 2016-2017 by Cybage software pvt. ltd. ,
 * pune india
 * All rights reserved.
 * 
 * This software is the confidential and proprietary information
 * of Cybage software pvt. ltd  ("Confidential Information").  You
 * shall not disclose such Confidential Information and shall use
 * it only in accordance with the terms of the license agreement
 * you entered into with Cybage.
 */
package com.cybage.jiraservice.controller;

import org.apache.log4j.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.cybage.jiraservice.exception.JiraException;
import com.cybage.jiraservice.model.DataProviderRequest;
import com.cybage.jiraservice.model.DataProviderPResponse;
import com.cybage.jiraservice.model.ProjectRequest;
import com.cybage.jiraservice.service.DataPointService;
import com.cybage.jiraservice.util.JsonFactory;
import java.util.Arrays;
import java.util.List;

/*
 * This class will return the  data provider response entity with HTTP  status.
 * The HTTP request body is expected to contain  project key,applicable measures,
 * list of data point list in JSON format.
 * 
 * 
 */

@RestController
@RequestMapping("/jira")
public class DataPointController {
	
	private final Logger logger = Logger.getLogger(this.getClass());
	
	@Autowired
	private DataPointService dataPointService;
	
	@Autowired
	private ApplicationContext applicationContext;
	
	@Autowired
	private JsonFactory jsonFactory;
	
	public DataPointController() {
		super();
	}
	
	@SuppressWarnings("unchecked")            
	@RequestMapping(value = "/datapoints/{key}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> getDatapoints(@PathVariable(value = "key") String key) throws Exception, JiraException {
		DataProviderRequest dpRequest = applicationContext.getBean(DataProviderRequest.class);
		DataProviderPResponse dpResponse = applicationContext.getBean(DataProviderPResponse.class);
		dpRequest.setProjects(((List<ProjectRequest>)(List<?>) Arrays.asList(jsonFactory.getFromJsonArray(key, ProjectRequest[].class))));
		logger.info("dpRequest.getProjects() : " + dpRequest.getProjects());
		for(ProjectRequest project: dpRequest.getProjects()){
			dpResponse.getProjects().add(dataPointService.getProjectData(project));
		}
		return new ResponseEntity<>(dpResponse.getProjects(), HttpStatus.OK);
	}
}