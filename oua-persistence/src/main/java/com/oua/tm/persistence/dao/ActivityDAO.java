/* @(#)ActivityDAO.java 1.0 02/16
 * Copyright (c) Open Universities Australia 2016
 */
package com.oua.tm.persistence.dao;

import java.util.List;

import com.oua.tm.persistence.model.Activity;

/**
 * This interface comprises ORM functionality of the Activity Table. Each ORM 
 * method represents a unit of work and typically execute as one atomic transaction.
 * 
 * @author Kaleeswaran R
 * 
 */
public interface ActivityDAO {
	
   /**
    * This method lists the activity for a particular search criteria.The search criteria is encapsulated as {@link Activity}
    * and passed to the method.If the object is null, then all the activity are returned.
    *  
    * @param Activity (@link activity} the Search criteria.
    * @return List {@link activity}
    * @throws OUAException in case of unknown errors.
    */	
	public List<Activity> list(Activity pActivity) throws Exception;
	
	/**
    * This method creates new activity based on user submitted values.The submitted values encapsulated as {@link Activity}
    * and passed to the method.
    * 
    * @param project (@link Activity.
    * @return  value 1, if activity created successfully.
    * @throws OUAException in case of unknown errors.
    */
	public boolean add(Activity pActivity) throws Exception;
	
	
	/**
    * This method delete the activity based on user submitted values.The submitted values encapsulated as {@link Activity}
    * and passed to the method.
    * 
    * @param project (@link Activity.
    * @return  value 1, if activity delete successfully.
    * @throws OUAException in case of unknown errors.
    */
	public boolean delete(Activity pActivity) throws Exception;
}
