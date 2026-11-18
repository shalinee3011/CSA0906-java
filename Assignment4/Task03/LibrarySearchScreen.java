import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class LibrarySearchScreen extends JFrame {
    private final JTextField search = new JTextField(22);
    private final JComboBox<String> category =
        new JComboBox<>(new String[]{"All", "Books", "Journals", "Theses"});
    private final DefaultListModel<String> results = new DefaultListModel<>();
    private final JList<String> resultList = new JList<>(results);
    private final JLabel status = new JLabel("Ready");

    public LibrarySearchScreen() {
        super("Campus Library Search");
        setLayout(new BorderLayout(8, 8));
        JLabel title = new JLabel("Library Search", SwingConstants.CENTER);
        add(title, BorderLayout.NORTH);

        JPanel searchRow = new JPanel(new FlowLayout(FlowLayout.LEFT));
        searchRow.add(new JLabel("Search:"));
        searchRow.add(search);
        searchRow.add(category);
        JButton find = new JButton("Search");
        searchRow.add(find);
        JPanel top = new JPanel(new BorderLayout());
        top.add(title, BorderLayout.NORTH);
        top.add(searchRow, BorderLayout.SOUTH);
        add(top, BorderLayout.NORTH);

        resultList.setBorder(BorderFactory.createTitledBorder("Results"));
        add(new JScrollPane(resultList), BorderLayout.CENTER);

        JPanel bottom = new JPanel(new BorderLayout());
        JPanel actions = new JPanel(new FlowLayout(FlowLayout.CENTER));
        actions.add(new JButton("Prev"));
        actions.add(new JButton("Next"));
        actions.add(new JButton("Reserve"));
        bottom.add(actions, BorderLayout.CENTER);
        bottom.add(status, BorderLayout.SOUTH);
        add(bottom, BorderLayout.SOUTH);

        find.addActionListener(e -> {
            results.clear();
            String q = search.getText().trim();
            if (q.isEmpty()) status.setText("Enter a search term.");
            else {
                results.addElement(q + " – sample catalogue result (" + category.getSelectedItem() + ")");
                status.setText("1 result shown. Connect catalogue search for live data.");
            }
        });
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(720, 460);
        setLocationRelativeTo(null);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new LibrarySearchScreen().setVisible(true));
    }
}
