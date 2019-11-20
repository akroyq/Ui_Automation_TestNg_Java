package com.demoapp.testauto.listeners;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.WebDriver;
import org.testng.IReporter;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;
import org.testng.xml.XmlSuite;

import com.demoapp.testauto.library.ScreenShot;

/**
 * @author Amit
 *
 */
public class TestCaseListener extends TestListenerAdapter implements IReporter, ISuiteListener {

	private static final String DRIVER_METHOD_NAME = "getDriver";

	public static final org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(TestCaseListener.class);

	public static int passedTest = 0;
	public static int failedTest = 0;
	public static int skippedTest = 0;
	public static int totalNumberOfTest = 0;
	public static int rerunCounter = 0;

	private static ArrayList<String> failedtestNames = new ArrayList<String>();

	@Override
	public void onTestStart(final ITestResult tr) {
		//log.info(tr.getTestClass());
		log.info("Test Method name: " + tr.getName());
		log.info("Test Started.....");
	}

	@Override
	public void onTestSuccess(final ITestResult tr) {
		incrementPassedTestCount(tr.getTestClass().getName() + "." + tr.getName());
		log.info("Test '" + tr.getName() + "' PASSED");
		doProcessScreenshot(tr);
		printResultAfterTestExecution();
	}

	@Override
	public void onTestFailure(final ITestResult tr) {
		String testName = tr.getTestClass().getName() + "." + tr.getName();
		incrementFailedTestCount(testName);
		log.info("Test '" + tr.getName() + "' FAILED");
		doProcessScreenshot(tr);
		printResultAfterTestExecution();
	}

	@Override
	public void onTestSkipped(final ITestResult tr) {
		incrementSkippedTestCount();
		log.info("Test '" + tr.getName() + "' SKIPPED");
		printResultAfterTestExecution();
	}

	private void doProcessScreenshot(final ITestResult testResult) {
		// String packageName = null;
		// String className = null;
		Object testScriptObj = testResult.getInstance();

		try {
			log.info(testScriptObj.getClass().toString());
			// String[] splitString =
			// testScriptObj.getClass().toString().split("\\.");
			// packageName = splitString[splitString.length -2];
			// className = splitString[splitString.length - 1];
			for (Method method : testScriptObj.getClass().getMethods()) {
				if (method.getName().equals(TestCaseListener.DRIVER_METHOD_NAME)) {
					WebDriver driver = (WebDriver) method.invoke(testResult.getInstance());
					// ScreenShot.takeScreenshot(driver,
					// packageName.toUpperCase(), className,
					// testResult.getName());
					ScreenShot.takeScreenShot(driver);
					break;
				}
			}
		} catch (Exception e) {
			log.error("Exception thrown at TestCaseListener class: onTestSuccess() : ", e);
		}
	}

	public void onFinish(final ITestContext testContext) {

	}

	public static void printResultAfterTestExecution() {
		getTotalTestCount();
		log.info("Passed:" + passedTest + " " + "Failed:" + failedTest + " " + "Skipped:" + skippedTest + " " + "Total:"
				+ totalNumberOfTest);
	}

	public static void incrementPassedTestCount(final String name) {
		if (failedtestNames.contains(name)) {
			failedtestNames.remove(name);
			failedTest = failedTest - 1;
		}
		passedTest++;
	}

	public static void incrementFailedTestCount(final String name) {
		if (!failedtestNames.contains(name)) {
			failedtestNames.add(name);
			failedTest++;
		}
	}

	public static void incrementSkippedTestCount() {
		skippedTest++;
	}

	public static void getTotalTestCount() {
		totalNumberOfTest = passedTest + failedTest + skippedTest;
	}

	public void onFinish(ISuite suite) {
		// TODO Auto-generated method stub

	}

	public void onStart(ISuite suite) {
		// TODO Auto-generated method stub

	}

	public void generateReport(List<XmlSuite> arg0, List<ISuite> arg1, String arg2) {
		// TODO Auto-generated method stub

	}

}
