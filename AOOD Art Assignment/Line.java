import java.awt.*;

public class Line {
    private Color color;
    private int yStartPixel;
    private int currentXPixel;
    private int currentYPixel;
    private int pixelWidth;
    private int[] xPixelsAtChanges = new int[0];
    private int[] previousYPixels = new int[0];

    public Line(Color color, int yStartPixel, int pixelWidth) {
        this.color = color;
        this.yStartPixel = yStartPixel;
        currentXPixel = 0;
        currentYPixel = yStartPixel;
        this.pixelWidth = pixelWidth;
    }

    public static int[] appendArray(int[] array, int item) {
        int[] returnArray = new int[array.length + 1];
        for (int i = 0; i < array.length; i++) {
            returnArray[i] = array[i];
        }
        returnArray[returnArray.length - 1] = item;
        return returnArray;
    }

    public int[] getXPixelsAtChanges() {
        return xPixelsAtChanges;
    }

    public int[] getPreviousYPixels() {
        return previousYPixels;
    }

    public void addXPixelChange(int addition) {
        xPixelsAtChanges = appendArray(xPixelsAtChanges, addition);
    }

    public void addPreviousYPixels(int addition) {
        previousYPixels = appendArray(previousYPixels, addition);
    }

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

    public Color getColor() {
        return color;
    }
}
