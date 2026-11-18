# Task 3 – Campus Library Search Screen

## 3.1 Layout choices
- JFrame: BorderLayout. NORTH holds title/search controls, CENTER holds results, SOUTH holds navigation and status.
- Top panel: BorderLayout. Title at NORTH and search row at SOUTH.
- Search row: FlowLayout(LEFT), keeping text field, category and Search button at preferred sizes.
- Bottom panel: BorderLayout. Action buttons in CENTER and status in SOUTH.
- Action buttons: FlowLayout(CENTER).
- Results: JScrollPane in CENTER; BorderLayout gives it the remaining space when resized.

## 3.2 Nested structure
```
JFrame [BorderLayout]
├── Top JPanel [BorderLayout]
│   ├── Title [NORTH]
│   └── Search JPanel [FlowLayout] [SOUTH]
├── JScrollPane / Results [CENTER]
└── Bottom JPanel [BorderLayout] [SOUTH]
    ├── Actions JPanel [FlowLayout] [CENTER]
    └── Status JLabel [SOUTH]
```

## 3.4 Resize evidence
Run LibrarySearchScreen.java and capture the window at small, medium and large sizes. Verify the centre scroll pane expands/contracts while the search row and action buttons retain their preferred sizes. Screenshots are not fabricated or included because they must come from your own execution.
