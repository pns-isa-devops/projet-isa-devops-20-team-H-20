Feature: Find supplier
  This feature support the way a gestionnaire find a supplier

  Background:
    Given some suppliers with names Nozama contact 0649712257 and LePosta contact posta@mymail.fr

  Scenario: Searching for a supplier by its name
    When the gestionnaire search the supplier with name Nozama
    Then the supplier is found

  Scenario: Searching for a wrong supplier
    When the gestionnaire search the supplier with name LePostaWrongNameAhahaha
    Then the supplier is not found
