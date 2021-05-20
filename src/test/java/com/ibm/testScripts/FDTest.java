package com.ibm.testScripts;

import java.io.IOException;

import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import com.ibm.testPages.FDPage;
import com.ibm.testUtility.TestBase;

public class FDTest extends TestBase {
	
	public FDPage fdPage = new FDPage();

	@Test
	public void calculateFDTest() throws IOException {
		
		//fdPage.calculateFixedIncome();
		fdPage.sendMail();
	}
	
	
}
