# Task 4 – Event-Handling Forensics

## 4.1 Defect
The listener method is misspelled as mouseclicked. Java's MouseListener callback is mouseClicked(MouseEvent), with a capital C. Therefore the intended callback is never invoked. The label remains unchanged because its update code is inside that method.

## 4.2 Failing interaction
Click several times, minimise the window, then restore it. The dots drawn directly with getGraphics() can disappear because the window system may repaint the component and the program has not retained the drawing data.

## 4.3 Repair
Store every click as a Point, update the label using the list size, call repaint(), and redraw all points in paint(Graphics). The supplied DrawPad.java implements this approach.

## 4.4 @Override
Adding @Override above the incorrectly named mouseclicked method causes a compile-time error because no superclass or interface method with that signature is being overridden. It catches the typo before runtime.

## Alternative: BufferedImage
An off-screen BufferedImage can retain the drawing. Each click draws into the image; paint() copies the image to the window. Repainting by iterating n points costs O(n) time; copying an image costs O(width × height) pixel work in the general case (often optimized by the graphics pipeline). A point list uses O(n) memory, while the image uses O(width × height) pixels. The image approach is useful for many repeated repaints.
