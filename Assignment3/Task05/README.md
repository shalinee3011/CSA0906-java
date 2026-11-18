# Q5 — Cinema seat-booking race condition (6 marks)

SeatBooking.java launches 100 users competing for 10 seats. The check-and-set operation is synchronized so each seat can be sold only once. Expected output: Seats sold: 10. Remove synchronized temporarily to observe a race; the unsynchronized outcome is timing-dependent and may not reproduce every run. A reliable stress test can use a CountDownLatch to release many contenders simultaneously and repeat many trials.

The critical section is checking booked[seat], setting it true and incrementing bookingsMade. A synchronized instance method locks the SeatBooking object. A synchronized block on the same object protects the same state with a narrower region. Static synchronization locks the Class object and is needed only when coordinating static shared state across instances.

For s seats, the boolean array is O(s); each booking attempt is O(1) absent contention. Under contention, threads may block and throughput is limited by the serialized critical section. AtomicBoolean[s] with compareAndSet also uses O(s) references/objects and gives O(1) expected CAS per attempt; ReentrantLock uses O(1) lock state plus the seat array. A ConcurrentHashMap with putIfAbsent is useful for sparse seat IDs but has O(s) entries.
