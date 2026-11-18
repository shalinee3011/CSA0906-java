# Task 01 — Problem Analysis and Objects
Inputs: registration number, vehicle type, user/pass details, entry/exit times, slot availability and EV charging units. Outputs: assigned slot, unique ticket, parking fee, released slot and daily collection.

Candidate classes: ParkingFacility manages floors and slots; ParkingSlot tracks occupancy; abstract Vehicle stores common registration data; TwoWheeler, Car and ElectricCar specialise vehicle behaviour; ParkingTicket records ID, vehicle, slot and entry time; ParkingUser represents student/staff/visitor; ParkingPass validates entitlement; PricingRule calculates fees; ParkingService coordinates entry and exit. Nouns suggest classes; verbs such as allocate, release, issue, validate and calculate suggest methods.

Rules: one vehicle per slot; ticket IDs are never reused; no duplicate active registration; allocate only compatible free slots; reject invalid releases; valid monthly passes make eligible staff/students free; visitors pay unless a valid one-day pass applies; charging is billed separately.

Assumptions: registration is unique; duration is in minutes; every started hour is billed; rates are configurable; floors may have unequal capacities.

Alternative: objects group attributes and behaviour. Object-list search is O(n); adding an attribute changes a class. Parallel arrays also search in O(n), but every update must keep arrays aligned and a new attribute requires another array. Both use O(n) storage; objects improve cohesion and maintainability.
