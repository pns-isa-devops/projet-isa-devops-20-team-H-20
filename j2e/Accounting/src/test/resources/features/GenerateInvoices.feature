Feature: Generate invoices
  This feature supports the way a gestionnaire generates the invoices

  Background:
    Given some deliveries with UIDL45A and UIDL45B as package with LePosta as supplier and UIDL45C as package with Nozama as supplier and 1 as drone

  Scenario: There are 3 deliveries in the system and 1 generation for every suppliers without completed delivery
    When the gestionnaire generates the invoices without any completed delivery
    Then there are 2 more invoices with 0 as price

  Scenario: There are 3 deliveries in the system and 1 generation for every suppliers
    When the gestionnaire generates the invoices
    Then there are 2 more invoices with 2.0 as price for LePosta and 1.0 as price for Nozama
