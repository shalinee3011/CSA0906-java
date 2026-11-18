# Q7 — Concurrent fault-tolerant result processor (10 marks)

ResultProcessor.java is a first-principles design using ExecutorService, Callable, Future and ConcurrentLinkedQueue. It creates 10,000 score files, injects corrupt and missing files, processes each independently, records success/failure outcomes and prints a complete summary. One bad file is captured as a per-file failure rather than stopping the batch.

Run with optional pool sizes: java ResultProcessor 1 2 4 8. It prints elapsed time and counts for each pool size. Timings vary by machine and storage; retain the actual output. A small hand-check is included in the README's validation instructions; test with a small dataset by changing FILE_COUNT.

For n files and p workers, ideal compute-bound time is approximately O(n/p) batches, while total work is O(n). File I/O can limit scaling. A fixed pool uses O(p × thread stack) stack memory plus O(n) task/result bookkeeping. One thread per file can require O(n × thread stack), which is wasteful. Parallel streams use the common ForkJoinPool by default and make pool sizing/isolation less explicit.

A failed Future is handled individually through ExecutionException; interrupted coordination restores the interrupt flag. Remaining limitation: results are held in memory and there is no durable retry/checkpoint mechanism.
