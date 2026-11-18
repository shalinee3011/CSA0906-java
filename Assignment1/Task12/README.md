# Task 12 — Day-4 Design Challenge
Changed requirements: add e-bikes with a flat fee, delivery vans charged at double car rate during 09:00–17:00, visitor one-day passes, and five floors with unequal capacities. A jagged array supports unequal floor sizes.

Retain the abstract vehicle concept and slot allocator, but introduce PricingRule and ParkingPass abstractions. E-bike uses a flat fee; delivery van uses a time-window rule; a valid visitor one-day pass waives parking fee. Pricing is separated from slot allocation, so adding a category need not modify the allocator. Trade-off: more strategy/pass classes, but fewer changes to tested code. See AdaptedParkingDemo.java.
