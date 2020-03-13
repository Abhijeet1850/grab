package com.project.grab.helper.wait;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.project.grab.helper.logger.LoggerHelper;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;

public class WaitHelper {

	private AppiumDriver<MobileElement> driver;
	private Logger log = LoggerHelper.getLogger(WaitHelper.class);

	public WaitHelper(AppiumDriver<MobileElement> driver) {
		this.driver = driver;
	}

	public void setImplicitWait(long timeout, TimeUnit unit) {
		log.info("the implicit time has been changed to " + timeout + " " + unit.toString());
		driver.manage().timeouts().implicitlyWait(timeout, unit);
	}

	public void setPageLoadTimeOut(long timeout, TimeUnit unit) {
		log.info("the page load time has been changed to " + timeout + " " + unit.toString());
		driver.manage().timeouts().pageLoadTimeout(timeout, unit);
	}

	private WebDriverWait getWait(long timeOutInSeconds, long pollingDurationInMiliSec) {
		log.info("the explicit time applied is " + timeOutInSeconds + " with polling in every "
				+ pollingDurationInMiliSec);
		WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
		wait.pollingEvery(pollingDurationInMiliSec, TimeUnit.MILLISECONDS);
		wait.ignoring(NoSuchElementException.class);
		wait.ignoring(ElementNotVisibleException.class);
		wait.ignoring(NoSuchElementException.class);
		wait.ignoring(NoSuchElementException.class);
		return wait;
	}

	private Wait<AppiumDriver<MobileElement>> getfluentWait(long timeOutInSeconds, long pollingEveryInMiliSec) {
		Wait<AppiumDriver<MobileElement>> fWait = new FluentWait<AppiumDriver<MobileElement>>(driver).withTimeout(timeOutInSeconds, TimeUnit.SECONDS)
				.pollingEvery(pollingEveryInMiliSec, TimeUnit.MILLISECONDS).ignoring(NoSuchElementException.class);
		return fWait;
	}

	public void waitForAlertIsPresent(long timeOutInSeconds, long pollingDurationInMiliSec) {
		WebDriverWait wait = getWait(timeOutInSeconds, pollingDurationInMiliSec);
		wait.until(ExpectedConditions.alertIsPresent());
	}

	public void waitForElementVisible(MobileElement ele, long timeOutInSeconds, long pollingDurationInMiliSec) {
		log.info("the explicit time applied for element visibility is " + timeOutInSeconds + " with polling in every "
				+ pollingDurationInMiliSec);
		WebDriverWait wait = getWait(timeOutInSeconds, pollingDurationInMiliSec);
		wait.until(ExpectedConditions.visibilityOf(ele));
		log.info("the element is visible now");
	}

	public void waitForURLContainsSpecificText(String text, long timeOutInSeconds, long pollingDurationInMiliSec) {
		log.info("the explicit time applied for URL text search is " + timeOutInSeconds + " with polling in every "
				+ pollingDurationInMiliSec);
		WebDriverWait wait = getWait(timeOutInSeconds, pollingDurationInMiliSec);
		wait.until(ExpectedConditions.urlContains(text));
	}

	public void waitForElementClickable(MobileElement ele, long timeOutInSeconds, long pollingDurationInMiliSec) {
		log.info("the explicit time applied for element visibility is " + timeOutInSeconds + " with polling in every "
				+ pollingDurationInMiliSec);
		WebDriverWait wait = getWait(timeOutInSeconds, pollingDurationInMiliSec);
		wait.until(ExpectedConditions.elementToBeClickable(ele));
		log.info("the element is clickable now");
	}

	public void waitForElementNotPresent(MobileElement ele, long timeOutInSeconds, long pollingDurationInMiliSec) {
		log.info("the explicit time applied for element visibility is " + timeOutInSeconds + " with polling in every "
				+ pollingDurationInMiliSec);
		WebDriverWait wait = getWait(timeOutInSeconds, pollingDurationInMiliSec);
		wait.until(ExpectedConditions.invisibilityOf(ele));
		log.info("the element is present now");
	}

	public MobileElement waitForElement(MobileElement element, long timeOutInSeconds, long pollingDurationInMiliSec) {
		log.info("waiting for the element " + element.toString() + "for time : " + timeOutInSeconds);
		Wait<AppiumDriver<MobileElement>> fwait = getfluentWait(timeOutInSeconds, pollingDurationInMiliSec);
		return (MobileElement) fwait.until(ExpectedConditions.visibilityOf(element));
	}

	public MobileElement waitForElementToBeClickable(MobileElement element, long timeOutInSeconds,
			long pollingDurationInMiliSec) {
		log.info("waiting for the element " + element.toString() + "for time : " + timeOutInSeconds);
		Wait<AppiumDriver<MobileElement>> fwait = getfluentWait(timeOutInSeconds, pollingDurationInMiliSec);
		return (MobileElement) fwait.until(ExpectedConditions.elementToBeClickable(element));
	}

	public List<WebElement> waitForElementsLocatedBy(By loc, long timeOutInSeconds, long pollingDurationInMiliSec) {
		log.info("waiting for the element " + loc.toString() + "for time : " + timeOutInSeconds);
		Wait<AppiumDriver<MobileElement>> fwait = getfluentWait(timeOutInSeconds, pollingDurationInMiliSec);
		return fwait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(loc));
	}

	public void waitForMillis(int milliSeconds) {
		try {
			Thread.sleep(milliSeconds);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
