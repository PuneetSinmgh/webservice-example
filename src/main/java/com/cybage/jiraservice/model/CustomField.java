package com.cybage.jiraservice.model;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CustomField {

	@SerializedName("id")
	@Expose
	private String id;
	@SerializedName("name")
	@Expose
	private String name;
	@SerializedName("custom")
	@Expose
	private Boolean custom;
	@SerializedName("orderable")
	@Expose
	private Boolean orderable;
	@SerializedName("navigable")
	@Expose
	private Boolean navigable;
	@SerializedName("searchable")
	@Expose
	private Boolean searchable;
	@SerializedName("clauseNames")
	@Expose
	private List<String> clauseNames = null;

	public CustomField(String id, String name, Boolean custom, Boolean orderable, Boolean navigable, Boolean searchable,
		List<String> clauseNames) {
		super();
		this.id = id;
		this.name = name;
		this.custom = custom;
		this.orderable = orderable;
		this.navigable = navigable;
		this.searchable = searchable;
		this.clauseNames = clauseNames;
	}

	public CustomField() {
		super();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Boolean getCustom() {
		return custom;
	}

	public void setCustom(Boolean custom) {
		this.custom = custom;
	}

	public Boolean getOrderable() {
		return orderable;
	}

	public void setOrderable(Boolean orderable) {
		this.orderable = orderable;
	}

	public Boolean getNavigable() {
		return navigable;
	}

	public void setNavigable(Boolean navigable) {
		this.navigable = navigable;
	}

	public Boolean getSearchable() {
		return searchable;
	}

	public void setSearchable(Boolean searchable) {
		this.searchable = searchable;
	}

	public List<String> getClauseNames() {
		return clauseNames;
	}

	public void setClauseNames(List<String> clauseNames) {
		this.clauseNames = clauseNames;
	}
}
