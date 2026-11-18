# Task 6 – Campus Event Registration Desktop Application

## Stage 1: requirements
**Users:** students and committee staff.
**Screens:** student registration form; committee registration table with event filter; menu bar with File/Export and View/Refresh.
**Data:** student ID, student name, event ID/name, registration timestamp.
**Rules (testable):** required fields cannot be blank; event must be selected; the same student cannot register twice for one event; invalid input shows a clear message; committee can filter by event; export produces a CSV; database work does not block the Event Dispatch Thread.

## Stage 2: screen and event flow
Registration form: student ID field, name field, event combo box and Register button.
Committee view: event filter combo box, JTable, Refresh and Export controls.
Menu: File → Export CSV / Exit; View → Refresh.
Register → validate → background service/JDBC insert → success/error dialog. Filter → background paginated query → update table model. Export → choose destination → write visible rows to CSV.

## Stage 3: critical decisions
Validate at the UI boundary for immediate feedback and again in the service for correctness. Enforce uniqueness in MySQL with UNIQUE(student_id,event_id), not only with a UI check. Put JDBC calls in SwingWorker; update Swing components only in done() on the EDT.

## Stage 4: class design
- EventRegistrationApp: creates JFrame, panels, menus and event handlers.
- RegistrationService: validates and coordinates registration/filter operations.
- RegistrationDAO: prepared JDBC statements and pagination.
- DB: MySQL table with unique constraint.

**Register pseudocode:** read form values; validate required fields; execute RegistrationService.register in SwingWorker; DAO inserts with prepared statement; unique-key violation becomes “Already registered”; done() displays result.
**Filter pseudocode:** read event and page; SwingWorker calls DAO.findByEvent(event, limit, offset); done() replaces JTable model.

## Stage 5
Compile with the MySQL Connector/J JAR on the classpath. Create the database using schema.sql, configure JDBC URL/user/password with environment variables, then run EventRegistrationApp. Test valid registration, blank/invalid input and duplicate registration. Capture screenshots of each test after running locally.

## Alternative: load all rows vs pagination
Loading all 100,000 rows transfers and stores the full result set and JTable model at once. Time and memory are O(N) in the number of rows, with significant startup delay and UI memory pressure. Pagination fetches only page size p per request: each page transfers O(p) rows and the UI holds O(p) visible rows (plus any cache). Total database work to browse all records remains O(N), but initial response and memory are much smaller. SwingWorker keeps both strategies from blocking the EDT, but pagination is preferable for responsiveness.
