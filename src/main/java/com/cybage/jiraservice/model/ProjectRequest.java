
package com.cybage.jiraservice.model;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Component
@Scope("prototype")
public class ProjectRequest {

    @SerializedName("CybProjectId")
    @Expose
    private Integer cybProjectId;
    @SerializedName("HostName")
    @Expose
    private String hostName;
    @SerializedName("ALMProjectKey")
    @Expose
    private String aLMProjectKey;
    @SerializedName("url")
    @Expose
    private String url;
    @SerializedName("DataPoints")
    @Expose
    private List<DataPointRequest> dataPoints;

    public ProjectRequest(Integer cybProjectId, String hostName, String aLMProjectKey, String url,
			List<DataPointRequest> dataPoints) {
		super();
		this.cybProjectId = cybProjectId;
		this.hostName = hostName;
		this.aLMProjectKey = aLMProjectKey;
		this.url = url;
		this.dataPoints = dataPoints;
	}

    public ProjectRequest() {
		super();
	}
    
	public Integer getCybProjectId() {
        return cybProjectId;
    }

    public void setCybProjectId(Integer cybProjectId) {
        this.cybProjectId = cybProjectId;
    }

    public String getHostName() {
        return hostName;
    }

    public void setHostName(String hostName) {
        this.hostName = hostName;
    }

    public String getALMProjectKey() {
        return aLMProjectKey;
    }

    public void setALMProjectKey(String aLMProjectKey) {
        this.aLMProjectKey = aLMProjectKey;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public List<DataPointRequest> getDataPoints() {
        return dataPoints;
    }

    public void setDataPoints(List<DataPointRequest> dataPoints) {
        this.dataPoints = dataPoints;
    }

	@Override
	public String toString() {
		return "Project [cybProjectId=" + cybProjectId + ", hostName=" + hostName + ", aLMProjectKey=" + aLMProjectKey
				+ ", url=" + url + ", dataPoints=" + dataPoints + "]";
	}

}
