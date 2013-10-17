package com.sin.rest;

import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;

/**
 * WebResource <br/>
 * 
 * @author RobinTang {@link https://github.com/sintrb/SinREST4Android}
 */
public class WebResource {
	public static final String VERSION = "1.0";
	private String url;
	private String remoteEncode = "utf-8";
	private String localEncode = "utf-8";
	private boolean trace = true;
	public WebResource(String url) {
		this.url = url.endsWith("/") ? url : url + "/";
	}

	@Deprecated
	public String getEncode() {
		return remoteEncode;
	}

	@Deprecated
	public void setEncode(String encode) {
		this.remoteEncode = encode;
	}
	
	public boolean isTrace() {
		return trace;
	}

	public void setTrace(boolean trace) {
		this.trace = trace;
	}

	public String getRemoteEncode() {
		return remoteEncode;
	}

	public void setRemoteEncode(String remoteEncode) {
		this.remoteEncode = remoteEncode;
	}

	public String getLocalEncode() {
		return localEncode;
	}

	public void setLocalEncode(String localEncode) {
		this.localEncode = localEncode;
	}

	public String getUrl() {
		return url;
	}

	public String getFullPath(String path) {
		return this.url + (path.startsWith("/") ? path.substring(1) : path);
	}

	public ResourceAccess get(){
		return get("");
	}
	public ResourceAccess get(String path) {
		return new ResourceAccess(this, new HttpGet(getFullPath(path)));
	}
	public ResourceAccess post(){
		return post("");
	}
	public ResourceAccess post(String path) {
		return new ResourceAccess(this, new HttpPost(getFullPath(path)));
	}
	public ResourceAccess delete(){
		return delete("");
	}
	public ResourceAccess delete(String path) {
		return new ResourceAccess(this, new HttpDelete(getFullPath(path)));
	}
	public ResourceAccess put(){
		return put("");
	}
	public ResourceAccess put(String path) {
		return new ResourceAccess(this, new HttpPut(getFullPath(path)));
	}
}
