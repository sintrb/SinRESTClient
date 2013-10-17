package com.sin.rest;

/**
 * MediaType <br/> Resource Type
 * 
 * @author RobinTang {@link https://github.com/sintrb/SinREST4Android}
 */
public enum MediaType {
	APPLICATION_XML("application/xml"), // APPLICATION_XML
	APPLICATION_JSON("application/json"); // APPLICATION_JSON

	String types;

	private MediaType(String typestr) {
		this.types = typestr;
	}

	public String toString() {
		return this.types;
	}
}
