/**
 * Test Package
 */
package com.oua.tm.service;

import static org.junit.Assert.assertTrue;

import java.util.Properties;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.oua.common.constants.Constants;
import com.oua.tm.persistence.model.Activity;

/**
 * This class serves the JUnit test cases for service layer of Activity.
 * 
 * @author Kaleeswaran R
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/unit-test-context.xml" })
public class ActivityServicesTest {
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
   private ActivityService mActivityService;  
   
   @Before
   public void setUp() {}
   
   
	@Transactional
	@Test(expected = RuntimeException.class)
	public void createActivityWhenMoreThenLengthOfFields() throws Exception {
		Activity mActivity = (Activity)mContext.getBean("createActivity");
	    mActivity.setDescription("This is testing length of description, This is testing length of description"
	    		+ "This is testing length of description, This is testing length of description,This is testing length of description"
	    		+ "This is testing length of description, This is testing length of description This is testing length of description");
	    Activity  mtActivity = mActivityService.save(mActivity);
	    assertTrue(mProperties.getProperty("service.activity.save"), mtActivity.getId() > 1);
	}
  
	@Transactional
	@Test
	public void createActivityWhenDescription() throws Exception{
		Activity mActivity = new Activity();
		mActivity.setDescription("This is testing length of description");
		mActivity.setDelFlag(Constants.YES_FLG);
		Activity mtActivity = mActivityService.save(mActivity);
   		assertTrue(mProperties.getProperty("service.activity.save"), mtActivity.getId() > 1);	   
	}
  
   	@Transactional
   	@Test
   	public void listById() throws Exception{
	   Activity mActivity = new Activity();
	   mActivity.setId(1l);
	   mActivityService.list(mActivity);
   	}
  
	@Transactional
	@Test
	public void listByDescription() throws Exception{
		Activity mActivity = new Activity();
		mActivity.setDescription("descTest");
		mActivityService.list(mActivity);	   
	}
  
  	@Transactional
  	@Test(expected = RuntimeException.class)
  	public void deleteWithoutId() throws Exception{	  
  		Activity mActivity = new Activity();
  		mActivityService.delete(mActivity);
  	}
  

  	@Transactional
	@Test(expected = RuntimeException.class)
	public void deleteById() throws Exception{	   
		Activity mActivity = new Activity();
		mActivity.setId(1l);
		mActivityService.delete(mActivity);
	}
  
}
