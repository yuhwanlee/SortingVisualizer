import java.awt.*;
import java.util.*;

public class SortingVisualizer {
    private Panel panel;
    public static int pixelsBetweenSwitch = 500;
    public static final int WINDOW_WIDTH = 1500;
    public static final int WINDOW_HEIGHT = 750;

    public SortingVisualizer() {
        // ArrayList<Line> lines = new ArrayList<Line>();
        // Line line = new Line(Color.RED, 0, 200, 5);

        // // line.addXValue(800);
        // // line.addYValue(400);
        // line.addXValue(300);
        // line.addYValue(200);

        // line.addXValue(300 + pixelsBetweenSwitch);
        // line.addYValue(400);

        // lines.add(line);

        // Line line1 = new Line(Color.BLUE, 0, 500, 5);

        // line1.addXValue(800);
        // line1.addYValue(500); // must be same as original y value
        // line1.addXValue(800 + pixelsBetweenSwitch);
        // line1.addYValue(200); // must be repeated twice

        // lines.add(line1);

        // Line line2 = new Line(Color.GREEN, 0, 350, 5);

        // line2.addXValue(500);
        // line2.addYValue(350);
        // line2.addXValue(500 + pixelsBetweenSwitch);
        // line2.addYValue(100);

        // lines.add(line2);
        //////////////////////////////
        ArrayList<Line> lines = sortThreeColors();
        /////////////////////////////

        // for (Line arrayLine : lines) {
        // arrayLine.addXValue(WINDOW_WIDTH);
        // int[] yArray = arrayLine.getPreviousYPixels();
        // arrayLine.addYValue(yArray[yArray.length - 1]);
        // }
        panel = new Panel(lines);

        Thread thread = new Thread() {
            int i = 0;

            public void run() {
                for (; i != 1;) {
                    for (Line line : lines) {
                        line.addCurrentXPixel(5);
                    }
                    if (lines.get(0).getCurrentXPixel() >= WINDOW_WIDTH) {
                        i = 1;
                    }
                    System.out.println(lines.get(0).getCurrentXPixel());
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

    public static ArrayList<Line> sortThreeColors() {
        ArrayList<Line> lines = new ArrayList<Line>();
        Line redLine = new Line(Color.RED, 1, 0, 375, 5);
        Line greenLine = new Line(Color.GREEN, 2, 0, 650, 5);
        Line blueLine = new Line(Color.BLUE, 3, 0, 100, 5);

        lines.add(blueLine);
        lines.add(redLine);
        lines.add(greenLine);

        int numberOfSwitches = 2;
        pixelsBetweenSwitch = WINDOW_WIDTH / (numberOfSwitches + 1);

        int indexOfSwap = 1;
        int tempIndex1 = 0;
        int tempIndex2 = 1;
        int tempYVal1;
        int tempYVal2;
        swap(lines, tempIndex1, tempIndex2);
        tempYVal1 = lines.get(tempIndex1).getLastYValue();
        tempYVal2 = lines.get(tempIndex2).getLastYValue();
        lines.get(tempIndex1).addXValue(indexOfSwap * pixelsBetweenSwitch);
        lines.get(tempIndex1).addYValue(tempYVal2);

        lines.get(tempIndex2).addXValue(indexOfSwap * pixelsBetweenSwitch);
        lines.get(tempIndex2).addYValue(tempYVal1);

        // for every other line, add an x change at this x value, with the y value being
        // the same as previous (horizontal), use for loop, exclude if either of the two
        // indices
        lines.get(2).addXValue(indexOfSwap * pixelsBetweenSwitch);
        lines.get(2).addYValue(lines.get(2).getLastYValue());

        indexOfSwap++;
        tempIndex1 = 1;
        tempIndex2 = 2;
        swap(lines, tempIndex1, tempIndex2);
        tempYVal1 = lines.get(tempIndex1).getLastYValue();
        tempYVal2 = lines.get(tempIndex2).getLastYValue();

        lines.get(tempIndex1).addXValue(indexOfSwap * pixelsBetweenSwitch);
        lines.get(tempIndex1).addYValue(tempYVal2);

        lines.get(tempIndex2).addXValue(indexOfSwap * pixelsBetweenSwitch);
        lines.get(tempIndex2).addYValue(tempYVal1);

        // for every other line, add an x change at this x value, with the y value being
        // the same as previous (horizontal), use for loop, exclude if either of the two
        // indices
        lines.get(0).addXValue(indexOfSwap * pixelsBetweenSwitch);
        lines.get(0).addYValue(lines.get(0).getLastYValue());

        for (Line arrayLine : lines) {
            arrayLine.addXValue(WINDOW_WIDTH);
            int[] yArray = arrayLine.getPreviousYPixels();
            arrayLine.addYValue(yArray[yArray.length - 1]);
        }

        return lines;
    }

    public static void swap(ArrayList<Line> lines, int index1, int index2) {
        Line temp = lines.get(index1);
        lines.set(index1, lines.get(index2));
        lines.set(index2, temp);
    }

    public static void main(String[] args) {
        SortingVisualizer obj = new SortingVisualizer();
        // ArrayList<Line> lines = new ArrayList<Line>();
        // Line redLine = new Line(Color.RED, 1, 0, 375, 5);
        // Line greenLine = new Line(Color.GREEN, 2, 0, 650, 5);
        // Line blueLine = new Line(Color.BLUE, 3, 0, 100, 5);
        // lines.add(blueLine);
        // lines.add(redLine);
        // lines.add(greenLine);
        // for (Line line : lines) {
        // System.out.println(line.getColorVal());
        // }
        // swap(lines, 0, 1);
        // for (Line line : lines) {
        // System.out.println(line.getColorVal());
        // }
    }
}
