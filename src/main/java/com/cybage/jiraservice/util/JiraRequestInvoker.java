package com.cybage.jiraservice.util;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;

import com.cybage.jiraservice.common.ExceptionConstantsMap;
import com.cybage.jiraservice.exception.JiraException;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;


@Component
@Scope("singleton")
public class JiraRequestInvoker {
	
	private String username;
	
	private String password;
	
	private String jiraUrl;
	
	public JiraRequestInvoker(@Value("${jira.username}") String username, @Value("${jira.password}") String password, @Value("${jira.url}") String jiraUrl) {
		super();
		this.username = username;
		this.password = password;
		this.jiraUrl = jiraUrl;
	}
	
	public Response execute(String url, HttpMethod method) throws IOException{
		OkHttpClient client = new OkHttpClient();
		Response response=null;
		if(method.equals(HttpMethod.GET)){
			Request request = new Request.Builder()
					  .url(this.jiraUrl + url)
					  .get()
					  .addHeader("Authorization", getAuthorization())
					  .build();
			response = client.newCall(request).execute();
			if(response.code()!=200){
				throw new JiraException(response.code(), ExceptionConstantsMap.getMessage(response.code()));
			}
		}
		return response;
	}

	
	private final String getAuthorization() throws UnsupportedEncodingException{
		String authString = this.username + ":" + this.password;
		byte[] authEncBytes = Base64.encodeBase64(authString.getBytes(StandardCharsets.UTF_8.toString()));
		String authStringEnc = new String(authEncBytes );
		return "Basic " + authStringEnc;
	}
	
}