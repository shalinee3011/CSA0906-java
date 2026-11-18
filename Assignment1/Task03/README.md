# Task 03 — Control Logic and Pseudocode
Allocation: scan each floor and slot; on first empty slot, store vehicle number and return coordinates; if none is free, return null. Release: validate floor and slot bounds, reject an already-empty slot, clear it and return true.

Fee pseudocode: reject negative duration; compute started hours as (minutes + 59) / 60; multiply by category rate; apply daily cap; waive parking fee for a valid eligible pass; add EV charging separately.

A scan takes O(N) worst case for N total slots; release by coordinates is O(1). A stack of free coordinates can provide O(1) amortised allocation/release with O(N) extra space. See ParkingFacility.java and expected-output.txt.
