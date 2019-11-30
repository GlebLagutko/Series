import javax.swing.*;
import java.awt.*;

public class SeriesWindows extends JFrame {
    private JTextField inputFileName;
    private JRadioButton exp;
    private JRadioButton lin;
    private Series s;

    public SeriesWindows() {
        s = new Exponential(0, 0, 0);
    }

    @Override
    protected JRootPane createRootPane() {
        JPanel panel = new JPanel();
        JRootPane pane = new JRootPane();
        pane.setContentPane(panel);
        panel.setLayout(new GridLayout(4, 2));
        exp = createRadioButton("Exp");
        lin = createRadioButton("Liner");
        ButtonGroup switchSeries = new ButtonGroup();
        switchSeries.add(exp);
        switchSeries.add(lin);
        JButton toFile = createWriteButton();
        JButton showN = createButtonShowN();
        JButton editButton = createEditButton();
        JLabel lFileName = new JLabel(" File Name  ");

        inputFileName = new JTextField("output.txt");
        panel.add(lFileName);
        panel.add(inputFileName);
        panel.add(exp);
        exp.setSelected(true);
        panel.add(lin);
        panel.add(toFile);
        panel.add(showN);
        panel.add(editButton);
        pack();
        return pane;
    }


    private JRadioButton createRadioButton(String exp) {
        JRadioButton button = new JRadioButton(exp);
        button.addActionListener(e -> {
            s = getSeries(s.getN(), s.getStep(), s.getFirst());
        });
        return button;
    }

    private JButton createButtonShowN() {
        JButton button = new JButton("showN");
        button.addActionListener(e -> {
            showNElements(s);
        });
        return button;
    }

    private void showNElements(Series s) {
        JOptionPane.showMessageDialog(this, s.toString(), "N Elements", JOptionPane.PLAIN_MESSAGE);
    }

    private JButton createEditButton() {
        JButton edit = new JButton("Edit");
        edit.addActionListener(e -> {
            new EditJDialog(this, "Edit", s).setModal(true);
        });
        return edit;
    }

    private JButton createWriteButton() {
        JButton writeFile = new JButton("WriteToFile");
        writeFile.addActionListener(e -> {
            String filename = inputFileName.getText();
            writeAnswerInFile(s, filename);
        });
        return writeFile;
    }

    private Series getSeries(int n, double step, double first) {
        return exp.isSelected() ? new Exponential(first, step, n) : new Liner(first, step, n);
    }


    protected void writeAnswerInFile(Series s, String filename) {
        s.save(filename);
    }
}
