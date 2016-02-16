/**
 * Test Package
 */
package com.oua.tm.service;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

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
   /**
    * String literal to hold locale code for English.
    */
   private static final String LOCALE_EN_GB = "en_GB";

   /**
    * String literal to hold locale code for Reverse English.
    */
   private static final String LOCALE_EN_BG = "en_BG";

   /**
    * String literal to hold locale code for French.
    */
   private static final String LOCALE_EN_FR = "en_FR";

   /**
    * Logger Name for this class.
    */
   private static final String LOGGING_CLASS_NAME = ActivityServicesTest.class.getName();
   /**
    * Logger for this class.
    */
   private static final Logger LOGGER = Logger.getLogger(LOGGING_CLASS_NAME);

	
   private Activity mActivity;
	
   @Before
	public void setUp() {
	   mActivity = (Activity)mContext.getBean("createActivity");
	}
   
   
   @Test
	public void testToCreateProject() throws Exception {
	    mActivity= mActivityService.save(mActivity);
		assertTrue(mProperties.getProperty("service.activity.save"), mActivity.getId() == 1);
	}
}
