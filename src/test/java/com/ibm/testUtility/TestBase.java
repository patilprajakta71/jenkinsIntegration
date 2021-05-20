package com.ibm.testUtility;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

public class TestBase {

	public static WebDriver driver;
	public static ExtentReports report;

	public static ExtentTest test;

	@BeforeSuite
	@Parameters("browser")
	public void init(String browser) {

		String currentDirectory = System.getProperty("user.dir");

		if (browser.equalsIgnoreCase("chrome")) {

			System.setProperty("webdriver.chrome.driver", currentDirectory + "/src/test/resources/chromedriver");

			driver = new ChromeDriver();
		} else {

			driver = new FirefoxDriver();
		}

		driver.get("https://www.moneycontrol.com/");

		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

	}

	

	public void explicitWait(WebElement element) {

		WebDriverWait wait = new WebDriverWait(driver, 20);

		wait.until(ExpectedConditions.elementToBeClickable(element));

	}

}
