package com.project.grab.TestBase;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import com.project.grab.helper.browserConfiguration.BrowserType;
import com.project.grab.helper.browserConfiguration.DriverFactory;
import com.project.grab.helper.configReader.PropertyReader;
import com.project.grab.helper.logger.LoggerHelper;
import com.project.grab.helper.resource.ResourceHelper;
import com.project.grab.helper.wait.WaitHelper;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;

public class TestBase {

	public static Properties prop = new Properties();
	PropertyReader reader = new PropertyReader();
	public static DesiredCapabilities capabilties = new DesiredCapabilities();

	// Abstract class
	public static AppiumDriver<MobileElement> driver;

	public final String appPath = "\\src\\main\\resources\\application\\ebay.apk";

	/*
	 * private Logger log = LoggerHelper.getLogger(TestBase.class); PropertyReader
	 * reader = new PropertyReader(); DriverFactory driverfactory = new
	 * DriverFactory();
	 * 
	 * public void setupDriver(BrowserType btype, String path) { driver =
	 * driverfactory.getBrowser(btype, path); log.info("Initialize Web driver: " +
	 * driver.hashCode()); WaitHelper wait = new WaitHelper(driver);
	 * wait.setImplicitWait(reader.getImplicitWait(), TimeUnit.SECONDS);
	 * wait.setPageLoadTimeOut(reader.getPageLoadTime(), TimeUnit.SECONDS); }
	 * 
	 * public String captureScreen(String fileName) { if (driver == null)
	 * log.info("driver is null");
	 * 
	 * if (fileName.equals("")) log.info("file name is null");
	 * 
	 * File destFile = null; Calendar cal = Calendar.getInstance(); SimpleDateFormat
	 * formater = new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss"); File srcFile =
	 * ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE); destFile = new
	 * File(ResourceHelper.getResourcePath("/src/main/resources/Screenshots") + "/"
	 * + fileName + "_" + formater.format(cal.getTime()) + ".png"); try {
	 * Files.copy(srcFile.toPath(), destFile.toPath()); } catch (IOException e) {
	 * e.printStackTrace(); } log.info("snapshot taken at " + destFile.toString());
	 * return destFile.toString(); }
	 */
	public void shutDownDriver() {
		driver.quit();
	}

	public void setAndroidDriver() throws MalformedURLException {

		capabilties.setCapability("platformName", reader.getPlatformName());
		capabilties.setCapability("platformVersion", reader.getPlatformVersion());
		capabilties.setCapability("deviceName", reader.getDeviceName());
		capabilties.setCapability("automationName", reader.getAutomationName());
		capabilties.setCapability("appPackage", "com.ebay.mobile");
		capabilties.setCapability("appActivity", ".activities.MainActivity");
		capabilties.setCapability("app", ResourceHelper.getResourcePath(appPath));

		URL url = new URL("http://" + reader.getAppiumServerURL() + ":" + reader.getAppiumServerPort() + "/wd/hub");
		driver = new AndroidDriver(url, capabilties);

	}

}
