package com.ibm.testScripts;

import org.testng.annotations.Test;

import com.ibm.testPages.MutualFundPage;
import com.ibm.testUtility.TestBase;

public class MutualFundTest extends TestBase {
	
	public MutualFundPage mutualFundPage;
	
	@Test
	public void checkForData() {
		mutualFundPage = new MutualFundPage();
		mutualFundPage.largeFund();
		
	}

}
