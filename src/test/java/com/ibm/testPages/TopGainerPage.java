package com.ibm.testPages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import com.ibm.testUtility.TestBase;

public class TopGainerPage extends TestBase{
	
	public WebElement seeAllTopGainer;
	public List<WebElement> gainValue;
	List<String> companyNameList = new ArrayList<String>();

	
	public void captureList() {
		seeAllTopGainer = driver.findElement(By.xpath("//*[@id='tgNifty']/div[2]/a"));
		
		explicitWait(seeAllTopGainer);
		
		JavascriptExecutor js = (JavascriptExecutor) driver;
		
		js.executeScript("arguments[0].scrollIntoView(true);", seeAllTopGainer);
		
		Actions action = new Actions(driver);
		
		action.moveToElement(seeAllTopGainer).perform();
		seeAllTopGainer.click();
		
		
		gainValue = driver.findElements(By.xpath("//div[@class='bsr_table hist_tbl_hm']/table/tbody/tr/td[7]"));
		
	
		explicitWait(gainValue.get(1));
		
		for(int i =0 ;i<gainValue.size();i++) {
			
			Double val = (Double.parseDouble(gainValue.get(i).getText()));
			
			if(val>1.0) {
				
				int index = i ;
				
				WebElement companyname = driver.findElement(By.xpath("//div[@class='bsr_table hist_tbl_hm']/table/tbody/tr["+(i+1)+"]/td[1]"));
			
				
				companyNameList.add(companyname.getText());
				
			
			}
			
			
		}
		
		System.out.println(companyNameList);
		
		
	}
}
