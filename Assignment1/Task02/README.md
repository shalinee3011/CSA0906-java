# Task 02 — Data Representation
Use int for floor/slot indexes, String for registration, LocalDateTime for entry/exit, long for duration minutes and BigDecimal for production currency calculations. A slot stores a Vehicle reference or null.

Use a jagged array ParkingSlot[][] because each floor can have a different slot count. Coordinate access is O(1), scanning all N slots is O(N). A flat array formula floor * slotsPerFloor + slot assumes equal floor capacity; ArrayList<ParkingSlot[]> is useful if rows change dynamically.

Integer division example: 90 / 60 gives 1; casting afterward still gives 1.0. Use minutes / 60.0 for fractional hours or (minutes + 59) / 60 for started-hour billing. Scanner supports interactive input; command-line arguments suit scripted runs.
