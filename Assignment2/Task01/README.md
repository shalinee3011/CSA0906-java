# Task 01 — Building Text Under Load (6 marks)

The PDF asks for String + in a loop, StringBuilder and StringBuffer, three deliberately different workloads, repeated nanoTime measurements, estimated intermediate String creation, and a recommendation for per-ticket text versus a shared multithreaded log.

## Prediction
String is immutable: repeated concatenation creates replacement String objects and copies the existing prefix. For n similarly sized fields this can copy a quadratic amount of character data. StringBuilder uses a growable buffer and amortised append, giving O(total characters) work. StringBuffer has similar asymptotic behaviour but synchronizes individual methods.

## Workloads and evidence
The program measures a short ticket, a medium batch and a large batch. It warms up, repeats each workload and reports average elapsed milliseconds. Results depend on JDK, hardware, load and JIT warm-up. Estimated intermediate String counts are approximations, not JVM allocation instrumentation.

## Design decision
Use StringBuilder for a ticket built privately by one request thread. For a shared log, prefer a dedicated logging framework/asynchronous logger or synchronized ownership; StringBuffer is synchronized but does not make a multi-step log record transaction atomic. Do not share a per-ticket builder between threads.

## Alternative and complexity
StringJoiner / String.join can assemble fields cleanly; String.format is convenient but may have formatting overhead. Repeated concatenation can copy O(C²) characters for C similarly sized appended content; builder/joiner: O(C) amortised. Builder needs O(C) buffer space; repeated concatenation creates temporary strings and can increase allocation/GC pressure.

Run StringBuildBenchmark.java and retain its CSV output. Add your actual measured results before submission.
