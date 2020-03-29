Feature: Find package
  This feature support the way a manutentionnaire find a package

  Background:
    Given some packages with trackingIds UIDL45A UIDL45B and LePosta as supplier

  Scenario: There is 2 packages in the system
    When the manutentionnaire search the package with id UIDL45B
    Then the package is found

  Scenario: There is 3 drones in the system
    When the manutentionnaire search the package with id UIDL45Z
    Then the package is not found
