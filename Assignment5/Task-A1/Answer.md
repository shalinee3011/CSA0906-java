# Task A1 – Design a File Format

## A1.1 Record layouts

**Delimited text:** UTF-8 CSV fields in this order: pass ID, student register number, name, route number, boarding stop, validity date, amount paid. Use a CSV-aware encoder: quote a field containing a comma, double embedded quote characters, and preserve the field count. Do not simply concatenate unescaped user text.

Example:
`P1001,192511048,"Anu, R",12,"Central Stop",2026-12-31,4500.00`

**Binary using DataOutputStream:** write fields in this exact order and read them in the same order:
1. `writeUTF(passId)`
2. `writeUTF(registerNumber)`
3. `writeUTF(name)`
4. `writeInt(routeNumber)`
5. `writeUTF(boardingStop)`
6. `writeUTF(validityDate)`
7. `writeDouble(amountPaid)`

`writeUTF` writes a length-prefixed modified UTF-8 string; primitive numeric values have defined binary representations.

## A1.2 SIMATS-style operation pseudocode

### ADD-PASS
1. START
2. RECEIVE a bus-pass record.
3. VALIDATE mandatory fields, route number, date and amount.
4. OPEN the text file in append mode using FileWriter wrapped in BufferedWriter.
5. ENCODE fields as one valid CSV record.
6. WRITE the record and a newline.
7. CLOSE the writer.
8. DISPLAY success.
9. STOP.

### FIND-PASS
1. START
2. RECEIVE the pass ID.
3. OPEN the file using FileReader wrapped in BufferedReader.
4. READ each line until end-of-file.
5. PARSE the CSV record.
6. IF malformed, RECORD its line number and CONTINUE.
7. IF the pass ID matches, DISPLAY the record, CLOSE the reader and STOP.
8. CLOSE the reader and DISPLAY “Pass not found”.
9. STOP.

### LIST-BY-ROUTE
1. START
2. RECEIVE the route number.
3. OPEN the file using BufferedReader.
4. READ each line until end-of-file.
5. PARSE and VALIDATE the record.
6. IF valid and route number matches, DISPLAY the record.
7. SKIP malformed records and RECORD their line numbers.
8. CLOSE the reader.
9. STOP.

Use FileReader/FileWriter for character text, BufferedReader/BufferedWriter to reduce small operations, and FileInputStream/FileOutputStream for raw bytes or binary data.

## A1.3 Damaged data
- **Damaged line:** validate field count, required values, date and numeric formats. Skip/quarantine invalid lines and log the line number; do not stop the entire scan.
- **Missing file:** create it when adding a new pass; for find/list, report that the store is missing or empty.
- **Incomplete write/power failure:** write a complete replacement to a temporary file and atomically move it over the old file where supported, or use a journal/backup. A trailing partial record should fail validation.

## RandomAccessFile alternative and complexity
With fixed-length records, record index i is accessed at offset i × recordSize. Seeking to a known index is O(1). Finding by pass ID still requires O(n) scanning unless a separate ID-to-offset index is maintained. Fixed-width fields waste space through padding when names or stops are shorter than their allocated widths.
