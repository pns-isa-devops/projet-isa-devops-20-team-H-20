#Feature: Delete supplier
#  This feature support the way a gestionnaire deletes a supplier
#
#  Background:
#    Given some suppliers with names Nozama contact 0649712257 and LePosta contact posta@mymail.fr
#
#  Scenario: There is 2 suppliers in the system and 1 deletion
#    When the gestionnaire deletes the supplier with name Nozama
#    Then there is 1 item in the supplier list and the supplier with name Nozama does not exist anymore
#
#  Scenario: There is 2 suppliers in the system and 2 deletion
#    When the gestionnaire deletes the suppliers with names Nozama and LePosta
#    Then there is 0 item in the supplier list and the supplier with name Nozama does not exist anymore
#    Then there is 0 item in the supplier list and the supplier with name LePosta does not exist anymore
#
#  Scenario: There is 2 suppliers in the system and 1 deletion
#    When the gestionnaire wants to delete the supplier with name SuperFournisser there is an error
#    Then there is an exception
