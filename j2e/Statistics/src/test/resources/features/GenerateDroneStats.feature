Feature: Generate drone stats
  This feature supports the automatic or manual generation of the drones use rate statistics entries

  Background:
    Given some drones with IDs 1 and 2

  Scenario: None of the drones is active and we generate a stats entry
    When 1 drone stats entry is generated
    Then the drone use rate for the stats entry is 0

  Scenario: 1 drone is active and we generate a stats entry
    When the manutentionnaire sends the drone 1
    And 1 drone stats entry is generated
    Then the drone use rate for the stats entry is 0.5