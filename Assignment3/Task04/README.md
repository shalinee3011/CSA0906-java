# Q4 — Thread creation and life cycle (7 marks)

ScannerStations.java implements the four stations with Runnable and demonstrates the Thread subclass pattern. start() asks the JVM to schedule a new thread; run() called directly is an ordinary method call on the current thread. sleep() pauses the current thread for a duration, join() waits for another thread to finish, and isAlive() checks whether it is still running.

The life cycle includes NEW, RUNNABLE (including executing), BLOCKED, WAITING, TIMED_WAITING and TERMINATED. Thread scheduling and sleep durations mean completion order can vary; correct code must not rely on it. join() ensures all workers finish before the final total is printed. Priority is only a scheduling hint and does not guarantee order. Daemon threads do not keep the JVM alive after all user threads finish.

Alternative: ExecutorService with a fixed pool bounds active worker threads. For n equal-sized scripts and p workers, idealized processing is approximately O(n/p) batches; total work remains O(n). Thread stacks use O(p × stack-size) rather than O(n × stack-size) for one-thread-per-station/task.
