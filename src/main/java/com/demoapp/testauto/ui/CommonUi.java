package com.demoapp.testauto.ui;

import org.openqa.selenium.Alert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * @author Amit
 *
 */
public class CommonUi {

	public WebDriver driver;

	/**
	 * @param driver
	 * @param webElement
	 */
	public static void scrollToElement(WebDriver driver, WebElement webElement) {
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", webElement);
	}

	/**
	 * @param driver
	 */
	public static void scrollToDown(WebDriver driver) {
		((JavascriptExecutor) driver).executeScript("window.scrollBy(0, 250)", "");
	}

	/**
	 * @param driver
	 */
	public static void scrollToUp(WebDriver driver) {
		((JavascriptExecutor) driver).executeScript("window.scrollBy(0, -250)", "");
	}

	/**
	 * Handle Alert pop-ups
	 * 
	 * @param driver
	 */
	public static void acceptAlert(WebDriver driver) {
		Alert alt = driver.switchTo().alert();
		alt.accept();
	}

	/**
	 * handle confirmation pop-ups
	 * 
	 * @param driver
	 */
	public static void dismiss(WebDriver driver) {
		Alert alt = driver.switchTo().alert();
		alt.dismiss();
	}

	/**
	 * Handle Alert prompt popo-ups
	 * 
	 * @param driver
	 * @param text
	 */
	public static void enterText(WebDriver driver, String text) {
		Alert alt = driver.switchTo().alert();
		alt.sendKeys(text);
	}

	/**
	 * Get text from Alert
	 * 
	 * @param driver
	 * @return
	 */
	public static String getAlertText(WebDriver driver) {
		Alert alt = driver.switchTo().alert();
		String data = alt.getText();
		return data;
	}

}
