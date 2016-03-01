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
	
	@Transactional
	@Test
	public void createActivityWithDescription() throws Exception{
		Activity mActivity = new Activity();
		mActivity.setDescription("This is testing description");
		mActivity.setDelFlag(Constants.YES_FLG);
		activityDAO.add(mActivity);
		Assert.assertNotEquals(mProperties.getProperty("dao.activity.save"), mActivity.getId() > 1);	   
	}

	@Transactional
	@Test(expectedExceptions  = RuntimeException.class)
	public void createActivityWhenMoreThenLengthOfFields() throws Exception {
		Activity mActivity = new Activity();		
	    mActivity.setDescription("This is testing length of description, This is testing length of description"
	    		+ "This is testing length of description, This is testing length of description,This is testing length of description"
	    		+ "This is testing length of description, This is testing length of description This is testing length of description");
	    activityDAO.add(mActivity);
	}
	
	@Transactional
	@Test
	public void listById() throws Exception{
		Activity mActivity = new Activity();
		mActivity.setDescription("This is testing description");
		List<Activity> mActivityList = activityDAO.list(mActivity);
		Assert.assertNotEquals(mProperties.getProperty("dao.activity.list"), mActivityList.size() > 0);
	}
	
	
	
	@Transactional
	@Test
	public void deleteById() throws Exception{	   
		Activity mActivity = new Activity();
		mActivity.setId(1l);
		boolean mReturn = activityDAO.delete(mActivity);
	    Assert.assertNotEquals(mProperties.getProperty("activity.dao.delete"), mReturn == false);
	}
	
	@Transactional
	@Test(expectedExceptions = RuntimeException.class)
	public void deleteWithoutId() throws Exception{	  
		Activity mActivity = new Activity();
		activityDAO.delete(mActivity);
	}
	   
}
