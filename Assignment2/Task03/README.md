# Task 03 — hashCode–equals Contract (6 marks)

HashSet uses hashCode() to choose a bucket and equals() to check candidates in that bucket. Equal objects must have equal hash codes. If equals() is overridden but hashCode() is inherited from Object, two Student objects with the same registration may land in different buckets and both remain in the set.

Attendance.java includes a deliberately broken type, a corrected immutable Student type, and a mutable-key demonstration. Changing a field used by hashCode after insertion can make lookup/removal fail because the object remains in its old bucket. Keep identity fields final.

The completed Attendance program prints Unique students: 2. Registration number is identity; name is not part of equality.

Alternative: TreeSet with a comparator on registration de-duplicates in O(log n) per add, but comparator equality must match intended identity. HashMap<String,Student> uses expected O(1) average put/lookup and O(n) storage; TreeSet uses O(log n) operations and O(n) storage.
