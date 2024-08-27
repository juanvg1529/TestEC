Feature: PetShopAPITest

  Scenario: The user interacts with the shop through an API of a pet shop
    Given the user Get all of the Pets "200"
    When The user add a new Pet
      | ID   | Name              |
      | 1529 | DoggieTestPet1529 |
    And the user consults the pet Created "200"
    When the user updates an existing pet
      | Name              |
      | TestPet1529Doggie |
    And the deletes consults the pet Created
    Then then the user consults the pet Created