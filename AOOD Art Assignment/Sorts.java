import java.awt.*;
import java.util.*;

public class Sorts {
    public static int pixelsBetweenSwitch;

    public static ArrayList<Line> cocktailSort() {
        ArrayList<Line> lines = new ArrayList<Line>();
        ArrayList<Line> tempLines = new ArrayList<Line>();

        String[] colors = { "red", "orange", "yellow", "green", "blue", "pink", "black", "red", "orange", "yellow",
                "green", "blue", "pink", "black", "red", "orange", "yellow", "green", "blue", "pink", "black" };

        // String[] colors = { "red", "orange", "yellow", "green", "blue", "pink",
        // "black" };

        String[] worstCaseColors = { "black", "black", "black", "black", "pink", "pink", "pink", "pink", "blue", "blue",
                "blue", "blue", "green", "green", "green", "green", "yellow", "yellow", "yellow", "orange", "orange",
                "orange", "red", "red", "red", "red" };

        // String[] colors = { "red", "orange", "green", "blue" };
        int pixelsBetweenLines = SortingVisualizer.WINDOW_HEIGHT / (colors.length + 1);
        lines = makeLinesInOrder(worstCaseColors);
        tempLines = copyArrayList(lines);

        int totalSwaps = 0;
        int swaps = -1;

        int start = 0;
        int end = tempLines.size() - 1;
        while (swaps != 0) {
            swaps = 0;
            // forwards
            for (int i = start; i < end; i++) {
                if (tempLines.get(i).getColorVal() > tempLines.get(i + 1).getColorVal()) {
                    swap(tempLines, i, i + 1);
                    swaps++;
                }
            }
            end--;
            // backwards
            for (int i = end; i > start; i--) {
                if (tempLines.get(i).getColorVal() < tempLines.get(i - 1).getColorVal()) {
                    swap(tempLines, i, i - 1);
                    swaps++;
                }
            }
            start++;
            totalSwaps += swaps;
        }

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

        int tempIndex1;
        int tempIndex2;
        int tempYVal1;
        int tempYVal2;
        // index of xPixel is the index of which # switch it is
        int indexOfX = 1;
        // actual sorting and proper pixel values
        swaps = -1;
        start = 0;
        end = lines.size() - 1;
        while (swaps != 0) {
            swaps = 0;
            // forwards
            for (int i = start; i < end; i++) {
                if (lines.get(i).getColorVal() > lines.get(i + 1).getColorVal()) {
                    tempYVal1 = lines.get(i).getLastYValue();
                    tempYVal2 = lines.get(i + 1).getLastYValue();
                    lines.get(i).addYValue(tempYVal2);
                    lines.get(i + 1).addYValue(tempYVal1);

                    swap(lines, i, i + 1);
                    swaps++;
                    for (Line line : lines) { // every line gets the same new xvalue
                        line.addXValue(pixelsBetweenSwitch * indexOfX);
                    }
                    indexOfX++;

                    for (int k = 0; k < lines.size(); k++) {
                        if (k != i && k != i + 1) {
                            lines.get(k).duplicateLastYValue();
                        }
                    }

                }
            }
            end--;
            // backwards
            for (int i = end; i > start; i--) {
                if (lines.get(i).getColorVal() < lines.get(i - 1).getColorVal()) {
                    tempYVal1 = lines.get(i).getLastYValue();
                    tempYVal2 = lines.get(i - 1).getLastYValue();
                    lines.get(i).addYValue(tempYVal2);
                    lines.get(i - 1).addYValue(tempYVal1);

                    swap(lines, i, i - 1);
                    swaps++;
                    for (Line line : lines) { // every line gets the same new xvalue
                        line.addXValue(pixelsBetweenSwitch * indexOfX);
                    }
                    indexOfX++;

                    for (int k = 0; k < lines.size(); k++) {
                        if (k != i && k != i - 1) {
                            lines.get(k).duplicateLastYValue();
                        }
                    }

                }
            }
            start++;
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

    public static ArrayList<Line> selectionSort() {
        ArrayList<Line> lines = new ArrayList<Line>();
        ArrayList<Line> tempLines = new ArrayList<Line>();

        /*
         * String[] colors = { "red", "red", "orange", "orange", "yellow", "yellow",
         * "green", "green", "blue", "blue", "pink", "pink", "black", "black" };
         */
        // String[] colors = { "red", "orange", "yellow", "green", "blue", "pink",
        // "black" };
        String[] colors = { "red", "orange", "yellow", "green", "blue", "pink", "black", "red", "orange", "yellow",
                "green", "blue", "pink", "black" };

        /*
         * String[] worstCaseColors = { "black", "black", "pink", "pink", "blue",
         * "blue", "green", "green", "yellow", "yellow", "orange", "orange", "red",
         * "red" };
         */
        // String[] colors = { "red", "orange", "green", "blue" };
        int pixelsBetweenLines = SortingVisualizer.WINDOW_HEIGHT / (colors.length + 1);
        lines = makeRandomLines(colors);
        tempLines = copyArrayList(lines);

        int totalSwaps = 0;

        for (int i = 0; i < tempLines.size() - 1; i++) {
            int lowestIndex = i;
            for (int k = i + 1; k < tempLines.size(); k++) {
                if (tempLines.get(k).getColorVal() < tempLines.get(lowestIndex).getColorVal()) {
                    lowestIndex = k;
                }
            }
            if (lowestIndex != i) {
                totalSwaps++;
                swap(tempLines, i, lowestIndex);
            }
        }

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

        int tempIndex1;
        int tempIndex2;
        int tempYVal1;
        int tempYVal2;
        // index of xPixel is the index of which # switch it is
        int indexOfX = 1;
        // actual sorting and proper pixel values

        for (int i = 0; i < lines.size() - 1; i++) {
            int lowestIndex = i;
            for (int k = i + 1; k < lines.size(); k++) {
                if (lines.get(k).getColorVal() < lines.get(lowestIndex).getColorVal()) {
                    lowestIndex = k;
                }
            }
            if (lowestIndex != i) {
                for (int k = 0; k < lines.size(); k++) {
                    if (k != lowestIndex && k != i) {
                        lines.get(k).duplicateLastYValue();
                    }
                }
                tempYVal1 = lines.get(lowestIndex).getLastYValue();
                tempYVal2 = lines.get(i).getLastYValue();

                lines.get(lowestIndex).addYValue(tempYVal2);
                lines.get(i).addYValue(tempYVal1);
                swap(lines, i, lowestIndex);

                for (Line line : lines) { // every line gets the same new xvalue
                    line.addXValue(pixelsBetweenSwitch * indexOfX);
                }
                indexOfX++;

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

    public static ArrayList<Line> insertionSort() {
        ArrayList<Line> lines = new ArrayList<Line>();
        ArrayList<Line> tempLines = new ArrayList<Line>();

        /*
         * String[] colors = { "red", "red", "orange", "orange", "yellow", "yellow",
         * "green", "green", "blue", "blue", "pink", "pink", "black", "black" };
         */

        String[] colors = { "red", "orange", "yellow", "green", "blue", "pink", "black", "red", "orange", "yellow",
                "green", "blue", "pink", "black", "red", "orange", "yellow", "green", "blue", "pink", "black", "red",
                "orange", "yellow", "green", "blue", "pink", "black" };
        /*
         * String[] colors = { "red", "orange", "yellow", "green", "blue", "pink",
         * "black" };
         */
        // String[] worstCaseColors = { "black", "black", "pink", "pink", "blue",
        // "blue", "green", "green", "yellow",
        // "yellow", "orange", "orange", "red", "red" };

        int pixelsBetweenLines = SortingVisualizer.WINDOW_HEIGHT / (colors.length + 1);
        lines = makeRandomLines(colors);
        tempLines = copyArrayList(lines);

        int totalSwaps = 0;
        int swaps = -1;

        ArrayList<Line> tempSortedArrayList = new ArrayList<Line>();
        tempSortedArrayList.add(lines.get(0));

        ///////
        boolean inserted = false;
        for (int i = 1; i < tempLines.size(); i++) {
            inserted = false;
            Line currentLine = tempLines.get(i);
            int currentColorVal = currentLine.getColorVal();
            for (int k = 0; k < i; k++) {
                if (tempLines.get(k).getColorVal() > currentColorVal && !inserted) {
                    tempLines.remove(i);
                    tempLines.add(k, currentLine); // put the currentline in the right place
                    inserted = true;
                    totalSwaps++;
                }

            }

            if (tempLines.get(i - 1).getColorVal() <= currentColorVal) {// if number is greater than everything in the
                                                                        // sorted portion, nothing changes.
                inserted = true;
            }
            // printArray(sortedArray);
            if (!inserted) {
                tempLines.remove(i);
                tempLines.add(0, currentLine);

                totalSwaps++;
            }

        }

        // this first time through is done to only determine the pixels between each
        // switch
        // if there are no swaps, make sure it draws to the end
        if (totalSwaps == 0) {
            totalSwaps = 1;
            pixelsBetweenSwitch = SortingVisualizer.WINDOW_WIDTH / totalSwaps;
            for (int k = 0; k < lines.size(); k++) {
                lines.get(k).addXValue(SortingVisualizer.WINDOW_WIDTH);
                lines.get(k).duplicateLastYValue();
            }
        }
        pixelsBetweenSwitch = SortingVisualizer.WINDOW_WIDTH / totalSwaps;

        // index of xPixel is the index of which # switch it is
        int indexOfX = 1;
        // actual sorting and proper pixel values

        inserted = false;
        for (int i = 1; i < lines.size(); i++) { // if the currentLine is the greatest, then it doesn't count as a swap

            inserted = false;
            int currentColorVal = lines.get(i).getColorVal();

            Line currentLine = lines.get(i);
            int currentLastYValue = currentLine.getLastYValue();
            // System.out.println("currentNum: " + currentNum);
            for (int k = 0; k < i; k++) {
                // System.out.println(currentNum >= sortedArray[k]);
                if (lines.get(k).getColorVal() > /* >= */currentColorVal && !inserted) {
                    int tempYVal = lines.get(k).getLastYValue();

                    for (int m = 0; m < k; m++) { // the lines before k remain unchanged
                        lines.get(m).duplicateLastYValue();
                    }
                    for (int m = k; m < i; m++) { // lines from k and after get shifted
                        lines.get(m).addYValue(lines.get(m + 1).getLastYValue());
                    }
                    lines.remove(i);
                    currentLine.addYValue(tempYVal); // give currentline the y value of the kth line
                    lines.add(k, currentLine); // put the currentline in the right place

                    inserted = true;

                    for (int j = i + 1; j < lines.size(); j++) { // lines after the selected line won't be changed
                        lines.get(j).duplicateLastYValue();
                    }
                    // xValues are added here because it is an actual swap
                    for (Line line : lines) { // every line gets the same new xvalue
                        line.addXValue(pixelsBetweenSwitch * indexOfX);
                    }
                    indexOfX++;

                }
            }
            // System.out.println("inserted: " + inserted);
            // if currentNum is greater than everything in sorted list
            if (lines.get(i - 1).getColorVal() <= currentColorVal) {// if number is greater than everything in the
                                                                    // sorted
                                                                    // portion, nothing changes.

                /*
                 * for (int k = 0; k <= i; k++) {
                 * lines.get(k).addYValue(lines.get(k).getLastYValue()); }
                 */

                inserted = true;
            }

            // if currentNum is less than everything, everything shifts
            if (!inserted) {
                int tempYVal = lines.get(0).getLastYValue();
                for (int k = 0; k < i; k++) {
                    lines.get(k).addYValue(lines.get(k + 1).getLastYValue());
                }
                currentLine.addYValue(tempYVal);
                lines.remove(i);
                lines.add(0, currentLine);

                for (int j = i + 1; j < lines.size(); j++) { // lines after the selected line won't be changed
                    lines.get(j).duplicateLastYValue();
                }
                // xValues are added here because it is an actual swap
                for (Line line : lines) { // every line gets the same new xvalue
                    line.addXValue(pixelsBetweenSwitch * indexOfX);
                }
                indexOfX++;

            }
            // printArray(sortedArray);
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

    public static ArrayList<Line> bubbleSort() { // Goes through the list, looks at each index and the subsequent one,
                                                 // compares them, and switches if needed
        ArrayList<Line> lines = new ArrayList<Line>();
        ArrayList<Line> tempLines = new ArrayList<Line>();

        String[] colors = { "red", "orange", "yellow", "green", "blue", "pink", "black", "red", "orange", "yellow",
                "green", "blue", "pink", "black", "red", "orange", "yellow", "green", "blue", "pink", "black", "red",
                "orange", "yellow", "green", "blue", "pink", "black" };
        // String[] colors = { "red", "red", "orange", "orange", "yellow", "yellow",
        // "green", "green", "blue", "blue",
        // "pink", "pink", "black", "black" };
        // String[] colors = { "red", "orange", "yellow", "green", "blue", "pink",
        // "black" };
        String[] worstCaseColors = { "black", "black", "pink", "pink", "blue", "blue", "green", "green", "yellow",
                "yellow", "orange", "orange", "red", "red" };

        int pixelsBetweenLines = SortingVisualizer.WINDOW_HEIGHT / (colors.length + 1);

        lines = makeRandomLines(colors);
        tempLines = copyArrayList(lines);

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
            pixelsBetweenSwitch = SortingVisualizer.WINDOW_WIDTH / totalSwaps;
            for (int k = 0; k < lines.size(); k++) {

                lines.get(k).addXValue(SortingVisualizer.WINDOW_WIDTH);
                lines.get(k).duplicateLastYValue();

            }
        }
        pixelsBetweenSwitch = (int) ((double) SortingVisualizer.WINDOW_WIDTH / (double) totalSwaps);

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
                            lines.get(k).duplicateLastYValue();
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

    public static ArrayList<Line> copyArrayList(ArrayList<Line> lines) {
        ArrayList<Line> returnLines = new ArrayList<Line>();
        for (Line line : lines) {
            returnLines.add(new Line(line.getColor(), line.getColorVal(), 0, line.getYStartPixel(), 5));
        }
        return returnLines;
    }

    public static ArrayList<Line> makeRandomLines(String[] colors) {
        int pixelsBetweenLines = SortingVisualizer.WINDOW_HEIGHT / (colors.length + 1);
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
        int pixelsBetweenLines = SortingVisualizer.WINDOW_HEIGHT / (colors.length + 1);
        ArrayList<Line> returnList = new ArrayList<Line>();
        for (int i = 0; i < colors.length; i++) {
            returnList.add(
                    new Line(stringToColor(colors[i]), stringToInt(colors[i]), 0, pixelsBetweenLines * (i + 1), 5));
        }
        return returnList;
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

    public static void swap(ArrayList<Line> lines, int index1, int index2) {
        Line temp = lines.get(index1);
        lines.set(index1, lines.get(index2));
        lines.set(index2, temp);
    }

    public static void printArray(int[] arr) {
        System.out.print("{");
        for (int i = 0; i < arr.length - 1; i++) {
            System.out.print(arr[i] + ", ");
        }
        System.out.println(arr[arr.length - 1] + "}");
    }

}