package com.demoapp.testautomation.pageobject;

import java.util.Map;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;

import com.demoapp.testauto.library.RandomUtil;
import com.org.testauto.ui.CommonUi;

/**
 * @author Amit
 *
 */
public class ContactUsModel {

	private final WebDriver driver;
	private final WebDriverWait wait;

	public static final org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(ContactUsModel.class);

	public static final String YOUR_MESSAGE_DETAILS_SUCCESS_MSG = "Your Form is submitted";

	@FindBy(xpath = "//input [@id='name']")
	private WebElement yourName;

	@FindBy(xpath = "//input [@id='email']")
	private WebElement yourEmail;

	@FindBy(xpath = "//input [@id='subject']")
	private WebElement yourSubject;

	@FindBy(xpath = "//textarea [@name='message']")
	private WebElement yourMessage;

	@FindBy(xpath = "//button [@type='submit']")
	private WebElement sendMsg;

	public ContactUsModel(final WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		wait = new WebDriverWait(driver, 30);
	}

	public void enterYourName() {
		String name = RandomUtil.generateAlphabatic(15);
		yourName.clear();
		yourName.sendKeys(name);
		Reporter.log("Entered your name: " + name);
		logger.info("Entered your name: " + name);
	}

	public void enterYourEmail(Map<String, String> profileProperties) {
		yourEmail.clear();
		yourEmail.sendKeys(profileProperties.get("emailAddress"));
		Reporter.log("Your email address entered : " + profileProperties.get("emailAddress"));
		logger.info("Your email address entered : " + profileProperties.get("emailAddress"));
	}

	public void enterYourSubject() {
		String subject = RandomUtil.generateAlphabatic(10);
		yourSubject.clear();
		yourSubject.sendKeys(subject);
		Reporter.log("Your subject entered : " + subject);
		logger.info("Your subject entered: " + subject);
	}

	public void enterYourMessage() {
		String message = RandomUtil.generateAlphabatic(30);
		yourMessage.clear();
		yourMessage.sendKeys(message);
		Reporter.log("Your message entered: " + message);
		logger.info("Your message entered: " + message);
	}

	public void clickOnSendMsg() {
		sendMsg.click();
		Reporter.log("Send message button clicked");
		logger.info("Send message button clicked");
	}

	public void verifySuccessfullySubmittedYourMessageDetailsPopUp() {
		String alertText = CommonUi.getAlertText(driver);
		Assert.assertTrue(alertText.equalsIgnoreCase(YOUR_MESSAGE_DETAILS_SUCCESS_MSG));
		CommonUi.acceptAlert(driver);
		Reporter.log("Successfully your message details submitted");
		logger.info("Successfully your message details submitted");
	}
	
	public void enterYourMessageDetails(Map<String, String> profileProperties) {
		enterYourName();
		enterYourEmail(profileProperties);
		enterYourSubject();
		enterYourMessage();
		clickOnSendMsg();
	}

}
