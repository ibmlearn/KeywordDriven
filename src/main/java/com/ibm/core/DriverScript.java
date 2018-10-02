package com.ibm.core;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

public class DriverScript {

	public String testStatus = "PASS";
	
	public String keyword_execution_result = null;
	
	Keywords keywords = new Keywords();
	BusinessKeywords businessKeywords = new BusinessKeywords();
	
	public void start(List<Map<String,String>> testCaseData){
		try{
			executeKeywords(testCaseData);
		}catch(Exception e){
			e.printStackTrace();
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
				keyword_execution_result = (String) method.invoke(keywords, parameters);
			}catch(NoSuchMethodException noSuchMethodException){
				method = businessKeywords.getClass().getMethod(eachKeyword, new Class[] { String.class} );
				keyword_execution_result = (String) method.invoke(businessKeywords, parameters);
			}catch(Exception e){
				e.printStackTrace();
			}
			if (keyword_execution_result.contains("FAIL")){
				//Capture screenshot
				testStatus = "FAIL";
			}
		}
	}
}
