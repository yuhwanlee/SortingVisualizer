import java.awt.*;
import java.awt.event.*;

import javax.swing.JComboBox;
public class Listener implements ActionListener {
//    private String[] sorts = {"bubbleSort", "insertionSort", "selectionSort", "cocktailSort", "gnomeSort", "mergeSort", "combSort"};
    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        JComboBox comboBox = (JComboBox) e.getSource();
        String sort = (String) comboBox.getSelectedItem();

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

        Thread thread = new Thread() {
            //int i = 0;

            public void run() {
                while (SortingVisualizer.lines.get(0).getCurrentXPixel() < SortingVisualizer.WINDOW_WIDTH) {
//                for (; i != 1;) {
                    for (Line line : SortingVisualizer.lines) {
                        line.addCurrentXPixel(5);
                        //System.out.println(line);
                    }
                    /*if (SortingVisualizer.lines.get(0).getCurrentXPixel() >= SortingVisualizer.WINDOW_WIDTH) {
                        i = 1;
                    }*/
                    // System.out.println(lines.get(0).getCurrentXPixel());
                    try {
                        Thread.sleep(25);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    SortingVisualizer.panel.repaint();
                }
            }
        };
        thread.start();
    }

}


