#Feature: Find supplier
#  This feature support the way a gestionnaire find a supplier
#
#  Background:
#    Given some suppliers with names Nozama contact 0649712257 and LePosta contact posta@mymail.fr
#
#  Scenario: There is 2 suppliers in the system
#    When the gestionnaire search the supplier with name Nozama
#    Then the supplier is found
#
#  Scenario: There is 3 drones in the system
#    When the gestionnaire search the supplier with name LePostaWrongNameAhahaha
#    Then the supplier is not found
