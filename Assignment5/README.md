# Programming in Java – Assignment 5 (CO5)

**Register number:** 192511048  
**Repository:** CSA0906-java  
**Topics:** Java file I/O, buffering, DAO pattern, serialization, JDBC, transactions and batch processing.

## Folder guide
- Task-A1 to Task-A5: design answers, record layouts, pseudocode and analysis.
- Task-B1: file store and buffered line counter.
- Task-B2: adversarial login and damaged-file tests.
- Task-B3: JDBC PreparedStatement batch loader and SQL schema.
- Task-B4: metadata inspection and final engineering decision.

## Requirements
- JDK 17 or later recommended.
- MySQL Server and MySQL Connector/J for JDBC tasks.
- Set DB_USER and DB_PASSWORD as environment variables; never commit passwords.

## Compile and run
For B1:
```sh
cd Assignment5/Task-B1
javac PassStore.java BufferedLineCounter.java
java PassStore
java BufferedLineCounter attendance.log
```

For JDBC tasks, create the database using the relevant schema.sql file and add MySQL Connector/J to the Java classpath. See each task README for guidance.

## Execution evidence and measurements
The assignment requires actual execution, input files, output screenshots and measured timing results. Measurement tables are intentionally left blank until runs are performed on the student's computer. Do not submit invented timings or screenshots. Add your actual evidence under the corresponding task folder.

## Submission checklist
- [ ] Execute every program locally.
- [ ] Add input/test files and screenshots of actual outputs.
- [ ] Fill measurement tables with actual results and explain trends.
- [ ] Test transaction rollback and invalid inputs.
- [ ] Confirm all files compile and the GitHub repository is accessible to faculty.
