package com.cybage.jiraservice.service;

import java.io.IOException;

import com.cybage.jiraservice.exception.JiraException;
import com.cybage.jiraservice.model.DataPointResponse;

@FunctionalInterface
public interface DataPointServiceInterface {
	public DataPointResponse getDataPoint(String projectKey, String dPName, String lastRunTimestamp, int applicableMeasures) throws JiraException, IOException;
}
