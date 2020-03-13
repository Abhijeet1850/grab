package com.project.grab.PageActions;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.project.grab.TestBase.TestBase;
import com.project.grab.helper.configReader.PropertyReader;
import com.project.grab.helper.logger.LoggerHelper;
import com.project.grab.helper.wait.WaitHelper;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class Homepage {

	private AppiumDriver<MobileElement> driver;
	private Logger log = LoggerHelper.getLogger(Homepage.class);
	TestBase tb = new TestBase();
	WaitHelper wait;
	PropertyReader reader = new PropertyReader();
	Actions action;

	@AndroidFindBy(id = "com.ebay.mobile:id/social_account_splash_screen_layout")
	public MobileElement popUpWindow;

	@AndroidFindBy(accessibility = "Close")
	public MobileElement popUpWindowClose;

	@AndroidFindBy(className = "android.widget.ProgressBar")
	public MobileElement loader;

	@AndroidFindBy(accessibility = "Search Keyword Search for anything")
	public MobileElement searchBoxClick;

	@AndroidFindBy(id = "com.ebay.mobile:id/search_src_text")
	public MobileElement searchBoxType;

	public Homepage(AppiumDriver<MobileElement> driver) {
		this.driver = driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
		wait = new WaitHelper(driver);
		action = new Actions(driver);
	}

	public void closePopupIfPresent() {
		try {
			if (wait.waitForElement(popUpWindow, reader.getExplicitWait(), reader.getPollingTime()).isDisplayed())
				popUpWindowClose.click();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void searchItem(String searchItem) {

		wait.waitForElement(searchBoxClick, reader.getExplicitWait(), reader.getPollingTime()).click();
		wait.waitForElement(searchBoxType, reader.getExplicitWait(), reader.getPollingTime()).sendKeys(searchItem);
		driver.getKeyboard().sendKeys(Keys.ENTER);
	}

}
