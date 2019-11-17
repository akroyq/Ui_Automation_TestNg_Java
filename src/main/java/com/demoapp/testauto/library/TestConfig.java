package com.demoapp.testauto.library;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.demoapp.testauto.exceptions.BrowserNotSupportedException;

public class TestConfig {

	WebDriver driver = null;
	public static final org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(TestConfig.class);
	public static final String FF = "firefox";
	public static final String IE = "ie";
	public static final String CHROME = "chrome";

	public TestConfig(final String browser) {
		try {
			setupBrowser(browser);
		} catch (Exception e) {
			log.info("Exception thrown: ", e);
		}
	}

	public void setupBrowser(final String browser) throws MalformedURLException {
		String gridUrl = System.getProperty("gridurl");
		if (FF.equalsIgnoreCase(browser)) {
			System.setProperty("webdriver.gecko.driver",
					System.getProperty("user.dir") + "/src/main/resources/" + "geckodriver.exe");
			DesiredCapabilities capabilities = DesiredCapabilities.firefox();
			capabilities.setCapability(CapabilityType.ForSeleniumServer.ENSURING_CLEAN_SESSION, true);
			if (StringUtils.isNotEmpty(gridUrl)) {
				driver = new RemoteWebDriver(new URL(gridUrl), capabilities);
			} else {
				driver = new FirefoxDriver(capabilities);

			}
		} else if (CHROME.equalsIgnoreCase(browser)) {
			System.setProperty("webdriver.chrome.driver",
					System.getProperty("user.dir") + "/src/main/resources/" + "chromedriver.exe");
			DesiredCapabilities capabilities = DesiredCapabilities.chrome();
			capabilities.setCapability(CapabilityType.ForSeleniumServer.ENSURING_CLEAN_SESSION, true);
			if (StringUtils.isNotEmpty(gridUrl)) {
				driver = new RemoteWebDriver(new URL(gridUrl), capabilities);
			} else {
				driver = new ChromeDriver(capabilities);
			}
		} else if (IE.equalsIgnoreCase(browser)) {
			System.setProperty("webdriver.ie.driver",
					System.getProperty("user.dir") + "/src/main/resources/" + "IEDriverServer.exe");
			DesiredCapabilities capabilities = DesiredCapabilities.internetExplorer();
			capabilities.setCapability(CapabilityType.ForSeleniumServer.ENSURING_CLEAN_SESSION, true);
			if (StringUtils.isNotEmpty(gridUrl)) {
				driver = new RemoteWebDriver(new URL(gridUrl), capabilities);
			} else {
				driver = new InternetExplorerDriver(capabilities);
			}
		} else {
			log.info("No valid browser is available in the congiguration file");
			throw new BrowserNotSupportedException();

		}

		browserMaximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}

	public void browserMaximize() {
		driver.manage().window().maximize();
	}

	public WebDriver getDriver() {
		return driver;
	}

	public void closeAllBrowser() {
		driver.quit();
	}

}