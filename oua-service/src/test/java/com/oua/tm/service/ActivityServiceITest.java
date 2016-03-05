/**
 * Test Package
 */
package com.oua.tm.service;



import java.util.List;
import java.util.Properties;
import java.util.logging.Logger;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.transaction.annotation.Transactional;

import com.oua.tm.common.constants.Constants;
import com.oua.tm.persistence.dao.ActivityDAO;
import com.oua.tm.persistence.model.Activity;

/**
 * This class serves the Integration test cases for persistence layer of Activity.
 * 
 * @author Kaleeswaran R
 */
@Test
@ContextConfiguration(locations = {"/unit-service-test-context.xml" })
public class ActivityServiceITest extends AbstractTestNGSpringContextTests {
	
	private static final Logger LOGGER = Logger.getLogger(ActivityServiceITest.class.getName());
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
    * The @Autowired annotation is auto wire the HierarchyService.
    */
	@Autowired
   	private ActivityDAO activityDAO;
	
	@Autowired
	private ActivityService mActivityService;  
	
	private Activity mActivity= null;
	
	@BeforeClass
	public void setUp() {
		mActivity = (Activity)mContext.getBean("createActivityFromService");
	}
	
	@Test
	@Transactional
	public void test1AddActivity() throws Exception {
		LOGGER.info("Enter into the test1AddActivity from "+LOGGER.getName());
		boolean mResult = false;		
	    mResult = mActivityService.save(mActivity);
		if(mActivity.getId() >= Constants.ATLEAST_ONE){		
			mResult = true;
		}
		Assert.assertTrue(mResult,mProperties.getProperty("service.activity.save"));
	    LOGGER.info("Exit into the test1AddActivity from "+LOGGER.getName());
	}

	@Test
	@Transactional
	public void test2List() throws Exception {
		LOGGER.info("Enter into the test2List from "+LOGGER.getName());	     
	    List<Activity> mtActivityList = mActivityService.list(mActivity);
		boolean mResult = false;
		if(mtActivityList.size() == Constants.ATLEAST_ONE){		
			mResult = true;
		}
		Assert.assertTrue(mResult,mProperties.getProperty("service.activity.list"));
		LOGGER.info("Exit into the test2List from "+LOGGER.getName());
	}
	
	@Test
	@Transactional
	public void test3Delete() throws Exception {
		LOGGER.info("Enter into the test3Delete from "+LOGGER.getName());
		boolean mReturn = mActivityService.delete(mActivity);
		Assert.assertEquals(mReturn,true, mProperties.getProperty("service.activity.delete"));
		LOGGER.info("Exit into the test3Delete from "+LOGGER.getName());		
	}
}


