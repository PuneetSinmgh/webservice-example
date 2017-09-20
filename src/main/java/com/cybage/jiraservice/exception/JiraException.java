
package com.cybage.jiraservice.exception;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class JiraException extends RuntimeException{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@SerializedName("status")
    @Expose
    private int status;
    @SerializedName("message")
    @Expose
    private String message;
    
	public JiraException(int status, String message) {
		super();
		this.status = status;
		this.message = message;
	}

	public JiraException() {

	}
	
	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

	@Override
	public String toString() {
		return "JiraException [status=" + status + ", message=" + message + "]";
	}

}
