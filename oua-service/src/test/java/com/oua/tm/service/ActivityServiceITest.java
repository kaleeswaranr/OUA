/**
 * Test Package
 */
package com.oua.tm.service;



import java.util.List;
import java.util.Properties;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.transaction.annotation.Transactional;

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
	
	@Test
	@Transactional
	public void test1AddActivity() throws Exception {
		boolean mResult = false;
		Activity mActivity = (Activity)mContext.getBean("createActivityFromService");
	    mResult = mActivityService.save(mActivity);
	    Assert.assertNotEquals(mProperties.getProperty("activity.service.save"), mResult == true);
	}

	@Test
	@Transactional
	public void test2List() throws Exception {
		Activity mActivity = (Activity)mContext.getBean("createActivityFromService");
	    List<Activity> mtActivityList = mActivityService.list(mActivity);
	    Assert.assertNotEquals(mProperties.getProperty("activity.dao.list"), mtActivityList.size() > 1);
	}
	
	@Test
	@Transactional
	public void test3Delete() throws Exception {
		Activity mActivity = (Activity)mContext.getBean("createActivityFromService");
		boolean mReturn = mActivityService.delete(mActivity);
		 Assert.assertNotEquals(mProperties.getProperty("activity.dao.delete"), mReturn == true);
	}
}


