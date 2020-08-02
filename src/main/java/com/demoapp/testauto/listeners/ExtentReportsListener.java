package com.demoapp.testauto.listeners;

import java.io.File;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.demoapp.testauto.library.ScreenShot;

/**
 * @author Amit
 *
 */
public class ExtentReportsListener implements ITestListener, ISuiteListener {

	public static final org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(ExtentReportsListener.class);
	static final String DRIVER_METHOD_NAME = "getDriver";
	static Date date = new Date();
	static String fileName = "ExtentReport_" + date.toString().replace(":", "_").replace(" ", "_") + ".html";
	private static ExtentReports extent = ExtentReportsConfig
			.createInstance(System.getProperty("user.dir") + "\\reports\\" + fileName);

	public static ThreadLocal<ExtentTest> testReport = new ThreadLocal<ExtentTest>();
	static String messageBody;

	public void onTestStart(ITestResult result) {
		ExtentTest test = extent
				.createTest(result.getTestClass().getName() + "     @TestCase : " + result.getMethod().getMethodName());
		testReport.set(test);
	}

	public void onTestSuccess(ITestResult result) {
		try {
			String methodName = result.getMethod().getMethodName();
			String logText = "<b>" + "TEST CASE:- " + methodName.toUpperCase() + " PASSED" + "</b>";
			Markup m = MarkupHelper.createLabel(logText, ExtentColor.GREEN);
			testReport.get().pass(m);
			String screenshotMsg = "Click below icon to see screenshot:";
			Markup sc = MarkupHelper.createLabel(screenshotMsg, ExtentColor.GREY);
			testReport.get().addScreenCaptureFromPath(processScreenshot(result)).log(Status.INFO, sc);
		} catch (Exception e) {
			log.error("Exception thrown at TestCaseListener class: onTestSuccess() : ", e);
		}
	}

	public void onTestFailure(ITestResult result) {
		try {
			testReport.get().fail(result.getThrowable().getMessage().toString());
			String excepionMessage = Arrays.toString(result.getThrowable().getStackTrace());
			testReport.get()
					.fail("<details>" + "<summary>" + "<b>" + "<font color=" + "red>" + "Exception Occured:Click to see"
							+ "</font>" + "</b >" + "</summary>" + excepionMessage.replaceAll(",", "<br>")
							+ "</details>" + " \n");
			String failureLogg = "TEST CASE FAILED";
			Markup m = MarkupHelper.createLabel(failureLogg, ExtentColor.RED);
			testReport.get().log(Status.FAIL, m);

			String screenshotMsg = "Click below icon to see screenshot:";
			Markup sc = MarkupHelper.createLabel(screenshotMsg, ExtentColor.BROWN);
			testReport.get().addScreenCaptureFromPath(processScreenshot(result)).log(Status.INFO, sc);
		} catch (Exception e) {
			log.error("Exception thrown at TestCaseListener class: onTestFailure() : ", e);
		}

	}

	public void onTestSkipped(ITestResult result) {
		String methodName = result.getMethod().getMethodName();
		String logText = "<b>" + "Test Case:- " + methodName + " Skipped" + "</b>";
		Markup m = MarkupHelper.createLabel(logText, ExtentColor.YELLOW);
		testReport.get().skip(m);
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub

	}

	public void onStart(ITestContext context) {

	}

	public void onFinish(ITestContext context) {
		if (extent != null) {
			extent.flush();
		}
	}

	public void onStart(ISuite suite) {

	}

	public void onFinish(ISuite suite) {

	}

	private String processScreenshot(final ITestResult testResult) {
		String errImgPath = null;
		String packageName = null;
		String className = null;
		String methodName = null;
		Object testScriptObj = testResult.getInstance();
		Calendar calender = Calendar.getInstance();
		String toDay = calender.get(Calendar.DATE) + "-" + (calender.get(Calendar.MONTH) + 1) + "-"
				+ calender.get(Calendar.YEAR);
		String screenShotName = calender.getTimeInMillis() + ".png";

		try {
			String[] splitString = testScriptObj.getClass().toString().split("\\.");
			packageName = splitString[splitString.length - 2];
			className = splitString[splitString.length - 1];
			methodName = testResult.getName();
			for (Method method : testScriptObj.getClass().getMethods()) {
				if (method.getName().equals(ExtentReportsListener.DRIVER_METHOD_NAME)) {
					WebDriver driver = (WebDriver) method.invoke(testResult.getInstance());
					File file = new File(ScreenShot.SCREENSHOT_DIRECTORY_NAME + ScreenShot.fileSeperator
							+ ScreenShot.RESULT_DIRECTORY_NAME + ScreenShot.fileSeperator + className
							+ ScreenShot.fileSeperator + methodName);
					if (!file.exists()) {
						file.mkdir();
					}
					File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
					File dest = new File(
							ScreenShot.SCREENSHOT_DIRECTORY_NAME + ScreenShot.fileSeperator
									+ ScreenShot.RESULT_DIRECTORY_NAME + ScreenShot.fileSeperator + toDay
									+ ScreenShot.fileSeperator + className + ScreenShot.fileSeperator + methodName,
							screenShotName);
					FileUtils.copyFile(src, dest);
					errImgPath = dest.getAbsolutePath();
					break;
				}
			}
		} catch (Exception e) {
			log.error("Exception thrown at TestCaseListener class: onTestSuccess() : ", e);
		}
		return errImgPath;
	}

}
