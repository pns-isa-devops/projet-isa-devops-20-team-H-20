Feature: Edit drone
  This feature support the way a Garagiste adds a drone

  Background:
    Given some drones with ids 5 6 7

  Scenario: There is 3 drones in the system
    When the garagiste edit the drone with id 5 and put the status to maintenance
    Then the drone with id 5 has a maintenance status

  Scenario: There is 3 drones in the system
    When the garagiste edit the drone with id 5 and put the status to flight
    Then the drone with id 5 has a flight status

  Scenario: There is 3 drones in the system
    When the garagiste edit the drone with id 6 and put the status to charge
    Then the drone with id 6 has a charge status

  Scenario: There is 3 drones in the system
    When the garagiste edit the drone with id 7 and put the status to ready
    Then the drone with id 7 has a ready status
