# Task 04 — Pharmacy Inventory Maps (6 marks)

Use medicine code as key and stock/medicine record as value. HashMap provides expected O(1) put/get and no guaranteed iteration order. TreeMap provides O(log n) put/get and sorted-key iteration, naturally producing the code-order report. LinkedHashMap provides expected O(1) put/get and insertion-order iteration, naturally producing the added-order report.

If the wrong map is used, copy and sort keys for code order (O(n log n)) or maintain a separate insertion-order view. Hashtable is a legacy synchronized API; modern code normally uses HashMap with explicit concurrency control or ConcurrentHashMap when appropriate.

The program benchmarks lookup and report generation for three inventory sizes. Define an acceptable lookup threshold and compare your own run results. Binary-search alternative: sorted array lookup O(log n), insertion O(n) due to shifting, code-order report O(n), storage O(n). HashMap and TreeMap both use O(n) storage.
