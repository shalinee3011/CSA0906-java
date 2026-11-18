# Task 2 – Hospital Outpatient Appointment Desk

## 2.1 Event model
| Source | Event object | Listener | Action |
|---|---|---|---|
| Department JComboBox | ItemEvent | ItemListener | Load doctors for selected department |
| Doctor JComboBox | ItemEvent | ItemListener | Refresh available slots |
| Slot JComboBox | ItemEvent | ItemListener | Store selected slot |
| Book JButton | ActionEvent | ActionListener | Validate and book |
| Patient ID JTextField | ActionEvent | ActionListener | Optional submit/lookup |
| Window | WindowEvent | WindowAdapter | Confirm close if booking is in progress |

## 2.2 Component containment tree
```
JFrame (BorderLayout)
├── JPanel NORTH (GridLayout)
│   ├── JLabel + JTextField (patient ID)
│   ├── JLabel + JComboBox (department)
│   ├── JLabel + JComboBox (doctor)
│   └── JLabel + JComboBox (time slot)
├── JPanel CENTER (BorderLayout)
│   └── JLabel (status / confirmation)
└── JPanel SOUTH (FlowLayout)
    └── JButton (Book Appointment)
```

## 2.3 Handler pseudocode
**Department change**
1. Read selected department.
2. Clear doctor and time-slot models.
3. Get doctors from the preloaded department-to-doctors map.
4. Add each doctor to the doctor combo box.
5. If no doctors exist, show a status message.
6. Refresh the slot list after a doctor is selected.

**Booking**
1. Read patient ID, department, doctor and slot.
2. Reject blank patient ID or missing selections.
3. Check that the selected slot is still available.
4. Set bookingInProgress to true.
5. Save the appointment in a background worker.
6. On success, show confirmation; on failure, show an error.
7. Set bookingInProgress to false.

## 2.4 Event sequence and applet comparison
The application constructs the JFrame and components, registers listeners, then calls setVisible(true). User input generates events dispatched to registered listeners on the Event Dispatch Thread. On close, windowClosing is called; if a booking is active, show a confirmation dialog. If closing is confirmed, dispose the frame; otherwise cancel closing.

Unlike a JFrame, an applet historically follows init() once, start() when active, paint() for drawing, stop() when inactive and destroy() before removal. A desktop frame is controlled by its constructor, visibility and window events rather than the applet lifecycle.

## Alternative and complexity
Preloading a Map<String,List<Doctor>> costs O(d+m) time and O(d+m) memory for d departments and m total doctors; a department change is O(1) map lookup plus O(mᵢ) to populate its doctor list. Fetching from a database on every change adds network/query latency per change and reduces local memory to roughly O(mᵢ), but can make the UI slower. Database calls should run in SwingWorker.
