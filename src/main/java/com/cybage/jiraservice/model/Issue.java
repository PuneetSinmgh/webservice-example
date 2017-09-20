package com.cybage.jiraservice.model;

import java.util.Map;

import org.springframework.stereotype.Component;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Component
public class Issue {

	@SerializedName("expand")
    @Expose
    private String expand;
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("self")
    @Expose
    private String self;
    @SerializedName("key")
    @Expose
    private String key;
    @SerializedName("fields")
    @Expose
    private Map<String,String> fields;
    
    public Issue() {
		super();
	}

	public String getExpand() {
        return expand;
    }

    public void setExpand(String expand) {
        this.expand = expand;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSelf() {
        return self;
    }

    public void setSelf(String self) {
        this.self = self;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

	public Map<String, String> getFields() {
		return fields;
	}

	public void setFields(Map<String, String> fields) {
		this.fields = fields;
	}

}