package com.ibm.core;

import org.apache.log4j.Logger;

public class Keywords {
	
	private static final Logger logger = Logger.getLogger(Keywords.class);
	
	public String click(String object){
		logger.info("click : "+object);
		return "PASS";
	}
	
	public String enterText(String object){
		logger.info("enterText : "+object);
		return "PASS";
	}

}
