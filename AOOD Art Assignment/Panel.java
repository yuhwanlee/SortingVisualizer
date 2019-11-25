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

    public static void main(String[] args) {
        testXChangesArray();
    }

    public static void testXChangesArray() {
        Line line = new Line(Color.RED, 0, 200, 10);
        // line.setCurrentXPixel(0);
        line.addXPixelChange(300);
        line.addXPixelChange(500);
        // line.addXPixelChange(800);
        line.addPreviousYPixels(700);
        line.addPreviousYPixels(400);
        System.out.print("x pixel changes: ");
        printArray(line.getXPixelsAtChanges());
        System.out.print("previous y pixels: ");
        printArray(line.getPreviousYPixels());
    }

    public static void printArray(int[] arr) {
        System.out.print("{");
        for (int i = 0; i < arr.length - 1; i++) {
            System.out.print(arr[i] + ", ");
        }
        System.out.println(arr[arr.length - 1] + "}");
    }

    public void drawLineWidth(Graphics g, Line line) {
        for (int i = 0; i < line.getPixelWidth(); i++) { // for each pixel

            // xpixelchanges and ypixelchanges should be the same sizes

            int[] xPixelChanges = line.getXPixelsAtChanges();
            int[] yPixelChanges = line.getPreviousYPixels();

            for (int k = 0; k < xPixelChanges.length - 1; k++) {
                g.drawLine(xPixelChanges[k], yPixelChanges[k] + i, xPixelChanges[k + 1], yPixelChanges[k + 1] + i);
            }

            // g.drawLine(0, line.getYStartPixel() + i, line.getCurrentXPixel(),
            // line.getCurrentYPixel() + i);
        }
    }
}
