Feature: Edit invoices
  This feature supports the way a gestionnaire edits the invoices

  Background:
    Given some deliveries with UIDL45A and UIDL45B as package with LePosta as supplier and UIDL45C as package with Nozama as supplier and 1 as drone and all deliveries are completed

  Scenario: There are 3 deliveries in the system and we edit an invoice
    When the gestionnaire edits the invoice for Nozama
    Then there invoice for Nozama is now paid

  Scenario: There are 3 deliveries in the system and we edit an invoice
    When the gestionnaire edits the invoice for LePosta
    Then there invoice for LePosta is now paid



