import java.awt.*;
import java.io.*;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class EventRegistrationApp extends JFrame {
    private final JTextField studentId = new JTextField(12), studentName = new JTextField(12);
    private final JComboBox<String> event = new JComboBox<>(new String[]{"1", "2", "3"});
    private final JComboBox<String> filter = new JComboBox<>(new String[]{"All", "1", "2", "3"});
    private final DefaultTableModel model = new DefaultTableModel(
        new String[]{"Student ID", "Name", "Event ID", "Registered At"}, 0);
    private final JTable table = new JTable(model);
    private final RegistrationDAO dao;
    private final RegistrationService service;

    public EventRegistrationApp() {
        super("Campus Event Registration");
        String url = System.getenv().getOrDefault("DB_URL", "jdbc:mysql://localhost:3306/campus_events");
        String user = System.getenv().getOrDefault("DB_USER", "root");
        String pass = System.getenv().getOrDefault("DB_PASSWORD", "");
        dao = new RegistrationDAO(url, user, pass);
        service = new RegistrationService(dao);

        JPanel form = new JPanel(new FlowLayout());
        form.add(new JLabel("Student ID")); form.add(studentId);
        form.add(new JLabel("Name")); form.add(studentName);
        form.add(new JLabel("Event ID")); form.add(event);
        JButton register = new JButton("Register"); form.add(register);

        JPanel tools = new JPanel(new FlowLayout(FlowLayout.LEFT));
        tools.add(new JLabel("Filter event:")); tools.add(filter);
        JButton refresh = new JButton("Refresh"), export = new JButton("Export CSV");
        tools.add(refresh); tools.add(export);

        JPanel north = new JPanel(new BorderLayout());
        north.add(form, BorderLayout.NORTH); north.add(tools, BorderLayout.SOUTH);
        add(north, BorderLayout.NORTH); add(new JScrollPane(table), BorderLayout.CENTER);
        JMenuBar bar = new JMenuBar();
        JMenu file = new JMenu("File"); JMenuItem exportItem = new JMenuItem("Export CSV");
        JMenuItem exit = new JMenuItem("Exit");
        exportItem.addActionListener(e -> exportCsv()); exit.addActionListener(e -> dispose());
        file.add(exportItem); file.add(exit); bar.add(file); setJMenuBar(bar);

        register.addActionListener(e -> register());
        refresh.addActionListener(e -> loadPage());
        filter.addActionListener(e -> loadPage());
        export.addActionListener(e -> exportCsv());
        setDefaultCloseOperation(EXIT_ON_CLOSE); setSize(850, 500); setLocationRelativeTo(null);
    }

    private void register() {
        String id = studentId.getText(), name = studentName.getText(), eventId = (String) event.getSelectedItem();
        new SwingWorker<Void, Void>() {
            protected Void doInBackground() throws Exception { service.register(id, name, eventId); return null; }
            protected void done() {
                try { get(); JOptionPane.showMessageDialog(EventRegistrationApp.this, "Registration successful."); loadPage(); }
                catch (Exception ex) { JOptionPane.showMessageDialog(EventRegistrationApp.this,
                    ex.getCause() == null ? ex.getMessage() : ex.getCause().getMessage(), "Registration error", JOptionPane.ERROR_MESSAGE); }
            }
        }.execute();
    }

    private void loadPage() {
        String selected = (String) filter.getSelectedItem();
        Integer eventId = "All".equals(selected) ? null : Integer.valueOf(selected);
        new SwingWorker<List<String[]>, Void>() {
            protected List<String[]> doInBackground() throws Exception { return dao.findPage(eventId, 100, 0); }
            protected void done() {
                try { model.setRowCount(0); for (String[] row : get()) model.addRow(row); }
                catch (Exception ex) { JOptionPane.showMessageDialog(EventRegistrationApp.this, "Load failed: " + ex.getMessage()); }
            }
        }.execute();
    }

    private void exportCsv() {
        JFileChooser chooser = new JFileChooser();
        if (chooser.showSaveDialog(this) != JFileChooser.APPROVE_OPTION) return;
        try (PrintWriter out = new PrintWriter(chooser.getSelectedFile())) {
            for (int c = 0; c < model.getColumnCount(); c++) out.print((c == 0 ? "" : ",") + model.getColumnName(c));
            out.println();
            for (int r = 0; r < model.getRowCount(); r++) {
                for (int c = 0; c < model.getColumnCount(); c++) {
                    String value = String.valueOf(model.getValueAt(r, c)).replace("\"", "\"\"");
                    out.print((c == 0 ? "" : ",") + "\"" + value + "\"");
                }
                out.println();
            }
            JOptionPane.showMessageDialog(this, "CSV exported.");
        } catch (Exception ex) { JOptionPane.showMessageDialog(this, "Export failed: " + ex.getMessage()); }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new EventRegistrationApp().setVisible(true));
    }
}
