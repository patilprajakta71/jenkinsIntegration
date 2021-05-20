package com.ibm.testPages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.ibm.testUtility.TestBase;

public class MutualFundPage extends TestBase {

	public WebElement mutualFundLink;
	public WebElement largeCapFundLink;
	public List<WebElement> weekData;
	public List<String> companyNameList = new ArrayList<String>();

	public void largeFund() {

		mutualFundLink = driver.findElement(By.linkText("Mutual Funds"));
		explicitWait(mutualFundLink);
		mutualFundLink.click();
		largeCapFundLink = driver.findElement(By.linkText("Large Cap Fund »"));
		explicitWait(largeCapFundLink);
		largeCapFundLink.click();
		weekData = driver.findElements(By.xpath("//table[@id='dataTableId']/tbody/tr/td[6]"));

		for (int i = 0; i < weekData.size(); i++) {
			
			String[] parseValue = weekData.get(i).getText().split("%");

			Double val = (Double.parseDouble(parseValue[0]));

			if (val > 1.0 && val<2.0) {

				int index = i;

				WebElement companyname = driver.findElement(
						By.xpath("//table[@id='dataTableId']/tbody/tr[" + (i + 1) + "]"));

				companyNameList.add(companyname.getText());

			}

		}
		
		System.out.println(companyNameList);

	}

}
