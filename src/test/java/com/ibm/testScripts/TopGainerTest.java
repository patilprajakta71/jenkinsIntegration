package com.ibm.testScripts;

import org.testng.annotations.Test;

import com.ibm.testPages.TopGainerPage;
import com.ibm.testUtility.TestBase;

public class TopGainerTest extends TestBase {
	
	public TopGainerPage topGainerPage ;
	
	@Test
	public void listCreateTest() {
		topGainerPage = new TopGainerPage();
		topGainerPage.captureList();
		
	}

}
