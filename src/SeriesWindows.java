import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class SeriesWindows extends JFrame {
    private JTextField inputFileName;
    private JRadioButton exp;
    private JRadioButton lin;
    Exponential expSeries;
    Liner linSeries;

    public SeriesWindows() {
        this.expSeries = new Exponential(0, 0, 0);
        this.linSeries = new Liner(0, 0, 0);
    }

    @Override
    protected JRootPane createRootPane() {
        JPanel panel = new JPanel();
        JRootPane pane = new JRootPane();
        pane.setContentPane(panel);
        panel.setLayout(new GridLayout(4, 2));
        exp = new JRadioButton("exp");
        lin = new JRadioButton("Liner");
        ButtonGroup switchSeries = new ButtonGroup();
        switchSeries.add(exp);
        switchSeries.add(lin);
        JButton toFile = createWriteButton();
        JButton showN = createButtonShowN();
        JButton editButton = createEditButton();
        JLabel lFileName = new JLabel(" File Name : ");
        toFile.setBackground(Color.GREEN);
        showN.setBackground(Color.ORANGE);
        editButton.setBackground(Color.RED);
        inputFileName = new JTextField();
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

    private JButton createButtonShowN() {
        JButton button = new JButton("showN");
        button.addActionListener(e -> {
            if (exp.isSelected()) {
                showNElements(expSeries);
            } else {
                showNElements(linSeries);
            }
        });
        return button;
    }

    private void showNElements(Series s) {
        JOptionPane.showMessageDialog(this, s.toString(), "N Elements", JOptionPane.PLAIN_MESSAGE);
    }

    private JButton createEditButton() {
        JButton edit = new JButton("Edit");
        edit.addActionListener(e -> {
            new EditJDialog(this, "Edit", exp.isSelected() ? expSeries : linSeries);
        });
        return edit;
    }

    private JButton createWriteButton() {
        JButton writeFile = new JButton("WriteToFile");
        writeFile.addActionListener(e -> {
            String filename = inputFileName.getText();
            if (exp.isSelected()) {
                try {
                    writeAnswerInFile(expSeries, filename);

                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            } else {
                try {
                    writeAnswerInFile(linSeries, filename);
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        });
        return writeFile;
    }

    protected void writeAnswerInFile(Series s, String filename) throws IOException {
        s.save(filename);
    }
}
