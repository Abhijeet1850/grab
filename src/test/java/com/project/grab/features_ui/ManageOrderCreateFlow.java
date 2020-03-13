package com.project.grab.features_ui;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.assertj.core.api.Assertions;

import com.project.grab.PageActions.Homepage;
import com.project.grab.PageActions.ProductDescriptionPage;
import com.project.grab.PageActions.ProductListPage;
import com.project.grab.TestBase.TestBase;
import com.project.grab.helper.configReader.PropertyReader;
import com.project.grab.helper.logger.LoggerHelper;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import io.cucumber.datatable.DataTable;

public class ManageOrderCreateFlow extends TestBase {

	private Logger log = LoggerHelper.getLogger(ManageOrderCreateFlow.class);

	Homepage homepage = new Homepage(driver);
	ProductListPage plp = new ProductListPage(driver);
	ProductDescriptionPage pdp = new ProductDescriptionPage(driver);
	PropertyReader reader = new PropertyReader();

	@Given("I open the Ebay App")
	public void i_open_the_Ebay_App() {

	}

	@Given("I enter the search text {string} on homepage")
	public void i_enter_the_search_text_on_homepage(String searchItem) {
		homepage.searchItem(searchItem);
	}

	@Given("I select the Sort filter as {string}")
	public void i_select_the_Sort_filter_as(String filterText) {
		plp.ifSaveTextPresentAcknowledge();
		plp.applySortFilter(filterText);

	}

	@Given("I fetch the first {int} Elements with their Details")
	public void i_fetch_the_first_Elements_with_their_Details(Integer int1) {
		plp.printItemNamePrice(10);

	}

	@Given("I click on product link for {int} product in the list")
	public void i_click_on_product_link_for_product_in_the_list(Integer int1) {
		plp.reSearchResults();
		plp.selectFirstProduct();
	}

	@Given("I Click on {string} Button")
	public void i_Click_on_Button(String string) {
		pdp.clickWatchButton();
	}

	@Then("I should get Signin Option Page")
	public void i_should_get_Signin_Option_Page() {
		Assertions.assertThat(pdp.isSignInHeaderDisplayed()).as("This product is having some attributes to be selected")
				.isTrue();
	}

	@Then("I select {string} option")
	public void i_select_option(String string) {
		pdp.selectUsernameOption();

	}

	@Then("I enter the username as {string}")
	public void i_enter_the_username_as(String userName) {
		pdp.enterUserName(userName);
	}

	@Then("I click on Close button")
	public void i_click_on_Close_button() {
		pdp.closeSignIn();
	}
}
