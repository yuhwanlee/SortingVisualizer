import java.awt.event.*;

public class Listener implements ActionListener {
    Thread thread;
    @Override
    public void actionPerformed(ActionEvent e) {
        if (!Panel.sortsBox.getSelectedItem().equals("Sort")) {
            Panel.setAllInvisible();
            setLines();
            thread = new Thread() {
                public void run() {
                    for (Line line: SortingVisualizer.lines){
                        line.setCurrentXPixel(0);
                    }

                    while (SortingVisualizer.lines.get(0).getCurrentXPixel() < SortingVisualizer.WINDOW_WIDTH) {
                        for (Line line : SortingVisualizer.lines) {
                            line.addCurrentXPixel(2);
                        }
                        try {
                            Thread.sleep(10);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        SortingVisualizer.panel.repaint();
                    }
                    Panel.setAllVisible();

                }

            };
            thread.start();
        }
    }

    public static void setLines() {
        String sort = (String) Panel.sortsBox.getSelectedItem();
        String order = (String) Panel.sortOrder.getSelectedItem();
        if (Panel.lineNumberInput.getText().equals("")) {
            Panel.lineNumberInput.setText("1");
        }
        int lineSets = Integer.parseInt(Panel.lineNumberInput.getText());

        if (lineSets <= 0) {
            Panel.lineNumberInput.setText("1");
            lineSets = 1;
        }
        if (sort.equals("bubbleSort")) {
            if (lineSets > 4) {
                Panel.lineNumberInput.setText("4");
                lineSets = 4;
            }
            Panel.recommendation.setText("max lines: 4");
            SortingVisualizer.lines = Sorts.bubbleSort(lineSets);
        } else if (sort.equals("insertionSort")) {
            if (lineSets > 8) {
                Panel.lineNumberInput.setText("8");
                lineSets = 8;
            }
            Panel.recommendation.setText("max lines: 8");
            SortingVisualizer.lines = Sorts.insertionSort(lineSets);

        } else if (sort.equals("selectionSort")) {
            if (lineSets > 8) {
                Panel.lineNumberInput.setText("8");
                lineSets = 8;
            }
            Panel.recommendation.setText("max lines: 8");
            SortingVisualizer.lines = Sorts.selectionSort(lineSets);
        } else if (sort.equals("cocktailSort")) {
            if (lineSets > 4) {
                Panel.lineNumberInput.setText("4");
                lineSets = 4;
            }
            Panel.recommendation.setText("max lines: 4. Reverse is recommended for cocktail sort");
            SortingVisualizer.lines = Sorts.cocktailSort(lineSets);
        } else if (sort.equals("gnomeSort")) {
            if (lineSets > 4) {
                Panel.lineNumberInput.setText("4");
                lineSets = 4;
            }
            Panel.recommendation.setText("max lines: 4. gnomeSort is very... slow");
            SortingVisualizer.lines = Sorts.gnomeSort(lineSets);
        } else if (sort.equals("mergeSort")) {
            if (lineSets > 5) {
                Panel.lineNumberInput.setText("5");
                lineSets = 5;
            }
            Panel.recommendation.setText("max lines: 5");
            SortingVisualizer.lines = Sorts.mergeSort(lineSets);
        } else if (sort.equals("combSort")) {
            if (lineSets > 5) {
                Panel.lineNumberInput.setText("5");
                lineSets = 5;
            }
            Panel.recommendation.setText("max lines: 5");
            SortingVisualizer.lines = Sorts.combSort(lineSets);
        }
    }
}



