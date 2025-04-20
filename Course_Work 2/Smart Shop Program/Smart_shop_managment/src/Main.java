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

    //input Jframe box
    private static JFrame inputbox;
    private static JTextField txtProductName;
    private static JButton validateButton;


    //Create a frame
    static JFrame frame;
    public static ArrayList<String> Products = new ArrayList<>();


    public static void main(String[] args) {
        //entrypoint to code
        //default objects added in to start with
        Product p1 = new Product();


        initialize();
        try {
            File myObj = new File("Stock_List.txt");// The stock list txt file will be in a src and this will be the pathway to find it
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

    public static void initialize(){

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

                try {
                    File file = new File("Stock_List.txt");
                    Scanner reader = new Scanner(file);
                    while (reader.hasNextLine()) {
                        String line = reader.nextLine();
                        String[] parts = line.split(",");
                        if (parts.length == 2) {
                            String name = parts[0].trim();
                            String qty = parts[1].trim();
                            JLabel label = new JLabel("Name: " + name + ", Quantity: " + qty);
                            frame.add(label);
                        }
                    }
                    reader.close();
                } catch (FileNotFoundException ex) {
                    JLabel errorLabel = new JLabel("No products found.");
                    frame.add(errorLabel);
                }

                JButton Product_Add = new JButton("New Product");
                frame.add(Product_Add);


                JButton Product_Back = new JButton("Main Menu");
                frame.add(Product_Back);
                Product_Back.addActionListener(new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent e) {
                        frame.getContentPane().removeAll();
                        frame.revalidate();
                        frame.repaint();


                        frame.add(title);

                        frame.add(Product_button);

                        frame.add(SalesRecord);

                        frame.add(InventoryManager);
                    }
                });
                frame.revalidate();
                Product_Add.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        frame.getContentPane().removeAll();
                        frame.revalidate();
                        frame.repaint();



                        JLabel lblTitle = new JLabel("Enter New Product Details:");
                        frame.add(lblTitle);

                        JLabel lblName = new JLabel("Product Name:");
                        frame.add(lblName);
                        JTextField txtName = new JTextField(15);
                        frame.add(txtName);

                        JLabel lblQuantity = new JLabel("Quantity:");
                        frame.add(lblQuantity);
                        JTextField txtQty = new JTextField(5);
                        frame.add(txtQty);

                        JButton btnSave = new JButton("Save Product");
                        frame.add(btnSave);

                        JLabel resultLabel = new JLabel();
                        frame.add(resultLabel);
                        frame.add(Product_Back);
                        btnSave.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                String name = txtName.getText().trim();
                                String qtyText = txtQty.getText().trim();

                                if (name.isEmpty() || qtyText.isEmpty()) {
                                    resultLabel.setText("Please enter both name and quantity.");
                                    return;
                                }

                                int qty;
                                try {
                                    qty = Integer.parseInt(qtyText);
                                } catch (NumberFormatException ex) {
                                    resultLabel.setText("Quantity must be a number.");
                                    return;
                                }

                                Product newProduct = new Product();
                                newProduct.setProduct(name);
                                newProduct.setQuantity(qty);

                                // Write to file
                                try {
                                    FileWriter writer = new FileWriter("Stock_List.txt", true); // true = append
                                    writer.write(newProduct.getProduct() + "," + newProduct.getQuantity() + "\n");
                                    writer.close();
                                    resultLabel.setText("Product saved to file: " + newProduct.getProduct() + ", Qty: " + newProduct.getQuantity());
                                 } catch (IOException ioException) {
                                    resultLabel.setText("Error saving to file.");
                                    ioException.printStackTrace();
                                 }
                                resultLabel.setText("Product saved: " + newProduct.getProduct() + ", Quantity: " + newProduct.getQuantity());
                                frame.revalidate();
                                frame.repaint();


                            }
                        });

                        frame.revalidate();
                        frame.repaint();
                    }
                });
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
