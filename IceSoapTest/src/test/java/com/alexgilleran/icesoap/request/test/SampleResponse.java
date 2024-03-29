package com.alexgilleran.icesoap.request.test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
//<soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" xmlns:foot="http://footballpool.dataaccess.eu">
//<soapenv:Header/>
//<soapenv:Body>

public class SampleResponse {
	private final static String SINGLE_RESPONSE = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:response=\"http://example.com/response\">"
			+ "<soapenv:Header/>"//
			+ "<soapenv:Body>"//
			+ "<response:Response>"//
			+ "<response:Details id=\"1\">"//
			+ "<response:TextField>Text</response:TextField>"//
			+ "</response:Details>"//
			+ "</response:Response>"//
			+ "</soapenv:Body>"//
			+ "</soapenv:Envelope>";//

	private final static String LIST_RESPONSE = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:response=\"http://example.com/response\">"
			+ "<soapenv:Header/>"//
			+ "<soapenv:Body>"//
			+ "<response:Response>"//
			+ "<response:Details id=\"1\">"//
			+ "<response:TextField>Text1</response:TextField>"//
			+ "</response:Details>"//
			+ "<response:Details id=\"2\">"//
			+ "<response:TextField>Text2</response:TextField>"//
			+ "</response:Details>"//
			+ "<response:Details id=\"3\">"//
			+ "<response:TextField>Text3</response:TextField>"//
			+ "</response:Details>"//
			+ "<response:Details id=\"4\">"//
			+ "<response:TextField>Text4</response:TextField>"//
			+ "</response:Details>"//
			+ "</response:Response>"//
			+ "</soapenv:Body>"//
			+ "</soapenv:Envelope>";//

	public static InputStream getSingleResponse() {
		return new ByteArrayInputStream(SINGLE_RESPONSE.getBytes());
	}

	public static InputStream getListResponse() {
		return new ByteArrayInputStream(LIST_RESPONSE.getBytes());
	}
}
