package com.oua.controller;

import java.util.List;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ValidationUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.oua.tm.common.constants.Constants;
import com.oua.tm.common.constants.JspPathMappingConstants;
import com.oua.tm.common.domain.ResponseJSON;
import com.oua.tm.common.exception.OUAException;
import com.oua.tm.persistence.model.Activity;
import com.oua.tm.service.ActivityService;

/**
 * This Controller is responsible to handle TODO item. TODO Services are injected into the
 * controller through Auto wiring.
 * 
 * @author Kaleeswaran.R
 * 
 */

@Controller
public class ActivityMaintenanceController {

   /**
    * Logger Name for this class.
    */
   private static final String LOGGING_CLASS_NAME = ActivityMaintenanceController.class.getName();
   /**
    * Logger for this class.
    */
   private static final Logger LOGGER = Logger.getLogger(LOGGING_CLASS_NAME);
   /**
    * Resource Bundle.
    */
   @Autowired
   private transient ReloadableResourceBundleMessageSource messageSource;


   @Autowired
   private transient ActivityService mActivityService;
   /**
    * Constants for Views.
    */
   
   @Autowired
   private transient JspPathMappingConstants mJspPathMappingConstants;
	
	/**
	* This method is invoked when a activity view page is requested by user. 
	* 
	* @param activity
	* @return List of activity.
	*/
  
	@RequestMapping("/user/activity/view")
	public final ModelAndView view(){
		LOGGER.entering(LOGGING_CLASS_NAME, " : view");
		LOGGER.info("Request for view page");
		final ModelAndView mModelAndView = new ModelAndView();
		try{
			mModelAndView.setViewName(mJspPathMappingConstants.ACTIVITY_VIEW);
			LOGGER.exiting(LOGGING_CLASS_NAME, " : view");
			return mModelAndView;
		}
		catch (Exception mException) {
			LOGGER.log(Level.SEVERE, "There is unknown exception from ActivityMaintenanceController when getting the activity view", mException);
			return mModelAndView;
		}
	}   
   
	
	/**
	* This method is invoked when a list of activity is requested by user. Activity model object specified as parameter,
	* which is used for filter the activity. if all attributes are null, it returns all activities, otherwise it will be fetch
	* the record based on the filter.
	* 
	* @param activity
	* @return List of activity.
	*/
	@RequestMapping(value = "/user/activity/list", method = RequestMethod.POST)
	@ResponseBody
	public final ResponseJSON list(Activity pActivity){
		LOGGER.entering(LOGGING_CLASS_NAME, " : list");
		LOGGER.info("Request for list page");
		final ResponseJSON mResponseJSON = new ResponseJSON();
		List<Activity> mActivityList = null;
		try{
			mActivityList = mActivityService.list(pActivity);
			mResponseJSON.setMessage(messageSource.getMessage("general.list.success", null, Locale.getDefault()));
			mResponseJSON.setResult(mActivityList);
			mResponseJSON.setStatus(Constants.SUCCESS);
			LOGGER.exiting(LOGGING_CLASS_NAME, " : list");
		}
		catch (Exception mException) {
			LOGGER.log(Level.SEVERE, "Unable to retrieve search results for activity", mException);
			LOGGER.logrb(Level.SEVERE, LOGGING_CLASS_NAME, "list", "errorMessages", "general.search.errorMessage");
			mResponseJSON.setStatus(Constants.FAILURE);
			mResponseJSON.setMessage(messageSource.getMessage("general.search.errorMessage", null, Locale.getDefault()));
			mResponseJSON.setResult(null);
		}
		return mResponseJSON;
	}   
	   
   	/**
	* This method is invoked when add activity requested by user. activity model object specified as parameter, which is used
	* for input the activity information for create. It returns created activity.
	* 
	* @param activity {@link activity}
	* @param request HttpServletRequest
	* 
	* @return JSON data {@link ResponseJSON}
	*/
	@RequestMapping(value = "/user/activity/add", method = RequestMethod.POST)
	@ResponseBody
	public final ResponseJSON add(HttpServletRequest request, HttpSession session, @RequestBody final Activity pActivity,final BindingResult pResults) {
		final ResponseJSON mResponseJSON = new ResponseJSON();
		try {
			LOGGER.entering(LOGGING_CLASS_NAME, " : add");
			LOGGER.info("Request for add activity");
			
			ValidationUtils.rejectIfEmptyOrWhitespace(pResults, "description",messageSource.getMessage("mandatory.activity.description", null, request.getLocale()));
	        if (pResults.hasErrors()){
	        	mResponseJSON.setStatus(Constants.FAILURE);
	        	mResponseJSON.setMessage(messageSource.getMessage("fail.input.validation", null, request.getLocale()));
	        	mResponseJSON.setResult(pResults.getAllErrors());
	        }
	        else{
				mActivityService.save(pActivity);			
				List<Activity> mActivityList = mActivityService.list(pActivity);			
				mResponseJSON.setMessage(messageSource.getMessage("general.save.success", null, Locale.getDefault()));
				mResponseJSON.setResult(mActivityList);
				mResponseJSON.setStatus(Constants.SUCCESS);
	        }
		}
		catch (OUAException mOUAException) {
			mResponseJSON.setResult(null);
			LOGGER.log(Level.SEVERE, "Unable to save activity", mOUAException);
			LOGGER.logrb(Level.SEVERE, LOGGING_CLASS_NAME, "Add", "errorMessages", mOUAException.getMessage());
			mResponseJSON.setStatus(Constants.FAILURE);
			mResponseJSON.setMessage(messageSource.getMessage(mOUAException.getKey(), null, Locale.getDefault()));			
			return mResponseJSON;
		}
		catch (Exception mException) {
			mResponseJSON.setResult(null);
			LOGGER.log(Level.SEVERE, "Unable to save activity", mException);
			LOGGER.logrb(Level.SEVERE, LOGGING_CLASS_NAME, "Add", "errorMessages", "general.save.errorMessage");
			mResponseJSON.setStatus(Constants.FAILURE);
			mResponseJSON.setMessage(messageSource.getMessage("general.save.errorMessage", null, Locale.getDefault()));			
			return mResponseJSON;
		}
		return mResponseJSON;
	}
	
	
	
	
	/**
	* This method is invoked when delete TODO item requested by user. activity model object specified as parameter, which is used
	* for input the activity information for delete. It returns once it's deleted activity.
	* 
	* @param activity {@link activity}
	* @param request HttpServletRequest
	* 
	* @return JSON data {@link ResponseJSON}
	*/
	@RequestMapping(value = "/user/activity/delete", method = RequestMethod.POST)
	@ResponseBody
	public final ResponseJSON delete(HttpServletRequest request, HttpSession session, @RequestBody final Activity pActivity) {
		final ResponseJSON mResponseJSON = new ResponseJSON();
		try {
			LOGGER.entering(LOGGING_CLASS_NAME, " : delete");
			LOGGER.info("Request for add activity");
			mActivityService.delete(pActivity);
			List<Activity> mActivityList = mActivityService.list(pActivity);			
			mResponseJSON.setMessage(messageSource.getMessage("general.delete.success", null, Locale.getDefault()));
			mResponseJSON.setResult(mActivityList);
			mResponseJSON.setStatus(Constants.SUCCESS);
		} 
		catch (Exception mException) {
			mResponseJSON.setResult(null);
			LOGGER.log(Level.SEVERE, "Unable to delete activity", mException);
			LOGGER.logrb(Level.SEVERE, LOGGING_CLASS_NAME, "delete", "errorMessages", "general.delete.errorMessage");
			mResponseJSON.setStatus(Constants.FAILURE);
			mResponseJSON.setMessage(messageSource.getMessage("general.delete.errorMessage", null, Locale.getDefault()));	
			return mResponseJSON;
		}
		return mResponseJSON;
	}
}
