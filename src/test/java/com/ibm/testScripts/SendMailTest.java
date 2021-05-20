package com.ibm.testScripts;

import org.testng.annotations.Test;

import com.ibm.testPages.SendMailPage;
import com.ibm.testUtility.TestBase;

public class SendMailTest extends TestBase{
	
	public SendMailPage sendMailPage = new SendMailPage();
	
	@Test
	
	public void sendMail() throws InterruptedException
	{
		sendMailPage.differenceCalculation();
	}
}
