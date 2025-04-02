import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class Main {
    //Create a frame
    private static JFrame frame;


    public static void main(String[] args) { //entrypoint to code
        initialize();


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
}
