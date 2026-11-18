# Q6 — Deadlock and inter-thread communication (7 marks)

TokenCounter.java implements a bounded circular buffer of capacity five. put() waits in a while loop while full; take() waits in a while loop while empty. Both notifyAll() after changing the condition. wait() must be called while holding the object's monitor and releases that monitor while waiting. A loop is required because of spurious wakeups and because another thread may consume the condition before the awakened thread reacquires the lock. sleep() does not release a monitor; wait() does.

WalletTransfer.java demonstrates deadlock-prone opposite lock acquisition and a safe consistent lock ordering. The four Coffman conditions are mutual exclusion, hold-and-wait, no preemption and circular wait. Ordering locks by a stable account ID removes circular wait.

ArrayBlockingQueue<Integer>(5) can replace the manual producer-consumer monitor; put/take block safely with O(1) queue operation and O(5) buffer space. ReentrantLock.tryLock(timeout) can avoid indefinite lock waiting but requires retry/failure policy. A transfer locks two accounts, O(1) work per transfer plus contention; queue put/take are O(1) typical.
