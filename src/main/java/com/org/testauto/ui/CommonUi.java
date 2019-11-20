package com.org.testauto.ui;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

/**
 * @author Amit
 *
 */
public class CommonUi {

	public WebDriver driver;

	/**
	 * Select item from a list by index value
	 * 
	 * @param ddl
	 * @param index
	 */
	public static void selectDdlByIndex(WebElement ddl, int index) {
		Select sct = new Select(ddl);
		sct.selectByIndex(index);

	}

	/**
	 * Select item by value
	 * 
	 * @param ddl
	 * @param value
	 */
	public static void selectDdlByValue(WebElement ddl, String value) {
		Select sct = new Select(ddl);
		sct.selectByValue(value);
	}

	/**
	 * select item by visibleText
	 * 
	 * @param ddl
	 * @param visibleText
	 */
	public static void selectDdlByVisibleText(WebElement ddl, String visibleText) {
		Select sct = new Select(ddl);
		sct.selectByVisibleText(visibleText);
	}

	/**
	 * Deselect item by index
	 * 
	 * @param ddl
	 * @param index
	 */
	public static void deselectByIndex(WebElement ddl, int index) {
		Select sct = new Select(ddl);
		sct.deselectByIndex(index);
	}

	/**
	 * deselect item by value
	 * 
	 * @param ddl
	 * @param value
	 */
	public static void deselectByValue(WebElement ddl, String value) {
		Select sct = new Select(ddl);
		sct.deselectByValue(value);
	}

	//
	/**
	 * Deselect item by VisibleText
	 * 
	 * @param ddl
	 * @param visibleText
	 */
	public static void deselectByVisibleText(WebElement ddl, String visibleText) {
		Select sct = new Select(ddl);
		sct.deselectByVisibleText(visibleText);
	}

	/**
	 * Deselect all selected item
	 * 
	 * @param ddl
	 */
	public static void deselectAll(WebElement ddl) {
		Select sct = new Select(ddl);
		sct.deselectAll();
	}

	/**
	 * Handle Alert pop-ups
	 * 
	 * @param driver
	 */
	public static void acceptAlert(WebDriver driver)

	{
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
