Feature: Delete drone
  This feature support the way a Garagiste delete a drone

  Background:
    Given some drones with ids 1 3 4

  Scenario: There is 3 drones in the system
    When the garagiste delete the drone with id 1
    Then there is 2 items in the drone list
