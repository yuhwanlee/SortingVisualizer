import java.awt.*;
import javax.swing.*;

class Panel extends JPanel {
    JFrame frame;

    public Panel() {
        frame = new JFrame("Sorting");
        this.setPreferredSize(new Dimension(1500, 750));
        this.setBackground(Color.WHITE);
        this.setVisible(true);

        frame.add(this);

        frame.pack();
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void paintComponent(Graphics g) {

        repaint();
    }

}