import java.awt.*;
import javax.swing.*;

class Panel extends JPanel {
    private JFrame frame;
    public static JComboBox sortsBox;
    private String[] sorts = {"Sort", "bubbleSort", "insertionSort", "selectionSort", "cocktailSort", "gnomeSort", "mergeSort", "combSort"};
    public static JComboBox sortOrder;
    private String[] orders = {"random", "reverse"};
    public static JLabel recommendation;
    private static JLabel lineNumberLabel;
    public static JTextField lineNumberInput;


    public Panel() {
        frame = new JFrame("Sorting Visualizer");

        Listener listener = new Listener();

        this.setPreferredSize(new Dimension(SortingVisualizer.WINDOW_WIDTH, SortingVisualizer.WINDOW_HEIGHT));
        this.setBackground(Color.WHITE);
        this.setVisible(true);

        sortsBox = new JComboBox(sorts);
        sortsBox.addActionListener(listener);

        this.add(sortsBox);
        sortOrder = new JComboBox(orders);
        sortOrder.addActionListener(listener);

        this.add(sortOrder);

        recommendation = new JLabel();
        recommendation.setText("The first sort is always the slowest");

        this.add(recommendation);

        lineNumberLabel = new JLabel("| # of sets of lines:");
        lineNumberInput = new JTextField(2);
        lineNumberInput.setActionCommand("number");
        lineNumberInput.addActionListener(listener);

        this.add(lineNumberLabel);
        this.add(lineNumberInput);

        frame.add(this);

        frame.pack();
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void setAllVisible() {
        sortsBox.setVisible(true);
        sortOrder.setVisible(true);
        recommendation.setVisible(true);
        lineNumberLabel.setVisible(true);
        lineNumberInput.setVisible(true);
    }
    public static void setAllInvisible() {
        sortsBox.setVisible(false);
        sortOrder.setVisible(false);
        recommendation.setVisible(false);
        lineNumberLabel.setVisible(false);
        lineNumberInput.setVisible(false);
    }


    public void paintComponent(Graphics g) {
        if (!sortsBox.getSelectedItem().equals("Sort")) {
            super.paintComponent(g); //maybe breaks
            for (Line line : SortingVisualizer.lines) {
                g.setColor(line.getColor());
                drawLineWidth(g, line);
            }
            repaint();
        }
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
                    //highestIndex = k - 1;
                    //highestIndex = k + 1;

                }
            }

            for (int k = 0; k < highestIndex; k++) {
                drawFullLine(g, xPixelChanges[k], xPixelChanges[k + 1], yPixelChanges[k], yPixelChanges[k + 1], i);
            }


            boolean diagonal;
            if (highestIndex + 1 == yPixelChanges.length){
                diagonal = false;
            } else {
                diagonal = yPixelChanges[highestIndex] != yPixelChanges[highestIndex + 1];
            }

            if (diagonal) {
                drawPartialDiagonal(g, xPixelChanges[highestIndex], xPixelChanges[highestIndex + 1],
                        yPixelChanges[highestIndex], yPixelChanges[highestIndex + 1], currentX, i);
            } else {
                drawPartialHorizontal(g, xPixelChanges[highestIndex], xPixelChanges[highestIndex + 1],
                        yPixelChanges[highestIndex], yPixelChanges[highestIndex + 1], currentX, i);
            }
        }
    }

    public void drawPartialDiagonal(Graphics g, int startX, int endX, int startY, int endY, int currentX, int offset) {
        int height = endY - startY;
        int width = endX - startX;
        int xChange = currentX - startX;
        int drawY = (xChange * height) / width + startY;

        g.drawLine(startX, startY + offset, currentX, drawY + offset);

        g.drawLine(startX + offset, startY, currentX + offset, drawY);
    }

    public void drawFullLine(Graphics g, int startX, int endX, int startY, int endY, int offset) {
        g.drawLine(startX, startY + offset, endX, endY + offset);
        g.drawLine(startX + offset, startY, endX + offset, endY);
    }

    public void drawPartialHorizontal(Graphics g, int startX, int endX, int startY, int endY, int currentX,
                                      int offset) {
        g.drawLine(startX, startY + offset, currentX, endY + offset);
    }
}



