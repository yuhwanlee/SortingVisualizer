import java.awt.*;
import java.util.*;

public class SortingVisualizer {
    private Panel panel;
    public static int pixelsBetweenSwitch = 200;

    public SortingVisualizer() {
        ArrayList<Line> lines = new ArrayList<Line>();
        Line testLine = new Line(Color.RED, 200, 10);
        testLine.setCurrentXPixel(200);
        lines.add(testLine);

        panel = new Panel(lines);

        /*
         * Timer timer = new Timer(); TimerTask task = new TaskHelper(panel, lines);
         * timer.schedule(task, 1000, 500);
         */

        Thread thread = new Thread() {
            public void run() {
                for (;;) {
                    for (Line line : lines) {
                        line.addCurrentXPixel(5);
                    }
                    try {
                        Thread.sleep(25);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    panel.repaint();
                }
            }
        };
        thread.start();
    }

    public static void main(String[] args) {
        SortingVisualizer obj = new SortingVisualizer();

    }
}
