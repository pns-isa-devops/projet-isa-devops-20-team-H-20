Feature: Delete drone
  This feature support the way a Garagiste adds a drone

  Background:
    Given some drones with ids 1 2 3

  Scenario: There is 3 drones in the system
    When the garagiste search the drone with id 2
    Then the drone is found

  Scenario: There is 3 drones in the system
    When the garagiste search the drone with id 8
    Then the drone is not found
