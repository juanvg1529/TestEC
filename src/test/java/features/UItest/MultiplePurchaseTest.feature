@UI
Feature: MultiplePurchaseTest

  @Reggresion
  Scenario: The user buy multiple items at the SwagLab web page ant then logout

    Given the user is at the SwagLabs home page
    When the user logs in
    And the user validates that is logged in
    And the user add Items to the marketCar
      | Item                     |
      | Sauce Labs Bolt T-Shirt  |
      | Sauce Labs Fleece Jacket |
      | Sauce Labs Backpack      |
    And the user continues the purchase
    And the user removes one Item
      | Number Of Items to Buy |
      | 2                      |
    And the user provides the personal information purchase
      | Name | LastName | zip   |
      | test | testLAst | 06584 |
    And the user checkout the overview Info of the order
      | CardInfo  | DeliveryInfo |
      | SauceCard | Pony Express |
    Then the user completes the order