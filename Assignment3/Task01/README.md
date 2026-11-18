# Q1 — Bank ATM exception handling (6 marks)

ATM.java demonstrates invalid numeric input, negative amount, insufficient balance and missing card-reader file. It uses a multi-catch for input parsing, specific catches before broader exceptions, and finally for cleanup messaging. The Throwable hierarchy relevant here is Error → serious JVM/application failures; Exception → checked exceptions such as IOException; RuntimeException → unchecked exceptions such as IllegalArgumentException and InputMismatchException.

Catch order: specific exceptions must precede their superclasses, otherwise the compiler reports unreachable catch clauses. finally runs whether the try completes normally or an exception is caught (except abnormal JVM termination or process exit). A single catch(Exception) can be acceptable at a top-level boundary for logging and user-safe error reporting, but it loses precise recovery and classification if used everywhere.

Test at least: valid withdrawal; letters for amount; negative amount; amount above balance; missing card file; unexpected unchecked exception. Record caught block and finally output. To show an uncaught stack trace, temporarily remove the relevant catch and run the failing case; do not leave the final program uncaught.

Alternative: Scanner.hasNextInt() and explicit validation/error codes avoids exception creation for expected bad input. Both paths are O(1) per input; exception construction captures stack context and has higher constant time/space cost, while error-code validation requires explicit checks. Overall space is O(1) excluding stack trace storage.
