# Task B2 – Adversarial Inputs

Use only a disposable local test database. The unsafe implementation is for demonstration only.

Create the database using schema.sql. Compile with MySQL Connector/J and call both login methods from a small JDBC driver. Try the ID value: ' OR '1'='1' -- and an invalid password. The unsafe Statement may treat the input as SQL syntax; PreparedStatement treats it as a literal value and should reject it. Comment syntax may vary by SQL dialect.

Also test the B1 file store with a short row, a non-numeric route/amount and an empty line. Record actual output. Escaping quotes or regex validation is weaker than parameter binding because SQL syntax and encoding edge cases are easy to miss.
