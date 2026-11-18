# Task B3 – Statement vs PreparedStatement vs Batch

Create the table using schema.sql. Add MySQL Connector/J to the classpath and set DB_USER and DB_PASSWORD environment variables. Do not hard-code credentials.

Compile: javac -cp mysql-connector-j.jar PassLoader.java
Run your driver with the JDBC URL jdbc:mysql://localhost:3306/assignment5.

PassLoader binds all five fields, executes a batch every 1,000 records, commits once, and rolls back if an error occurs. Add a test driver that supplies 10, 1,000, 10,000 and 100,000 generated rows, verifies rows with SELECT and checks column types with ResultSetMetaData.

## Record actual measurements
| Rows | Statement time | PreparedStatement time | Batch time |
|---:|---|---|---|
| 10 | Record actual result | Record actual result | Record actual result |
| 1,000 | Record actual result | Record actual result | Record actual result |
| 10,000 | Record actual result | Record actual result | Record actual result |
| 100,000 | Record actual result | Record actual result | Record actual result |

Compare auto-commit and one transaction by forcing an error halfway through and recording table row counts. Do not report estimated timings as measured results.
