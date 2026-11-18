# Task 5 – Event-Handling Styles

The runnable examples are ConverterStyles.java (four windows) and ConverterLambda.java (the completed TODO version).

## 5.1 Four styles
(a) Frame implements ActionListener: the frame receives actionPerformed directly.
(b) Named inner class: a named handler class implements ActionListener and can access enclosing frame fields.
(c) Anonymous inner class: handler is declared at the registration site; concise for one-off behavior.
(d) Lambda: concise functional implementation for a single-method listener interface.

All versions should register a WindowAdapter to handle window closing. The sample does this in the shared Base frame.

## 5.2 Requirement change
To support Enter in the amount field, register the same handler with amount.addActionListener(...). To show a dialog, replace or supplement the result label update with JOptionPane.showMessageDialog(...). A shared handler requires one additional registration; frame/named/anonymous versions each need a small listener registration change. The lambda can be reused as one ActionListener variable, as in ConverterLambda.java.

## 5.3 Comparison
| Style | Readability | Access to fields | Reuse |
|---|---|---|---|
| Frame listener | Simple for tiny forms; frame can become crowded | Direct | Frame itself is coupled to event interface |
| Named inner | Clear name and separation | Direct | Good when reused in the same outer class |
| Anonymous inner | Local and explicit | Direct | Limited outside registration site |
| Lambda | Most concise | Direct through enclosing instance | Reuse by storing it in a variable |

For a large application, use a shared Action or named controller/service handler so multiple controls can invoke the same behavior and the UI remains organized.

## Alternative: method reference/shared Action
this::convert can be registered wherever a compatible listener is required. A single AbstractAction can be shared by a button, menu item and keyboard shortcut. This reduces duplicated handler logic; adding a new trigger is mainly another registration. Listener/action object count depends on whether a shared instance is reused; sharing uses one handler object rather than one per trigger.
