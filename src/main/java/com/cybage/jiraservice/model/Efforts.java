
package com.cybage.jiraservice.model;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Component
@Scope("prototype")
public class Efforts {

    @SerializedName("value")
    @Expose
    private Integer value;
    @SerializedName("uom")
    @Expose
    private String uom;

    public Efforts(Integer value, String uom) {
		super();
		this.value = value;
		this.uom = uom;
	}

    public Efforts() {
		super();
	
	}
    
	public Integer getValue() {
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
		return "Efforts [value=" + value + ", uom=" + uom + "]";
	}

}
