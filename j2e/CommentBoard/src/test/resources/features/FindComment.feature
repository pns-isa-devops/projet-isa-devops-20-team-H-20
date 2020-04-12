Feature: Find comment
  This feature supports the way a boss finds a comment

  Background:
    Given some deliveries with date 2020-05-20 time 15:30 and with UIDL45A as package and with date 2020-07-20 time 15:30 and with UIDL45B as package with LePosta as Supplier and 1 as drone and a comment for delivery UIDL45A with rating 4 and comment Ok ca va !

  Scenario: There are 2 deliveries in the system and 1 comment found
    When the client searchs a comment to the delivery UIDL45A
    Then the comment is found

  Scenario: There are 2 deliveries in the system and 1 comment not found
    When the client searchs a comment to the delivery UIDL45B
    Then the comment is not found
