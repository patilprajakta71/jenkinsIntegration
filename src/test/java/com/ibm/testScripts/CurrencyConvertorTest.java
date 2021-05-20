package com.ibm.testScripts;

import java.io.IOException;

import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.ibm.testPages.CurrencyConvertorPage;
import com.ibm.testUtility.TestBase;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

public class CurrencyConvertorTest extends TestBase {
	
	
	
	@BeforeClass
	public static void startTest() {
		report = new ExtentReports(System.getProperty("user.dir") + "/ExtentReportResults.html");
		test = report.startTest("ExtentDemo");
	}
	
	
	public CurrencyConvertorPage currencyConvertorPage=new CurrencyConvertorPage();
	
	@Test
	public void captureScreenShot() throws IOException {
		
		currencyConvertorPage.convertor();
		currencyConvertorPage.sendMail();
	}
	
	
}
