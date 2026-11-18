# Task A4 – What Should Be Serialized?

Persist the hostel domain state needed to restore the application: Hostel, Room data and Resident details. Mark the live JDBC Connection reference transient because a live connection is a runtime resource and cannot meaningfully be restored from a saved object. Do not serialize plaintext passwords; store a secure password hash only if the application requires it.

When Hostel is serialized, Java traverses the reachable object graph: the Hostel, its room list, each Room, resident lists and Resident objects. Reachable objects must be serializable unless references are transient or specially handled.

## Version change
When a phoneNumber field is added, an older stream read by a compatible class generally supplies the default value (null for a String) for the new field. Declare an explicit serialVersionUID to control compatibility. An incompatible serialVersionUID causes InvalidClassException. Application code should handle missing/default values.

## CSV/JSON alternative
CSV/JSON is human-readable, portable and easier to migrate across languages, but needs explicit parsing, escaping and object mapping. Java serialization conveniently saves an object graph but is Java-specific and more sensitive to class evolution. Saving/loading n residents is O(n) time in either approach. Text formats may be larger; actual size depends on encoding and representation. Versioned JSON fields or CSV headers make schema changes explicit.
