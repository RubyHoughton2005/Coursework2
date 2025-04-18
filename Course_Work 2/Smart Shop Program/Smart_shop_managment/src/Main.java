import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;


public class Main {
    //Create a frame
    private static JFrame frame;
    public static ArrayList<String> Products = new ArrayList<>();


    public static void main(String[] args) {
        //entrypoint to code
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

        JButton Product = new JButton ("Product"); //"Add" Button created to frame.
        frame.add(Product);

        JButton SalesRecord = new JButton ("Sales"); //"S"
        frame.add(SalesRecord);

        JButton InventoryManager = new JButton ("Inventory");
        frame.add(InventoryManager);


        Product.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                frame.getContentPane().removeAll();
                frame.revalidate();
                frame.repaint();
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

    //Krystian test sale/product without GUI
    public class ShopCalculator {

        public static void main(String[] args) {
            Scanner scanner = new Scanner(System.in);

            //ask how many products
            System.out.print("How many products do you want to enter? ");
            int numberOfProducts = scanner.nextInt();

            double totalCost = 0; 

            //how many products
            for (int i = 1; i <= numberOfProducts; i++) {
                System.out.println("\nProduct " + i + ":");

                //product name
                System.out.print("Enter product name: ");
                scanner.nextLine(); 
                String name = scanner.nextLine();

                //price
                System.out.print("Enter price of " + name + ": ");
                double price = scanner.nextDouble();

                //amount
                System.out.print("Enter quantity of " + name + ": ");
                int quantity = scanner.nextInt();

                //math
                double productTotal = price * quantity;
                totalCost += productTotal;

                System.out.println("Cost for " + name + ": $" + productTotal);
            }

            //total cost
            System.out.println("\n==========================");
            System.out.println("Total cost for all products: $" + totalCost);
            System.out.println("==========================");

            scanner.close();
        }
    }
}
