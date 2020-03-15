## Drone Delivery Command-Line Interface (CLI)

## Available Commands

```
DD > ?
  - bye: Exit Cookie on Demand
  - add-delivery (trackingId shippingDateTime(mm-dd-yyyy_hh:mm))
  - edit-delivery-status (trackingId status[completed|failed|inflight])
  - add-drone (id weightCapacity)
  - remove-drone (id)
  - get-drone (id)
  - edit-drone-status (id status[incharge|inmaintenance|ready])
  - add-package (trackingId supplierName packageWeight destinationAddress)
  - edit-package (trackingId supplierName packageWeight destinationAddress)
  - delete-package (trackingId)
  Not for mvp :
  - add-supplier (name)
  - get-suppliers
  - get-paid-invoices
  - get-notpaid-invoices
```
