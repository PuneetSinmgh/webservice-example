
package com.cybage.jiraservice.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Component
@Scope("prototype")
public class Value {

    @SerializedName("actualValue")
    @Expose
    @Autowired
    private ActualValue actualValue;
    @SerializedName("size")
    @Expose
    @Autowired
    private Size size;
    @SerializedName("executionUnit")
    @Expose
    @Autowired
    private ExecutionUnit executionUnit;
    @SerializedName("frequency")
    @Expose
    @Autowired
    private Frequency frequency;
    @SerializedName("efforts")
    @Expose
    @Autowired
    private Efforts efforts;
    @SerializedName("count")
    @Expose
    @Autowired
    private Count count;
    @SerializedName("actor")
    @Expose
    @Autowired
    private Actor actor;
    @SerializedName("user")
    @Expose
    @Autowired
    private User user;
    @SerializedName("exists")
    @Expose
    @Autowired
    private Exists exists;


	public Value(ActualValue actualValue, Size size, ExecutionUnit executionUnit, Frequency frequency, Efforts efforts,
			Count count, Actor actor, User user, Exists exists) {
		super();
		this.actualValue = actualValue;
		this.size = size;
		this.executionUnit = executionUnit;
		this.frequency = frequency;
		this.efforts = efforts;
		this.count = count;
		this.actor = actor;
		this.user = user;
		this.exists = exists;
	}
	
	public Value() {
		super();
	}

	public ActualValue getActualValue() {
        return actualValue;
    }

    public void setActualValue(ActualValue actualValue) {
        this.actualValue = actualValue;
    }

    public Size getSize() {
        return size;
    }

    public void setSize(Size size) {
        this.size = size;
    }

    public ExecutionUnit getExecutionUnit() {
        return executionUnit;
    }

    public void setExecutionUnit(ExecutionUnit executionUnit) {
        this.executionUnit = executionUnit;
    }

    public Frequency getFrequency() {
        return frequency;
    }

    public void setFrequency(Frequency frequency) {
        this.frequency = frequency;
    }

    public Efforts getEfforts() {
        return efforts;
    }

    public void setEfforts(Efforts efforts) {
        this.efforts = efforts;
    }

    public Count getCount() {
        return count;
    }

    public void setCount(Count count) {
        this.count = count;
    }

    public Actor getActor() {
        return actor;
    }

    public void setActor(Actor actor) {
        this.actor = actor;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Exists getExists() {
        return exists;
    }

    public void setExists(Exists exists) {
        this.exists = exists;
    }

	@Override
	public String toString() {
		return "Value [actualValue=" + actualValue + ", size=" + size + ", executionUnit=" + executionUnit
				+ ", frequency=" + frequency + ", efforts=" + efforts + ", count=" + count + ", actor=" + actor
				+ ", user=" + user + ", exists=" + exists + "]";
	}

    
}
