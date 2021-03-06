/**
 * Test Package
 */
package com.oua.tm.persistence.dao;

import java.util.List;
import java.util.Properties;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.transaction.annotation.Transactional;

import com.oua.tm.common.constants.Constants;
import com.oua.tm.common.exception.OUAException;
import com.oua.tm.persistence.dao.ActivityDAO;
import com.oua.tm.persistence.model.Activity;

/**
 * This class serves the Integration test cases for persistence layer of Activity.
 * 
 * @author Kaleeswaran R
 */
@Test
@ContextConfiguration(locations = {"/unit-dao-test-context.xml" })
public class ActivityDAOUTest extends AbstractTestNGSpringContextTests {
	/**
    * The @Autowired annotation is auto wire the Application Context.
    */
	@Autowired
	private ApplicationContext mContext;
   	/**
    * The @Autowired annotation is auto wire the Properties.
    */
	@Autowired
   	private Properties mProperties;
	
   	/**
    * The @Autowired annotation is auto wire the ActivityDAO.
    */
	@Autowired
   	private ActivityDAO activityDAO;
	
	/**
	 * create record with right input and expecting success case,
	 */
	@Transactional
	@Test
	public void createActivityWithDescription() throws Exception{
		Activity mActivity = new Activity();
		mActivity.setDescription("This is testing description from DAO UT");
		mActivity.setDelFlag(Constants.YES_FLG);
		activityDAO.add(mActivity);
		boolean mResult = false;
		if(mActivity.getId() >= Constants.ATLEAST_ONE){		
			mResult = true;
		}
		Assert.assertTrue(mResult,mProperties.getProperty("dao.activity.save"));
	}

	/**
	 * Try to create record with more then max length and it's expecting failure case,
	 */
	@Transactional
	@Test(expectedExceptions  = OUAException.class)
	public void createActivityWhenMoreThenLengthOfFields() throws Exception {
		Activity mActivity = new Activity();
	    mActivity.setDescription("This is testing length of description, This is testing length of description"
	    		+ "This is testing length of description, This is testing length of description,This is testing length of description"
	    		+ "This is testing length of description, This is testing length of description This is testing length of description");
	    activityDAO.add(mActivity);
	}
	
	/**
	 * List by Id and it's expecting success case,
	 */
	@Transactional
	@Test
	public void listById() throws Exception{
		Activity mActivity = new Activity();
		mActivity.setDescription("This is testing description from DAO UT List");
		activityDAO.add(mActivity);
		
		Activity tActivity = new Activity();
		tActivity.setId(mActivity.getId());
		List<Activity> mActivityList = activityDAO.list(tActivity);		
		boolean mResult = false;
		if(mActivityList.size() == Constants.ATLEAST_ONE){		
			mResult = true;
		}
		Assert.assertTrue(mResult,mProperties.getProperty("dao.activity.list"));
	}	
	
	/**
	 * List by wrong Id and it's expecting failure case,
	 */
	@Transactional
	@Test
	public void listByWorngId() throws Exception{
		Activity mActivity = new Activity();
		mActivity.setId(10000l);
		List<Activity> mActivityList = activityDAO.list(mActivity);		
		boolean mResult = false;
		if(mActivityList.size() == Constants.ZERO){		
			mResult = true;
		}
		Assert.assertTrue(mResult,mProperties.getProperty("dao.activity.list"));
	}
	
	/**
	 * List by description and it's expecting success case,
	 */
	@Transactional
	@Test
	public void listByDescription() throws Exception{
		Activity mActivity = new Activity();
		mActivity.setDescription("This is testing description from DAO UT List for Description Search");
		activityDAO.add(mActivity);
		
		Activity tActivity = new Activity();
		tActivity.setDescription("This is testing description from DAO UT List for Description Search");
		List<Activity> mActivityList = activityDAO.list(tActivity);		
		boolean mResult = false;
		if(mActivityList.size() >= Constants.ATLEAST_ONE){		
			mResult = true;
		}
		Assert.assertTrue(mResult,mProperties.getProperty("dao.activity.list"));
	}
	
	/**
	 * List by wrong description and it's expecting failure case,
	 */
	@Transactional
	@Test
	public void listByWorngDescription() throws Exception{
		Activity mActivity = new Activity();
		mActivity.setDescription("Wrong Description");
		List<Activity> mActivityList = activityDAO.list(mActivity);		
		boolean mResult = false;
		if(mActivityList.size() == Constants.ZERO){		
			mResult = true;
		}
		Assert.assertTrue(mResult,mProperties.getProperty("dao.activity.list"));
	}
	
	/**
	 * Delete with Id show it's expecting success case,
	 */
	@Transactional
	@Test
	public void deleteById() throws Exception{	   
		Activity mActivity = new Activity();
		mActivity.setDescription("This is testing description from DAO UT Delete");
		activityDAO.add(mActivity);
		boolean mReturn = activityDAO.delete(mActivity);
	    Assert.assertEquals(mReturn,true, mProperties.getProperty("dao.activity.delete"));
	}
	
	/**
	 * Delete without Id show it's expecting failure case,
	 */
	@Transactional
	@Test
	public void deleteWithoutId() throws Exception{	  
		Activity mActivity = new Activity();
		boolean mReturn = activityDAO.delete(mActivity);
	    Assert.assertEquals(mReturn,false, mProperties.getProperty("dao.activity.delete"));
	}   
}
