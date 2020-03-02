package com.ibm.core;

import org.apache.log4j.Logger;

public class BusinessKeywords {

	private static final Logger logger = Logger.getLogger(BusinessKeywords.class);
	
	public String verifySomeLogic(String object){
		logger.info("verifySomeLogic : "+object);
		return "PASS";
	}
	
}
