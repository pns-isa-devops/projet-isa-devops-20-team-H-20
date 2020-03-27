Feature: Delete drone
  This feature support the way a Garagiste delete a drone

  Background:
    Given some drones with ids 1 3 4

  Scenario: There is 3 drones in the system and 1 deletion
    When the garagiste deletes the drone with id 1
    Then there is 2 items in the drone list and the drone with id 1 does not exist anymore

  Scenario: There is 3 drones in the system and 2 deletion
    When the garagiste deletes the drones with id 1 and 3
    Then there is 1 items in the drone list and the drone with id 1 does not exist anymore
