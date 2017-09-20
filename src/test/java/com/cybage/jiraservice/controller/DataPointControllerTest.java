package com.cybage.jiraservice.controller;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import com.cybage.jiraservice.exception.GlobalExceptionHandler;
import com.cybage.jiraservice.model.Actor;
import com.cybage.jiraservice.model.ActualValue;
import com.cybage.jiraservice.model.Count;
import com.cybage.jiraservice.model.DataProviderRequest;
import com.cybage.jiraservice.model.DataProviderPResponse;
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
import com.cybage.jiraservice.service.DataPointService;
import com.cybage.jiraservice.util.JsonFactory;


@RunWith(SpringRunner.class)
@WebMvcTest(value=DataPointController.class,secure=false)
public class DataPointControllerTest {

	private final Logger logger = Logger.getLogger(this.getClass());
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private DataPointService dataPointService;
	
	@MockBean
	private GlobalExceptionHandler globalExceptionHandler;
	
	@MockBean
	private JsonFactory jsonFactory;
	
	@InjectMocks
	private DataPointController dataPointController;
	
	@MockBean
	ApplicationContext applicationContext;
	
	public DataPointControllerTest() {
		super();
	}

	@Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(dataPointController).build();
    }
	
	@Test
	public void testGetDatapoints() throws Exception{
		String keyEncoded = "%5B%7B%22CybProjectId%22%3A0%2C%22HostName%22%3Anull%2C%22ALMProjectKey%22%3A%22TEST%22%2C%22url%22%3Anull%2C%22DataPoints%22%3A%5B%7B%22DPName%22%3A%22Total+Story+Points%22%2C%22last_run_timestamp%22%3A%222017-04-05+12%3A00%3A00%22%2C%22ApplicableMeasure%22%3A17%2C%22values%22%3Anull%7D%5D%7D%5D";
		
		Value mockValueRequest = new Value();
		DataPointRequest mockDataPointRequest = new DataPointRequest("Total Story Points", "2017-04-05+12:00:00", 17 , mockValueRequest);
		Object[] mockProjectRequestArray = {new ProjectRequest(0, null, "TEST", null, Arrays.asList((mockDataPointRequest)))}; 
		
		Value mockValueResponse = new Value(new ActualValue(null, null),new Size(null, null),
				new ExecutionUnit(10, "Number"), new Frequency(null, null),
				new Efforts(null, null), new Count(3, "Number"), 
				new Actor(null, null), new User(null, null), 
				new Exists(null, null));
		
		DataPointResponse mockDataPointResponse = new DataPointResponse("Total Story Points", 17 , mockValueResponse);
		ProjectResponse mockProjectResponse = new ProjectResponse("TEST", Arrays.asList(mockDataPointResponse));
		
		Mockito.when(jsonFactory.getFromJsonArray(Mockito.anyString(),Mockito.eq(ProjectRequest[].class))).thenReturn(mockProjectRequestArray);
		Mockito.when(dataPointService.getProjectData(Mockito.anyObject())).thenReturn(mockProjectResponse);
		Mockito.when(applicationContext.getBean(DataProviderRequest.class)).thenReturn(new DataProviderRequest());
		Mockito.when(applicationContext.getBean(DataProviderPResponse.class)).thenReturn(new DataProviderPResponse());
		
		MvcResult result = mockMvc.perform(get("/jira/datapoints/" + keyEncoded)).andReturn();
		mockMvc.perform(get("/jira/datapoints/" + keyEncoded)).andExpect(status().isOk());
		
		String expected = "[{\"dataPoints\":[{\"applicableMeasure\":17,\"values\":{"
				+ "\"actualValue\":{\"value\":null,\"uom\":null},"
				+ "\"size\":{\"value\":null,\"uom\":null},"
				+ "\"executionUnit\":{\"value\":10.0,\"uom\":\"Number\"},"
				+ "\"frequency\":{\"value\":null,\"uom\":null},"
				+ "\"efforts\":{\"value\":null,\"uom\":null},"
				+ "\"count\":{\"value\":3,\"uom\":\"Number\"},"
				+ "\"actor\":{\"value\":null,\"uom\":null},"
				+ "\"user\":{\"value\":null,\"uom\":null},"
				+ "\"exists\":{\"value\":null,\"uom\":null}},"
				+ "\"dpname\":\"Total Story Points\"}],\"almprojectKey\":\"TEST\"}]";
		
		logger.info("Result : " + result.getResponse().getContentAsString());
		assertEquals(expected, result.getResponse().getContentAsString());
	}
}
