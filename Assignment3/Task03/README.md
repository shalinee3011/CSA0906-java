# Q3 — Resource safety and try-with-resources (7 marks)

SalesReport.java compares a manual-close version and a try-with-resources version. It reads store files and writes a combined report. A deliberately missing input file causes a mid-process failure. The manual version demonstrates the risk of failing before close; the safe version closes already-open resources automatically in reverse declaration order.

For f input files, processing each record once is O(total records + f) time. Sequential try-with-resources holds one input plus the output open at a time (O(1) handles). A naïve approach that opens all f inputs before processing can hold O(f) handles. Manual finally can be correct if every resource is tracked and each close is guarded, but it is easier to get wrong. try-with-resources is not sufficient for atomic report publication, disk-full handling, validation, partial-output cleanup or logging.

A suppressed exception occurs when close() throws while another exception is already being thrown; the primary exception is preserved and close failure is attached via getSuppressed().
