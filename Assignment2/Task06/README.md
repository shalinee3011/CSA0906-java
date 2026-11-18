# Task 06 — Hospital Emergency Triage (8 marks)

A FIFO Queue alone cannot prioritize severity. PriorityQueue uses severity ascending (1 is most critical), then arrival sequence ascending. A monotonically increasing counter preserves FIFO order among equal severities. Adding and polling are O(log n); peek is O(1); storage is O(n).

Do not print PriorityQueue directly to show treatment order: its iterator is not sorted. Poll a copy to display priority order.

Starvation occurs if severe patients continually arrive. A possible aging policy raises effective priority after defined waiting-time thresholds while protecting immediately life-threatening cases. Production must use clinically approved triage rules, not an arbitrary timer.

Alternative 1: sorted LinkedList insertion O(n), next patient O(1), space O(n). Alternative 2: five FIFO queues, one per severity; admission O(1), serving checks at most five queue heads (O(1)), space O(n). For 5,000 patients/day, these operation costs are manageable, but production also needs persistence, concurrency, auditing and approved clinical policy.
