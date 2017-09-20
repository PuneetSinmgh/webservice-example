
package com.cybage.jiraservice.model;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Component
@Scope("prototype")
public class ProjectResponse {

    @SerializedName("ALMProjectKey")
    @Expose
    private String aLMProjectKey;
    @SerializedName("DataPoints")
    @Expose
    @Autowired
    private List<DataPointResponse> dataPoints;

    public ProjectResponse(String aLMProjectKey, List<DataPointResponse> dataPoints) {
		super();
		this.aLMProjectKey = aLMProjectKey;
		this.dataPoints = dataPoints;
	}

	public ProjectResponse() {
		super();
	}
    
    public String getALMProjectKey() {
        return aLMProjectKey;
    }

    public void setALMProjectKey(String aLMProjectKey) {
        this.aLMProjectKey = aLMProjectKey;
    }

    public List<DataPointResponse> getDataPoints() {
        return dataPoints;
    }

    public void setDataPoints(List<DataPointResponse> dataPoints) {
        this.dataPoints = dataPoints;
    }

	@Override
	public String toString() {
		return "ProjectResponse [aLMProjectKey=" + aLMProjectKey + ", dataPoints=" + dataPoints + "]";
	}

}
