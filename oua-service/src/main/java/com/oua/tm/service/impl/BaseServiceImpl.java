/* @(#)BaseServiceImpl.java 1.0 02/16
 * Copyright (c) Open Universities Australia 2016
 */

package com.oua.tm.service.impl;

import java.util.logging.Logger;

import com.oua.tm.service.BaseService;

/**
 * This class represents the base service which is available to be used in this application.
 * 
 * @author Kaleeswaran R
 */

public class BaseServiceImpl implements BaseService {

	private static final String LOGGING_CLASS_NAME = BaseServiceImpl.class
			.getName();

	/**
	 * Logger for this class.
	 */
	private static final Logger LOGGER = Logger.getLogger(LOGGING_CLASS_NAME);

}
