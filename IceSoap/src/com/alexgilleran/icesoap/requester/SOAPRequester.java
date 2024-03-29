package com.alexgilleran.icesoap.requester;

import java.io.IOException;
import java.io.InputStream;

import com.alexgilleran.icesoap.envelope.SOAPEnvelope;
import com.alexgilleran.icesoap.exception.SOAPException;

/**
 * Handles SOAP requests
 * 
 * @author Alex Gilleran
 * 
 */
public interface SOAPRequester {
	/**
	 * Performs a SOAP request
	 * 
	 * @param envelope
	 *            The SOAP envelope to send
	 * @param targetUrl
	 *            The url of the SOAP web service to communicate with.
	 * @return An InputStream representing the
	 * @throws IOException
	 */
	public InputStream doSoapRequest(SOAPEnvelope envelope, String targetUrl)
			throws SOAPException;

	/**
	 * Performs a SOAP request
	 * 
	 * @param envelope
	 *            The SOAP envelope to send
	 * @param targetUrl
	 *            The url of the SOAP web service to communicate with.
	 * @param soapAction
	 *            The SOAP Action to perform - this is put in the
	 *            <code>SOAPAction</code> field of the outgoing HTTP post.
	 * @return An InputStream representing the
	 * @throws IOException
	 */
	public InputStream doSoapRequest(SOAPEnvelope envelope, String targetUrl,
			String soapAction) throws SOAPException;

	/**
	 * Set the timeout for making connections to the server.
	 * 
	 * @param timeout
	 *            Timeout time in milliseconds.
	 */
	public void setConnectionTimeout(int timeout);

	/**
	 * Set the timeout for receiving data from the server - note that this takes
	 * into account time to establish a connection, send the envelope, wait for
	 * the server to process and then recieve it.
	 * 
	 * @param timeout
	 *            Timeout time in milliseconds.
	 */
	public void setSocketTimeout(int timeout);
}
