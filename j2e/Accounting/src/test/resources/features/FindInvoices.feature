Feature: Find invoices
  This feature supports the way a gestionnaire finds the invoices

  Background:
    Given some deliveries with UIDL45A and UIDL45B as package with LePosta as supplier and UIDL45C as package with Nozama as supplier and 1 as drone and all deliveries are completed

  Scenario: There are 3 deliveries in the system and we find unpaid invoices
    When the gestionnaire finds the invoices unpaid
    Then there are 2 invoices and paid is false

#  Scenario: There are 3 deliveries in the system and we find unpaid invoices but one is paid
#    When the gestionnaire finds the invoices unpaid but Nozama is paid
#    Then there are 2 invoices and paid is false for supplier LePosta because for Nozama paid is true

