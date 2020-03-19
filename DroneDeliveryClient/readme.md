## Drone Delivery Command-Line Interface (CLI)

### Technology
[Java 8](https://www.java.com/fr/download/)

## Available Commands
```
DD > ?
  - bye: Exit Cookie on Demand
  - add-delivery (trackingId shippingDateTime(yyyy-mm-dd) shippingTime(hh:mm))
  - edit-delivery-status (trackingId status[completed|failed|inflight])
  - get-deliveries ()
  - add-drone (id weightCapacity)
  - edit-drone-status (id status[charge|maintenance|ready])
  - remove-drone (id)
  - get-drone (id)
  - add-package (trackingId supplierName packageWeight destinationAddress)
  - edit-package (trackingId supplierName packageWeight destinationAddress)
  - remove-package (trackingId)
  - add-supplier (name contact(mail or phone))
  - add-supplier-contact (name contact(mail or phone))
  - get-supplier (name)
  - remove-supplier (name)
  - get-suppliers ()
  Not for mvp :
  - get-paid-invoices
  - get-notpaid-invoices
```

### How to use
To launch the project get to the root project then use this command line `mvn clean package` then `mvn exec:java`
