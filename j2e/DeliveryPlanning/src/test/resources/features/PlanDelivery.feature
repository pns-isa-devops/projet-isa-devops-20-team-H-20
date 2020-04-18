Feature: Plan delivery
  This feature supports the way a service client plans a delivery

  Background:
    Given some delivery with date 2020-05-20 time 07:30 and with UIDL45A as package with LePosta as Supplier and 1 as drone and randoms packages UIDL45Z and UIDL45C and UIDL45E

  Scenario: There is 1 delivery in the system and 1 add
    When the service client adds the delivery with date 2020-06-20 time 16:30 and with UIDL45Z as package
    Then there is a planing entry for the delivery UIDL45Z
    Then there is a delivery for package UIDL45Z

  Scenario: There is 1 delivery in the system and 2 add
    When the service client adds the delivery with date 2020-06-20 time 16:30 and UIDL45Z as package and UIDL45C as other package with date 2020-07-20 time 14:30
    Then there is a planing entry for the delivery UIDL45Z
    Then there is a delivery for package UIDL45Z
    Then there is a planing entry for the delivery UIDL45C
    Then there is a delivery for package UIDL45C
