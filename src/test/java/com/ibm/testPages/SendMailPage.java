package com.ibm.testPages;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.ibm.testUtility.TestBase;

public class SendMailPage extends TestBase {
	
	public WebElement commodityLink;
	public List<WebElement> commodityList;
	
	public List<Double> highValueCommodityList = new ArrayList<Double>();
	
	

	public void differenceCalculation() throws InterruptedException {
		commodityLink = driver.findElement(By.linkText("Commodities"));
		commodityLink.click();
		
		commodityList = driver.findElements(By.xpath("//table[@class='mctable1']/tbody/tr/td[1]/a"));
		
		String currentWindow = driver.getWindowHandle();
		
		explicitWait(commodityList.get(1));
		
		for(int i = 0;i<commodityList.size();i++) {
			
			commodityList.get(i).click();
	
			
			String currentWindow1 = driver.getWindowHandle();
			
			for (String handle : driver.getWindowHandles()) {
			    if (!handle.equals(currentWindow)) {
			        driver.switchTo().window(handle);
			   
			    }
			}
			
			WebElement highValueCommodity = driver.findElement(By.xpath("//div[@id='commodity_innertab0']/table/tbody/tr/td/div[2]/div[@class='FL PR20 w135']"));
		
			explicitWait(highValueCommodity);
			
			String [] str = highValueCommodity.getText().split(" ");
			
		
			
			System.out.println(highValueCommodity.getText());
			
			System.out.println(highValueCommodity.getAttribute("textContent"));
			System.out.println(highValueCommodity.getAttribute("innerHTML"));
			
			
			highValueCommodityList.add(Double.parseDouble(str[3].replaceAll(",", "")));
			driver.close();
			
			driver.switchTo().window(currentWindow);
			    
			
	
		}
		
		
	}
}
