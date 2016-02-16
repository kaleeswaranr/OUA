/**
 * Test Package
 */
package com.oua.tm.persistence.dao;

import static org.junit.Assert.assertTrue;

import java.util.Properties;
import java.util.logging.Logger;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.oua.common.constants.Constants;
import com.oua.tm.persistence.model.Activity;

/**
 * This class serves the JUnit test cases for service layer of Activity.
 * 
 * @author Kaleeswaran R
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/unit-test-context.xml" })
public class ActivityDAOTest {
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
   //private ActivityDAO activityDAO;
   private ActivityDAO activityDAO;
	
   public void setActivityDAO(ActivityDAO mActivityDAO) {
       this.activityDAO = mActivityDAO;
   }
   /**
    * String literal to hold locale code for English.
    */
   private static final String LOCALE_EN_GB = "en_GB";

   private static final String LOGGING_CLASS_NAME = ActivityDAOTest.class.getName();
   /**
    * Logger for this class.
    */
   private static final Logger LOGGER = Logger.getLogger(LOGGING_CLASS_NAME);
	   
   @Before
	public void setUp() {
		//mActivity = (Activity)mContext.getBean("createActivity");	
	}   
      
   @Transactional
   @Test(expected = RuntimeException.class)
   public void createActivityWhenMoreThenLengthOfFields() throws Exception {
	    Activity mActivity = (Activity)mContext.getBean("createActivity");	
	    mActivity.setDescription("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA"
	    		+ "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA"
	    		+ "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
	    Activity  mtActivity = activityDAO.add(mActivity);
	    assertTrue(mProperties.getProperty("service.activity.save"), mtActivity.getId() > 1);
	}
   
   @Transactional
   @Test
   public void createActivityWhenDescription() throws Exception{	   
	    Activity mActivity= (Activity)mContext.getBean("createActivity");
	    Activity mtActivity = activityDAO.add(mActivity);
	    assertTrue(mProperties.getProperty("service.activity.save"), mtActivity.getId() > 1);
	}
   
   @Transactional
   @Test
   public void createActivityWhenDeleteFlagChanges() throws Exception{
	    Activity mActivity= (Activity)mContext.getBean("createActivity");
	    mActivity.setDelFlag(Constants.YES_FLG);
	    Activity  mtActivity = activityDAO.add(mActivity);
	    assertTrue(mProperties.getProperty("service.activity.save"), mtActivity.getId() > 1);
	}
   
   @Transactional
   @Test(expected = RuntimeException.class)
   public void deleteWithoutId() throws Exception{
	    Activity mActivity = new Activity();
	    activityDAO.delete(mActivity);
	}
   
   @Transactional
   @Test(expected = RuntimeException.class)
   public void deleteById() throws Exception{
	    Activity mActivity = new Activity();
	    mActivity.setId(30l);
	    activityDAO.delete(mActivity);
	}
   
   @Transactional
   @Test(expected = RuntimeException.class)
   public void listById() throws Exception{
	    Activity mActivity = new Activity();
	    mActivity.setId(30l);
	    activityDAO.list(mActivity);
   }
   
   @Transactional
   @Test(expected = RuntimeException.class)
   public void listByDescription() throws Exception{
	    Activity mActivity = new Activity();
	    mActivity.setDescription("descTest");
	    activityDAO.list(mActivity);
	}
}


