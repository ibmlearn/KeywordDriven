package com.ibm.core;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class DriverScript {

	private static final Logger logger = Logger.getLogger(DriverScript.class);
	
	public String testStatus = "PASS";
	private String keywordExecutionResult = null;
	
	Keywords keywords = new Keywords();
	BusinessKeywords businessKeywords = new BusinessKeywords();
	
	public void start(List<Map<String,String>> testCaseData){
		String userHome = System.getProperty("user.dir");
		PropertyConfigurator.configure(userHome + "\\src\\test\\resources\\log4j\\log4j.properties");
		try{
			executeKeywords(testCaseData);
		}catch(Exception e){
			logger.error(e);
		}
	}
	
	public void executeKeywords(List<Map<String,String>> testStepsList)throws NoSuchMethodException, IllegalAccessException, InvocationTargetException{
		//for every test step execute one keyword
		Method method = null;
		for(Map<String,String> eachTestStep : testStepsList){
			String eachKeyword = eachTestStep.get("Keyword");
			Object[] parameters = { eachTestStep.get("Object") };
			try{
				method = keywords.getClass().getMethod(eachKeyword, new Class[] { String.class} );
				keywordExecutionResult = (String) method.invoke(keywords, parameters);
			}catch(NoSuchMethodException noSuchMethodException){
				method = businessKeywords.getClass().getMethod(eachKeyword, new Class[] { String.class} );
				keywordExecutionResult = (String) method.invoke(businessKeywords, parameters);
			}catch(Exception e){
				logger.error(e);
			}
			if (keywordExecutionResult.contains("FAIL")){
				//Capture screenshot
				testStatus = "FAIL";
			}
		}
	}
}
