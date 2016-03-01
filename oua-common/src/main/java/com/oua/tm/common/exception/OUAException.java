/* @(#)OUAException.java 1.0 02/16
 * Copyright (c) Open Universities Australia 2016
 */

package com.oua.tm.common.exception;

/**
 * The OUAException is a common class extended by all the Exception classes of the application. 
 * This class acts as the parent class for all the other exception classes
 * 
 * @author Kaleeswaran R 
 */

@SuppressWarnings("serial")
public class OUAException extends RuntimeException {
	
	/** The Key for Localization. */
	private String key;

	/** The message for communication. */
	private String message;

	/**
	* Create OUAException instance with given key and description.
	* 
	* @param newKey
	*           String key to access the message
	* @param newMessage
	*           String description of the message
	* @param newThroweable
	*           Throwable original exception.
	*/
	public OUAException(final String newKey, final String newMessage, final Throwable newThroweable){
		super(newMessage);
		this.key = newKey;
		this.message = newMessage;
		this.initCause(newThroweable);
	}

	/**
	* Create OUAException instance with a description.
	* 
	* @param newMessage
	*           description of the message
	* @param newThroweable
	*           Throwable original exception.
	*/
	public OUAException(final String newMessage, final Throwable newThroweable){
	  super(newMessage);
	  this.message = newMessage;
	  this.initCause(newThroweable);
	}

	/**
	* Get the key.
	* @return key the key value as a string
	*/
	public final String getKey(){
		return key;
	}

	/**
	* Sets the Key.
	* @param newKey
	*           the Key value as a string
	*/
	public final void setKey(final String newKey){
		this.key = newKey;
	}

	/**
	* Gets the message.
	* @return message the message
	*/
	public final String getMessage(){
		return message;
	}

	/**
	* Set the new message. 
	* @param newMessage
	*           the message
	*/
	public final void setMessage(final String newMessage){
		this.message = newMessage;
	}
}
