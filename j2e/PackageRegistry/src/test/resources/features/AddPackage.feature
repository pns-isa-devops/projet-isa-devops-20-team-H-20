Feature: Add package
  This feature support the way a manutentionnaire adds a package

  Background:
    Given some packages with trackingIds UIDL45A UIDL45B and LePosta as supplier

  Scenario: There is 2 packages in the system and 1 add
    When the manutentionnaire adds the package with trackingId UIDL45C and LePosta as supplier
    Then the package with trackingId UIDL45C is found

  Scenario: There is 2 packages in the system and 2 add
    When the manutentionnaire adds the packages with trackingId UIDL45C and UIDL45D and LePosta as supplier
    Then the package with trackingId UIDL45C is found
    Then the package with trackingId UIDL45D is found

  Scenario: There is 2 packages in the system and 1 addition
    When the gestionnaire wants to add the package with trackingId UIDL45A and supplier LePosta there is an error
    Then there is an exception
