import java.awt.FlowLayout;
import java.awt.event.*;
import java.text.DecimalFormat;
import javax.swing.*;

// Four separate small frames demonstrate the four listener styles.
public class ConverterStyles {
    static double rate(String currency) { return currency.equals("USD") ? 0.012 : 0.011; }

    static class Base extends JFrame {
        JTextField amount = new JTextField(8);
        JComboBox<String> target = new JComboBox<>(new String[]{"USD", "EUR"});
        JLabel result = new JLabel(" ");
        Base(String title) {
            super(title); setLayout(new FlowLayout());
            add(new JLabel("INR")); add(amount); add(target);
            JButton button = new JButton("Convert"); add(button); add(result);
            setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            addWindowListener(new WindowAdapter() {
                @Override public void windowClosing(WindowEvent e) { dispose(); }
            });
            pack();
        }
        void convert() {
            try {
                double v = Double.parseDouble(amount.getText().trim());
                if (!Double.isFinite(v) || v < 0) throw new NumberFormatException();
                result.setText(new DecimalFormat("0.00").format(v * rate((String) target.getSelectedItem())));
            } catch (NumberFormatException ex) { result.setText("Invalid amount"); }
        }
    }

    // (a) The frame itself implements ActionListener.
    static class FrameListener extends Base implements ActionListener {
        FrameListener() { super("Frame implements ActionListener"); ((JButton)getContentPane().getComponent(3)).addActionListener(this); }
        @Override public void actionPerformed(ActionEvent e) { convert(); }
    }

    // (b) Named inner class.
    static class NamedInner extends Base {
        NamedInner() { super("Named inner class"); ((JButton)getContentPane().getComponent(3)).addActionListener(new ConvertHandler()); }
        private class ConvertHandler implements ActionListener {
            @Override public void actionPerformed(ActionEvent e) { convert(); }
        }
    }

    // (c) Anonymous inner class.
    static class AnonymousInner extends Base {
        AnonymousInner() {
            super("Anonymous inner class");
            ((JButton)getContentPane().getComponent(3)).addActionListener(new ActionListener() {
                @Override public void actionPerformed(ActionEvent e) { convert(); }
            });
        }
    }

    // (d) Lambda expression.
    static class Lambda extends Base {
        Lambda() { super("Lambda expression"); ((JButton)getContentPane().getComponent(3)).addActionListener(e -> convert()); }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame[] windows = {new FrameListener(), new NamedInner(), new AnonymousInner(), new Lambda()};
            int x = 20;
            for (JFrame f : windows) { f.setLocation(x, 80); f.setVisible(true); x += 330; }
        });
    }
}
