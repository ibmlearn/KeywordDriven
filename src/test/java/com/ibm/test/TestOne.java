package com.ibm.test;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.ibm.core.DriverScript;

public class TestOne {
	
	private static final Logger logger = Logger.getLogger(TestOne.class);
	
	@Test( dataProvider="testCaseData")
	public void test(List<Map<String,String>> testCaseData){
		System.out.println(testCaseData);
		DriverScript test = new DriverScript();
		try {
			test.start(testCaseData);
			Assert.assertEquals(test.testStatus.equalsIgnoreCase("PASS"), true);
		} catch (Exception e) {
			logger.error(e);
		} finally {
			if (null != test) {
				test = null;
			}
		}
	}
	
	@DataProvider(name = "testCaseData")
	public Iterator<Object[]> getData(Method method, ITestContext context){
		System.out.println(method.getName());
		System.out.println(context.getName());
		List<Map<String,String>> testCaseData = new ArrayList<Map<String,String>>();
		Map<String,String> firstTestStep = new HashMap<String,String>();
		firstTestStep.put("S.No","1");
		firstTestStep.put("Keyword","click");
		firstTestStep.put("Object","clickObject");
		testCaseData.add(firstTestStep);
		Map<String,String> secondTestStep = new HashMap<String,String>();
		secondTestStep.put("S.No","2");
		secondTestStep.put("Keyword","enterText");
		secondTestStep.put("Object","enterTextObject");
		testCaseData.add(secondTestStep);
		Map<String,String> thirdTestStep = new HashMap<String,String>();
		thirdTestStep.put("S.No","3");
		thirdTestStep.put("Keyword","verifySomeLogic");
		thirdTestStep.put("Object","verifySomeLogicObject");
		testCaseData.add(thirdTestStep);
		Collection<Object[]> collection = new ArrayList<Object[]>();
		collection.add(new Object[] {testCaseData});
		return collection.iterator();
	}

}
