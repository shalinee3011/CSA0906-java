# Task 09 — Abstraction
Vehicle is abstract because it has shared registration state and fee behaviour while each category supplies its own rate. Chargeable is an interface because charging is a capability that only some vehicles have; a class can implement multiple interfaces. Default interface methods provide shared optional behaviour; static interface methods provide related utilities.

If Vehicle became an interface, it could not initialise per-object instance state through constructors. If Chargeable became an abstract class, ElectricCar could not extend both Car and Chargeable because Java permits one superclass. Method calls remain O(1); storage differences are constant per object.
