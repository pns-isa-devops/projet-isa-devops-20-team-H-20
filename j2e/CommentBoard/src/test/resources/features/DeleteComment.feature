Feature: Delete comment
  This feature supports the way someone deletes a comment

  Background:
    Given some deliveries with date 2020-05-20 time 15:30 and with UIDL45A as package and with date 2020-07-20 time 15:30 and with UIDL45B as package with LePosta as Supplier and 1 as drone and a comment for delivery UIDL45A with rating 4 and comment Ok ca va !

  Scenario: There are 2 deliveries in the system and 1 comment to delete
    When the client deletes a comment from the delivery UIDL45A
    Then the comment for UIDL45A is deleted

  Scenario: There are 2 deliveries in the system and 1 comment to delete but not found
    When the client deletes a comment that not exists from the delivery with id UIDL45B
    Then there is an exception
