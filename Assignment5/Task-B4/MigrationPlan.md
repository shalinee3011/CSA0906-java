# Task B4 – Final Engineering Decision

## Migration workflow
1. Read the existing file with BufferedReader.
2. Parse each line with a CSV-aware parser and validate all fields.
3. Add valid rows to a PreparedStatement batch.
4. Execute batches of 1,000 within a transaction.
5. Commit after successful completion; roll back on a database failure.
6. Write rejected line numbers and validation reasons to a failure report.
7. Reconcile source count = imported count + rejected count.

## Production decision
For 12 campuses, 100 times the current pass volume and concurrent counters, use a relational database as the authoritative store. It supports concurrent transactions, constraints, indexes, centralized backup and safe querying. Text files are useful for interchange/import/export, but not as a shared multi-counter system of record. Java serialization is Java-specific and does not provide safe multi-user concurrent updates.

A campus-per-file design requires searching multiple files (up to O(12n) for a naive scan) and coordinating locks across campus files. An indexed database can locate a pass using an index (typically O(log n) B-tree lookup) and manages concurrency through transactions. Base final performance claims on the measurements collected in B1 and B3.
