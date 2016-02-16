/* @(#)OUAApplicationException.java 1.0 02/16
 * Copyright (c) Open Universities Australia 2016
 */

package com.oua.common.exception;

/**
 * Parent Checked Exception.
 * @author Kaleeswaran R 
 */

public class OUAApplicationException extends Exception {
	
	/** Serial Version ID. 	*/
	private static final long serialVersionUID = 1L;

	/** The Key for Localization. */
	private String key;

	/** The message for communication. */
	private String message;

	/**
	* Create OUAException instance with given key and description.
	* 
	* @param newKey
	*           key to access the message
	* @param newMessage
	*           description of the message
	* @param newThroweable
	*           Throwable original exception.
	*/
	public OUAApplicationException(final String newKey, final String newMessage, final Throwable newThroweable){
		super();
		this.key = newKey;
		this.message = newMessage;
		this.initCause(newThroweable);
	}

	/**
	* Create OUAException instance with a description.
	* 
	* @param newMessage
	*           description of the message
	* @param throwable
	*           Throwable.
	*/
	public OUAApplicationException(final String newMessage, final Throwable throwable){
		super();
		this.message = newMessage;
		this.initCause(throwable);
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
	* @param newKey, Key value as a string
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
