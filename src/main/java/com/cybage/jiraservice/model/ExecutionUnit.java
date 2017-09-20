
package com.cybage.jiraservice.model;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Component
@Scope("prototype")
public class ExecutionUnit {

    @SerializedName("value")
    @Expose
    private double value;
    @SerializedName("uom")
    @Expose
    private String uom;
    
    public ExecutionUnit(double value, String uom) {
		super();
		this.value = value;
		this.uom = uom;
	}

    public ExecutionUnit() {
		super();
	}
    
	public double getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public String getUom() {
        return uom;
    }

    public void setUom(String uom) {
        this.uom = uom;
    }

	@Override
	public String toString() {
		return "ExecutionUnit [value=" + value + ", uom=" + uom + "]";
	}

}
