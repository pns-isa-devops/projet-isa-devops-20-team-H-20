Feature: Edit delivery
  This feature supports the way a service client edits a delivery

  Background:
    Given some deliveries with date 2020-05-20 time 10:30 and with UIDL45A as package and with date 2020-07-20 time 15:30 and with UIDL45T as package with LePosta as Supplier and 1 as drone and random package UIDL45C

  Scenario: Set delivery completed
    When the service client edits the delivery with package UIDL45A and put the status to completed
    Then the delivery with package UIDL45A has a completed status

  Scenario: Set delivery in flight
    When the service client edits the delivery with package UIDL45T and put the status to in-flight
    Then the delivery with package UIDL45T has a in-flight status

  Scenario: Set delivery failed
    When the service client edits the delivery with package UIDL45A and put the status to failed
    Then the delivery with package UIDL45A has a failed status

  Scenario: Set delivery not sent
    When the service client edits the delivery with package UIDL45T and put the status to not-sent
    Then the delivery with package UIDL45T has a not-sent status

