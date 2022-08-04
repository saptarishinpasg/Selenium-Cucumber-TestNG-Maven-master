#Author: Gian Espiritu
Feature: Perform Login DemoWebshop

  @login_account
  Scenario: Verify account login
    When User fetches test data "LoginTestValid" from "TestData.xlsx"
		Given User navigates to "WebShop" site "pte" environment
		And User with valid credential logs in
  	Then User account is logged in