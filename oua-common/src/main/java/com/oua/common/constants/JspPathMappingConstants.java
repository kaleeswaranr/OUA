/* @(#)JspPathMappingConstants.java 1.0 02/16
 * Copyright (c) Open Universities Australia 2016
 */

package com.oua.common.constants;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * This class represents the constants to be used in various controller classes used in this application. This is not
 * intended for General constants used in the application.
 * 
 * The constants should be named according to the view/page which is being represented and should be self explanatory.
 *
 * @author Kaleeswaran R
 */

@Component
public final class JspPathMappingConstants {
	
	/* Note:Check Style/PMD/findbug errors for variables are ignored for the 
	* public variables since they have to be public and accessed through annotations.
	*/
	
	/** Constant representing dashboard view. */
	@Value("${path.home.dashboard}")
	public String DASHBOARD;  
   
	/** Constant representing activity list view. */
	@Value("${path.user.activity.view}")
	public String ACTIVITY_VIEW;  
   
	/** Instance instantiated at the class loading. */
	private static JspPathMappingConstants constantsInstance = new JspPathMappingConstants();

	/** Private constructor to prevent Instantiation. */   
	private JspPathMappingConstants(){ 
		// Do nothing! 
	}
   
	/**
    * Returns the instance.
    * @return JspPathMappingConstants
    */
	public static JspPathMappingConstants getInstance(){
		return constantsInstance;
	}
}
