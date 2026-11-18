# Task 05 — Static Members and Object Life Cycle
A ticket sequence is class-wide, so a static counter (or database sequence) avoids duplicate IDs across north/south facilities. A per-facility counter is appropriate only if ticket IDs include a facility identifier. Static methods suit shared ID generation and stateless validation; static blocks suit immutable shared setup.

After exit, active collections and slots stop referencing the active ticket/vehicle. It becomes eligible for garbage collection only when no reachable reference remains; history may retain it. For k facilities, instance counters use O(k) counter storage; one static counter uses O(1). n retained tickets use O(n) storage.
