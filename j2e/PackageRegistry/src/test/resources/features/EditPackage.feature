Feature: Edit package
  This feature support the way a manutentionnaire edits a package

  Background:
    Given some suppliers LePosta and Nozama and packages with trackingIds UIDL45A UIDL45K

  Scenario: There is 2 packages in the system and 1 edit
    When the manutentionnaire edit the package with trackingId UIDL45A and set Nozama as supplier
    Then the package with trackingId UIDL45A has now Nozama as supplier

  Scenario: There is 2 packages in the system and 1 deletion
    When the gestionnaire wants to edit the package with trackingId UIDL45F and supplier Nozama there is an error
    Then there is an exception
