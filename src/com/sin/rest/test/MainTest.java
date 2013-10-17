package com.sin.rest.test;

import org.apache.http.conn.ssl.AllowAllHostnameVerifier;
import org.apache.http.conn.ssl.SSLSocketFactory;

import com.sin.rest.MediaType;
import com.sin.rest.WebResource;

public class MainTest {
	public static void main(String[] args) {
		SSLSocketFactory.getSocketFactory().setHostnameVerifier(new AllowAllHostnameVerifier());

		
		// Create a WebResource
		WebResource resource = new WebResource("http://sinpy.sinaapp.com/rest/book/");
//		WebResource resource = new WebResource("http://127.0.0.1:9011/rest/book/");

		// Get all resource
		resource.get().accept(MediaType.APPLICATION_JSON).read();
		
		// Add a resource
		resource.post().accept(MediaType.APPLICATION_JSON).read("{\"name\": \"C Language\"}");
		
		// Get all resource
		resource.get().accept(MediaType.APPLICATION_JSON).read();
		
		// Modify resource witch id==1
		resource.put("1").accept(MediaType.APPLICATION_JSON).read("{\"name\": \"TCP/IP\"}");
		
		// Get all resource
		resource.get().accept(MediaType.APPLICATION_JSON).read();
		
		// Delete resource witch id==1
		resource.delete("1").accept(MediaType.APPLICATION_JSON).read();
		
		// Get all resource
		resource.get().accept(MediaType.APPLICATION_JSON).read();
	}
}
