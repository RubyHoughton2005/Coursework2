import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    //input Jframe box
    private static JFrame inputbox;
    private static JTextField txtProductName;
    private static JButton validateButton;


    //Create a frame
    private static JFrame frame;
    public static ArrayList<String> Products = new ArrayList<>();


    public static void main(String[] args) {
        //entrypoint to code
        //default objects added in to start with
        Product p1 = new Product();
        p1.quantity = 3;
        p1.product = "Coke";

        initialize();
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

    }

    static void initialize(){

        frame = new JFrame("Store Management System");
        frame.setSize(800, 600);
        frame.setLayout(new FlowLayout());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel title = new JLabel("Store Management System");
        frame.add(title);

        JButton Product_button = new JButton ("Product"); //"Add" Button created to frame.
        frame.add(Product_button);

        JButton SalesRecord = new JButton ("Sales"); //"S"
        frame.add(SalesRecord);

        JButton InventoryManager = new JButton ("Inventory");
        frame.add(InventoryManager);


        Product_button.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                frame.getContentPane().removeAll();
                frame.revalidate();
                frame.repaint();

                JLabel Product_title = new JLabel("Products");
                frame.add(Product_title);

                JButton Product_Add = new JButton("New Product");
                frame.add(Product_Add);
                JLabel Current_Products = new JLabel (Product.getProduct);
                frame.add(Current_Products);
                frame.revalidate();
            }
        });
        SalesRecord.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                frame.getContentPane().removeAll();
                frame.revalidate();
                frame.repaint();
            }
        });
        InventoryManager.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                frame.getContentPane().removeAll();
                frame.revalidate();
                frame.repaint();
                for (int i = 0; i < Products.size(); i++){
                    String product = Products.get(i);
                    JLabel label = new JLabel(product);
                    frame.add(label);
                }
            }
        });

        frame.setVisible(true);
    }
}
