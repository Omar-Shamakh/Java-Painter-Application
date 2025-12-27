package paint.app;

import javax.swing.JFrame;
import paint.ui.PaintPanel;


public class PaintBrushApp {

    public static void main(String[] args) {

        JFrame frame = new JFrame("Painter");

        PaintPanel panel = new PaintPanel(); 

        frame.setContentPane(panel);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);   // to make full screen on whatever screen dimensions 
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
