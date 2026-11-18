# Task 05 — Generic Leaderboard (7 marks)

Leaderboard<T> stores generic items and accepts Comparator<? super T>, allowing a comparator for T or one of its supertypes. ranking() returns a sorted copy without mutating insertion order. List<? extends T> is useful when importing a list of a subtype into a method that consumes T values.

The rule is points descending, goal difference descending, then name ascending. Bisons ranks above Panthers because both have 9 points and goal difference 4, so alphabetical order decides.

Each field comparator is transitive; lexicographic composition preserves transitivity. Avoid subtraction (a.points-b.points) because integer overflow can violate ordering; use comparingInt and reversed.

Comparable suits one natural universal order. Comparator is reusable when Team, Player, College or QuizTeam need different ranking rules. One chained sort is O(n log n); three stable sorts take approximately 3 × O(n log n), with sorting workspace depending on implementation.
