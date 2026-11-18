# Task 06 — Inheritance Hierarchy
~~~text
Vehicle (abstract)
  |-- TwoWheeler
  |-- Car
       |-- ElectricCar (implements Chargeable)
~~~
Vehicle holds common registration data and declares ratePerHour(); subclasses provide rates. ElectricCar adds charging capability. This demonstrates hierarchical and multilevel inheritance. ElectricCar construction executes Vehicle's constructor first, then Car's constructor body, then ElectricCar's body, through super(...).

Composition alternative: Vehicle holds a VehicleType/PricingStrategy. Adding a type means adding a strategy rather than a subclass. Fee lookup is O(1) in either approach; composition is more flexible when price policies vary independently from vehicle categories, with a strategy reference/object overhead.
