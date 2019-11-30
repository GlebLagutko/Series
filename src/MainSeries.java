import javax.swing.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class MainSeries {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
          SeriesWindows seriesWindows = createWindow();
          seriesWindows.setVisible(true);
        });

    }

    private static SeriesWindows createWindow() {
        SeriesWindows seriesWindows = new SeriesWindows();
        seriesWindows.setSize(300, 200);
        seriesWindows.setResizable(false );

        seriesWindows.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentHidden(ComponentEvent e) {
                System.exit(0);
            }
        });
        return seriesWindows;
    }
}
