/**
 * Test Package
 */
package com.oua.tm.persistence.dao;

import java.util.List;
import java.util.Properties;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.transaction.annotation.Transactional;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.oua.tm.common.constants.Constants;
import com.oua.tm.persistence.model.Activity;

/**
 * This class serves the Integration test cases for persistence layer of Activity.
 * 
 * @author Kaleeswaran R
 */
@Test
@ContextConfiguration(locations = {"/unit-dao-test-context.xml" })
public class ActivityDAOITest extends AbstractTestNGSpringContextTests {
	
	private static final Logger LOGGER = Logger.getLogger(ActivityDAOITest.class.getName());	
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
	
	private Activity mActivity= null;
	
	@BeforeClass
	public void setUp() {
		mActivity = (Activity)mContext.getBean("createActivityFromDAO");
	}
	
	/**
	 * Create Record
	 */
	@Test()
	@Transactional
	public void test1AddActivity() throws Exception {
		LOGGER.info("Enter into the test1AddActivity from "+LOGGER.getName());
		activityDAO.add(mActivity);
		boolean mResult = false;
		if(mActivity.getId() == Constants.ATLEAST_ONE){		
			mResult = true;
		}
		Assert.assertTrue(mResult,mProperties.getProperty("dao.activity.save"));
		LOGGER.info("Exit into the test1AddActivity from "+LOGGER.getName());
	}
	
	/**
	 * List Record
	 */
	@Test
	@Transactional
	public void test2SearchById() throws Exception {
		LOGGER.info("Enter into the test2SearchById from "+LOGGER.getName());
		List<Activity> mtActivityList = activityDAO.list(mActivity);
		boolean mResult = false;
		if(mtActivityList.size() == Constants.ATLEAST_ONE){		
			mResult = true;
		}
		Assert.assertTrue(mResult,mProperties.getProperty("dao.activity.list"));
		LOGGER.info("Exit into the test2SearchById from "+LOGGER.getName());
	}
	
	/**
	 * Delete Record
	 */
	@Test
	@Transactional
	public void test3Delete() throws Exception {
		LOGGER.info("Enter into the test3Delete from "+LOGGER.getName());
		boolean mReturn = activityDAO.delete(mActivity);
	    Assert.assertEquals(mReturn,true, mProperties.getProperty("dao.activity.delete"));
	    LOGGER.info("Exit into the test3Delete from "+LOGGER.getName());
	}
}


