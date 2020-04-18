Feature: Add comment
  This feature supports the way a client adds a comment

  Background:
    Given some deliveries with date 2020-05-20 time 15:30 and with UIDL45A as package and with date 2020-07-20 time 15:30 and with UIDL45Y as package with LePosta as Supplier and 1 as drone

  Scenario: There are 2 deliveries in the system and 1 comment added #1
    When the client adds a comment to the delivery UIDL45A with rating 5 and comment Livraison rapide !
    Then there is a comment for the delivery UIDL45A and the comment is Livraison rapide

  Scenario: There are 2 deliveries in the system and 1 comment added #2
    When the client adds a comment to the delivery UIDL45Y with rating 4 and comment Retard !
    Then there is a comment for the delivery UIDL45Y and the comment is Retard
