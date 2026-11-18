# Task A2 – Don’t Read What You Don’t Need

A 50 MiB file contains 52,428,800 bytes. A loop calling FileInputStream.read() once per byte makes about 52.4 million Java-level read calls. The exact number of native operating-system calls depends on buffering and the JVM/OS implementation, but per-byte method and loop overhead is still expensive.

## Buffered redesign
Wrap the input stream in BufferedInputStream or read into a reusable byte array (for example, 64 KiB). Scan each returned block in memory and count newline bytes. With a 64 KiB buffer, the number of bulk reads is approximately fileSize / bufferSize, rather than one Java read call per byte.

## When buffering may not help
If the input is already an in-memory byte array, or processing each byte dominates the total time, adding another buffer may offer little benefit.

## Files.readAllLines() alternative
Files.readAllLines(path) loads all lines and strings into memory. It is convenient for small files when all lines are needed. Its memory use is O(file size) or higher due to Java object overhead. A streaming buffered reader uses O(buffer size) extra memory and suits a 50 MB log when only a count is required. Both scan in O(n) time.
