Feature: MultiplePurchaseTest


  Scenario: The user buy an item after Filtering at the SwagLab web page ant then logout

    Given the user is at the SwagLabs home page
    When the user logs in
    And the user validates that is logged in
    And the user filters the WebPage
      | Filter        |
      | Name (Z to A) |
    And the user selects the item after filtering
    And the user continues the purchase
    And the user provides the personal information purchase
      | Name | LastName | zip   |
      | test | testLAst | 06584 |
    And the user checkout the overview Info of the order
      | CardInfo  | DeliveryInfo |
      | SauceCard | Pony Express |
    Then the user completes the order