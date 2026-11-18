import java.awt.*;
import java.awt.event.*;

public class FeeWindow extends Frame {
    private final TextField regNo = new TextField(18);
    private final TextField amount = new TextField(18);
    private final Label status = new Label("Enter fee details.");
    private final FeeService service = new FeeService();

    public FeeWindow() {
        super("Student Fee Payment");
        setLayout(new GridLayout(4, 2, 8, 8));
        add(new Label("Registration number:")); add(regNo);
        add(new Label("Amount:")); add(amount);
        Button pay = new Button("Pay");
        Button clear = new Button("Clear");
        Button receipt = new Button("Receipt");
        add(pay); add(clear); add(receipt); add(status);

        pay.addActionListener(e -> {
            try {
                String message = service.pay(regNo.getText(), amount.getText());
                status.setText(message);
            } catch (IllegalArgumentException ex) {
                status.setText(ex.getMessage());
            }
        });
        clear.addActionListener(e -> { regNo.setText(""); amount.setText(""); status.setText("Cleared."); });
        receipt.addActionListener(e -> status.setText("Receipt is available after a successful payment."));
        addWindowListener(new WindowAdapter() {
            @Override public void windowClosing(WindowEvent e) { dispose(); }
        });
        setSize(430, 190);
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> new FeeWindow().setVisible(true));
    }
}
