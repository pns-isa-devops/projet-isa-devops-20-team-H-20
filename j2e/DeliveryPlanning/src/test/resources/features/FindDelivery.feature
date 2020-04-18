Feature: Find delivery
  This feature supports the way a service client finds a delivery

  Background:
    Given some deliveries with date 2020-05-20 time 09:30 and with UIDL45A as package and with date 2020-07-20 time 15:30 and with UIDL45S as package with LePosta as Supplier and 1 as drone and random package UIDL45C

  Scenario: There are 2 deliveries in the system and 1 find delivery #1
    When the service client searches the delivery with UIDL45A as package
    Then the delivery is found

  Scenario: There are 2 deliveries in the system and 1 find delivery #2
    When the service client searches the delivery with UIDL45S as package
    Then the delivery is found

  Scenario: There are 2 deliveries in the system and 1 find planing #1
    When the service client searches the planning entry for delivery with UIDL45S as package
    Then the planning entry is found

  Scenario: There are 2 deliveries in the system and 1 find planing #2
    When the service client searches the planning entry for delivery with UIDL45A as package
    Then the planning entry is found

  Scenario: There are 2 deliveries in the system and 1 not found delivery
    When the service client searches the delivery with UIDL45E as package
    Then the delivery is not found

  Scenario: There are 2 deliveries in the system and 1 not found planing
    When the service client searches the planning entry for delivery with UIDL45E as package
    Then the planning entry is not found

