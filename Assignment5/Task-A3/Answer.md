# Task A3 – Data-Access Object Pattern

## A3.1 Common and different steps
Repeated steps are obtaining a connection, preparing/executing SQL, binding values, reading ResultSet rows, mapping rows to Java objects, handling exceptions and closing resources. Differences include table name, columns, entity type, primary key and SQL statements.

## A3.2 Generic DAO
```java
public interface Dao<T, K> {
    void insert(T entity) throws SQLException;
    Optional<T> findById(K id) throws SQLException;
    List<T> findAll() throws SQLException;
    void update(T entity) throws SQLException;
    void delete(K id) throws SQLException;
}
```

`StudentDao implements Dao<Student, Integer>` and supplies Student-specific SQL and ResultSet mapping. Use PreparedStatement and try-with-resources in each method.

## A3.3 Object-to-table mapping

| Java field | SQL column | Role |
|---|---|---|
| studentId | student_id | Primary key |
| registerNumber | register_no | Unique register number |
| name | name | Student name |
| email | email | Contact email |

A DAO centralizes SQL and mapping. If SQL is placed directly in service/GUI classes, a column rename may require changes in several places. The per-operation database time and space complexity are generally unchanged; DAO mainly improves maintainability, separation and testability.
