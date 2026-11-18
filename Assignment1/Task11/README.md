# Task 11 — Implementation, Testing and Defence
Test plan (record actual output after running): normal entry/exit; last free slot; full facility; zero-minute stay; exactly 60 minutes; 61 minutes; duplicate active registration; invalid floor/slot; negative duration. Expected: allocation only when compatible capacity exists; release only for valid occupied coordinates; reject invalid inputs.

Manual fee example using illustrative rates: 61-minute two-wheeler = 2 × Rs 10 = Rs 20; 60-minute car = 1 × Rs 30 = Rs 30; total Rs 50 before pass/charging. This is a calculation, not a measured program result.

A simple array or ArrayList search by registration is O(n). HashMap lookup is expected O(1) and uses O(n) storage. In-memory data is lost when the process ends; production needs persistence, concurrency control and payment auditing. Add genuine execution screenshots and actual test results before submission.
