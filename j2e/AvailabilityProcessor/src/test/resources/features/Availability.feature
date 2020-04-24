Feature: Testing the AvailabilityProcessor algorithm
  This feature tests the algorithm

  Background:
    Given 3 drones in the fleet, with speed 60 km/h and max load of 5 kgs

  Scenario: Simple test
    When the gestionnaire adds a supplier with name Amadon and contact abcd
    When the manutentionnaire adds a package with tracking number masaka and with weight 1 kgs and with destination Biot and with Amadon as supplier
    When the service client starts a delivery for package masaka date 2020-10-10 at 10:10
    Then a drone can deliver the package
    Then the drone 0 will deliver the package

  Scenario: Making sure the algorithm chooses the best drone
    Given the drone 0 was already used, has a remaining battery of 10 % and a flight time of 20 hours
    Given the drone 1 was already used, has a remaining battery of 10 % and a flight time of 20 hours
    When the gestionnaire adds a supplier with name Amadon and contact abcd
    When the manutentionnaire adds a package with tracking number masaka and with weight 1 kgs and with destination Biot and with Amadon as supplier
    When the service client starts a delivery for package masaka date 2020-10-10 at 10:10
    Then a drone can deliver the package
    Then the drone 2 will deliver the package

  Scenario: More complex choosing scenario
    #TODO
