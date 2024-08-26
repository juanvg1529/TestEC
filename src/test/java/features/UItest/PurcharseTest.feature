Feature: PurcharseTest
  As user I want to interact all over the marvel developer page

  Scenario Outline: The user buy one item the SwagLab web page ant then logout

    Given the user is at the SwagLabs home page
    When the user logs in
    And the user validates that is logged in
    And the user add an <Item> to the marketCar
    And the user continues the purchase
    And the user provides the personal information purchase
      | Name | LastName | zip   |
      | test | testLAst | 06584 |
    And the user checkout the overview Info of the order
      | CardInfo  | DeliveryInfo |
      | SauceCard | Pony Express |
    Then the user completes the order
    Then the user logs out
    Examples:
      | Item                              |
      | Sauce Labs Bike Light             |
      | Test.allTheThings() T-Shirt (Red) |
      | Sauce Labs Onesie                 |
