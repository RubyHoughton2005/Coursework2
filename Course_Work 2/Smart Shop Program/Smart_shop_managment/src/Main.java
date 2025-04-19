import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    //Create a frame
    /* All of these arrays are continuously used across the entire program and are therefore
    declared as public so they can be accessed by the user. Each array contains the contents
    of their equivalent txt file.
     */
    private static JFrame frame;
    public static ArrayList<String> Products_Name = new ArrayList<>();
    public static ArrayList<String> Products_Price = new ArrayList<>();
    public static ArrayList<String> Products_Quantity = new ArrayList<>();


    public static void main(String[] args) {
        //entrypoint to code
        initialize();
        try {
            File myObj = new File("src/Stock_Name.txt");// The stock list txt file will be in a src and this will be the pathway to find it
            Scanner myReader = new Scanner(myObj);// Opens a scanner to read the txt file
            while (myReader.hasNextLine()) {
                String data_name = myReader.nextLine();
                Products_Name.add(data_name);// Adds the txt file data into the array
                //System.out.println(data);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");// Error handling if it cant find the file
            e.printStackTrace();
        }

        try {
            File myObj = new File("src/Stock_Price.txt");// The stock list txt file will be in a src and this will be the pathway to find it
            Scanner myReader = new Scanner(myObj);// Opens a scanner to read the txt file
            while (myReader.hasNextLine()) {
                String data_price = myReader.nextLine();
                //double data_price = Double.parseDouble(str_Price);
                Products_Price.add(data_price);// Adds the txt file data into the array
                //System.out.println(data);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");// Error handling if it cant find the file
            e.printStackTrace();
        }
        try {
            File myObj = new File("src/Stock_Quantity.txt");// The stock list txt file will be in a src and this will be the pathway to find it
            Scanner myReader = new Scanner(myObj);// Opens a scanner to read the txt file
            while (myReader.hasNextLine()) {
                String data_quantity = myReader.nextLine();
                //Integer data_quantity = Integer.parseInt(str_quantity);
                Products_Quantity.add(data_quantity);// Adds the txt file data into the array
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
        frame.setSize(1000, 600);
        frame.setLayout(new FlowLayout());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel title = new JLabel("Store Management System");
        frame.add(title);

        JButton Product = new JButton ("Product"); //"Add" Button created to frame.
        frame.add(Product);

        JButton SalesRecord = new JButton ("Sales"); //"Sales button"
        frame.add(SalesRecord);

        JButton InventoryManager = new JButton ("Inventory");// Inventory button added to the frame
        frame.add(InventoryManager);

        Product.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                frame.getContentPane().removeAll();// Wipes the content to give a fresh frame acting as the subsections of the program
                frame.revalidate();
                frame.repaint();
                JButton Add_Product = new JButton ("Add Product"); //Adds a button that calls the add product method
                frame.add(Add_Product);
                Add_Product.addActionListener(new ActionListener(){
                    @Override
                    public void actionPerformed(ActionEvent e){
                    adding_product();// Runs the adding product method found at the bottom of the code with further details

                    }
                });
                JButton Remove_Product = new JButton ("Remove Product");// Adds a button that calls the remove product method
                frame.add(Remove_Product);
                Remove_Product.addActionListener(new ActionListener(){
                    @Override
                    public void actionPerformed(ActionEvent e){
                        removing_product();// Runs the removing product method found at the bottom of the code with further details
                    }
                });
                back_button();
                inventory();
                frame.revalidate();

            }
        });

        SalesRecord.addActionListener(new ActionListener() {
                                          @Override
                                          public void actionPerformed(ActionEvent e) {
                                              frame.getContentPane().removeAll();
                                              frame.revalidate();
                                              frame.repaint();
                                              //back_button();
                                              //inventory();

                                              JButton addsale = new JButton("Add Sale"); //Creates a add sale button
                                              frame.add(addsale);
                                              back_button();
                                              inventory();
                                              /* The maths method was originally created by Krystian as a way to track and make a total price on a sale
                                              owen then adapted it to work within a GUI by replacing inputs through a scanner to be inputs through
                                              dialogue boxes as well as inputs being shown in these same boxes without the ability for inputs
                                               */

                                              Scanner scanner = new Scanner(System.in);

                                              addsale.addActionListener(new ActionListener() {
                                                  @Override
                                                  public void actionPerformed(ActionEvent e) {
                                                      int numberOfProducts = Integer.parseInt(JOptionPane.showInputDialog("How Many products do you want to enter"));

                                                      double totalCost = 0;

                                                      //how many products
                                                      for (int i = 1; i <= numberOfProducts; i++) {
                                                          String NameofProduct = JOptionPane.showInputDialog("What is the name of product " + i); // Prevents repetitive action for the user
                                                          JLabel name = new JLabel(NameofProduct);

                                                          //price
                                                          double priceOfProducts = Double.parseDouble(JOptionPane.showInputDialog("What is the price of the product"));//Ensures the price is in a double

                                                          //amount
                                                          int quantityOfProducts = Integer.parseInt(JOptionPane.showInputDialog("How Many of the product do you want to enter"));// Makes the input into an integer
                                                          //int quantity = scanner.nextInt();

                                                          //math
                                                          double productTotal = priceOfProducts * quantityOfProducts;
                                                          totalCost = totalCost + productTotal;

                                                          //JOptionPane.showMessageDialog(frame,"Price of" + name + ": $" + productTotal);
                                                      }
                                                      JOptionPane.showMessageDialog(frame, "Total Price of:" + totalCost);//Displays the final cost
                                                      frame.setVisible(true);
                                                  }
                                              });
                                          }

                                          ;
                                      });

        InventoryManager.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                frame.getContentPane().removeAll();
                frame.revalidate();
                frame.repaint();
                back_button();
                inventory();
            }
        });

        frame.setVisible(true);
    }
    public static void back_button(){ // Will be used multiple times and therefore better as an independent method
        JButton back = new JButton("Back");
        frame.add(back);// Adds back button to relevent areas
        back.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.getContentPane().removeAll();// Emptys the current frame
                frame.revalidate();
                frame.repaint();
                initialize(); // Reruns the initialization
                frame.setVisible(true);
            }
        });
    }

    //Testing Inventory as Method
    static void inventory(){
        for (int i = 0; i < Products_Name.size(); i++){
            String product_name = Products_Name.get(i);
            String product_price = Products_Price.get(i);
            String product_quantity = Products_Quantity.get(i);
            //String lrg_label = (" Name: " + product_name + " Price:  " + product_price + " Quantity: " + product_quantity + "\n");
            JLabel label = new JLabel("<html> Name: "+ product_name + "<br> Price: " + product_price + "<br> Quantity: " + product_quantity + "<br></html>", SwingConstants.CENTER);
            frame.add(label);
            frame.setVisible(true);
        }
    }

    /* The adding and removing product button opens the text file before wiping the text file so that it can be re-added as an updated list
    This is done as addition for the add product button however needs to be compared in the remove product button so that the input indeed matches the
    one in the text file. This is done by adding their input to a comparative array that is then ran against the compare all function and as long as
    the input is in the txt file it continues the method. This is done across multiple if statements as another way of error handling with elses at different
    stages of the process
     */

    static void adding_product(){
        int numberOfProductsadd = Integer.parseInt(JOptionPane.showInputDialog("How Many products do you want to add"));
        for (int i = 1; i <= numberOfProductsadd; i++) {
            String New_name = JOptionPane.showInputDialog("What is the name of product " + i);
            Products_Name.add(New_name);
            String filePath = "src/Stock_Name.txt";
            try {
                FileWriter writer = new FileWriter(filePath, false);;// The stock list txt file will be in a src and this will be the pathway to find it
                writer.write("");
                for (int j = 0; j < Products_Name.size(); j++) {
                    String product_name = Products_Name.get(j);
                    writer.write(product_name);
                    writer.write("\n");
                }
                writer.close();
            } catch (IOException e) {
                System.out.println("File not found.");// Error handling if it cant find the file
                e.printStackTrace();
            }
            String New_price = JOptionPane.showInputDialog("What is the price of the product" + i);
            Products_Price.add(New_price);
            String filePathp = "src/Stock_Price.txt";
            try {
                FileWriter writer = new FileWriter(filePathp, false);;// The stock list txt file will be in a src and this will be the pathway to find it
                writer.write("");
                for (int j = 0; j < Products_Price.size(); j++) {
                    String product_price = Products_Price.get(j);
                    writer.write(product_price);
                    writer.write("\n");
                }
                writer.close();
            } catch (IOException e) {
                System.out.println("File not found.");// Error handling if it cant find the file
                e.printStackTrace();
            }
            String New_quantity = JOptionPane.showInputDialog("What is the quantity of the product" + i);
            Products_Quantity.add(New_quantity);
            String filePathq = "src/Stock_Quantity.txt";
            try {
                FileWriter writer = new FileWriter(filePathq, false);;// The stock list txt file will be in a src and this will be the pathway to find it
                writer.write("");
                for (int j = 0; j < Products_Quantity.size(); j++) {
                    String product_quantity = Products_Quantity.get(j);
                    writer.write(product_quantity);
                    writer.write("\n");
                }
                writer.close();
            } catch (IOException e) {
                System.out.println("File not found.");// Error handling if it cant find the file
                e.printStackTrace();
            }

        }
    }
    static void removing_product(){
        int numberOfProductsrem = Integer.parseInt(JOptionPane.showInputDialog("How Many products do you want to remove"));
        for (int i = 1; i <= numberOfProductsrem; i++) {
            String removed_name = JOptionPane.showInputDialog("What is the name of product " + i);
            ArrayList<String> compare = new ArrayList<>();
            compare.add(removed_name);
            boolean result = Products_Name.containsAll(compare);
            if (result) {
                String filePath = "src/Stock_Name.txt";
                Products_Name.remove(removed_name);
                try {
                    FileWriter writer = new FileWriter(filePath, false);;// The stock list txt file will be in a src and this will be the pathway to find it
                    writer.write("");
                    for (int j = 0; j < Products_Name.size(); j++) {
                        String product_name = Products_Name.get(j);
                        writer.write(product_name);
                        writer.write("\n");
                    }
                    writer.close();
                } catch (IOException e) {
                    System.out.println("File not found.");// Error handling if it cant find the file
                    e.printStackTrace();
                }
                compare.remove(removed_name);
                String Removed_price = JOptionPane.showInputDialog("What is the price of the product" + i);
                compare.add(Removed_price);
                boolean resultproduct = Products_Price.containsAll(compare);
                if (resultproduct) {
                    Products_Price.remove(Removed_price);
                    String filePathp = "src/Stock_Price.txt";
                    try {
                        FileWriter writer = new FileWriter(filePathp, false);;// The stock list txt file will be in a src and this will be the pathway to find it
                        writer.write("");
                        for (int j = 0; j < Products_Price.size(); j++) {
                            String product_price = Products_Price.get(j);
                            writer.write(product_price);
                            writer.write("\n");
                        }
                        writer.close();
                    } catch (IOException e) {
                        System.out.println("File not found.");// Error handling if it cant find the file
                        e.printStackTrace();
                    }
                    compare.remove(Removed_price);
                    String Removed_quantity = JOptionPane.showInputDialog("What is the quantity of the product" + i);
                    compare.add(Removed_quantity);
                    boolean resultquantity = Products_Quantity.containsAll(compare);
                    if (resultquantity) {
                        Products_Quantity.remove(Removed_quantity);
                        String filePathq = "src/Stock_Quantity.txt";
                        try {
                            FileWriter writer = new FileWriter(filePathq, false);;// The stock list txt file will be in a src and this will be the pathway to find it
                            writer.write("");
                            for (int j = 0; j < Products_Quantity.size(); j++) {
                                String product_quantity = Products_Quantity.get(j);
                                writer.write(product_quantity);
                                writer.write("\n");
                            }
                            writer.close();
                        } catch (IOException e) {
                            System.out.println("File not found.");// Error handling if it cant find the file
                            e.printStackTrace();
                        }

                        }
                    else{
                            JOptionPane.showMessageDialog(frame, "The product quantity not found");}
                }
                else{
                    JOptionPane.showMessageDialog(frame, "Product price not found");
                }
            }
            else {
                JOptionPane.showMessageDialog(frame, "Product not found");
            }

        }
    }
}