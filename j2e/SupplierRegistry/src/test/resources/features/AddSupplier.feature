Feature: Add supplier
  This feature support the way a gestionnaire adds a supplier

  Background:
    Given some suppliers with names Nozama contact 0649712257 and LePosta contact posta@mymail.fr

  Scenario: Adding a supplier
    When the gestionnaire adds the supplier with name Nostromo and contact mysupramail@gmail.com
    Then the supplier with name Nostromo is found

  Scenario: Adding two suppliers
    When the gestionnaire adds the suppliers with name Nostromo and contact mysupramail@gmail.com and name LePosta2 and contact 0649712247
    Then the supplier with name Nostromo is found
    Then the supplier with name LePosta2 is found

  Scenario: Adding an already existing supplier
    When the gestionnaire wants to add the supplier with name Nozama and contact posta@mymail.fr there is an error
    Then there is an exception
