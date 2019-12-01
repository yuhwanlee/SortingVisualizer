import java.awt.*;
import java.util.*;

public class SortingVisualizer {
    private Panel panel;
    public static int pixelsBetweenSwitch = 500;
    public static final int WINDOW_WIDTH = 1500;
    public static final int WINDOW_HEIGHT = 750;

    public SortingVisualizer() {

        ArrayList<Line> lines = Sorts.cocktailSort();

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

    public static ArrayList<Line> makeRandomLines(String[] colors) {
        int pixelsBetweenLines = WINDOW_HEIGHT / (colors.length + 1);
        ArrayList<Line> returnList = new ArrayList<Line>();
        int bound = colors.length;
        for (int i = 0; i < bound; i++) {
            int index = (int) (Math.random() * colors.length);
            returnList.add(new Line(stringToColor(colors[index]), stringToInt(colors[index]), 0,
                    pixelsBetweenLines * (i + 1), 5));
            colors = removeArrayIndex(colors, index);
        }
        return returnList;
    }

    public static ArrayList<Line> makeLinesInOrder(String[] colors) {
        int pixelsBetweenLines = WINDOW_HEIGHT / (colors.length + 1);
        ArrayList<Line> returnList = new ArrayList<Line>();
        for (int i = 0; i < colors.length; i++) {
            returnList.add(
                    new Line(stringToColor(colors[i]), stringToInt(colors[i]), 0, pixelsBetweenLines * (i + 1), 5));
        }
        return returnList;
    }

    public static ArrayList<Line> copyArrayList(ArrayList<Line> lines) {
        ArrayList<Line> returnLines = new ArrayList<Line>();
        for (Line line : lines) {
            returnLines.add(new Line(line.getColor(), line.getColorVal(), 0, line.getYStartPixel(), 5));
        }
        return returnLines;
    }

    public static ArrayList<Line> sortTemplate() {
        ArrayList<Line> lines = new ArrayList<Line>();
        ArrayList<Line> tempLines = new ArrayList<Line>();

        String[] colors = { "red", "red", "orange", "orange", "yellow", "yellow", "green", "green", "blue", "blue",
                "pink", "pink", "black", "black" };
        // String[] colors = { "red", "orange", "yellow", "green", "blue", "pink",
        // "black" };
        String[] worstCaseColors = { "black", "black", "pink", "pink", "blue", "blue", "green", "green", "yellow",
                "yellow", "orange", "orange", "red", "red" };

        int pixelsBetweenLines = SortingVisualizer.WINDOW_HEIGHT / (colors.length + 1);
        lines = makeRandomLines(colors);
        tempLines = copyArrayList(lines);

        int totalSwaps = 0;
        int swaps = -1;

        ////
        // sort the tempLines arrayList and increment swaps for each swap
        /////

        // this first time through is done to only determine the pixels between each
        // switch
        // if there are no swaps, make sure it draws to the end
        if (totalSwaps == 0) {
            totalSwaps = 1;
            pixelsBetweenSwitch = SortingVisualizer.WINDOW_WIDTH / totalSwaps;
            for (int k = 0; k < lines.size(); k++) {

                lines.get(k).addXValue(SortingVisualizer.WINDOW_WIDTH);
                lines.get(k).addYValue(lines.get(k).getLastYValue());

            }
        }
        pixelsBetweenSwitch = SortingVisualizer.WINDOW_WIDTH / totalSwaps;

        swaps = -1;
        int tempIndex1;
        int tempIndex2;
        int tempYVal1;
        int tempYVal2;
        // index of xPixel is the index of which # switch it is
        int indexOfX = 1;
        // actual sorting and proper pixel values
        while (swaps != 0) {

            swaps = 0;
            // loops through each line, switches it and the next one if the order is not
            // correct
            for (int i = 0; i < lines.size() - 1; i++) {
                tempIndex1 = i;
                // MAKE TEMPINDEX2 THE INDEX YOU'RE COMPARING
                tempIndex2 = i + 1;
                // System.out.println("tempIndex1 colorval: " +
                // lines.get(tempIndex1).getColorVal());
                // System.out.println("tempindex2 colorval: " +
                // lines.get(tempIndex2).getColorVal());
                if (true /* MAKE A CONDITION FOR WHEN TO SWAP */ ) {

                    // REST OF THIS CODE IS SWAPPING
                    swap(lines, tempIndex1, tempIndex2);

                    tempYVal1 = lines.get(tempIndex1).getLastYValue();
                    tempYVal2 = lines.get(tempIndex2).getLastYValue();

                    lines.get(tempIndex1).addXValue((indexOfX) * pixelsBetweenSwitch);
                    lines.get(tempIndex1).addYValue(tempYVal2);

                    lines.get(tempIndex2).addXValue((indexOfX) * pixelsBetweenSwitch);
                    lines.get(tempIndex2).addYValue(tempYVal1);
                    // makes all other lines have a x value here
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
            if (line.getLastXValue() != SortingVisualizer.WINDOW_WIDTH) {
                line.addXValue(SortingVisualizer.WINDOW_WIDTH);
                line.addYValue(line.getLastYValue());
            }
        }
        for (Line line : lines) {
            System.out.println(line);
        }

        return lines;
    }

    public static int[] insertionSortIntArray(int[] arr) {
        int[] sortedArray = { arr[0] };
        boolean inserted = false;
        for (int i = 1; i < arr.length; i++) {
            inserted = false;
            int currentNum = arr[i];
            // System.out.println("currentNum: " + currentNum);
            for (int k = 0; k < sortedArray.length; k++) {
                // System.out.println(currentNum >= sortedArray[k]);
                if (sortedArray[k] >= currentNum && !inserted) {
                    sortedArray = insertArrayIndex(sortedArray, k, currentNum);
                    inserted = true;
                }
            }
            // System.out.println("inserted: " + inserted);
            // if currentNum is greater than everything in sorted list
            if (sortedArray[sortedArray.length - 1] < currentNum) {
                sortedArray = insertArrayIndex(sortedArray, sortedArray.length, currentNum);
                inserted = true;
            }
            // if currentNum is less than everything
            if (!inserted) {
                sortedArray = insertArrayIndex(sortedArray, 0, currentNum);
            }
            // printArray(sortedArray);
        }
        return sortedArray;
    }

    public static int[] insertArrayIndex(int[] arr, int index, int value) {
        int[] returnArray = new int[arr.length + 1];
        for (int i = 0; i < index; i++) {
            returnArray[i] = arr[i];
        }
        returnArray[index] = value;
        for (int i = index; i < arr.length; i++) {
            returnArray[i + 1] = arr[i];
        }
        return returnArray;
    }

    public static int[] bubbleSortIntArray(int[] array) {
        int swaps = -1;

        while (swaps != 0) {
            swaps = 0;
            for (int i = 0; i < array.length - 1; i++) {
                if (array[i] > array[i + 1]) {
                    int temp = array[i];
                    array[i] = array[i + 1];
                    array[i + 1] = temp;
                    swaps++;
                }
            }
        }
        return array;
    }

    public static int[] selectionSortIntArray(int[] array) {
        int swaps = 0;
        for (int i = 0; i < array.length - 1; i++) {
            int lowestIndex = i;
            for (int k = i + 1; k < array.length; k++) {
                if (array[k] < array[lowestIndex]) {
                    lowestIndex = k;
                }
            }
            if (lowestIndex != i) {
                swaps++;
                swap(array, i, lowestIndex);
            }
        }
        System.out.println(swaps);
        return array;
    }

    public static int[] cocktailSortIntArray(int[] array) {
        int swaps = -1;
        int totalSwaps = 0;
        int start = 0;
        int end = array.length - 1;
        printArray(array);
        while (swaps != 0) {
            swaps = 0;
            // forwards
            for (int i = start; i < end; i++) {
                if (array[i] > array[i + 1]) {
                    swap(array, i, i + 1);
                    swaps++;
                }
            }
            printArray(array);
            end--;
            // backwards
            for (int i = end; i > start; i--) {
                if (array[i] < array[i - 1]) {
                    swap(array, i, i - 1);
                    swaps++;
                }
            }
            printArray(array);
            start++;
            totalSwaps += swaps;
        }
        System.out.println("totalSwaps: " + totalSwaps);
        return array;
    }

    public static void swap(ArrayList<Line> lines, int index1, int index2) {
        Line temp = lines.get(index1);
        lines.set(index1, lines.get(index2));
        lines.set(index2, temp);
    }

    public static void swap(int[] array, int index1, int index2) {
        int temp = array[index1];
        array[index1] = array[index2];
        array[index2] = temp;
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

    public static void testingSort() {
        ArrayList<Line> lines = new ArrayList<Line>();
        ArrayList<Line> tempLines = new ArrayList<Line>();

        // String[] colors = { "red", "red", "orange", "orange", "yellow", "yellow",
        // "green", "green", "blue", "blue",
        // "pink", "pink", "black", "black" };
        // // String[] colors = { "red", "orange", "yellow", "green", "blue", "pink",
        // // "black" };
        // String[] worstCaseColors = { "black", "black", "pink", "pink", "blue",
        // "blue", "green", "green", "yellow",
        // "yellow", "orange", "orange", "red", "red" };

        String[] colors = { "green", "red", "blue" };
        int pixelsBetweenLines = WINDOW_HEIGHT / (colors.length + 1);
        lines = makeLinesInOrder(colors);
        tempLines = copyArrayList(lines);

        int totalSwaps = 0;
        int swaps = -1;

        ArrayList<Line> tempSortedArrayList = new ArrayList<Line>();
        tempSortedArrayList.add(lines.get(0));

        ///////
        boolean inserted = false;
        for (int i = 1; i < tempLines.size(); i++) {
            inserted = false;
            int currentColorVal = tempLines.get(i).getColorVal();
            Line currentLine = tempLines.get(i);
            // System.out.println("currentNum: " + currentNum);
            for (int k = 0; k < tempSortedArrayList.size(); k++) {
                // System.out.println(currentNum >= sortedArray[k]);
                if (tempSortedArrayList.get(k).getColorVal() >= currentColorVal && !inserted) {
                    tempSortedArrayList.add(k, currentLine);
                    inserted = true;
                    totalSwaps++;
                }
            }
            // System.out.println("inserted: " + inserted);
            // if currentNum is greater than everything in sorted list
            if (tempSortedArrayList.get(tempSortedArrayList.size() - 1).getColorVal() < currentColorVal) {
                tempSortedArrayList.add(tempSortedArrayList.size(), currentLine);
                inserted = true;
                totalSwaps++;
            }
            // if currentNum is less than everything
            if (!inserted) {
                tempSortedArrayList.add(0, currentLine);
                totalSwaps++;
            }
            // printArray(sortedArray);
        }
        for (Line line : tempSortedArrayList) {
            System.out.println(line);
        }
        System.out.println(SortingVisualizer.WINDOW_WIDTH / totalSwaps);
    }

    public static void printArray(int[] arr) {
        System.out.print("{");
        for (int i = 0; i < arr.length - 1; i++) {
            System.out.print(arr[i] + ", ");
        }
        System.out.println(arr[arr.length - 1] + "}");
    }

    public static void main(String[] args) {
        SortingVisualizer obj = new SortingVisualizer();
        // printArray(cocktailSortIntArray(new int[] { 5, 4, 3, 2, 1 }));
        // printArray(insertionSortIntArray(new int[] { 6, 5, 4, 3, 2, 1 }));
        // testingSort();
    }
}
