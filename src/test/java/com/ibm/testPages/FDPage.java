package com.ibm.testPages;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

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

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;

import com.ibm.testUtility.TestBase;

public class FDPage extends TestBase {

	public WebElement fixedIncomeLink;
	public WebElement typesBankTab;
	public WebElement filterCheck;
	public WebElement applyFilterBtn;

	List<WebElement> tableListRow = new ArrayList<WebElement>();
	List<WebElement> tableListCell = new ArrayList<WebElement>();

	public void calculateFixedIncome() throws IOException {

		fixedIncomeLink = driver
				.findElement(By.xpath("//div[@class='FL footleft']/div/ul/li/a[@title='Fixed Income']"));
		explicitWait(fixedIncomeLink);

		JavascriptExecutor jse = (JavascriptExecutor) driver;

		jse.executeScript("arguments[0].scrollIntoView(true);", fixedIncomeLink);

		fixedIncomeLink.click();

		typesBankTab = driver.findElement(By.xpath("//div[@id='common_accordion']//li[@class='active tabactive']/a"));

		explicitWait(typesBankTab);

		typesBankTab.click();

		filterCheck = driver.findElement(
				By.xpath("//ul[@id='type_of_bank_ul']/li/label/input[@id='bank_type1']/following-sibling::span"));

		explicitWait(filterCheck);

		filterCheck.click();

		applyFilterBtn = driver.findElement(By.linkText("Apply Filter"));

		applyFilterBtn.click();

		tableListRow = driver.findElements(By.xpath("//div[@id='regular']//table[@id='fdResultsTableAll']//tr"));

		FileInputStream fis = new FileInputStream("/Users/prajakta/Desktop/Table.xlsx");

		XSSFWorkbook wf = new XSSFWorkbook(fis);

		XSSFSheet sheet = wf.getSheet("Sheet1");

		for (int i = 1; i < tableListRow.size(); i++) {

			tableListCell = tableListRow.get(i).findElements(By.tagName("td"));

			XSSFRow row = sheet.createRow(i);

			for (int j = 0; j < tableListCell.size(); j++) {

				XSSFCell cell = row.createCell(j);

				if (j > 0) {

					cell.setCellValue(tableListCell.get(j).getText());

					System.out.println(tableListCell.get(j).getText());
				} else {

					String bankName = tableListCell.get(j).findElement(By.tagName("a")).getText();

					cell.setCellValue(bankName);
				}

			}

		}

		FileOutputStream fos = new FileOutputStream("/Users/prajakta/Desktop/Table.xlsx");

		wf.write(fos);
		fos.close();

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
			String filename = "/Users/prajakta/Desktop/Table.xlsx";

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
