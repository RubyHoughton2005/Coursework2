import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class Main {
    //Create a frame
    private static JFrame frame;


    public static void main(String[] args) {
        /*This section of code is what allows the initial creation of the frame
        and is important to the functionality of the code
         */
        frame = new JFrame("Store Management System");
        frame.setSize(800, 600);
        frame.setLayout(new FlowLayout());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JLabel title = new JLabel("Store Management System");
        frame.add(title);
        frame.setVisible(true);
    }
}
