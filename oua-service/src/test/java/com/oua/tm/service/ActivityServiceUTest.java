/**
 * Test Package
 */
package com.oua.tm.service;



import java.util.List;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.transaction.annotation.Transactional;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.oua.tm.common.constants.Constants;
import com.oua.tm.persistence.model.Activity;

/**
 * This class serves the Integration test cases for persistence layer of Activity.
 * 
 * @author Kaleeswaran R
 */
@Test
@ContextConfiguration(locations = {"/unit-service-test-context.xml" })
public class ActivityServiceUTest extends AbstractTestNGSpringContextTests {
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
	private ActivityService mActivityService;
	
	@Transactional
	@Test
	public void createActivity() throws Exception{
		Activity mActivity = new Activity();
		mActivity.setDescription("This is testing description");
		mActivity.setDelFlag(Constants.YES_FLG);
		mActivityService.save(mActivity);
		Assert.assertNotEquals(mProperties.getProperty("service.activity.save"), mActivity.getId() > 1);	   
	}

	@Transactional
	@Test
	public void listByDescription() throws Exception{
		Activity mActivity = new Activity();
		mActivity.setDescription("This is testing description");
		List<Activity> mActivityList = mActivityService.list(mActivity);
		Assert.assertNotEquals(mProperties.getProperty("service.activity.list"), mActivityList.size() > 0);
	}
	
	@Transactional
	@Test
	public void logicalDelete() throws Exception{	   
		Activity mActivity = new Activity();
		mActivity.setId(1l);
		boolean mReturn = mActivityService.delete(mActivity);
	    Assert.assertNotEquals(mProperties.getProperty("service.dao.delete"), mReturn == false);
	}
}
