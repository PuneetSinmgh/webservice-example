package com.cybage.jiraservice.service;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import javafx.beans.binding.When;
import javafx.scene.chart.PieChart.Data;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Answers;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.internal.configuration.injection.MockInjection;
import  org.powermock.api.*;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.api.mockito.internal.PowerMockitoCore;
import org.powermock.api.mockito.internal.mockmaker.PowerMockMaker;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.modules.junit4.PowerMockRunnerDelegate;
import org.powermock.reflect.internal.WhiteboxImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

import com.cybage.jiraservice.JiraserviceApiApplication;
import com.cybage.jiraservice.exception.JiraException;
import com.cybage.jiraservice.model.Actor;
import com.cybage.jiraservice.model.ActualValue;
import com.cybage.jiraservice.model.Count;
import com.cybage.jiraservice.model.DataPointRequest;
import com.cybage.jiraservice.model.DataPointResponse;
import com.cybage.jiraservice.model.DataProviderRequest;
import com.cybage.jiraservice.model.Efforts;
import com.cybage.jiraservice.model.ExecutionUnit;
import com.cybage.jiraservice.model.Exists;
import com.cybage.jiraservice.model.Frequency;
import com.cybage.jiraservice.model.ProjectRequest;
import com.cybage.jiraservice.model.ProjectResponse;
import com.cybage.jiraservice.model.Size;
import com.cybage.jiraservice.model.User;
import com.cybage.jiraservice.model.Value;



@RunWith(PowerMockRunner.class)
@SpringBootConfiguration
@PowerMockRunnerDelegate(SpringRunner.class)
@PrepareForTest(DataPointService.class)
public class DataPointServicePowerMockTest {
	
	
	@MockBean
	ApplicationContext applicationContext;
		
	@MockBean(answer=Answers.RETURNS_DEEP_STUBS)
	private DataPointServiceImplementation dataPointServiceMethodImplementation;
	
	@InjectMocks
	private DataPointService datapointservice;
	
	
	public DataPointServicePowerMockTest() {
			super();
	}

	@Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        //datapointservice = new DataPointService();   
    }

	
	@Test
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
		
		dataPointServiceMethodImplementation.mapServiceMethod = new HashMap<String,DataPointServiceInterface>();
		
		DataPointServiceInterface datapointserviceint = PowerMockito.mock(DataPointServiceInterface.class);
		dataPointServiceMethodImplementation.mapServiceMethod = PowerMockito.mock(HashMap.class);
		Mockito.when(dataPointServiceMethodImplementation.mapServiceMethod.keySet()).thenReturn(stubkeyset);
		
		
		
		PowerMockito.when(dataPointServiceMethodImplementation.mapServiceMethod.get("Total Active Story Points")).thenReturn(datapointserviceint);
		PowerMockito.when(dataPointServiceMethodImplementation.mapServiceMethod.get("Updated Story Points")).thenReturn(datapointserviceint);
		
		//PowerMockito.whenNew(HashMap.class).withNoArguments().thenReturn(mockedmap);
		
		Mockito.when(datapointserviceint.getDataPoint("AL", "Total Active Story Points", "2017-04-05+12:00:00", 17)).thenReturn(alldataPointsresponse.get(0));
		Mockito.when(datapointserviceint.getDataPoint("AL", "Updated Story Points", "2017-04-14 17:48:00", 17)).thenReturn(alldataPointsresponse.get(1));
		Mockito.when(applicationContext.getBean(ProjectResponse.class)).thenReturn(new ProjectResponse());
		// call the function to be tested and take the result into
		
		
		ProjectResponse projectresponse = datapointservice.getProjectData(stubproject);
		
		// assert and verify the returned value with the desired value.
		assertEquals(expectedprojectresponse.getALMProjectKey(), projectresponse.getALMProjectKey());
		assertEquals(expectedprojectresponse.getDataPoints(), projectresponse.getDataPoints());
		//assertEquals(expectedprojectresponse, projectresponse);	
		//Mockito.verify(dataPointServiceMethodImplementation, Mockito.atLeast(2)).mapServiceMethod.keySet();
		//Mockito.verify(dataPointServiceMethodImplementation.mapServiceMethod, Mockito.atLeast(1)).keySet();
		//Mockito.verify(datapointserviceint, Mockito.atLeast(1));
	
	}

}
