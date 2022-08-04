#Author: Gian Espiritu
Feature: Home Page Verification

  @home_page
  Scenario: Check Blog Link displayed
    When User fetches test data "HelloTest" from "TestData.xlsx"
  	Given User navigates to "QATest" site "SIT" environment
  	Given User searches for a text
  	Then Verify search result