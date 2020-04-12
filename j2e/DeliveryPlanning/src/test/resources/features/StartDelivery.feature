Feature: Start delivery
  This feature supports the way a manutentionnaire starts a delivery

  Background:
    Given some deliveries with date 2020-05-20 time 15:30 and with UIDL45A as package and with date 2020-05-20 time 15:30 and with UIDL45B as package with LePosta as Supplier and 1 as drone and random package UIDL45C

  Scenario: There are 2 deliveries in the system and 1 start
    When the manutentionnaire starts the delivery with UIDL45A as package and drone was 1
    Then the delivery with package UIDL45A has a in-flight status

