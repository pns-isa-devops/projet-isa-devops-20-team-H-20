Feature: Edit drone
  This feature support the way a Garagiste edits a drone

  Background:
    Given some drones with ids 5 6 7

  Scenario: Set drone in maintenance
    When the garagiste edit the drone with id 5 and put the status to maintenance
    Then the drone with id 5 has a maintenance status

  Scenario: Set drone in flight
    When the garagiste edit the drone with id 5 and put the status to flight
    Then the drone with id 5 has a flight status

  Scenario: Set drone in charge
    When the garagiste edit the drone with id 6 and put the status to charge
    Then the drone with id 6 has a charge status

  Scenario: Set drone ready
    When the garagiste edit the drone with id 7 and put the status to ready
    Then the drone with id 7 has a ready status

  Scenario: Editing status of a non-existing drone
    When the garagiste wants to edit the drone with id 10 and set status to ready there is an error
    Then there is an exception
