package com.demoapp.testautomation.scripts;

import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.demoapp.testauto.library.FileUtil;
import com.demoapp.testauto.library.TestConfig;
import com.demoapp.testautomation.pageobject.AboutUsModel;
import com.demoapp.testautomation.pageobject.ContactUsModel;
import com.demoapp.testautomation.pageobject.CustomerModel;
import com.demoapp.testautomation.pageobject.LandingPageModel;
import com.demoapp.testautomation.pageobject.LoginPageModel;

/**
 * @author Amit
 *
 */
public class LandingPage {

	WebDriver driver;
	TestConfig testConfig;
	LoginPageModel loginModel;
	LandingPageModel landingModel;
	CustomerModel customerModel;
	ContactUsModel contactUsModel;
	AboutUsModel aboutUsModel;
	Map<String, String> envProperies;
	Map<String, String> profilePropertiesUser1;
	Map<String, String> profilePropertiesUser2;

	public static final org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(LandingPage.class);

	@Parameters({ "browser", "env" })
	@BeforeMethod(alwaysRun = true)
	public void preCondition(final String browser, final String env) {
		try {
			testConfig = new TestConfig(browser);
			driver = testConfig.getDriver();
			envProperies = FileUtil.getConfigProperties(env);
			profilePropertiesUser1 = FileUtil.getTestDataProperties("user1");
			profilePropertiesUser2 = FileUtil.getTestDataProperties("user2");
			loginModel = new LoginPageModel(driver);
			landingModel = new LandingPageModel(driver);
			customerModel = new CustomerModel(driver);
			contactUsModel = new ContactUsModel(driver);
			aboutUsModel = new AboutUsModel(driver);
			loginModel.login(profilePropertiesUser1, envProperies);
		} catch (Exception e) {
			logger.error("Exception thrown at Login Constructor:", e);

		}
	}

	@Test(groups = { "functionaltest" })
	public void verifyHomePage() {
		try {
			landingModel.verifyLandingPage();
		} catch (Exception e) {
			logger.error("Exception: ", e);
			Assert.fail("Exception thrown test cases failed :" + e.getMessage(), e);
		}

	}

	@Test(groups = { "functionaltest", "smoketest" })
	public void verifyCustomerUpdateDetails() {
		try {
			landingModel.clickOnUpdateCustomer();
			customerModel.enterCustomerDetails(profilePropertiesUser1);
			customerModel.verifySuccessfullySubmittedCustomerDetailsPopUp();
		} catch (Exception e) {
			logger.error("Exception: ", e);
			Assert.fail("Exception thrown test cases failed :" + e.getMessage(), e);
		}
	}

	@Test(groups = { "functionaltest" })
	public void verifyContactUs() {
		try {
			landingModel.clickOnContactUs();
			contactUsModel.enterYourMessageDetails(profilePropertiesUser1);
			contactUsModel.verifySuccessfullySubmittedYourMessageDetailsPopUp();
		} catch (Exception e) {
			logger.error("Exception: ", e);
			Assert.fail("Exception thrown test cases failed :" + e.getMessage(), e);
		}
	}

	@Test(groups = { "functionaltest" })
	public void verifyAboutUs() {
		try {
			landingModel.clickOnAboutUs();
			aboutUsModel.verifyAboutUsPage();
		} catch (Exception e) {
			logger.error("Exception: ", e);
			Assert.fail("Exception thrown test cases failed :" + e.getMessage(), e);
		}

	}

	@AfterMethod(alwaysRun = true)
	public void afterMethod() {
		testConfig.closeAllBrowser();
	}

	public WebDriver getDriver() {
		return driver;
	}
}
