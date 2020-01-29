import java.awt.*;

public class Line {
    private Color color;
    private int colorVal;
    private int xStartPixel;
    private int yStartPixel;
    private int currentXPixel;
    private int currentYPixel;
    private int pixelWidth;
    private int[] xValues = new int[0];
    private int[] yValues = new int[0];

    public Line(Color color, int xStartPixel, int yStartPixel, int pixelWidth) {
        this.color = color;
        this.xStartPixel = xStartPixel;
        this.yStartPixel = yStartPixel;
        currentXPixel = 0;
        currentYPixel = yStartPixel;
        this.pixelWidth = pixelWidth;
        addXValue(xStartPixel);
        addYValue(yStartPixel);
    }

    public Line(Color color, int colorVal, int xStartPixel, int yStartPixel, int pixelWidth) {
        this.color = color;
        this.colorVal = colorVal;
        this.xStartPixel = xStartPixel;
        this.yStartPixel = yStartPixel;
        currentXPixel = 0;
        currentYPixel = yStartPixel;
        this.pixelWidth = pixelWidth;
        addXValue(xStartPixel);
        addYValue(yStartPixel);
    }

    public void setXValues(int[] allXValues) {
        int pixelsBetweenSwitch = Sorts.pixelsBetweenSwitch;
        currentXPixel = 0;
        //int index = 0;
    	/*for (int i = 0; i < xValues.length && currentXPixel <= 1500; i++) {
    		if (xValues[i] != currentXPixel) {
    			addXValue(i, currentXPixel);
    			duplicateYValue(i);
    		}
    		currentXPixel += pixelsBetweenSwitch;
    	}*/
        for (int i = 0; i < xValues.length && currentXPixel <= 1500; i++) {
            if (xValues[i] != currentXPixel) {
                addXValue(i, currentXPixel);
                duplicateYValue(i - 1);
            }
            currentXPixel += pixelsBetweenSwitch;
        }
    	/*for (int i = 0; i < allXValues.length; i++) {
    		if (xValues.length >= i) {
    			addXValue(i, allXValues[i]);
    			duplicateYValue(i - 1);
    		} else if (xValues[i] != allXValues[i]) {
    			addXValue(i, allXValues[i]);
    			duplicateYValue(i - 1);
    		}
    	}*/
        //xValues = allXValues;
    }

    /*public static int[] testingSort() {
    	int[] allXValues = {0, 300, 600, 900, 1200, 1500};
    	int[] xValues = {0, 600, 1500};
    	int[] yValues = {500, 800, 100};
    	for (int i = 0; i < allXValues.length; i++) {
    		System.out.print("i: " + i + "\nxValues: "); printArray(xValues); System.out.print("yValues: "); printArray(yValues);
    		if (xValues[i] != allXValues[i]) {
    			System.out.println("HERE");
    			xValues = insertArrayIndex(xValues, i, allXValues[i]);
    			yValues = duplicateYValue(yValues, i - 1);
    		}
    	}
    	return xValues;

    }*/
    public int getLastYValue() {
        return yValues[yValues.length - 1];
    }

    public int getLastXValue() {
        return xValues[xValues.length - 1];
    }

    public void setColorVal(int val) {
        colorVal = val;
    }

    public int getColorVal() {
        return colorVal;
    }

    public static int[] appendArray(int[] array, int item) {
        int[] returnArray = new int[array.length + 1];
        for (int i = 0; i < array.length; i++) {
            returnArray[i] = array[i];
        }
        returnArray[returnArray.length - 1] = item;
        return returnArray;
    }

    public int[] getXValues() {
        return xValues;
    }

    public int[] getYValues() {
        return yValues;
    }

    public void duplicateLastYValue() {
        yValues = appendArray(yValues, getLastYValue());
    }
    /*
     * public int[] getXPixelsAtChanges() { return xPixelsAtChanges; }
     *
     * public int[] getPreviousYPixels() { return previousYPixels; }
     */

    public void addXValue(int addition) {
        xValues = appendArray(xValues, addition);

    }
    public static void main(String[] args) {
        Line line = new Line(Color.RED, 0, 500, 5);
        line.xValues = new int[] {0, 300, 800};
        line.yValues = new int[] {700, 200, 400};
        line.addXValue(1, 200);
        line.duplicateYValue(2);
        printArray(line.xValues);
        printArray(line.yValues);
    }

    public static void printArray(int[] arr) {
        System.out.print("{");
        for (int i = 0; i < arr.length - 1; i++) {
            System.out.print(arr[i] + ", ");
        }
        System.out.println(arr[arr.length - 1] + "}");
    }
    public void addXValue(int index, int addition) {
        int[] returnArray = new int[xValues.length + 1];
        for (int i = 0; i < index; i++) {
            returnArray[i] = xValues[i];
        }
        returnArray[index] = addition;
        for (int i = index; i < xValues.length; i++) {
            returnArray[i + 1] = xValues[i];
        }
        xValues = returnArray;
    }

    // public void addXPixelChange(int addition) {
    // xPixelsAtChanges = appendArray(xPixelsAtChanges, addition);
    // }

    public void addYValue(int addition) {
        yValues = appendArray(yValues, addition);
    }

    public void duplicateYValue(int index) {
        int[] returnArray = new int[yValues.length + 1];
        int duplicate = yValues[index];
        for (int i = 0; i <= index; i++) {
            returnArray[i] = yValues[i];
        }
        returnArray[index + 1] = duplicate;
        for (int i = index + 2; i < returnArray.length; i++) {
            returnArray[i] = yValues[i - 1];
        }
        yValues = returnArray;
    }

    // public void addPreviousYPixels(int addition) {
    // previousYPixels = appendArray(previousYPixels, addition);
    // }

    public void setPixelWidth(int pixelWidth) {
        this.pixelWidth = pixelWidth;
    }

    public int getPixelWidth() {
        return pixelWidth;
    }

    public void addCurrentXPixel(int addition) {
        currentXPixel += addition;
    }

    public int getCurrentXPixel() {
        return currentXPixel;
    }

    public void setCurrentXPixel(int pixel) {
        currentXPixel = pixel;
    }

    public void setCurrentYPixel(int pixel) {
        currentYPixel = pixel;
    }

    public int getCurrentYPixel() {
        return currentYPixel;
    }

    public int getYStartPixel() {
        return yStartPixel;
    }

    public int getXStartPixel() {
        return xStartPixel;
    }

    public Color getColor() {
        return color;
    }

    public static String arrayToString(int[] arr) {
        String returnString = "";
        returnString += "{";
        for (int i = 0; i < arr.length - 1; i++) {
            returnString += arr[i] + ", ";
        }
        returnString += arr[arr.length - 1] + "}";
        return returnString;
    }

    public String toString() {
        return "colorVal: " + Integer.toString(colorVal) + ", yStartPixel: " + Integer.toString(yStartPixel) + (yStartPixel >= 100 ? "" : " ")
                + ", xValues: " + arrayToString(xValues) + ", yValues: " + arrayToString(yValues);
    }
}


