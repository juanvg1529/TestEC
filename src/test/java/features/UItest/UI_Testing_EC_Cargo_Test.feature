Feature: UI_Testing_EC_Cargo_Test
  As user I want to interact all over the marvel developer page

  Scenario: The user log in into the marvel web page ant then logout

    Given the user is at the SwagLabs home page
    And the user select the option to log in
    When the user provide the user credentials
    Then the user validates that is logged in
    And the user logs out

  Scenario: The user log in into the marvel web page ant then logout2

    Given the user is at the SwagLabs home page
    And the user select the option to log in
    When the user provide the user credentials
    Then the user validates that is logged in
    And the user logs out