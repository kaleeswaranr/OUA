/* @(#)ActivityDAOImpl.java 1.0 02/16
 * Copyright (c) Open Universities Australia 2016
 */
package com.oua.tm.persistence.dao.impl;

import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.StatelessSession;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.oua.tm.common.constants.Constants;
import com.oua.tm.common.exception.OUAException;
import com.oua.tm.persistence.dao.ActivityDAO;
import com.oua.tm.persistence.model.Activity;

/**
 * This is the implementation of {@link ActivityDAO}.This interacts to the database and based on the operation requested.
 */

@Repository
public class ActivityDAOImpl implements ActivityDAO {
	
	private static final String LOGGING_CLASS_NAME = ActivityDAOImpl.class.getName();

	/**
	 * Logger for this class.
	 */
	private static final Logger LOGGER = Logger.getLogger(LOGGING_CLASS_NAME);

	@Autowired
	private SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory mSessionFactory){
		this.sessionFactory = mSessionFactory;
	}
	
	
	/**
	* Creates new Activity {@link Activity) with user submitted values.
	* @param activity 
	* @return activity with Id, if activity created successfully  
	 *
	* @throws Exception when there is an unknown Error otherwise OUAException
	* @see com.oua.tm.persistence.dao.ActivityDAO#add activity)
	*/
	//@Override
	public boolean add(Activity pActivity) throws Exception{
		LOGGER.entering(LOGGING_CLASS_NAME, " : add");
		LOGGER.info("Request for add the activity from persistence");
		boolean mResult = false;
		Session mSession= null;
		Transaction mTransaction = null;
		try{
			ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
			Validator validator = factory.getValidator();			
			Set<ConstraintViolation<Activity>> mConstraintViolationList = validator.validate(pActivity);			
			if(mConstraintViolationList.size() < 1){
				mSession = this.sessionFactory.openSession();
				mTransaction = mSession.beginTransaction();			
				pActivity.setDelFlag(Constants.NO_FLG);
				mSession.persist(pActivity);			
				mTransaction.commit();		
				mResult = true;
			}else{
				throw new OUAException("fail.input.validation","", null);
			}
		}
		catch(OUAException pOUAException){
			if (mTransaction!=null) {
				mTransaction.rollback();
			}
			pOUAException.printStackTrace(); 
			LOGGER.log(Level.SEVERE, "There OUAException from "+ LOGGING_CLASS_NAME +" when add activity", pOUAException);
			throw pOUAException;
		}
		catch(Exception pException){
			if (mTransaction!=null) {
				mTransaction.rollback();
			}
			pException.printStackTrace(); 
			LOGGER.log(Level.SEVERE, "There is unknown exception from "+ LOGGING_CLASS_NAME +" when add activity", pException);
			throw pException;
		}		
		finally{
			if(mSession != null){
				mSession.close();	
			}	 
	    }
		LOGGER.exiting(LOGGING_CLASS_NAME, " : add");
		return mResult;
	}
	
	/**
	 * Returns a list of activity {@link Activity) for the search criteria represented by activity parameter.
	 * Returns all if the parameter is null
	 *
	 * @param activity search criteria
	 * @return List {@link Activity} 
	 * @throws Exception 
	 * @throws Exception when there is an unknown Error otherwise OUAException
	 * 
	 * @see com.oua.tm.persistence.dao.ActivityDAO#list activity)
	 */
	//@Override
	@SuppressWarnings("unchecked")
	public List<Activity> list(Activity pActivity) throws Exception {
		LOGGER.entering(LOGGING_CLASS_NAME, " : list in persistence");
		LOGGER.info("Request for list the activity from persistence");			
		List<Activity> mActivityList = null;
		StatelessSession mSession = null;
		try{
			mSession = this.sessionFactory.openStatelessSession();
			Criteria mCriteria = mSession.createCriteria(Activity.class);			
			mCriteria.add(Restrictions.eq("delFlag", Constants.NO_FLG));
			if(pActivity.getDescription() !=null && !pActivity.getDescription().isEmpty()){
				mCriteria.add(Restrictions.eq("description", pActivity.getDescription()));
			}			
			mActivityList = mCriteria.list();
		}
		catch(OUAException pOUAException){
			LOGGER.log(Level.SEVERE, "There OUAException from "+ LOGGING_CLASS_NAME +" when list activity", pOUAException);
			throw pOUAException;
		}
		catch(Exception pException){
			pException.printStackTrace(); 
			LOGGER.log(Level.SEVERE, "There is unknown exception from "+ LOGGING_CLASS_NAME +" when list activity", pException);
			throw pException;
		}
		finally{
			if(mSession != null){
				mSession.close();	
			}
		}
		LOGGER.exiting(LOGGING_CLASS_NAME, " : list in persistence");
		return mActivityList;
	}

	/**
	* Deletes Activity {@link Activity) with user submitted values.
	* @param activity 
	* @return activity with Id, if activity delete successfully  
	* @throws Exception when there is an unknown Error otherwise OUAException
	* @see com.oua.tm.persistence.dao.ActivityDAO#add activity)
	*/
	//@Override
	public boolean delete(Activity pActivity) throws Exception{
		LOGGER.entering(LOGGING_CLASS_NAME, " : delete in persistence");
		LOGGER.info("Request for delete the activity from persistence");
		boolean mResult = false;
		Session mSession= null;
		Transaction mTransaction = null;
		try{
			mSession = this.sessionFactory.openSession();
			mTransaction = mSession.beginTransaction();
			Activity mActivity = (Activity)mSession.load(Activity.class, pActivity.getId());
			pActivity.setDelFlag(Constants.YES_FLG);
			mSession.update(mActivity);
			mTransaction.commit();
			mResult = true;			
		}
		catch(OUAException pOUAException){
			if (mTransaction!=null) {
				mTransaction.rollback();
			}
			pOUAException.printStackTrace(); 
			LOGGER.log(Level.SEVERE, "There is OUAException from "+ LOGGING_CLASS_NAME +" when delete activity", pOUAException);
		}
		catch(Exception pException){
			if (mTransaction!=null) {
				mTransaction.rollback();
			}
			pException.printStackTrace(); 
			LOGGER.log(Level.SEVERE, "There is unknown exception from "+ LOGGING_CLASS_NAME +" when delete activity", pException);
			throw pException;
		}
		finally{
			if(mSession != null){
				mSession.close();	
			}
	    }
		LOGGER.exiting(LOGGING_CLASS_NAME, " : delete in persistence");
		return mResult;
	}
}
	
