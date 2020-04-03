Feature: Delete supplier
  This feature support the way a gestionnaire deletes a supplier

  Background:
    Given some suppliers with names Nozama contact 0649712257 and LePosta contact posta@mymail.fr

  Scenario: Deleting a supplier
    When the gestionnaire deletes the supplier with name Nozama
    Then the supplier with name Nozama does not exist anymore

  Scenario: Deleting two suppliers
    When the gestionnaire deletes the suppliers with names Nozama and LePosta
    Then the supplier with name Nozama does not exist anymore
    Then the supplier with name LePosta does not exist anymore

  Scenario: Deleting a supplier that does not exist
    When the gestionnaire wants to delete the supplier with name SuperFournisser there is an error
    Then there is an exception
