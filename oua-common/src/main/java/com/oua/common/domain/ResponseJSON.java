/* @(#)ResponseJSON.java 1.0 02/16
 * Copyright (c) Open Universities Australia 2016
 */

package com.oua.common.domain;

/**
 * This class encapsulates JSON Response Object.
 */
public class ResponseJSON {
   
	/** Status. **/
	private String status = null;
	
	/** result. **/
	private Object result = null;   
   
	/** message. **/
	private String message = null;
   
	/**
    * Returns status.
    * @return String
    */
	public final String getStatus(){
      return status;
	}

	/**
    * Sets the status.
    * @param pStatus String
    */
	public final void setStatus(final String pStatus){
		this.status = pStatus;
	}

	/**
	* Returns result.
    * @return result Object
    */
	public final Object getResult(){
		return result;
	}

	/**
    * Sets the Result.
    * @param pResult Object
	*/
	public final void setResult(final Object pResult){
		this.result = pResult;
	}   
   
	/**
    * Returns message.
    * @return String
    */
	public final String getMessage(){
		return message;
	}

	/**
    * Sets the message.
    * @param pMessage String
    */
	public final void setMessage(final String pMessage){
		this.message = pMessage;
	}
}
