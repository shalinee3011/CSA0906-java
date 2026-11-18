# Task 07 — Day-4 Design Challenge (13 marks)

Chosen limitation: repeated String concatenation in Q1. As field count and ticket volume grow, each append can copy the accumulated prefix and create intermediate String objects, increasing work and garbage-collection pressure.

Objective: preserve identical ticket text while reducing construction to amortised linear time in total output characters. The redesign uses a fresh StringBuilder per ticket, appends each field, then converts once. Buffer space is O(C), where C is output length. The original may remain suitable for tiny fixed workloads.

Prediction: for larger workloads, StringBuilder should reduce average elapsed time and allocation pressure; small workloads may show negligible differences due to JVM optimisations and measurement noise.

RedesignBenchmark.java compares original and revised implementations under identical sizes and repeated runs, printing CSV. Use the same JDK, machine, warm-up and repetitions. The CSV from your local run is the evidence for parts E and F. Do not claim improvement until your data supports it.

Pseudocode: for each ticket, create builder; append each field and separator; convert once to String. Trade-offs: builder capacity may grow; it is mutable and must not be shared across threads.
