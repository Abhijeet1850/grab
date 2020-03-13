package com.project.grab.PageActions;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;

import com.project.grab.TestBase.TestBase;
import com.project.grab.helper.configReader.PropertyReader;
import com.project.grab.helper.logger.LoggerHelper;
import com.project.grab.helper.wait.WaitHelper;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class ProductDescriptionPage {

	private AppiumDriver<MobileElement> driver;
	private Logger log = LoggerHelper.getLogger(Homepage.class);
	TestBase tb = new TestBase();
	WaitHelper wait;
	PropertyReader reader = new PropertyReader();
	Actions action;

	@AndroidFindBy(accessibility = "Watch")
	public MobileElement watchBtn;

	@AndroidFindBy(id = "com.ebay.mobile:id/title")
	public MobileElement signInHeader;

	@AndroidFindBy(id = "com.ebay.mobile:id/button_classic")
	public MobileElement selectUsername;

	@AndroidFindBy(id = "com.ebay.mobile:id/edit_text_username")
	public MobileElement userNameText;

	@AndroidFindBy(accessibility = "Close")
	public MobileElement closeSignin;

	public ProductDescriptionPage(AppiumDriver<MobileElement> driver) {
		this.driver = driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
		wait = new WaitHelper(driver);
		action = new Actions(driver);
	}

	public void clickWatchButton() {
		wait.waitForElement(watchBtn, reader.getExplicitWait(), reader.getPollingTime()).click();
	}

	public boolean isSignInHeaderDisplayed() {
		if (wait.waitForElement(signInHeader, reader.getExplicitWait(), reader.getPollingTime()).getText()
				.equals("Sign in"))
			return true;
		return false;
	}

	public void selectUsernameOption() {
		wait.waitForElement(selectUsername, reader.getExplicitWait(), reader.getPollingTime()).click();
	}

	public void enterUserName(String userName) {
		wait.waitForElement(userNameText, reader.getExplicitWait(), reader.getPollingTime()).sendKeys(userName);
	}

	public void closeSignIn() {
		wait.waitForElement(closeSignin, reader.getExplicitWait(), reader.getPollingTime()).click();
	}

}
