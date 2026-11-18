# Task 08 — Polymorphism
Compile-time polymorphism: overload calculateFee(int) and calculateFee(LocalTime,LocalTime); the compiler chooses by argument types/count. Runtime polymorphism: Vehicle[] stores TwoWheeler, Car and ElectricCar references. For calculateFee(130), declared type is Vehicle but the actual object's overridden rate is used.

ElectricCar: 130 minutes rounds to 3 hours × Rs 30 = Rs 90; charging adds 10 × Rs 12 = Rs 120; total Rs 210. The final equality example is true because equals() compares registration only. That is correct only if registration alone defines vehicle identity. See FeeDemo.java.
