package com.cybage.jiraservice.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

//import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import com.cybage.jiraservice.exception.JiraException;
import com.cybage.jiraservice.model.DataPointRequest;
import com.cybage.jiraservice.model.DataPointResponse;
import com.cybage.jiraservice.model.ProjectRequest;
import com.cybage.jiraservice.model.ProjectResponse;

@Service
public class DataPointService {
	
	//private final Logger logger = Logger.getLogger(this.getClass());
	
	@Autowired
	private ApplicationContext applicationContext;
	
	@Autowired
	private DataPointServiceImplementation dataPointServiceMethodImplementation;
	
	public DataPointService(){
		super();
	}
	
	public ProjectResponse getProjectData(ProjectRequest projectRequest) throws Exception, JiraException{
		List<DataPointRequest> dataPointsRequest = projectRequest.getDataPoints();
		List<DataPointResponse> dataPointsResponse = new ArrayList<>();
		Set<String> allImplementedDataPoints = dataPointServiceMethodImplementation.mapServiceMethod.keySet();
		for(DataPointRequest dataPoint: dataPointsRequest){
			if(allImplementedDataPoints.contains(dataPoint.getDPName())){
				dataPointsResponse.add(
						dataPointServiceMethodImplementation.mapServiceMethod.get(dataPoint.getDPName()).getDataPoint(
											projectRequest.getALMProjectKey(),
											dataPoint.getDPName(), 
											dataPoint.getLastRunTimestamp(),
											dataPoint.getApplicableMeasure()));
			}
		}
		ProjectResponse projectResponse = applicationContext.getBean(ProjectResponse.class);
		projectResponse.setDataPoints(dataPointsResponse);
		projectResponse.setALMProjectKey(projectRequest.getALMProjectKey());
		return projectResponse;
	}
}
