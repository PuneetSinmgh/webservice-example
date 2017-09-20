
package com.cybage.jiraservice.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Component
@Scope("prototype")
public class DataPointResponse {

    @SerializedName("DPName")
    @Expose
    private String dPName;
    @SerializedName("ApplicableMeasure")
    @Expose
    private Integer applicableMeasure;
    @SerializedName("values")
    @Expose
    @Autowired
    private Value values;


	public DataPointResponse(String dPName, Integer applicableMeasure, Value values) {
		super();
		this.dPName = dPName;
		this.applicableMeasure = applicableMeasure;
		this.values = values;
	}

	public DataPointResponse() {
		super();
	}
    
	public String getDPName() {
        return dPName;
    }

    public void setDPName(String dPName) {
        this.dPName = dPName;
    }

    public Integer getApplicableMeasure() {
        return applicableMeasure;
    }

    public void setApplicableMeasure(Integer applicableMeasure) {
        this.applicableMeasure = applicableMeasure;
    }

	public Value getValues() {
		
		return values;
	}

	public void setValues(Value values) {
		this.values = values;
	}

	@Override
	public String toString() {
		return "DataPoint [dPName=" + dPName + ", applicableMeasure="
				+ applicableMeasure + ", values=" + values + "]";
	}
}
