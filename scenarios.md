# Drone Delivery Scenario

First of all you can type ? to see the commands we have implemented

## Scenario

Just copy past the following commands to see how our system works :

add-drone 1 10.0 60.0

add-supplier Nozama contact@nozama.com

add-supplier LePosta contact@leposta.com

add-supplier Nozama contact@nozama.com
*Here you can see that we have an exception because the supplier Nozama already exists*

add-package A7X Nozama 5.0 StarCity

add-package A7X Nozama 5.0 StarCity
*Here you can see that we have an exception because the package A7X already exists*

add-package SUM41 Nozama 5.0 Metropolis

add-package 5FDP Nozama 1.0 SmallVille

add-delivery A7X 2020-10-10 10:10

add-delivery SUM41 2020-10-10 10:10
*Here you can see that we have an exception because there are no availables drones*

add-drone 2 9.0 60.0

add-drone 2 9.0 60.0
*Here you can see that we have an exception because the drone with id 2 already exists*

add-delivery SUM41 2020-10-10 10:15

add-delivery 5FDP 2020-10-10 10:55

edit-delivery-status SUM41 completed

add-delivery SUM412 2020-10-10 10:10
*Here you can see that we have an exception because there are no delivery with package SUM412*

edit-delivery-status 5FDP completed

edit-delivery-status A7X completed

post-comment SUM41 5 "Not enough quality"

post-comment 5FDP 8 ""

post-comment A7X 2 ""

get-stats-user-average
