# Task A5 – Correctness and Safety

## A5.1 Wallet transfer pseudocode
1. START.
2. OPEN a JDBC connection.
3. DISABLE auto-commit.
4. CHECK that the hostel wallet has at least ₹500.
5. UPDATE hostel wallet by subtracting ₹500.
6. UPDATE canteen wallet by adding ₹500.
7. VERIFY both updates affected exactly one row.
8. COMMIT the transaction.
9. IF any operation fails, ROLLBACK and report failure.
10. RESTORE the original auto-commit setting and CLOSE resources.
11. STOP.

Both wallet updates must be in one transaction. A failure before commit causes rollback, preventing a partial transfer.

## A5.2 SQL injection
String concatenation allows user input to become SQL syntax. A crafted ID such as `' OR '1'='1' -- ` can alter the WHERE condition (comment syntax is dialect-dependent) and may bypass the intended check.

Safe design:
```java
String sql = "SELECT * FROM users WHERE id = ? AND pwd = ?";
try (PreparedStatement ps = con.prepareStatement(sql)) {
    ps.setString(1, id);
    ps.setString(2, pwd);
    try (ResultSet rs = ps.executeQuery()) {
        boolean authenticated = rs.next();
    }
}
```
PreparedStatement binds values as data, not SQL syntax. In a real system, store salted password hashes and verify them with a password-hashing function.

## Auto-commit alternative
Two independent auto-commit updates can leave the debit committed if the program fails before the credit. A transaction adds some coordination/logging overhead but ensures atomicity and correctness.
