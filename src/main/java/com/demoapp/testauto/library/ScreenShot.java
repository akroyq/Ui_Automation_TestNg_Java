package com.demoapp.testauto.library;

import java.io.File;
import java.util.Calendar;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.Reporter;

/**
 * @author Amit
 *
 */
public class ScreenShot {

	private static String fileSeperator = System.getProperty("file.separator");

	public static final org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(ScreenShot.class);

	private static final String SCREENSHOT_DIRECTORY_NAME = "screenshots";

	private static final String RESULT_DIRECTORY_NAME = "Result";

	private ScreenShot() {
	}

	public static void takeScreenShot(final WebDriver driver) {
		Calendar calender = Calendar.getInstance();
		String toDay = calender.get(Calendar.DATE) + "-" + (calender.get(Calendar.MONTH) + 1) + "-"
				+ calender.get(Calendar.YEAR);
		String screenShotName = calender.getTimeInMillis() + ".png";
		try {
			File file = new File(
					ScreenShot.SCREENSHOT_DIRECTORY_NAME + ScreenShot.fileSeperator + ScreenShot.RESULT_DIRECTORY_NAME);
			if (!file.exists()) {
				file.mkdir();
			}
			File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			File dest = new File(ScreenShot.SCREENSHOT_DIRECTORY_NAME + ScreenShot.fileSeperator
					+ ScreenShot.RESULT_DIRECTORY_NAME + ScreenShot.fileSeperator + toDay, screenShotName);
			FileUtils.copyFile(src, dest);
			Reporter.log("Screenshot: <a href='../" + dest.getPath() + " 'target='_blank'>View ScreenShot</a>");
		} catch (Exception e) {
			log.error("Exception at Screenshot:takeScreenshot() :", e);
		}
	}

}
