# Task 02 — ArrayList, LinkedList and ArrayDeque (4 marks)

The playlist supports append, insertion/removal at front and middle, indexed access and iteration. ArrayList is reasonable when indexed reads and iteration are frequent and appends dominate. Front/middle changes shift elements. LinkedList has O(1) insertion/removal once a node is reached, but locating an index is O(n) and each node has pointer overhead. ArrayDeque is a strong choice for an Up Next queue with frequent operations at both ends; it has no indexed access.

| Operation | ArrayList | LinkedList | ArrayDeque |
|---|---:|---:|---:|
| Add at end | amortised O(1) | O(1) | amortised O(1) |
| Add/remove front | O(n) | O(1) | amortised O(1) |
| Insert/remove middle by index | O(n) | O(n) locate, O(1) relink | O(n) |
| Get k-th | O(1) | O(n) | unsupported |
| Iterate all | O(n) | O(n) | O(n) |

ArrayList stores references in a resizable array; LinkedList stores links and an item per node; ArrayDeque stores a circular array. A plain array with manual shifting has O(n) front/middle changes and O(1) indexed access, but needs fixed capacity or manual resizing.

Run ListBenchmark.java. Timings are machine-dependent; keep the CSV and write your operating boundary after testing.
