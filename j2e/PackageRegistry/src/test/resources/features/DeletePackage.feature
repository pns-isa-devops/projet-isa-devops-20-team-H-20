Feature: Delete drone
  This feature support the way a manutentionnaire deletes a package

  Background:
    Given some packages with trackingIds UIDL45Z UIDL45W and LuPosto as supplier

  Scenario: There is 2 packages in the system and 1 deletion
    When the manutentionnaire deletes the package with trackingId UIDL45Z
    Then there is 1 item in the package list and the package with trackingId UIDL45Z does not exist anymore

  Scenario: There is 2 packages in the system and 2 deletion
    When the manutentionnaire deletes the packages with trackingId UIDL45Z and UIDL45W
    Then there is 0 item in the package list and the package with trackingId UIDL45Z does not exist anymore
    Then there is 0 item in the package list and the package with trackingId UIDL45W does not exist anymore
