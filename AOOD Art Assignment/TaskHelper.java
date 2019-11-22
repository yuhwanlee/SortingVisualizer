import java.util.*;

class TaskHelper extends TimerTask {
    Panel panel;

    public TaskHelper(Panel panel) {
        this.panel = panel;
    }

    public void run() {

        panel.repaint();
    }
}