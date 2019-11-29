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
        // ArrayList<Line> lines = sortThreeColors();
        ArrayList<Line> lines = sortFourBubble();
        /////////////////////////////

        // for (Line arrayLine : lines) {
        // arrayLine.addXValue(WINDOW_WIDTH);
        // int[] yArray = arrayLine.getYValues();
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
                    // System.out.println(lines.get(0).getCurrentXPixel());
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

    public static Color stringToColor(String color) {
        if (color.equals("red")) {
            return Color.RED;
        }
        if (color.equals("orange")) {
            return Color.ORANGE;
        }
        if (color.equals("yellow")) {
            return Color.YELLOW;
        }
        if (color.equals("green")) {
            return Color.GREEN;
        }
        if (color.equals("blue")) {
            return Color.BLUE;
        }
        if (color.equals("pink")) {
            return Color.PINK;
        }
        if (color.equals("black")) {
            return Color.BLACK;
        }
        return Color.RED;
    }

    public static int stringToInt(String color) {
        if (color.equals("red")) {
            return 1;
        }
        if (color.equals("orange")) {
            return 2;
        }
        if (color.equals("yellow")) {
            return 3;
        }
        if (color.equals("green")) {
            return 4;
        }
        if (color.equals("blue")) {
            return 5;
        }
        if (color.equals("pink")) {
            return 6;
        }
        if (color.equals("black")) {
            return 7;
        }
        return 1;
    }

    public static ArrayList<Line> sortFourBubble() {
        ArrayList<Line> lines = new ArrayList<Line>();
        ArrayList<Line> tempLines = new ArrayList<Line>();

        String[] colors = { "red", "orange", "yellow", "green", "blue", "pink", "black" };
        int pixelsBetweenLines = WINDOW_HEIGHT / (colors.length + 1);
        int bound = colors.length;
        for (int i = 0; i < bound; i++) {
            int index = (int) (Math.random() * colors.length);
            lines.add(new Line(stringToColor(colors[index]), stringToInt(colors[index]), 0,
                    pixelsBetweenLines * (i + 1), 5));
            tempLines.add(new Line(stringToColor(colors[index]), stringToInt(colors[index]), 0,
                    pixelsBetweenLines * (i + 1), 5));
            colors = removeArrayIndex(colors, index);
        }

        int totalSwaps = 0;
        int swaps = -1;

        while (swaps != 0) {
            swaps = 0;
            for (int i = 0; i < tempLines.size() - 1; i++) {
                if (tempLines.get(i).getColorVal() > tempLines.get(i + 1).getColorVal()) {
                    swap(tempLines, i, i + 1);
                    swaps++;
                }
            }
            totalSwaps += swaps;
        }

        // this first time through is done to only determine the pixels between each
        // switch
        if (totalSwaps == 0) {
            totalSwaps = 1;
            pixelsBetweenSwitch = WINDOW_WIDTH / totalSwaps;
            for (int k = 0; k < lines.size(); k++) {

                lines.get(k).addXValue(WINDOW_WIDTH);
                lines.get(k).addYValue(lines.get(k).getLastYValue());

            }
        }
        pixelsBetweenSwitch = WINDOW_WIDTH / totalSwaps;

        swaps = -1;
        int tempIndex1;
        int tempIndex2;
        int tempYVal1;
        int tempYVal2;
        int indexOfX = 1;
        // actual sorting and proper pixel values
        while (swaps != 0) {

            swaps = 0;
            // loops through each line, switches it and the next one if the order is not
            // correct
            for (int i = 0; i < lines.size() - 1; i++) {
                tempIndex1 = i;
                tempIndex2 = i + 1;
                // System.out.println("tempIndex1 colorval: " +
                // lines.get(tempIndex1).getColorVal());
                // System.out.println("tempindex2 colorval: " +
                // lines.get(tempIndex2).getColorVal());
                if (lines.get(tempIndex1).getColorVal() > lines.get(tempIndex2).getColorVal()) {

                    swap(lines, tempIndex1, tempIndex2);

                    tempYVal1 = lines.get(tempIndex1).getLastYValue();
                    tempYVal2 = lines.get(tempIndex2).getLastYValue();

                    lines.get(tempIndex1).addXValue((indexOfX) * pixelsBetweenSwitch);
                    lines.get(tempIndex1).addYValue(tempYVal2);

                    lines.get(tempIndex2).addXValue((indexOfX) * pixelsBetweenSwitch);
                    lines.get(tempIndex2).addYValue(tempYVal1);
                    for (int k = 0; k < lines.size(); k++) {
                        if (k != tempIndex1 && k != tempIndex2) {
                            lines.get(k).addXValue((indexOfX) * pixelsBetweenSwitch);
                            lines.get(k).addYValue(lines.get(k).getLastYValue());
                        }
                    }
                    swaps++;
                    indexOfX++;

                }
            }

        }
        // done in case rounding errors at the end gives an error
        for (Line line : lines) {
            if (line.getLastXValue() != WINDOW_WIDTH) {
                line.addXValue(WINDOW_WIDTH);
                line.addYValue(line.getLastYValue());
            }
        }
        for (Line line : lines) {
            System.out.println(line);
        }

        return lines;
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
            int[] yArray = arrayLine.getYValues();
            arrayLine.addYValue(yArray[yArray.length - 1]);
        }

        return lines;
    }

    public static void swap(ArrayList<Line> lines, int index1, int index2) {
        Line temp = lines.get(index1);
        lines.set(index1, lines.get(index2));
        lines.set(index2, temp);
    }

    public static String[] removeArrayIndex(String[] array, int index) {
        String[] returnArray = new String[array.length - 1];
        for (int i = 0; i < index; i++) {
            returnArray[i] = array[i];
        }
        for (int i = index; i < array.length - 1; i++) {
            returnArray[i] = array[i + 1];
        }
        return returnArray;
    }

    public static void testingBubbleSort() {
        int count = 0;
        for (int k = 0; k < 10000; k++) {
            ArrayList<Line> lines = new ArrayList<Line>();
            ArrayList<Line> tempLines = new ArrayList<Line>();

            String[] colors = { "red", "orange", "green", "blue", "pink", "black" };
            int pixelsBetweenLines = WINDOW_HEIGHT / (colors.length + 1);
            int bound = colors.length;
            for (int i = 0; i < bound; i++) {
                int index = (int) (Math.random() * colors.length);
                lines.add(new Line(stringToColor(colors[index]), stringToInt(colors[index]), 0,
                        pixelsBetweenLines * (i + 1), 5));
                tempLines.add(new Line(stringToColor(colors[index]), stringToInt(colors[index]), 0,
                        pixelsBetweenLines * (i + 1), 5));
                colors = removeArrayIndex(colors, index);
            }
            if (lines.get(5).getColorVal() == 7) {
                count++;
            }
        }
        System.out.println((double) count / 10000);
    }

    public static void main(String[] args) {
        SortingVisualizer obj = new SortingVisualizer();

        // testingBubbleSort();
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
