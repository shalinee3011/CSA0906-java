# Q2 — User-defined exceptions and propagation (7 marks)

StorageDemo.java defines checked ExamException and SubmissionStorageException. Storage wraps the low-level IOException while preserving it as the cause. Service and Controller demonstrate throws/propagation across storage, service and controller layers. getCause() retains the original failure; without passing the cause, the Cause line cannot report the original disk-quota reason (it will be null).

A storage-specific exception gives the upper layer a stable domain-level meaning without exposing filesystem details as UI policy. Checked exceptions force callers to handle or declare expected submission failures. Runtime exceptions are appropriate for programming errors or unrecoverable misuse, not ordinary submission rejection.

Exception stack-trace capture is proportional to call-stack depth O(d) time and storage; the cause chain retains additional Throwable objects and their stack traces, O(sum of chain depths). Chaining adds context but also memory.
