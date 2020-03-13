@test
Feature: Create Order Flow in Demo Blaze

  @tag1
  Scenario: Verify Order Placement on Demo Blaze
    Given I open the Ebay App
    And I enter the search text "Watch" on homepage
    And I select the Sort filter as "High to Low"
    And I fetch the first 10 Elements with their Details
    And I click on product link for 1 product in the list
    And I Click on "Watch" Button
    Then I should get Signin Option Page 
    And I select "Use Email Or UserName" option
    And I enter the username as "Abc@gmail.com"
    And I click on Close button