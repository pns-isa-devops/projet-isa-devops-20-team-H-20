#Feature: Add supplier
#  This feature support the way a gestionnaire adds a supplier
#
#  Background:
#    Given some suppliers with names Nozama contact 0649712257 and LePosta contact posta@mymail.fr
#
#  Scenario: There is 2 suppliers in the system and 1 contact add
#    When the gestionnaire add contact supermail@gmoule.com to the supplier Nozama
#    Then the supplier with name Nozama has now supermail@gmoule.com as contact
#
#  Scenario: There is 2 suppliers in the system and 1 contact add
#    When the gestionnaire wants to add contact 0649712257 to the supplier Nozama
#    Then there is an exception
#
