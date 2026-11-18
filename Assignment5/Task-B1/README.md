# Task B1 – File Store and Buffering

Compile: javac PassStore.java BufferedLineCounter.java
Run: java PassStore
Count lines: java BufferedLineCounter attendance.log

The sample prints the P1003 record and null for P1002. The damaged two-field line is skipped because valid records must have exactly five fields and numeric route/amount values.

Limitation: this compact sample uses comma splitting and assumes names contain no commas. A production CSV implementation must use a CSV parser/encoder that supports quoted fields.

## Record actual measurements
| File size | Unbuffered time | Buffered time |
|---|---|---|
| 1 MB | Fill after run | Fill after run |
| 10 MB | Fill after run | Fill after run |
| 50 MB | Fill after run | Fill after run |

| Records | Find time |
|---:|---|
| 1,000 | Fill after run |
| 10,000 | Fill after run |
| 100,000 | Fill after run |

Do not enter estimated times as measured results. Capture actual console output and screenshots.
