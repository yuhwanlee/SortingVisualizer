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

    /*
     * public int[] getXPixelsAtChanges() { return xPixelsAtChanges; }
     * 
     * public int[] getPreviousYPixels() { return previousYPixels; }
     */

    public void addXValue(int addition) {
        xValues = appendArray(xValues, addition);

    }

    // public void addXPixelChange(int addition) {
    // xPixelsAtChanges = appendArray(xPixelsAtChanges, addition);
    // }

    public void addYValue(int addition) {
        yValues = appendArray(yValues, addition);
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
}
