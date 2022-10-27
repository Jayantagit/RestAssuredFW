package com.krogerqa.service.scenarios;

import java.util.Map;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Utitility.LoadExcelData;

public class seedTest {

	public static final String TEST_DATA_FILE = "seedTest.xlsx";
	public static final String TEST_DATA_SHEET = "Functional";
	
	@DataProvider
	public Object[] seedData()
	{
		return LoadExcelData.getEnabledExcelTests(TEST_DATA_FILE, TEST_DATA_SHEET);
	}
	
	@Test(dataProvider="seedData")
	public void seedTest(Map<String,String> inputDataMap)
	{
		System.out.println(inputDataMap);
		System.out.println("Map --records");
		for(Map.Entry<String, String> m: inputDataMap.entrySet())
		{
	       System.out.print(m.getKey()+":"+m.getValue()+" ");
	       System.out.println();
		}
		
	}
	

}
