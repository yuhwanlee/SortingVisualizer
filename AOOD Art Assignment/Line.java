import java.awt.*;

class Line {
    private Color color;
    private int yPos;
    private int xPos;

    public Line(Color color) {
        this.color = color;
    }

    public void setY(int y) {
        yPos = y;
    }

    public void setX(int x) {
        xPos = x;
    }

    public int getY() {
        return yPos;
    }

    public int getX() {
        return xPos;
    }

    public Color getColor() {
        return color;
    }
}