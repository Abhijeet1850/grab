package com.project.grab.PageActions;

import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.map.HashedMap;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;

import com.project.grab.TestBase.TestBase;
import com.project.grab.helper.configReader.PropertyReader;
import com.project.grab.helper.logger.LoggerHelper;
import com.project.grab.helper.wait.WaitHelper;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;

public class ProductListPage {

	private AppiumDriver<MobileElement> driver;
	private Logger log = LoggerHelper.getLogger(Homepage.class);
	TestBase tb = new TestBase();
	WaitHelper wait;
	PropertyReader reader = new PropertyReader();
	Actions action;

	@AndroidFindBy(id = "com.ebay.mobile:id/text_slot_1")
	public MobileElement saveSearchText;

	@AndroidFindBy(id = "com.ebay.mobile:id/button_sort")
	public MobileElement sortSearch;

	@AndroidFindBy(xpath = "//android.widget.CheckedTextView[@text='Lowest Price + Shipping']")
	public MobileElement LowToHighFilter;

	@AndroidFindBy(xpath = "//android.widget.CheckedTextView[@text='Highest Price + Shipping']")
	public MobileElement HighToLowFilter;

	@AndroidFindBy(id = "com.ebay.mobile:id/cell_collection_item")
	public List<MobileElement> itemList;

	@AndroidFindBy(xpath = "//android.widget.RelativeLayout[@resource-id='com.ebay.mobile:id/cell_collection_item']/android.widget.LinearLayout/android.widget.TextView[@resource-id='com.ebay.mobile:id/textview_item_title']")
	public List<MobileElement> itemTitle;

	@AndroidFindBy(id = "com.ebay.mobile:id/textview_item_price")
	public List<MobileElement> itemPrice;

	@AndroidFindBy(id = "com.ebay.mobile:id/title")
	public MobileElement searchHeader;

	@AndroidFindBy(id = "com.ebay.mobile:id/search_src_text")
	public MobileElement searchTextField;

	By refreshedItemList = By.id("com.ebay.mobile:id/cell_collection_item");

	public ProductListPage(AppiumDriver<MobileElement> driver) {
		this.driver = driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
		wait = new WaitHelper(driver);
		action = new Actions(driver);
	}

	public void ifSaveTextPresentAcknowledge() {
		try {
			if (wait.waitForElement(saveSearchText, reader.getExplicitWait(), reader.getPollingTime()).isDisplayed())
				saveSearchText.click();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void applySortFilter(String filter) {
		wait.waitForElement(sortSearch, reader.getExplicitWait(), reader.getPollingTime()).click();
		if (filter.equals("Low to High"))
			wait.waitForElement(LowToHighFilter, reader.getExplicitWait(), reader.getPollingTime()).click();
		else
			wait.waitForElement(HighToLowFilter, reader.getExplicitWait(), reader.getPollingTime()).click();
	}

	public void printItemNamePrice(int noOfItems) {

		Map<String, String> itemData = new HashMap<>();
		int count = 0;
		while (itemList.size() != 0 && count < noOfItems) {
			WebElement bottomElement = itemList.get(itemList.size() - 1);
			WebElement topElement = itemList.get(0);

			for (int i = 0; i < itemPrice.size(); i++) {

				if (!itemData.containsKey(itemTitle.get(i).getText())) {
					itemData.put(itemTitle.get(i).getText(), itemPrice.get(i).getText());
					count = count + 1;
				}
			}

			Dimension dim = driver.manage().window().getSize();

			int startX = dim.getWidth() / 2;
			int startY = (int) (dim.getHeight() / 2 * 0.8);
			int endX = dim.getWidth() / 2;
			int endY = (int) (dim.getHeight() / 2 * 0.2);

			/*
			 * int startX = bottomElement.getLocation().getX() +
			 * (bottomElement.getSize().getWidth() / 2); int startY =
			 * bottomElement.getLocation().getY() + (bottomElement.getSize().getHeight() /
			 * 2);
			 * 
			 * int endX = topElement.getLocation().getX() + (topElement.getSize().getWidth()
			 * / 2); int endY = topElement.getLocation().getY() +
			 * (topElement.getSize().getHeight() / 2);
			 */

			new TouchAction(driver).press(new PointOption<>().withCoordinates(startX, startY))
					.waitAction(WaitOptions.waitOptions(Duration.ofMillis(1000)))
					.moveTo(new PointOption<>().withCoordinates(endX, endY)).release().perform();

			itemList = driver.findElements(By.id("com.ebay.mobile:id/cell_collection_item"));
			itemTitle = driver.findElements(By.xpath(
					"//android.widget.RelativeLayout[@resource-id='com.ebay.mobile:id/cell_collection_item']/android.widget.LinearLayout/android.widget.TextView[@resource-id='com.ebay.mobile:id/textview_item_title']"));
			itemPrice = driver.findElements(By.id("com.ebay.mobile:id/textview_item_price"));

		}

		int count1 = 0;
		for (Map.Entry<String, String> m : itemData.entrySet()) {
			if (count1 < noOfItems) {
				System.out.println("itemName : " + m.getKey() + " price : " + m.getValue());
				count1++;
			} else
				break;
		}

	}

	public void reSearchResults() {
		wait.waitForElement(searchHeader, reader.getExplicitWait(), reader.getPollingTime()).click();
		wait.waitForElement(searchTextField, reader.getExplicitWait(), reader.getPollingTime()).click();
		driver.getKeyboard().sendKeys(Keys.ENTER);
		wait.waitForElement(sortSearch, reader.getExplicitWait(), reader.getPollingTime()).click();
		wait.waitForElement(HighToLowFilter, reader.getExplicitWait(), reader.getPollingTime()).click();
	}

	public void selectFirstProduct() {

		List<WebElement> items = wait.waitForElementsLocatedBy(refreshedItemList, reader.getExplicitWait(),
				reader.getPollingTime());
		items.get(0).click();
	}

}
