Feature: Testing the whole Drone Delivery system
  This feature test le entire Drone Delivery system by using every component

  Scenario: Planing two deliveries with one drone at not at same time
    When the graragiste adds the drone with id 1 and 10.0 kg capacity and 60.0 km/h speed
    And the gestionnaire adds a supplier with name Nozama and contact adressebidon@truc.ouaideouf
    And the manutentionnaire adds a package with tracking number A7X and with weight 5.0 and with destination Wakanda and with Nozama as supplier
    And the manutentionnaire adds a package with tracking number SUM41 and with weight 5.0 and with destination Wakanda and with Nozama as supplier
    And the service client plan a delivery for package A7X date 2020-10-10 at 10:10
    And the service client tries to plan a delivery for package SUM41 date 2020-10-10 at 15:15
    Then the delivery with package A7X as 1 as drone id
    And the delivery with package SUM41 as 1 as drone id

  Scenario: Planing two deliveries with one drone at almost same time
    When the graragiste adds the drone with id 1 and 10.0 kg capacity and 60.0 km/h speed
    And the gestionnaire adds a supplier with name Nozama and contact adressebidon@truc.ouaideouf
    And the manutentionnaire adds a package with tracking number A7X and with weight 5.0 and with destination Wakanda and with Nozama as supplier
    And the manutentionnaire adds a package with tracking number SUM41 and with weight 5.0 and with destination Wakanda and with Nozama as supplier
    And the service client plan a delivery for package A7X date 2020-10-10 at 10:10
    And the service client tries to plan a delivery for package SUM41 date 2020-10-10 at 10:15
    Then the delivery with package A7X as 1 as drone id
    But the delivery with package SUM41 has not been planned

  Scenario: Planing two deliveries with two drones at almost same time
    When the graragiste adds the drone with id 1 and 10.0 kg capacity and 60.0 km/h speed
    And the graragiste adds the drone with id 2 and 10.0 kg capacity and 60.0 km/h speed
    And the gestionnaire adds a supplier with name Nozama and contact adressebidon@truc.ouaideouf
    And the manutentionnaire adds a package with tracking number A7X and with weight 5.0 and with destination Wakanda and with Nozama as supplier
    And the manutentionnaire adds a package with tracking number SUM41 and with weight 5.0 and with destination Wakanda and with Nozama as supplier
    And the service client plan a delivery for package A7X date 2020-10-10 at 10:10
    And the service client plan a delivery for package SUM41 date 2020-10-10 at 10:15
    Then the delivery with package A7X as 1 as drone id
    And the delivery with package SUM41 as 2 as drone id

   # 5FDP avec drone 2 car poids moins élevé + moins volé que drone 1
  Scenario: Planing three deliveries with two drones at almost same time
    When the graragiste adds the drone with id 1 and 10.0 kg capacity and 60.0 km/h speed
    And the gestionnaire adds a supplier with name Nozama and contact adressebidon@truc.ouaideouf
    And the manutentionnaire adds a package with tracking number A7X and with weight 5.0 and with destination Wakanda and with Nozama as supplier
    And the manutentionnaire adds a package with tracking number SUM41 and with weight 5.0 and with destination Wakanda and with Nozama as supplier
    And the manutentionnaire adds a package with tracking number 5FDP and with weight 1.0 and with destination Wakanda and with Nozama as supplier
    And the service client plan a delivery for package A7X date 2020-10-10 at 10:10
    And the graragiste adds the drone with id 2 and 9.0 kg capacity and 60.0 km/h speed
    And the service client plan a delivery for package SUM41 date 2020-10-10 at 10:15
    And the service client plan a delivery for package 5FDP date 2020-10-11 at 10:55
    Then the delivery with package A7X as 1 as drone id
    And the delivery with package SUM41 as 2 as drone id
    And the delivery with package 5FDP as 2 as drone id

  Scenario: Planing two deliveries with two drone but one is too weak
    When the graragiste adds the drone with id 1 and 10.0 kg capacity and 60.0 km/h speed
    And the graragiste adds the drone with id 2 and 2.0 kg capacity and 60.0 km/h speed
    And the gestionnaire adds a supplier with name Nozama and contact adressebidon@truc.ouaideouf
    And the manutentionnaire adds a package with tracking number A7X and with weight 5.0 and with destination Wakanda and with Nozama as supplier
    And the manutentionnaire adds a package with tracking number SUM41 and with weight 5.0 and with destination Wakanda and with Nozama as supplier
    And the service client plan a delivery for package A7X date 2020-10-10 at 10:10
    And the service client tries to plan a delivery for package SUM41 date 2020-10-10 at 10:15
    Then the delivery with package A7X as 1 as drone id
    But the delivery with package SUM41 has not been planned

  Scenario: Planing one delivery without drone
    When the gestionnaire adds a supplier with name Nozama and contact adressebidon@truc.ouaideouf
    And the manutentionnaire adds a package with tracking number A7X and with weight 5.0 and with destination Wakanda and with Nozama as supplier
    And the manutentionnaire adds a package with tracking number SUM41 and with weight 5.0 and with destination Wakanda and with Nozama as supplier
    And the service client tries to plan a delivery for package A7X date 2020-10-10 at 10:15
    But the delivery with package A7X has not been planned
