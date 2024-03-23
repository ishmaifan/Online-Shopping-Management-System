package GUI;
import org.example.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.TableRowSorter;

import static GUI.GUI1.shoppingCart;

public class GUI2 extends JFrame{


public GUI2() {
    JFrame frame = new JFrame("Shopping Cart");
    frame.setLayout(new GridLayout(2,1));
    frame.setSize(900, 500);   //FlowLayout.LEFT,50,60)
frame.setVisible(true);


//creating user cart table


    ////
    JPanel panel1 = new JPanel(new FlowLayout());
    JPanel panel2 = new JPanel(new FlowLayout());
    frame.add(panel1);
    frame.add(panel2);


    //adding the table
    frame.setSize(900, 500);
//setting the scroll pane with the tables

    ShoppingCartTableModel cartModel = new ShoppingCartTableModel(shoppingCart.getCart());
    ShoppingCartTable cartTable= new ShoppingCartTable(cartModel);
    JScrollPane tablePane = new JScrollPane(cartTable);
    tablePane.setBounds(30, 30, 650, 250);
    panel1.add(tablePane);







}
}
