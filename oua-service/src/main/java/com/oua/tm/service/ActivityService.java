/* @(#)ActivityService.java 1.0 02/16
 * Copyright (c) Open Universities Australia 2016
 */

package com.oua.tm.service;

import java.util.List;

import com.oua.tm.persistence.model.Activity;
import com.oua.tm.service.BaseService;

/**
 * This interface comprises operations that embed the core business functionality of the {@link Activity} . Each service
 * method represents a unit of work and typically execute as one atomic transaction.
 * 
 * @author Kaleeswaran R 
 */

public interface ActivityService extends BaseService{
	
	/**
	* This method lists the activities for a particular search criteria.The search criteria is encapsulated as {@link Activity}
	* and passed to the method.If the object is null, then all the activity are returned as List.
	* 
	* @param activity (@link Activity} the Search criteria.
	* @return List {@link Activity}
	* @throws OUAException in case of unknown errors.
	*/	
	public List<Activity> list(Activity pActivity) throws Exception;
	
	/**
	* This method creates new activity based on user submitted values.The submitted values encapsulated as {@link Activity}
	* and passed to the method.
	* 
	* @param activity (@link Activity.
	* @return activity with Id, if activity created successfully
	* @throws OUAException in case of unknown errors.
	*/
	public boolean save(Activity pActivity) throws Exception;
	
	
	/**
	* This method delete activity based on user submitted values.The submitted values encapsulated as {@link Activity}
	* and passed to the method.
	* 
	* @param activity (@link Activity.
	* @return activity with Id, if activity delete successfully
	* @throws OUAException in case of unknown errors.
	*/
	public boolean delete(Activity pActivity) throws Exception;
}
