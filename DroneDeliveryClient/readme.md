## Drone Delivery Command-Line Interface (CLI)

## Available Commands

```
DD > ?
  - bye: Exit Cookie on Demand
  - add-delivery (trackingId shippingDateTime(mm-dd-yyyy_hh:mm))
  - edit-delivery-status (trackingId status[completed|failed|inflight])
  - get-deliveries ()
  - add-drone (id weightCapacity)
  - edit-drone-status (id status[incharge|inmaintenance|ready])
  - remove-drone (id)
  - get-drone (id)
  - add-package (trackingId supplierName packageWeight destinationAddress)
  - edit-package (trackingId supplierName packageWeight destinationAddress)
  - delete-package (trackingId)
  - add-supplier (name contact(mail or phone))
  - get-supplier (name)
  Not for mvp :
  - get-paid-invoices
  - get-notpaid-invoices
```
