import java.awt.*;
import java.util.*;

import javax.swing.*;

class Panel extends JPanel {
    private JFrame frame;
    private ArrayList<Line> lines;

    public Panel(ArrayList<Line> lines) {
        frame = new JFrame("art class");
        this.lines = lines;

        this.setPreferredSize(new Dimension(1500, 750));
        this.setBackground(Color.WHITE);
        this.setVisible(true);
        frame.add(this);
        // graphFrame.setSize(new Dimension(windowSizeX, windowSizeY));
        // graphFrame.setResizable(false);
        frame.pack();
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void setLines(ArrayList<Line> lines) {
        this.lines = lines;
    }

    public void paintComponent(Graphics g) {
        for (Line line : lines) {
            g.setColor(line.getColor());
            drawLineWidth(g, line);
        }
        repaint();
    }

    public void drawLineWidth(Graphics g, Line line) {
        for (int i = 0; i < line.getPixelWidth(); i++) { // for each pixel
            int[] xChangesAndPixelsBetween = new int[line.getXPixelsAtChanges().length * 2];
            for (int j = 0; j < line.getXPixelsAtChanges().length; j++) {
                int[] arr = line.getXPixelsAtChanges();
                xChangesAndPixelsBetween[j * 2] = arr[j]; // adding the switch pixel length to
                                                          // each x change
                xChangesAndPixelsBetween[j * 2 + 1] = arr[j] + SortingVisualizer.pixelsBetweenSwitch;

            }
            int[] xChanges = Line.appendArray(xChangesAndPixelsBetween, line.getCurrentXPixel());

            int[] yPixels = Line.appendArray(line.getPreviousYPixels(), line.getCurrentYPixel());
            int indexOfFinalXChange = 0;
            boolean keepGoing = true;
            // Determining which xChange to graph to
            for (int j = 0; j < xChanges.length; j++) {
                if (xChanges[j] > line.getCurrentXPixel() && keepGoing) {
                    indexOfFinalXChange = j;
                    keepGoing = false;
                }
            }

            boolean diagonalLine = false;
            int yIndex = 0;

            // ALL IF LAST POINT ISN'T CURRENT Y???

            // graphing every line except the last one
            // k is the index of the xChanges array
            for (int k = 0; k < indexOfFinalXChange; k++) {
                if (k == 0) {
                    g.drawLine(0, yPixels[yIndex] + i, xChanges[k], yPixels[yIndex] + i);
                    diagonalLine = true;
                } else if (diagonalLine) {
                    g.drawLine(xChanges[k], yPixels[yIndex] + i, xChanges[k + 1], yPixels[yIndex + 1] + i);
                    yIndex++;
                    diagonalLine = false;
                } else {
                    g.drawLine(xChanges[k], yPixels[yIndex] + i, xChanges[k + 1], yPixels[yIndex] + i);
                    diagonalLine = true;
                }
            }
            // if the last line
            if (diagonalLine) {
                double yDistance = yPixels[yIndex + 1] - yPixels[yIndex];
                double currentXDistance = line.getCurrentXPixel() - xChanges[indexOfFinalXChange];
                double xDistance = xChanges[indexOfFinalXChange + 1] - xChanges[indexOfFinalXChange];
                g.drawLine(xChanges[indexOfFinalXChange], yPixels[yIndex] + i, line.getCurrentXPixel(),
                        (int) (yDistance * currentXDistance / xDistance) + i);
            } else {
                g.drawLine(xChanges[indexOfFinalXChange], yPixels[yIndex] + i, line.getCurrentXPixel(),
                        yPixels[yIndex] + i);
            }
            // g.drawLine(0, line.getYStartPixel() + i, line.getCurrentXPixel(),
            // line.getCurrentYPixel() + i);
        }
    }
}
