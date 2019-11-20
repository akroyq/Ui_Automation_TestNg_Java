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
public class CustomerModel {

	private final WebDriver driver;
	private final WebDriverWait wait;

	public static final org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(CustomerModel.class);

	private static final String CUSTOMER_DETAILS_SUCCESS_MSG = "Your Form is submitted";

	@FindBy(xpath = "//input[@placeholder='Enter Your Name']")
	private WebElement customerName;

	@FindBy(xpath = "//textarea")
	private WebElement customerAddress;

	@FindBy(xpath = "//input[@name='Email']")
	private WebElement customerEmail;

	@FindBy(xpath = "//input[@name='bday']")
	private WebElement customerDob;

	@FindBy(xpath = "//input[@value='Submit']")
	private WebElement submitBtn;

	@FindBy(xpath = "//a[text()='Home']")
	private WebElement homeLink;

	public CustomerModel(final WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		wait = new WebDriverWait(driver, 30);
	}

	public void enterCustomerName() {
		String name = RandomUtil.generateAlphabatic(12);
		customerName.clear();
		customerName.sendKeys(name);
		Reporter.log("Customer name entered : " + name);
		logger.info("Customer name entered : " + name);
	}

	public void enterCustomerAddress() {
		String addressName = RandomUtil.generateAlphabatic(20);
		customerAddress.clear();
		customerAddress.sendKeys(addressName);
		Reporter.log("Customer address entered  : " + addressName);
		logger.info("Customer customer address : " + addressName);
	}

	public void enterCustomerEmail(Map<String, String> profileProperties) {
		customerEmail.clear();
		customerEmail.sendKeys(profileProperties.get("emailAddress"));
		Reporter.log("Customer email address entered : " + profileProperties.get("emailAddress"));
		logger.info("Customer customer email address entered  : " + profileProperties.get("emailAddress"));
	}

	public void enterCustomerDob(Map<String, String> profileProperties) {
		customerDob.clear();
		customerDob.sendKeys(profileProperties.get("dob"));
		Reporter.log("Customer DOB entered : " + profileProperties.get("dob"));
		logger.info("Customer DOB entered : " + profileProperties.get("dob"));
	}

	public void clickOnSubmit() {
		submitBtn.click();
		Reporter.log("Submit button clicked");
		logger.info("Submit button clicked");
	}

	public void verifySuccessfullySubmittedCustomerDetailsPopUp() {
		String alertText = CommonUi.getAlertText(driver);
		Assert.assertTrue(alertText.equalsIgnoreCase(CUSTOMER_DETAILS_SUCCESS_MSG));
		CommonUi.acceptAlert(driver);
		Reporter.log("Successfully customer details submitted");
		logger.info("Successfully customer details submitted");
	}

	public void navigateToHomePage() {
		homeLink.click();
		Reporter.log("Navigated to home page");
		logger.info("Navigated to home page");
	}

	public void enterCustomerDetails(Map<String, String> profileProperties) {
		enterCustomerName();
		enterCustomerAddress();
		enterCustomerEmail(profileProperties);
		enterCustomerDob(profileProperties);
		clickOnSubmit();
	}

}
