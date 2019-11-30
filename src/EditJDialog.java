import javax.swing.*;
import javax.swing.text.NumberFormatter;
import java.awt.*;

public class EditJDialog extends JDialog {
    private JButton ok = createOkButton();
    private JLabel labelN = new JLabel(" N :");
    private JLabel labelFirst = new JLabel(" First:");
    private JLabel labelStep = new JLabel(" Step:");
    private JTextField inputN = new JFormattedTextField(new NumberFormatter());
    private JTextField inputFirst = new JTextField("");
    private JTextField inputStep = new JTextField("");
    private Series q;

    public EditJDialog(JFrame parent, String title, Series q) {
        super(parent, title);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);
        setVisible(true);
        setModal(true);
        this.q = q;
        Container container = this.getContentPane();
        container.setLayout(new GridLayout(4, 2));
        container.add(labelN);
        inputN.setText(Integer.toString(q.getN()));
        container.add(inputN);
        container.add(labelFirst);
        inputFirst.setText(Double.toString(q.getFirst()));
        container.add(inputFirst);
        container.add(labelStep);
        inputStep.setText(Double.toString(q.getStep()));
        container.add(inputStep);
        container.add(ok);
        pack();
    }

    private JButton createOkButton() {
        JButton ok = new JButton("Ok");
        ok.addActionListener(e -> {
            q.setFirst(readDoubleFromField(inputFirst));
            q.setStep(readDoubleFromField(inputStep));
            q.setN(Integer.parseInt(inputN.getText()));
            setVisible(false);
        });
        return ok;
    }

    private double readDoubleFromField(JTextField field) {
        try {
            return Double.parseDouble(field.getText());
        } catch (NumberFormatException e) {
            return 0d;
        }
    }
}