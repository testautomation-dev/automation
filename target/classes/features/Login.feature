Feature: Login Module
  Test Login module functionality

  Scenario: Login into EY
    Given Browser is open
    When I login into EY mail
    Then I am able to see the main page
    Then exit the page