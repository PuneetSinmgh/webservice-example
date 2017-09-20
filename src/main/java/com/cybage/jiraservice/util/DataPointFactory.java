package com.cybage.jiraservice.util;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.apache.log4j.Logger;
//import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import com.cybage.jiraservice.common.DataPointConstant;
import com.cybage.jiraservice.common.JiraURLConstants;
import com.cybage.jiraservice.model.CustomField;
import com.cybage.jiraservice.model.ProjectRequest;
import com.google.gson.Gson;
import com.squareup.okhttp.Response;

@Component
@Scope("singleton")
public class DataPointFactory {

	private final Logger logger = Logger.getLogger(this.getClass());
	
	@Autowired
	private Gson gson;
	
	@Autowired
	private JiraRequestInvoker jiraRequestInvoker;
	
	@Autowired
	private JsonFactory jsonFactory;
	
	public DataPointFactory(JiraRequestInvoker jiraRequestInvoker) {
		super();
		this.jiraRequestInvoker = jiraRequestInvoker;
	}

	public static final List<String> getAllDataPoints(){
		List<String> dataPointConstants = new ArrayList<>();
		for(DataPointConstant value: DataPointConstant.values()){
			dataPointConstants.add(value.getValue());
		}
		return dataPointConstants; 
	}
	
	@SuppressWarnings("unchecked")
	public final List<CustomField> getAllCustomFields() throws IOException{
		Response response =  jiraRequestInvoker.execute(JiraURLConstants.CUSTOMFIELD.getValue(), HttpMethod.GET);
		return (List<CustomField>)(List<?>)Arrays.asList(jsonFactory.getFromJsonArray(response.body().string(), CustomField[].class));
	}
	
	public final String getCustomField(String fieldName) throws IOException{
		List<CustomField> customFields=this.getAllCustomFields();
		Optional<CustomField> customField = customFields.stream().filter(i->i.getName().equals(fieldName.replace('+', ' ')))
								.findAny();
		if (customField.isPresent()){
			return customField.get().getId();
		}
		return null;
	}
	
	public String getMiniutes(String lastRunTime){
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		LocalDateTime now = LocalDateTime.now();
		logger.info("formatted curent date : "+dtf.format(now));
		LocalDateTime current= LocalDateTime.parse(dtf.format(now), dtf);
		LocalDateTime lastRunTimeDate=LocalDateTime.parse(lastRunTime,dtf);
		logger.info("last runtime : "+ lastRunTimeDate);
		long minutes =lastRunTimeDate.until(current, ChronoUnit.MINUTES);
		logger.info("mins "+minutes );
		return String.valueOf(minutes);
	}
	
	
}