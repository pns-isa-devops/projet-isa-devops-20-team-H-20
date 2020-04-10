Feature: Delete drone
  This feature support the way a Garagiste delete a drone

  Background:
    Given some drones with ids 1 3 4

  Scenario: Deleting a drone
    When the garagiste deletes the drone with id 1
    Then the drone with id 1 does not exist anymore

  Scenario: Deleting 2 drones
    When the garagiste deletes the drones with id 1 and 3
    Then the drone with id 1 does not exist anymore

  Scenario: Deleting a non-existing drone
    When the garagiste wants to delete the drone with id 10 there is an error
    Then there is an exception
