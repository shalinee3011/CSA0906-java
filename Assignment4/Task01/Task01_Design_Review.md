# Task 1 – GUI Design Review

## 1.1 Deficiencies
1. **UI and business logic are mixed:** database code is inside the listener, making the window difficult to test or reuse.
2. **String comparison uses ==:** Java compares references; use "Pay".equals(e.getActionCommand()) or separate listeners.
3. **No input validation:** empty registration number, non-numeric amount, zero or negative values can cause errors or invalid records.
4. **Blocking work on the event-dispatch thread:** JDBC and Thread.sleep(3000) freeze repainting and user interaction.
5. **No useful feedback:** printing "Paid" to the console does not inform the receptionist; show a status label/dialog.
6. **Receipt button is not implemented:** it falls into the else branch and clears the fields.
7. **SQL injection risk:** concatenating user input into SQL allows malformed or malicious SQL; use PreparedStatement.
8. **Resources are not closed:** Connection/Statement can leak; use try-with-resources.
9. **Window closing is not handled:** add a WindowListener and dispose the frame.
10. **Exceptions are not handled:** parsing, JDBC and interruption failures need clear handling.

## 1.2 Repaired structure
- FeeWindow: collects fields, registers listeners and displays feedback.
- FeeService: validates input and performs payment persistence.
- FeeRepository: optional JDBC persistence using PreparedStatement.

The sample below keeps the service independent of the UI. Replace the demonstration payment method with a repository call when the database schema and credentials are available.

## 1.3 Design defence (under 100 words)
The window only handles presentation, while FeeService owns validation and payment rules. The service can be tested without opening a GUI, and the interface can change without rewriting payment logic. Small methods make failures easier to locate. A repository can isolate JDBC details and use prepared statements safely.

## Alternative listener concept and complexity
With one listener and an if/else chain, dispatch may perform up to k command comparisons: O(k) time and O(1) extra dispatch space. With a map from command to handler, lookup is O(1) average. Separate listener objects avoid the chain but use O(k) listener storage. A shared Swing Action can centralize behavior when the same operation is triggered by buttons and menus.
