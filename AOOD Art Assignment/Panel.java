import java.awt.*;
import java.util.*;

import javax.swing.*;

class Panel extends JPanel {
    private JFrame frame;
    private ArrayList<Line> lines;

    public Panel(ArrayList<Line> lines) {
        frame = new JFrame("art class");
        this.lines = lines;

        this.setPreferredSize(new Dimension(SortingVisualizer.WINDOW_WIDTH, SortingVisualizer.WINDOW_HEIGHT));
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
        // test();
        // testXChangesArray();
    }

    public static void test() {
        int startX = 0;
        int startY = 200;
        int endX = 800;
        int endY = 600;
        int currentX = 400;
        int height = endY - startY;
        int width = endX - startX;
        int xChange = currentX - startX;
        int drawY = (xChange * height) / width;
        System.out.println(drawY);
    }

    public static void testXChangesArray() {
        Line line = new Line(Color.RED, 0, 200, 10);
        // line.setCurrentXPixel(0);
        // line.addXValue(300);
        // line.addXValue(500);
        // // line.addXValue(800);
        // line.addYValue(700);
        // line.addYValue(400);
        line.addXValue(300);
        line.addXValue(500);
        line.addYValue(200);
        line.addYValue(400);
        System.out.print("x pixel changes: ");
        printArray(line.getXValues());
        System.out.print("previous y pixels: ");
        printArray(line.getYValues());
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
            int currentX = line.getCurrentXPixel();
            int[] xPixelChanges = line.getXValues();
            int[] yPixelChanges = line.getYValues();
            int highestIndex = 0;
            for (int k = 0; k < xPixelChanges.length; k++) {
                if (currentX > xPixelChanges[k]) {
                    // if (currentX >= xPixelChanges[k]) {
                    highestIndex = k;

                }
            }

            /*
             * for (int k = 0; k < highestIndex; k++) { // if (!diagonal) { drawFullLine(g,
             * xPixelChanges[k], xPixelChanges[k + 1], yPixelChanges[k], yPixelChanges[k +
             * 1], i); // } }
             */

            for (int k = 0; k < highestIndex; k++) {
                // if (!diagonal) {
                drawFullLine(g, xPixelChanges[k], xPixelChanges[k + 1], yPixelChanges[k], yPixelChanges[k + 1], i);
                // }
            }

            // boolean diagonal;
            // if (highestIndex + 1 >= yPixelChanges.length) {
            // diagonal = false;
            // } else {
            // diagonal = yPixelChanges[highestIndex] != yPixelChanges[highestIndex + 1];
            // }
            boolean diagonal = yPixelChanges[highestIndex] != yPixelChanges[highestIndex + 1];
            if (diagonal) {
                drawPartialDiagonal(g, xPixelChanges[highestIndex], xPixelChanges[highestIndex + 1],
                        yPixelChanges[highestIndex], yPixelChanges[highestIndex + 1], currentX, i);
            } else {
                drawPartialHorizontal(g, xPixelChanges[highestIndex], xPixelChanges[highestIndex + 1],
                        yPixelChanges[highestIndex], yPixelChanges[highestIndex + 1], currentX, i);
            }
            // System.out.println(highestIndex);
            // drawPartialDiagonal(g, xPixelChanges[0], xPixelChanges[1], yPixelChanges[0],
            // yPixelChanges[1], currentX, i);
            // drawing a diagonal line partially

            // diagonal and horizontal lines alternate
            // there should be two of each y value

            // for (int k = 0; k < xPixelChanges.length - 1; k++) {
            // g.drawLine(xPixelChanges[k], yPixelChanges[k] + i, xPixelChanges[k + 1],
            // yPixelChanges[k + 1] + i);
            // }
            // g.drawLine(0, line.getYStartPixel() + i, line.getCurrentXPixel(),
            // line.getCurrentYPixel() + i);
        }
    }

    public void drawPartialDiagonal(Graphics g, int startX, int endX, int startY, int endY, int currentX, int offset) {
        int height = endY - startY;
        int width = endX - startX;
        int xChange = currentX - startX;
        int drawY = (xChange * height) / width + startY;

        // int drawY = (xChange * height) / width/* + startY*/;
        g.drawLine(startX, startY + offset, currentX, drawY + offset);
        /*
         * g.drawLine(startX + offset, startY + offset, currentX + offset, drawY +
         * offset);
         */
    }

    public void drawFullLine(Graphics g, int startX, int endX, int startY, int endY, int offset) {
        g.drawLine(startX, startY + offset, endX, endY + offset);
    }

    /*
     * public void drawFullLine(Graphics g, int startX, int endX, int startY, int
     * endY, int offset) { g.drawLine(startX + offset, startY + offset, endX +
     * offset, endY + offset); }
     */

    public void drawPartialHorizontal(Graphics g, int startX, int endX, int startY, int endY, int currentX,
            int offset) {
        g.drawLine(startX, startY + offset, currentX, endY + offset);
    }

    public void drawDiagonal(Graphics g, int startX, int endX, int startY, int endY, int offset) {

    }

    public void drawHorizontal(Graphics g, int startX, int endX, int startY, int endY, int offset) {

    }
}
