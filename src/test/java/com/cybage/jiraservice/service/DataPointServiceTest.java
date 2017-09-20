package com.cybage.jiraservice.service;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Answers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.internal.verification.AtLeast;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.cybage.jiraservice.controller.DataPointController;
import com.cybage.jiraservice.exception.JiraException;
import com.cybage.jiraservice.model.Actor;
import com.cybage.jiraservice.model.ActualValue;
import com.cybage.jiraservice.model.Count;
import com.cybage.jiraservice.model.DataPointRequest;
import com.cybage.jiraservice.model.DataPointResponse;
import com.cybage.jiraservice.model.Efforts;
import com.cybage.jiraservice.model.ExecutionUnit;
import com.cybage.jiraservice.model.Exists;
import com.cybage.jiraservice.model.Frequency;
import com.cybage.jiraservice.model.ProjectRequest;
import com.cybage.jiraservice.model.ProjectResponse;
import com.cybage.jiraservice.model.Size;
import com.cybage.jiraservice.model.User;
import com.cybage.jiraservice.model.Value;


//@RunWith(SpringRunner.class)
public class DataPointServiceTest {
	
	//@MockBean
	private DataPointRequest datapointrequestmock;
	
	//@MockBean
	private DataPointResponse datapointresponsemock;
	
	
	//@MockBean
	ApplicationContext applicationContext;
	
	
	//@MockBean(answer=Answers.RETURNS_DEEP_STUBS)
	private DataPointServiceImplementation dataPointServiceMethodImplementation;
	
	//@InjectMocks
	private DataPointService datapointservice;
	
	
	public DataPointServiceTest() {
			super();
	}

	//@Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        //datapointservice = new DataPointService();   
    }

	
	//@Test
	public void testGetProjectData() throws Exception,JiraException{
		
		// stub values for data point into mock variables (stubing)
		
		Value stubValue1 = new Value(new ActualValue(null, null),new Size(null, null),
				new ExecutionUnit(11, null), new Frequency(null, null),
				new Efforts(null, null), new Count(5, null), 
				new Actor(null, null), new User(null, null), 
				new Exists(null, null));
		
		Value stubValue2 = new Value(new ActualValue(null, null),new Size(null, null),
				new ExecutionUnit(6, null), new Frequency(null, null),
				new Efforts(null, null), new Count(4, null), 
				new Actor(null, null), new User(null, null), 
				new Exists(null, null));
		
		DataPointRequest stubdatapoint = new DataPointRequest("Total Active Story Points", "2017-04-05+12:00:00", 17 , stubValue1);
		
		
		// all implemented datapoints for testing
		ArrayList<DataPointRequest> alldataPoints = new ArrayList<>();
		alldataPoints.add(stubdatapoint);
		alldataPoints.add(new DataPointRequest("Updated Story Points", "2017-04-14 17:48:00", 17 , stubValue2));
		
		
		
		ProjectRequest stubproject = new ProjectRequest(0, null, "AL", null,Arrays.asList(stubdatapoint));
			

		Set<String> stubkeyset = new HashSet<String>();
		stubkeyset.add(alldataPoints.get(0).getDPName());
		stubkeyset.add(alldataPoints.get(1).getDPName());
		
		//stubkeyset.add(alldataPoints.listIterator().next().getDPName());
		// projectrequest will contain list of project and will have the datapoints.
		
		ArrayList<DataPointResponse> alldataPointsresponse = new ArrayList<>();
		alldataPointsresponse.add(new DataPointResponse("Total Active Story Points", 17 , stubValue1));
		alldataPointsresponse.add(new DataPointResponse("Updated Story Points",17 , stubValue2));
		
		ProjectResponse expectedprojectresponse = new ProjectResponse("AL",Arrays.asList(alldataPointsresponse.get(0)));
		
		// provide the stubed values when the specific functions are called 
		
		DataPointServiceInterface datapointserviceint = Mockito.mock(DataPointServiceInterface.class);

		dataPointServiceMethodImplementation.mapServiceMethod = new HashMap<String,DataPointServiceInterface>();
		
		Mockito.when(dataPointServiceMethodImplementation.mapServiceMethod.keySet()).thenReturn(stubkeyset);
		Mockito.when(dataPointServiceMethodImplementation.mapServiceMethod.get("Total Active Story Points").getDataPoint("AL", "Total Active Story Points", "2017-04-05+12:00:00", 17)).thenReturn(alldataPointsresponse.get(0));//datapointresponse;
		Mockito.when(dataPointServiceMethodImplementation.mapServiceMethod.get("Updated Story Points").getDataPoint("AL", "Updated Story Points", "2017-04-14 17:48:00", 17)).thenReturn(alldataPointsresponse.get(1));
		
		// call the function to be tested and take the result into 
		ProjectResponse projectresponse = datapointservice.getProjectData(stubproject);
		
		// assert and verify the returned value with the desired value.
		assertEquals(expectedprojectresponse.getALMProjectKey(), projectresponse.getALMProjectKey());
		assertEquals(expectedprojectresponse.getDataPoints(), projectresponse.getDataPoints());
		assertEquals(expectedprojectresponse, projectresponse);	
		Mockito.verify(dataPointServiceMethodImplementation, Mockito.atLeast(2));
	
	}

}
