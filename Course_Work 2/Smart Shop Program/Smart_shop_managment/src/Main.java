import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class Main {
    //Create a frame
    private static JFrame frame;
    public static ArrayList<String> Products = new ArrayList<>();

    public static void main(String[] args) {
        try {
            File myObj = new File("src/Stock_List.txt");// The stock list txt file will be in a src and this will be the pathway to find it
            Scanner myReader = new Scanner(myObj);// Opens a scanner to read the txt file
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                Products.add(data);// Adds the txt file data into the array
                //System.out.println(data);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");// Error handling if it cant find the file
            e.printStackTrace();
        }
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
