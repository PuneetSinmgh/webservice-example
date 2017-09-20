package com.cybage.jiraservice.service;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.modules.junit4.PowerMockRunnerDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.xml.sax.SAXException;

import com.cybage.jiraservice.common.DataPointConstant;
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
import com.cybage.jiraservice.util.DataPointFactory;
import com.cybage.jiraservice.util.JiraRequestInvoker;
import com.cybage.jiraservice.util.JsonFactory;
import com.sun.xml.internal.bind.v2.runtime.unmarshaller.Intercepter;
import com.sun.xml.internal.bind.v2.runtime.unmarshaller.UnmarshallingContext.State;

@RunWith(PowerMockRunner.class)
@SpringBootConfiguration
@PowerMockRunnerDelegate(SpringRunner.class)
@PrepareForTest(DataPointServiceImplementation.class)
public class DataPointServiceImplemnetationPowerMockTest implements Intercepter {

	
	@MockBean
	private final Logger logger = Logger.getLogger(this.getClass());

	@MockBean
	private DataPointFactory dataPointFactory;

	@MockBean
	private ApplicationContext applicationContext;

	@MockBean
	private JiraRequestInvoker jiraRequestInvoker;
	
	@MockBean
	private JsonFactory jsonFactory;
	
	@MockBean
	public Map<String, DataPointServiceInterface> mapServiceMethod = new HashMap<>();

	@InjectMocks
	private DataPointServiceImplementation datapointserviceimplementation;
	
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
		
		Value stubValue3 = new Value(new ActualValue(null, null),new Size(null, null),
				new ExecutionUnit(14, null), new Frequency(null, null),
				new Efforts(null, null), new Count(6, null), 
				new Actor(null, null), new User(null, null), 
				new Exists(null, null));
		
		Value stubValue4 = new Value(new ActualValue(null, null),new Size(null, null),
				new ExecutionUnit(3, null), new Frequency(null, null),
				new Efforts(null, null), new Count(3, null), 
				new Actor(null, null), new User(null, null), 
				new Exists(null, null));
		
		Value stubValue5 = new Value(new ActualValue(null, null),new Size(null, null),
				new ExecutionUnit(3, null), new Frequency(null, null),
				new Efforts(null, null), new Count(1, null), 
				new Actor(null, null), new User(null, null), 
				new Exists(null, null));
		
		DataPointRequest stubdatapoint = new DataPointRequest("Total Active Story Points", "2017-04-05+12:00:00", 17 , stubValue1);
		
		
		// all implemented datapoints for testing
		ArrayList<DataPointRequest> alldataPoints = new ArrayList<>();
		alldataPoints.add(stubdatapoint);
		alldataPoints.add(new DataPointRequest("Updated Story Points", "2017-04-14 17:48:00", 17 , stubValue2));
		alldataPoints.add(new DataPointRequest("Story Points", "2017-04-05 12:00:00", 17 , stubValue3));
		alldataPoints.add(new DataPointRequest("New Story Points", "2017-04-05 12:00:00", 17 , stubValue4));
		alldataPoints.add(new DataPointRequest("Closed Story Points", "2017-04-05 12:00:00", 17 , stubValue5));
		
		//ProjectRequest stubproject = new ProjectRequest(0, null, "AL", null,Arrays.asList(stubdatapoint));
			

		//Set<String> stubkeyset = new HashSet<String>();
		//stubkeyset.add(alldataPoints.get(0).getDPName());
		//stubkeyset.add(alldataPoints.get(1).getDPName());
		
		//stubkeyset.add(alldataPoints.listIterator().next().getDPName());
		// projectrequest will contain list of project and will have the datapoints.
		
		
		
		ArrayList<DataPointResponse> alldataPointsresponse = new ArrayList<>();
		alldataPointsresponse.add(new DataPointResponse("Total Active Story Points", 17 , stubValue1));
		alldataPointsresponse.add(new DataPointResponse("Updated Story Points",17 , stubValue2));
		
		ProjectResponse expectedprojectresponse = new ProjectResponse("AL",Arrays.asList(alldataPointsresponse.get(0)));
		
		// provide the stubed values when the specific functions are called 
		
		
		
		
		PowerMockito.when(dataPointServiceMethodImplementation.mapServiceMethod.get("Total Active Story Points")).thenReturn(datapointserviceint);
		PowerMockito.when(dataPointServiceMethodImplementation.mapServiceMethod.get("Updated Story Points")).thenReturn(datapointserviceint);
		//PowerMockito.whenNew(HashMap.class).withNoArguments().thenReturn(mockedmap);
		
		Mockito.when(applicationContext.getBean(DataPointResponse.class)).thenReturn(new DataPointResponse());
		Mockito.when(applicationContext.getBean(Value.class)).thenReturn(new Value());
		
		Mockito.when(dataPointFactory.getCustomField(DataPointConstant.STORYPOINTS.getValue())).thenReturn("Story Points");
		
		Mockito.when(datapointserviceint.getDataPoint("AL", "Updated Story Points", "2017-04-14 17:48:00", 17)).thenReturn(alldataPointsresponse.get(1));
		Mockito.when(applicationContext.getBean(ProjectResponse.class)).thenReturn(new ProjectResponse());
		// call the function to be tested and take the result into
		
		
		datapointserviceimplementation.mapServiceMethod.get(alldataPoints.get(0).getDPName()).getDataPoint("AL", "Total Active Story Points", "2017-04-05+12:00:00", 17);
		datapointserviceimplementation.mapServiceMethod.get(alldataPoints.get(1).getDPName()).getDataPoint("AL", "Updated Story Points", "2017-04-14 17:48:00", 17);
		datapointserviceimplementation.mapServiceMethod.get(alldataPoints.get(2).getDPName()).getDataPoint("AL", "Story Points", "2017-04-05 12:00:00", 17);
		datapointserviceimplementation.mapServiceMethod.get(alldataPoints.get(3).getDPName()).getDataPoint("AL", "New Story Points", "2017-04-05 12:00:00", 17 );
		datapointserviceimplementation.mapServiceMethod.get(alldataPoints.get(4).getDPName()).getDataPoint("AL", "Closed Story Points", "2017-04-05 12:00:00", 17);
		
		// assert and verify the returned value with the desired value.
		
		assertEquals(expectedprojectresponse.getALMProjectKey(), projectresponse.getALMProjectKey());
		assertEquals(expectedprojectresponse.getDataPoints(), projectresponse.getDataPoints());
		//assertEquals(expectedprojectresponse, projectresponse);	
		//Mockito.verify(dataPointServiceMethodImplementation, Mockito.atLeast(2)).mapServiceMethod.keySet();
		//Mockito.verify(dataPointServiceMethodImplementation.mapServiceMethod, Mockito.atLeast(1)).keySet();
		//Mockito.verify(datapointserviceint, Mockito.atLeast(1));
	
	}

	@Override
	public Object intercept(State arg0, Object arg1) throws SAXException {
		// TODO Auto-generated method stub
		return null;
	}
}
