#Feature: Add drone
#  This feature support the way a Garagiste adds a drone
#
#  Background:
#    Given some drones with ids 1 2 3
#
#  Scenario: There is 3 drones in the system and 1 add
#    When the garagiste adds the drone with id 4
#    Then there is 4 items in the drone list and the drone with id 4 is found
#
#  Scenario: There is 3 drones in the system and 2 add
#    When the garagiste adds the drones with id 4 and 5
#    Then there is 5 items in the drone list and the drone with id 4 is found
#    Then there is 5 items in the drone list and the drone with id 5 is found
#
#  Scenario: There is 3 drones in the system
#    When the garagiste wants to add the drone with id 2 there is an error
#    Then there is an exception
