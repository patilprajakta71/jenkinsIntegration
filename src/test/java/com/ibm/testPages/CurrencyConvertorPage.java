package com.ibm.testPages;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import com.ibm.testUtility.TestBase;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class CurrencyConvertorPage extends TestBase {
	
	public WebElement inputCurrency;
	

	
	
	public void convertor() throws IOException {
	
	JavascriptExecutor js = (JavascriptExecutor) driver;
	
	WebElement currencyField = driver.findElement(By.linkText("Currencies"));
	
	explicitWait(currencyField);
	
	js.executeScript("arguments[0].scrollIntoView(true);",currencyField);
	
	currencyField.click();
	
	inputCurrency = driver.findElement(By.xpath("//input[@id='rs_input']"));
	
	explicitWait(inputCurrency);
	
	inputCurrency.sendKeys("100");
	
	Select select = new Select(driver.findElement(By.name("currency")));
	
	List<WebElement> options = select.getOptions();
	
	
		for(int i=0;i<options.size()-1;i++) {
			
			options.get(i+1).click();
			driver.findElement(By.xpath("//input[@class='convertLink']")).click();
			
			test.log(LogStatus.INFO,test.addScreenCapture(capture(driver))+ "Test Failed");
			
			
		}
		
		report.flush();
		report.close();
	
	}
	public static String capture(WebDriver driver) throws IOException {
		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		File Dest = new File("src/../ErrImages/" + System.currentTimeMillis()
		+ ".png");
		String errflpath = Dest.getAbsolutePath();
		FileUtils.copyFile(scrFile, Dest);
		return errflpath;
		}
	
	
	public void sendMail() {
	Properties props = new Properties();

	// this will set host of server- you can change based on your requirement
	props.put("mail.smtp.host", "smtp.gmail.com");

	// set the port of socket factory
	props.put("mail.smtp.socketFactory.port", "465");

	// set socket factory
	props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");

	// set the authentication to true
	props.put("mail.smtp.auth", "true");

	// set the port of SMTP server
	props.put("mail.smtp.port", "465");

	// This will handle the complete authentication
	Session session = Session.getDefaultInstance(props,

			new javax.mail.Authenticator() {

				protected PasswordAuthentication getPasswordAuthentication() {

					return new PasswordAuthentication("contacttopraju@gmail.com", "Testing@1993");

				}

			});

	try {

		// Create object of MimeMessage class
		Message message = new MimeMessage(session);

		// Set the from address
		message.setFrom(new InternetAddress("patilprajakta71@gmail.com"));

		// Set the recipient address
		message.setRecipients(Message.RecipientType.TO, InternetAddress.parse("patilprajakta71@gmail.com"));

		// Add the subject link
		message.setSubject("Testing Subject");

		// Create object to add multimedia type content
		BodyPart messageBodyPart1 = new MimeBodyPart();

		// Set the body of email
		messageBodyPart1.setText("This is message body");

		// Create another object to add another content
		MimeBodyPart messageBodyPart2 = new MimeBodyPart();

		// Mention the file which you want to send
		String filename = System.getProperty("user.dir") + "/ExtentReportResults.html";

		// Create data source and pass the filename
		DataSource source = new FileDataSource(filename);

		// set the handler
		messageBodyPart2.setDataHandler(new DataHandler(source));

		// set the file
		messageBodyPart2.setFileName(filename);

		// Create object of MimeMultipart class
		Multipart multipart = new MimeMultipart();

		// add body part 1
		multipart.addBodyPart(messageBodyPart2);

		// add body part 2
		multipart.addBodyPart(messageBodyPart1);

		// set the content
		message.setContent(multipart);

		// finally send the email
		Transport.send(message);

		System.out.println("=====Email Sent=====");

	} catch (MessagingException e) {

		throw new RuntimeException(e);

	}

}
		

}
