Feature: Add drone
  This feature support the way a Garagiste adds a drone

  Background:
    Given some drones with ids 1 2 3

  Scenario: Adding one drone
    When the garagiste adds the drone with id 4
    Then the drone with id 4 is found

  Scenario: Adding 2 drones
    When the garagiste adds the drones with id 4 and 5
    Then the drone with id 4 is found
    Then the drone with id 5 is found

  Scenario: Adding an already existing drone
    When the garagiste wants to add the drone with id 2 there is an error
    Then there is an exception
