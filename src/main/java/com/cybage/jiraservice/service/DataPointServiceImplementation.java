package com.cybage.jiraservice.service;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;

import com.cybage.jiraservice.common.ApplicableMeasureConstant;
import com.cybage.jiraservice.common.DataPointConstant;
import com.cybage.jiraservice.common.JiraURLConstants;
import com.cybage.jiraservice.model.Count;
import com.cybage.jiraservice.model.DataPointResponse;
import com.cybage.jiraservice.model.ExecutionUnit;
import com.cybage.jiraservice.model.Issue;
import com.cybage.jiraservice.model.StoryPoint;
import com.cybage.jiraservice.model.Value;
import com.cybage.jiraservice.util.ApplicableMeasuresFactory;
import com.cybage.jiraservice.util.DataPointFactory;
import com.cybage.jiraservice.util.JiraRequestInvoker;
import com.cybage.jiraservice.util.JsonFactory;
import com.squareup.okhttp.Response;


@Service
@Scope("prototype")
public class DataPointServiceImplementation {
	
	private final Logger logger = Logger.getLogger(this.getClass());

	@Autowired
	private DataPointFactory dataPointFactory;

	@Autowired
	private ApplicationContext applicationContext;

	@Autowired
	private JiraRequestInvoker jiraRequestInvoker;
	
	@Autowired
	private JsonFactory jsonFactory;
	
	public Map<String, DataPointServiceInterface> mapServiceMethod = new HashMap<>();
	
	public DataPointServiceImplementation() {
		logger.info("In DataPointServiceMethodImplementation Const...");
		mapServiceMethod.put(DataPointConstant.TOTALSTORYPOINTS.getValue(),
			(projectKey,dPName,lastRunTimestamp,applicableMeasures) -> {
				logger.info("In Total Story Points Service Method..");
				String customFieldId = dataPointFactory.getCustomField(DataPointConstant.STORYPOINTS.getValue());
				DataPointResponse datapoint=applicationContext.getBean(DataPointResponse.class);
				Response response = jiraRequestInvoker.execute(JiraURLConstants.CUSTOMFIELDSEARCH.getValue()+projectKey+JiraURLConstants.ISSUETYPE.getValue()+" AND status!='Done'"+JiraURLConstants.FIELDS.getValue()+customFieldId+JiraURLConstants.MAXRESULTS.getValue(), HttpMethod.GET);
				StoryPoint storyPoint = (StoryPoint) jsonFactory.getFromJson(response.body().string(), StoryPoint.class);
				logger.info("Story Point : " + storyPoint);
				Value value = applicationContext.getBean(Value.class);
				
				List<String> applicableMeasuresList = ApplicableMeasuresFactory.getApplicableMeasuresList(applicableMeasures);
				logger.info("custome field id : " + customFieldId);
				logger.info("applicable measures : " + applicableMeasuresList);
				if(applicableMeasuresList.contains(ApplicableMeasureConstant.EXECUTIONUNIT.getValue())){
					double sum=0;
					for (Issue iterable_element : storyPoint.getIssues()) {
						if (iterable_element.getFields()!=null && iterable_element.getFields().get(customFieldId)!=null){
							logger.info("Execution Unit " + iterable_element.getFields().get(customFieldId));
							sum=sum+Double.parseDouble(iterable_element.getFields().get(customFieldId));
						}
					}
					//value.setExecutionUnit(new ExecutionUnit(sum,ApplicationMeasureUnitConstant.NUMBER.getValue()));
					value.setExecutionUnit(new ExecutionUnit(sum,null));
					logger.info(value);
				}
				
				if(applicableMeasuresList.contains(ApplicableMeasureConstant.COUNT.getValue())){
					//value.setCount(new Count(storyPoint.getTotal(),ApplicationMeasureUnitConstant.NUMBER.getValue()));
					value.setCount(new Count(storyPoint.getTotal(),null));
				}
				
				datapoint.setDPName(dPName.replace('+', ' '));
				datapoint.setValues(value);
				datapoint.setApplicableMeasure(applicableMeasures);
				logger.info("< Exiting getTotalStoryPoints");
				
				return datapoint;
			});
		mapServiceMethod.put(DataPointConstant.NEWSTORYPOINTS.getValue(),
				(projectKey,dPName,lastRunTimestamp,applicableMeasures) -> {
					logger.info("In new Story Points Service Method..");
					String customFieldId = dataPointFactory.getCustomField(DataPointConstant.STORYPOINTS.getValue());
					DataPointResponse datapoint=applicationContext.getBean(DataPointResponse.class);
					String minutes =dataPointFactory.getMiniutes(lastRunTimestamp.replace('+',' '));
					Response response = jiraRequestInvoker.execute(JiraURLConstants.CUSTOMFIELDSEARCH.getValue()+projectKey+JiraURLConstants.ISSUETYPE.getValue()+" AND created >= -"+minutes+"m"+JiraURLConstants.FIELDS.getValue()+customFieldId+JiraURLConstants.MAXRESULTS.getValue(), HttpMethod.GET);
					StoryPoint storyPoint = (StoryPoint) jsonFactory.getFromJson(response.body().string(), StoryPoint.class);
					logger.info("Story Point : " + storyPoint);
					Value value = applicationContext.getBean(Value.class);
					
					List<String> applicableMeasuresList = ApplicableMeasuresFactory.getApplicableMeasuresList(applicableMeasures);
					logger.info("custome field id : " + customFieldId);
					logger.info("applicable measures : " + applicableMeasuresList);
					if(applicableMeasuresList.contains(ApplicableMeasureConstant.EXECUTIONUNIT.getValue())){
						double sum=0;
						for (Issue iterable_element : storyPoint.getIssues()) {
							if (iterable_element.getFields()!=null && iterable_element.getFields().get(customFieldId)!=null){
								logger.info("Execution Unit " + iterable_element.getFields().get(customFieldId));
								sum=sum+Double.parseDouble(iterable_element.getFields().get(customFieldId));
							}
						}
						value.setExecutionUnit(new ExecutionUnit(sum,null));
						logger.info(value);
					}
					
					if(applicableMeasuresList.contains(ApplicableMeasureConstant.COUNT.getValue())){
						value.setCount(new Count(storyPoint.getTotal(),null));
					}
					
					datapoint.setDPName(dPName.replace('+', ' '));
					datapoint.setValues(value);
					datapoint.setApplicableMeasure(applicableMeasures);
					logger.info("< Exiting getNewStoryPoints");
					
					return datapoint;
				});
		
		mapServiceMethod.put(DataPointConstant.CLOSEDSTORYPOINTS.getValue(),
				(projectKey,dPName,lastRunTimestamp,applicableMeasures) -> {
					logger.info("In closed Story Points Service Method..");
					String customFieldId = dataPointFactory.getCustomField(DataPointConstant.STORYPOINTS.getValue());
					DataPointResponse datapoint=applicationContext.getBean(DataPointResponse.class);
					String minutes =dataPointFactory.getMiniutes(lastRunTimestamp.replace('+',' '));
					Response response = jiraRequestInvoker.execute(JiraURLConstants.CUSTOMFIELDSEARCH.getValue()+projectKey+JiraURLConstants.ISSUETYPE.getValue()+JiraURLConstants.UPDATED.getValue()+minutes+"m"+" AND status = 'Done'"+JiraURLConstants.FIELDS.getValue()+customFieldId+JiraURLConstants.MAXRESULTS.getValue(), HttpMethod.GET);
					StoryPoint storyPoint = (StoryPoint) jsonFactory.getFromJson(response.body().string(), StoryPoint.class);
					logger.info("Story Point : " + storyPoint);
					Value value = applicationContext.getBean(Value.class);
					
					List<String> applicableMeasuresList = ApplicableMeasuresFactory.getApplicableMeasuresList(applicableMeasures);
					logger.info("custome field id : " + customFieldId);
					logger.info("applicable measures : " + applicableMeasuresList);
					if(applicableMeasuresList.contains(ApplicableMeasureConstant.EXECUTIONUNIT.getValue())){
						double sum=0;
						for (Issue iterable_element : storyPoint.getIssues()) {
							if (iterable_element.getFields()!=null && iterable_element.getFields().get(customFieldId)!=null){
								logger.info("Execution Unit " + iterable_element.getFields().get(customFieldId));
								sum=sum+Double.parseDouble(iterable_element.getFields().get(customFieldId));
							}
						}
						value.setExecutionUnit(new ExecutionUnit(sum,null));
						logger.info(value);
					}
					
					if(applicableMeasuresList.contains(ApplicableMeasureConstant.COUNT.getValue())){
						value.setCount(new Count(storyPoint.getTotal(),null));
					}
					
					datapoint.setDPName(dPName.replace('+', ' '));
					datapoint.setValues(value);
					datapoint.setApplicableMeasure(applicableMeasures);
					logger.info("< Exiting getClosedStoryPoints");
					
					return datapoint;
				});
		mapServiceMethod.put(DataPointConstant.STORYPOINTS.getValue(),
				(projectKey,dPName,lastRunTimestamp,applicableMeasures) -> {
					logger.info("In Story Points Service Method..");
					String customFieldId = dataPointFactory.getCustomField(DataPointConstant.STORYPOINTS.getValue());
					DataPointResponse datapoint=applicationContext.getBean(DataPointResponse.class);
					Response response = jiraRequestInvoker.execute(JiraURLConstants.CUSTOMFIELDSEARCH.getValue()+projectKey+JiraURLConstants.ISSUETYPE.getValue()+JiraURLConstants.FIELDS.getValue()+customFieldId+JiraURLConstants.MAXRESULTS.getValue(), HttpMethod.GET);
					StoryPoint storyPoint = (StoryPoint) jsonFactory.getFromJson(response.body().string(), StoryPoint.class);
					logger.info("Story Point : " + storyPoint);
					Value value = applicationContext.getBean(Value.class);
					
					List<String> applicableMeasuresList = ApplicableMeasuresFactory.getApplicableMeasuresList(applicableMeasures);
					logger.info("custome field id : " + customFieldId);
					logger.info("applicable measures : " + applicableMeasuresList);
					if(applicableMeasuresList.contains(ApplicableMeasureConstant.EXECUTIONUNIT.getValue())){
						double sum=0;
						for (Issue iterable_element : storyPoint.getIssues()) {
							if (iterable_element.getFields()!=null && iterable_element.getFields().get(customFieldId)!=null){
								logger.info("Execution Unit " + iterable_element.getFields().get(customFieldId));
								sum=sum+Double.parseDouble(iterable_element.getFields().get(customFieldId));
							}
						}
						value.setExecutionUnit(new ExecutionUnit(sum,null));
						logger.info(value);
					}
					
					if(applicableMeasuresList.contains(ApplicableMeasureConstant.COUNT.getValue())){
						value.setCount(new Count(storyPoint.getTotal(),null));
					}
					
					datapoint.setDPName(dPName.replace('+', ' '));
					datapoint.setValues(value);
					datapoint.setApplicableMeasure(applicableMeasures);
					logger.info("< Exiting getStoryPoints");
					
					return datapoint;
				});
		mapServiceMethod.put(DataPointConstant.UPDATEDSTORYPOINTS.getValue(),
				(projectKey,dPName,lastRunTimestamp,applicableMeasures) -> {
					logger.info("In updated Story Points Service Method..");
					String customFieldId = dataPointFactory.getCustomField(DataPointConstant.STORYPOINTS.getValue());
					DataPointResponse datapoint=applicationContext.getBean(DataPointResponse.class);
					String minutes =dataPointFactory.getMiniutes(lastRunTimestamp.replace('+',' '));
					Response response = jiraRequestInvoker.execute(JiraURLConstants.CUSTOMFIELDSEARCH.getValue()+projectKey+JiraURLConstants.ISSUETYPE.getValue()+JiraURLConstants.UPDATED.getValue()+minutes+"m"+JiraURLConstants.FIELDS.getValue()+customFieldId+JiraURLConstants.MAXRESULTS.getValue(), HttpMethod.GET);
					StoryPoint storyPoint = (StoryPoint) jsonFactory.getFromJson(response.body().string(), StoryPoint.class);
					logger.info("Story Point : " + storyPoint);
					Value value = applicationContext.getBean(Value.class);
					List<String> applicableMeasuresList = ApplicableMeasuresFactory.getApplicableMeasuresList(applicableMeasures);
					logger.info("custome field id : " + customFieldId);
					logger.info("applicable measures : " + applicableMeasuresList);
					if(applicableMeasuresList.contains(ApplicableMeasureConstant.EXECUTIONUNIT.getValue())){
						double sum=0;
						for (Issue iterable_element : storyPoint.getIssues()) {
							if (iterable_element.getFields()!=null && iterable_element.getFields().get(customFieldId)!=null){
								logger.info("Execution Unit " + iterable_element.getFields().get(customFieldId));
								sum=sum+Double.parseDouble(iterable_element.getFields().get(customFieldId));
							}
						}
						value.setExecutionUnit(new ExecutionUnit(sum,null));
						logger.info(value);
					}
					
					if(applicableMeasuresList.contains(ApplicableMeasureConstant.COUNT.getValue())){
						value.setCount(new Count(storyPoint.getTotal(),null));
					}
					
					datapoint.setDPName(dPName.replace('+', ' '));
					datapoint.setValues(value);
					datapoint.setApplicableMeasure(applicableMeasures);
					logger.info("< Exiting getUpdatedStoryPoints");
					
					return datapoint;
				});
	}
	
}
