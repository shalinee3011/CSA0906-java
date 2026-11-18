# Task 6 – Setup

1. Install MySQL and create the schema by running `schema.sql`.
2. Add MySQL Connector/J to the project classpath.
3. Set environment variables:
   - `DB_URL=jdbc:mysql://localhost:3306/campus_events`
   - `DB_USER=your_mysql_user`
   - `DB_PASSWORD=your_mysql_password`
4. Compile all Java files in this folder with the connector JAR on the classpath.
5. Run `EventRegistrationApp`.

The sample uses event IDs 1, 2 and 3. It loads the first 100 records for the selected filter. Add further pages if required. Database execution and screenshots must be completed on your machine with your own MySQL credentials.
