@UI
Feature: LoginTest
  As user I want to interact all over the marvel developer page

  Scenario: The user log in into the SwagLab web page ant then logout

    Given the user is at the SwagLabs home page
    When the user logs in
    Then And the user validates that is logged in
    And the user logs out

