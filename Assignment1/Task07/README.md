# Task 07 — Method Overriding
Subclasses override ratePerHour(); calculateFee() invokes the correct method dynamically. @Override catches signature mistakes. An override must have a compatible signature/return type and cannot reduce access. Use final for immutable identifiers and intentionally fixed behaviour.

equals() and hashCode() must use the same identity rule. If registration number alone defines identity, a Car and ElectricCar with the same registration compare equal; decide whether that matches the system's identity policy. A switch-based fee method is typically O(1), but every new category requires editing the central switch. Overriding localises category behaviour.
