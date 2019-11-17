package com.demoapp.testautomation.pageobject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;

public class LandingPageModel {

	private final WebDriver driver;
	private final WebDriverWait wait;

	public static final org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(LandingPageModel.class);

	@FindBy(xpath = "//h1[contains(text(),'Welcome')]")
	private WebElement welcomeMsg;

	@FindBy(xpath = "//a[text()='Update Customer Information']")
	private WebElement updateCustomerInformation;

	@FindBy(xpath = "//a[text()='AboutUs']")
	private WebElement aboutUs;

	@FindBy(xpath = "//a[text()='ContactUs']")
	private WebElement contactUs;

	@FindBy(xpath = "//a[@href='Home.html']")
	private WebElement logOut;

	public LandingPageModel(final WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		wait = new WebDriverWait(driver, 60);
	}

	public void verifyLandingPage() {
		wait.until(ExpectedConditions.visibilityOf(welcomeMsg));
		Assert.assertTrue(welcomeMsg.isDisplayed());
		Reporter.log("Landing page displayed");
		logger.info("Landing page displayed");
	}

	public void clickOnUpdateCustomer() {
		wait.until(ExpectedConditions.visibilityOf(updateCustomerInformation));
		updateCustomerInformation.click();
		Reporter.log("Update customer information link clicked");
		logger.info("update customer information link clicked");
	}

	public void clickOnAboutUs() {
		wait.until(ExpectedConditions.visibilityOf(aboutUs));
		aboutUs.click();
		Reporter.log("About Us link clicked");
		logger.info("About Us link clicked");
	}

	public void clickOnContactUs() {
		wait.until(ExpectedConditions.visibilityOf(contactUs));
		contactUs.click();
		Reporter.log("Contact Us link clicked");
		logger.info("Contact Us link clicked");
	}

	public void clickOnLogout() {
		wait.until(ExpectedConditions.visibilityOf(logOut));
		logOut.click();
		Reporter.log("LogOut link clicked");
		logger.info("LogOut link clicked");
	}

}
