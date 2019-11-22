import java.util.*;

class SortingVisualizer {
    SortingVisualizer() {

    }

    public static void main(String[] args) {
        Timer timer = new Timer();
        TimerTask task = new TaskHelper();
        timer.schedule(task, 500, 1000);
        Panel panel = new Panel();
    }
}