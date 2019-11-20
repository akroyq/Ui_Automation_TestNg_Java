package com.demoapp.testautomation.pageobject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;

/**
 * @author Amit
 *
 */
public class AboutUsModel {
	
	private final WebDriver driver;
	private final WebDriverWait wait;

	public static final org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(AboutUsModel.class);

	@FindBy(xpath = "//h1[contains(text(),'Welcome to AboutUs')]")
	private WebElement aboutUsMsg;
	
	public AboutUsModel(final WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		wait = new WebDriverWait(driver, 30);
	}
	
	public void verifyAboutUsPage() {
		wait.until(ExpectedConditions.visibilityOf(aboutUsMsg));
		Assert.assertTrue(aboutUsMsg.isDisplayed());
		Reporter.log("AboutUs page displayed");
		logger.info("AboutUs page displayed");

	}
	

}
