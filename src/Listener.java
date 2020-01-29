import java.awt.*;
import java.awt.event.*;

import javax.swing.JComboBox;
public class Listener implements ActionListener {
    /*private static boolean sortAction;
    private static boolean orderAction;
    private static String lastSort = "";*/
//    private String[] sorts = {"bubbleSort", "insertionSort", "selectionSort", "cocktailSort", "gnomeSort", "mergeSort", "combSort"};
    Thread thread;
    @Override
    public void actionPerformed(ActionEvent e) {

        //-----------------------------------------

    	/*String action;
        // TODO Auto-generated method stub
    	if (e.getActionCommand().equals("number")) {
    		action = Panel.lineNumberInput.getText();
    	} else {
	        JComboBox comboBox = (JComboBox) e.getSource();
	        action = (String) comboBox.getSelectedItem();
    	}*/
        //-----------------------------------------

        /*if (lastSort.equals(action)) {
        	orderAction = false;
        }
        if (!(sortAction || orderAction)) {
        	lastSort = action;
        }*/
        if (!Panel.sortsBox.getSelectedItem().equals("Sort")) {
            Panel.setAllInvisible();
            //-----------------------------------------
            /*if (action.equals("random") || action.equals("reverse")) {
            	System.out.println("in random or reverse");
                setLinesOrder();
            } else {
            	System.out.println("in not random or reverse");
                setLines(action);
            }*/
            //-----------------------------------------
            setLines();


        	/*if (thread.isAlive()) {
        		thread.keepGoing = false;
        	}*/
            thread = new Thread() {
                //int i = 0;

                //boolean keepGoing;
                public void run() {
                    for (Line line: SortingVisualizer.lines){
                        line.setCurrentXPixel(0);
                        //System.out.println("line in listener: " + line);
                    }
                    //keepGoing = true;

                    while (SortingVisualizer.lines.get(0).getCurrentXPixel() < SortingVisualizer.WINDOW_WIDTH) {
//                for (; i != 1;) {
                        for (Line line : SortingVisualizer.lines) {
                            line.addCurrentXPixel(2);
                            //System.out.println(line);
                        }
                    /*if (SortingVisualizer.lines.get(0).getCurrentXPixel() >= SortingVisualizer.WINDOW_WIDTH) {
                        i = 1;
                    }*/
                        // System.out.println(lines.get(0).getCurrentXPixel());
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
    /*public static void setLines(String sort) {
        if (sort.equals("bubbleSort")) {
            //Panel.sortOrder.setSelectedItem("random");
        	Panel.recommendation.setText("");
            SortingVisualizer.lines = Sorts.bubbleSort();
        } else if (sort.equals("insertionSort")) {
            //Panel.sortOrder.setSelectedItem("random");
        	Panel.recommendation.setText("");
            SortingVisualizer.lines = Sorts.insertionSort();
        } else if (sort.equals("selectionSort")) {
            //Panel.sortOrder.setSelectedItem("random");
        	Panel.recommendation.setText("");
            SortingVisualizer.lines = Sorts.selectionSort();
        } else if (sort.equals("cocktailSort")) {
            //Panel.sortOrder.setSelectedItem("reverse");
        	Panel.recommendation.setText("Reverse is recommended for cocktail sort");
            SortingVisualizer.lines = Sorts.cocktailSort();
        } else if (sort.equals("gnomeSort")) {
            //Panel.sortOrder.setSelectedItem("random");
        	Panel.recommendation.setText("gnomeSort is very... slow");
            SortingVisualizer.lines = Sorts.gnomeSort();
        } else if (sort.equals("mergeSort")) {
        	Panel.recommendation.setText("");
            //Panel.sortOrder.setSelectedItem("random");
            SortingVisualizer.lines = Sorts.mergeSort();
        } else if (sort.equals("combSort")) {
            //Panel.sortOrder.setSelectedItem("random");
        	Panel.recommendation.setText("");
            SortingVisualizer.lines = Sorts.combSort();
        }
    }
    public static void setLinesOrder() {
        String sort = (String) Panel.sortsBox.getSelectedItem();
        if (sort.equals("bubbleSort")) {
            SortingVisualizer.lines = Sorts.bubbleSort();
        } else if (sort.equals("insertionSort")) {
            SortingVisualizer.lines = Sorts.insertionSort();
        } else if (sort.equals("selectionSort")) {
            SortingVisualizer.lines = Sorts.selectionSort();
        } else if (sort.equals("cocktailSort")) {
            SortingVisualizer.lines = Sorts.cocktailSort();
        } else if (sort.equals("gnomeSort")) {
            SortingVisualizer.lines = Sorts.gnomeSort();
        } else if (sort.equals("mergeSort")) {
            SortingVisualizer.lines = Sorts.mergeSort();
        } else if (sort.equals("combSort")) {
            SortingVisualizer.lines = Sorts.combSort();
        }
    }*/

}



