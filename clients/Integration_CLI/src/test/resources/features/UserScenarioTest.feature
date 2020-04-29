Feature: Testing the whole Drone Delivery system
  This feature test le entire Drone Delivery system by using every component

  Background:
    Given some lists to remove things

  Scenario: Planning two deliveries with one drone not at same time
    When the garagiste adds the drone with id 1 and 10.0 kg capacity and 60.0 km/h speed
    And the gestionnaire adds a supplier with name Nozama and contact adressebidon@truc.ouaideouf
    And the manutentionnaire adds a package with tracking number A7X and with weight 5.0 and with destination Wakanda and with Nozama as supplier
    And the manutentionnaire adds a package with tracking number SUM41 and with weight 5.0 and with destination Wakanda and with Nozama as supplier
    And the service client plan a delivery for package A7X date 2020-10-10 at 10:10
    And the service client plan a delivery for package SUM41 date 2020-10-10 at 15:15
    Then the delivery with package A7X have 1 as drone id
    And the delivery with package SUM41 have 1 as drone id

  Scenario: Planning two deliveries with one drone at almost same time
    When the garagiste adds the drone with id 1 and 10.0 kg capacity and 60.0 km/h speed
    And the gestionnaire adds a supplier with name Nozama and contact adressebidon@truc.ouaideouf
    And the manutentionnaire adds a package with tracking number A7X and with weight 5.0 and with destination Wakanda and with Nozama as supplier
    And the manutentionnaire adds a package with tracking number SUM41 and with weight 5.0 and with destination Wakanda and with Nozama as supplier
    And the service client plan a delivery for package A7X date 2020-10-10 at 10:10
    And the service client tries to plan a delivery for package SUM41 date 2020-10-10 at 10:10
    Then the delivery with package A7X have 1 as drone id
    But the delivery with package SUM41 has not been planned because there was no ready drone

   # 5FDP avec drone 2 car poids moins élevé + moins volé que drone 1
  Scenario: Planning three deliveries with two drones at almost same time
    When the garagiste adds the drone with id 1 and 10.0 kg capacity and 60.0 km/h speed
    And the gestionnaire adds a supplier with name Nozama and contact adressebidon@truc.ouaideouf
    And the manutentionnaire adds a package with tracking number A7X and with weight 5.0 and with destination Wakanda and with Nozama as supplier
    And the manutentionnaire adds a package with tracking number SUM41 and with weight 5.0 and with destination Wakanda and with Nozama as supplier
    And the manutentionnaire adds a package with tracking number 5FDP and with weight 1.0 and with destination Wakanda and with Nozama as supplier
    And the service client plan a delivery for package A7X date 2020-10-10 at 10:10
    And the garagiste adds the drone with id 2 and 9.0 kg capacity and 60.0 km/h speed
    And the service client plan a delivery for package SUM41 date 2020-10-10 at 10:10
    And the service client plan a delivery for package 5FDP date 2020-10-11 at 10:55
    Then the delivery with package A7X have 1 as drone id
    And the delivery with package SUM41 have 2 as drone id
    And the delivery with package 5FDP have 2 as drone id

  Scenario: Planning two deliveries with two drone but one is too weak
    When the garagiste adds the drone with id 1 and 10.0 kg capacity and 60.0 km/h speed
    And the garagiste adds the drone with id 2 and 2.0 kg capacity and 60.0 km/h speed
    And the gestionnaire adds a supplier with name Nozama and contact adressebidon@truc.ouaideouf
    And the manutentionnaire adds a package with tracking number A7X and with weight 5.0 and with destination Wakanda and with Nozama as supplier
    And the manutentionnaire adds a package with tracking number SUM41 and with weight 5.0 and with destination Wakanda and with Nozama as supplier
    And the service client plan a delivery for package A7X date 2020-10-10 at 10:10
    And the service client tries to plan a delivery for package SUM41 date 2020-10-10 at 10:10
    Then the delivery with package A7X have 1 as drone id
    But the delivery with package SUM41 has not been planned because there was no ready drone

  Scenario: Planning one delivery without drone
    When the gestionnaire adds a supplier with name Nozama and contact adressebidon@truc.ouaideouf
    And the manutentionnaire adds a package with tracking number A7X and with weight 5.0 and with destination Wakanda and with Nozama as supplier
    And the manutentionnaire adds a package with tracking number SUM41 and with weight 5.0 and with destination Wakanda and with Nozama as supplier
    And the service client tries to plan a delivery for package A7X date 2020-10-10 at 10:15
    But the delivery with package A7X has not been planned because there was no ready drone

  Scenario: Set drone in flight to verify statistics
    When the garagiste adds the drone with id 1 and 10.0 kg capacity and 60.0 km/h speed
    And the garagiste edits the drone with id 1 and set the status to flight
    And the boss generates the statistics for drones
    Then the drone statistics entry has a use rate 1.0

  Scenario: Set one drone in flight to verify statistics
    When the garagiste adds the drone with id 1 and 10.0 kg capacity and 60.0 km/h speed
    And the garagiste adds the drone with id 2 and 10.0 kg capacity and 60.0 km/h speed
    And the garagiste edits the drone with id 1 and set the status to flight
    And the boss generates the statistics for drones
    Then the drone statistics entry has a use rate 0.50

   # good scenario
  Scenario: A client post a comment
    When the garagiste adds the drone with id 1 and 10.0 kg capacity and 60.0 km/h speed
    And the gestionnaire adds a supplier with name Nozama and contact adressebidon@truc.ouaideouf
    And the gestionnaire adds a supplier with name LePosta and contact leposta@mysupramail.com
    And the manutentionnaire adds a package with tracking number A7X and with weight 5.0 and with destination Wakanda and with Nozama as supplier
    And the manutentionnaire adds a package with tracking number SUM41 and with weight 5.0 and with destination Wakanda and with Nozama as supplier
    And the manutentionnaire adds a package with tracking number 5FDP and with weight 1.0 and with destination Wakanda and with Nozama as supplier
    And the service client plan a delivery for package A7X date 2020-10-10 at 10:10
    And the garagiste adds the drone with id 2 and 9.0 kg capacity and 60.0 km/h speed
    And the service client plan a delivery for package SUM41 date 2020-10-10 at 10:15
    And the service client plan a delivery for package 5FDP date 2020-10-11 at 10:55
    And the manutentionnaire edits the delivery status to completed for tracking id SUM41
    And the manutentionnaire edits the delivery status to completed for tracking id 5FDP
    And the manutentionnaire edits the delivery status to completed for tracking id A7X
    And the client adds a comment for the delivery SUM41 with rate 5 and comment "Not enough quality"
    And the client adds a comment for the delivery 5FDP with rate 8 and comment ""
    And the client adds a comment for the delivery A7X with rate 2 and comment ""
    And the boss generates the statistics for ratings
#    And the gestionnaire generates the invoice for Nozama
#    And the gestionnaire generates the invoice for LePosta
#    And the supplier Nozama pay the invoice
#    Then there is a comment for the package SUM41 with rate 5 and comment "Not enough quality"
#    And there is a comment for the package 5FDP with rate 8 and comment ""
#    And there is a comment for the package A7X with rate 2 and comment ""
#    And the rating statistics entry as 5 as average
#    And the invoice for Nozama is about 3€
#    And the invoice for LePosta is about 0€
#
