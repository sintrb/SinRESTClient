package com.sin.rest;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.http.HttpEntity;
import org.apache.http.HttpEntityEnclosingRequest;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

/**
 * ResourceAccess <br/>
 * 
 * @author RobinTang {@link https://github.com/sintrb/SinREST4Android}
 */
public class ResourceAccess {
	private static DefaultHttpClient httpClient = new DefaultHttpClient();
	private static final int HTTP_STATUSCODE_OK = 200;
	private HttpUriRequest request;
	private WebResource webResource;

	public ResourceAccess(WebResource webResource, HttpUriRequest request) {
		super();
		this.webResource = webResource;
		this.request = request;
	}

	/**
	 * Get HTTP URI Request
	 * 
	 * @return HttpUriRequest
	 */
	public HttpUriRequest getRequest() {
		return request;
	}

	/**
	 * Set Accept resource type
	 * 
	 * @param mediaType
	 *            Resource type
	 * @return ResourceAccess
	 */
	public ResourceAccess accept(String mediaType) {
		this.request.setHeader("Accept", mediaType);
		return this;
	}

	/**
	 * Set Accept resource type
	 * 
	 * @param mediaType
	 *            Resource type
	 * @return ResourceAccess
	 */
	public ResourceAccess accept(MediaType mediaType) {
		return accept(mediaType.toString());
	}

	/**
	 * Set Submit resource type
	 * 
	 * @param mediaType
	 *            Resource type
	 * @return ResourceAccess
	 */
	public ResourceAccess type(String mediaType) {
		this.request.setHeader("Content-Type", mediaType);
		return this;
	}

	/**
	 * Set Submit resource type
	 * 
	 * @param mediaType
	 *            Resource type
	 * @return ResourceAccess
	 */
	public ResourceAccess type(MediaType mediaType) {
		return type(mediaType.toString());
	}

	/**
	 * Raw read from remote resource, it will return HttpEntity
	 * 
	 * @param entity
	 *            HTTP Request Entity
	 * @return HTTP Response Entity
	 * @throws Exception
	 */
	public HttpEntity rawRead(HttpEntity entity) throws Exception {
		if (webResource.isTrace())
			System.out.println("request: " + request.getURI().toString());
		if (entity != null) {
			// POST Data
			if (webResource.isTrace() && StringEntity.class.isInstance(entity)){
				System.out.print("request-data: \n");
				((StringEntity)entity).writeTo(System.out);
				System.out.print("\n");
			}
			((HttpEntityEnclosingRequest) request).setEntity(entity);
		}
		HttpResponse httpResp = httpClient.execute(request);

		if (httpResp.getStatusLine().getStatusCode() == HTTP_STATUSCODE_OK) {
			return httpResp.getEntity();
		} else {
			throw new Exception("Status Code Error: " + httpResp.getStatusLine().getStatusCode());
		}
	}

	/**
	 * Read string from remote resource direct
	 * 
	 * @return String
	 */
	public String read() {
		return read(null, null);
	}

	/**
	 * Read string from remote resource by submit data form
	 * 
	 * @param form
	 *            Data form
	 * @return String
	 */
	public String read(Map<String, String> form) {
		return read(form, null);
	}

	/**
	 * Read string from remote resource by submit text entity
	 * 
	 * @param text
	 *            Text entity
	 * @return String
	 */
	public String read(String text) {
		return read(null, text);
	}

	/**
	 * Read string from remote resource
	 * 
	 * @param form
	 *            Data form
	 * @param text
	 *            Text entity
	 * @return String
	 */
	public String read(Map<String, String> form, String text) {
		HttpEntity resEntity = null;
		HttpEntity reqEntity = null;
		String res = null;
		try {
			if (form != null) {
				reqEntity = new UrlEncodedFormEntity(keyValueToValuePairList(form), webResource.getLocalEncode());
			} else if (text != null) {
				reqEntity = new StringEntity(text, webResource.getLocalEncode());
			}
			resEntity = rawRead(reqEntity);
			res = EntityUtils.toString(resEntity, webResource.getRemoteEncode());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			request.abort();
		}
		if (webResource.isTrace())
			System.out.println("response: \n" + (res == null ? "" : res));
		return res;
	}

	/**
	 * Convert String Map to NameValuePair List
	 * 
	 * @param paramsMap
	 *            String Map will be Converted
	 * @return NameValuePair List
	 */
	static private List<NameValuePair> keyValueToValuePairList(Map<String, String> paramsMap) {
		final List<NameValuePair> dataList = new ArrayList<NameValuePair>();
		for (Entry<String, String> entry : paramsMap.entrySet()) {
			dataList.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
		}
		return dataList;
	}
}
