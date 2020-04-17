Feature: Testing the whole Drone Delivery system
  This feature test le entire Drone Delivery system by using every component

  Scenario: Every actor will configure the first drone delivery system
    When the graragiste adds the drone with id 1 and 8.0 kg capacity and 10.5 km/h speed
    And the gestionnaire adds a supplier with name Nozama and contact adressebidon@truc.ouaideouf
#    And the manutentionnaire adds a package with tracking number numerobidon and with weight 10 and with destination Wakanda and with Nozama as supplier
#    And the service client plans a delivery for the package numerobidon with date 2020-12-12 time 12:12
#    And the manutentionnaire sends the delivery for package numerobidon
#    And the delivery for package numerobidon is failed
#    And the service client plans a delivery for the package numerobidon with date 2020-12-15 time 15:15 and with 1 as drone
#    And the manutentionnaire sends the delivery for package numerobidon
#    And a drone statistics entry is generated
#    And the delivery for package numerobidon is successful
#    And the customer adds a comment on the delivery for package numberobidon with rating 8 and comment very good delivery thx m8
#    And a customer satisfaction entry is generated
