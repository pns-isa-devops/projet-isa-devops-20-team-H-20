Feature: Generate drone stats
  This feature supports the automatic or manual generation of the drones use rate statistics entries

  Background:
    Given some delivery with date 2020-05-20 time 08:30 and with UIDL0 as package with supp1 as Supplier and 1 as drone
    And some delivery with date 2020-05-20 time 21:30 and with UIDL1 as package with supp2 as Supplier and 2 as drone

  Scenario: No comment has been added and a stats entry is generated
    When 1 customer stats entry is generated
    Then the customer satisfaction rate for the stats entry is 0

  Scenario: One comment is added and a stats entry is generated
    When a comment with a score of 10 is added on delivery UIDL0
    And 1 customer stats entry is generated
    Then the customer satisfaction rate for the stats entry is 10

  Scenario: Two comments are added and a stats entry is generated
    When a comment with a score of 7 is added on delivery UIDL0
    And a comment with a score of 3 is added on delivery UIDL1
    And 1 customer stats entry is generated
    Then the customer satisfaction rate for the stats entry is 5
