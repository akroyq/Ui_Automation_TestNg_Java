package com.demoapp.testautomation.pageobject;

import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPageModel {

	private final WebDriver driver;
	private final WebDriverWait wait;

	public static final org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(LoginPageModel.class);

	@FindBy(xpath = "//input[@name='Email']")
	private WebElement emailTextBox;

	@FindBy(xpath = "//input[@type='password']")
	private WebElement passwordTextBox;

	@FindBy(xpath = "//input[@type='submit']")
	private WebElement loginButton;

	public LoginPageModel(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		wait = new WebDriverWait(driver, 60);
	}

	public void login(Map<String, String> profileProperties, Map<String, String> envProperies) {
		driver.get(System.getProperty("user.dir") + envProperies.get("url"));
		emailTextBox.sendKeys(profileProperties.get("userName"));
		logger.info("username entered : " + emailTextBox.getAttribute("value"));
		passwordTextBox.sendKeys(profileProperties.get("password"));
		logger.info("password entered : " + passwordTextBox.getAttribute("value"));
		loginButton.click();
		logger.info("login button clicked");
	}

}
