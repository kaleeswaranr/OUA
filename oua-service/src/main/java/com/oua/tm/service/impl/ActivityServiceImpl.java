/* @(#)ActivityServiceImpl.java 1.0 02/16
 * Copyright (c) Open Universities Australia 2016
 */

package com.oua.tm.service.impl;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oua.tm.common.exception.OUAException;
import com.oua.tm.persistence.dao.ActivityDAO;
import com.oua.tm.persistence.model.Activity;
import com.oua.tm.service.ActivityService;

/**
 * This is the implementation of {@link ActivityService}.This interacts to the Persistence layer to get the relevant
 * data based on the operation requested.
 * 
 * @author Kaleeswaran R
 * 
 */

@Service("activityService")
public class ActivityServiceImpl extends BaseServiceImpl implements
		ActivityService {

	/**
    * Logger Name for this class.
    */
	private static final String LOGGING_CLASS_NAME = ActivityServiceImpl.class
			.getName();
	/**
	 * Logger for this class.
	 */
	private static final Logger LOGGER = Logger.getLogger(LOGGING_CLASS_NAME);

	@Autowired
	private ActivityDAO activityDAO;
	    
	/**
	 * Returns a list of Activity {@link Activity) for the Search criteria represented by activity parameter.
	 * Returns all if the parameter is null
	 *
	 * @param activity search criteria
	 * @return List {@link Activity} 
	 * @throws Exception 
	 * @throws Exception when there is an unknown Error otherwise OUAException
	 * 
	 * @see com.oua.tm.service.ActivityService#list Activity)
	 */
	@Transactional
	public List<Activity> list(Activity pActivity) throws Exception{
		List<Activity> mActivityList = null;
		try {
			LOGGER.entering(LOGGING_CLASS_NAME, " : list in service");
			LOGGER.info("Request for list the activity from service");			
			mActivityList = this.activityDAO.list(pActivity);
			LOGGER.exiting(LOGGING_CLASS_NAME, " : list in service");
		}
		catch (OUAException mOUAException) {
			LOGGER.log(Level.SEVERE, "There OUAException from "+ LOGGING_CLASS_NAME +" when list activity", mOUAException);
			throw mOUAException;
		}
		catch (Exception mException) {
			LOGGER.log(Level.SEVERE, "There is unknown exception from "+ LOGGING_CLASS_NAME +" when list activity", mException);
			throw mException;
		}
		return mActivityList;
	}
	
    /**
    * Creates new Activity {@link Activity) with user submitted values.
    * @param activity 
    * @return activity with new Id, if project created successfully  
    * @throws Exception when there is an unknown Error otherwise OUAException
    * @see com.oua.tm.service.ActivityService#list Activity)
    */
	//@Override
    @Transactional
	public boolean save(Activity pActivity)throws Exception{
    	boolean mResult = false;
		try {
			LOGGER.entering(LOGGING_CLASS_NAME, " : save in service");
   			LOGGER.info("Request for save activity from service");
   			mResult = this.activityDAO.add(pActivity);
		}
		catch (OUAException mOUAException) {
			LOGGER.log(Level.SEVERE, "There OUAException from "+ LOGGING_CLASS_NAME +" when saving activity", mOUAException);
			throw mOUAException;
		}	
		catch (Exception mException) {
			LOGGER.log(Level.SEVERE, "There is unknown exception from "+ LOGGING_CLASS_NAME +" when saving activity", mException);
			throw mException;
		}
		return mResult;
	}
    
    
    /**
     * Delete Activity {@link Activity) with user submitted values.
     * @param activity 
     * @return activity with new Id, if project delete successfully  
     * @throws Exception when there is an unknown Error otherwise OUAException
     * @see com.oua.tm.service.ActivityService#list Activity)
     */
     @Transactional
 	public boolean delete(Activity pActivity)throws Exception{
    	boolean mResult = false;
 		try {
 			LOGGER.entering(LOGGING_CLASS_NAME, " : delete in service");
    		LOGGER.info("Request for delete activity from service");
    		mResult = this.activityDAO.delete(pActivity);
 		} 
 		catch (OUAException mOUAException) {
 			LOGGER.log(Level.SEVERE, "There is OUAException from "+ LOGGING_CLASS_NAME +" when delete activity is performed", mOUAException);
 			throw mOUAException;
 		}
 		catch (Exception mException) {
			LOGGER.log(Level.SEVERE, "There is unknown exception from "+ LOGGING_CLASS_NAME +" when saving activity", mException);
			throw mException;
		}
 		return mResult;
 	}
}
