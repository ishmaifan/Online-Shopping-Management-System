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


public class  GUI1 extends JFrame {
    ArrayList<Product> store = WestminsterShoppingManager.getSystemStore();
     Table table;
     JTextArea productDetails;

     JButton shoppingCartButton;

    JButton buttonAddToShoppingCart;
   public static  ShoppingCart shoppingCart = new ShoppingCart();


    public GUI1() {


        //main frame with grid lay out containing 2 panels
        JFrame frame = new JFrame("Westminster Shopping Centre");
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frame.setSize(1000, 950);    //FlowLayout.LEFT,50,60)


        frame.setLayout(new BorderLayout());

        JPanel panelA = new JPanel();
        panelA.setLayout(new FlowLayout());
        frame.add(panelA, BorderLayout.NORTH);
        JPanel pa1 = new JPanel(new FlowLayout(FlowLayout.LEFT, 50, 40));
        JPanel pa2 = new JPanel(new BorderLayout());


        panelA.add(pa1);
        panelA.add(pa2);


        JPanel panelE = new JPanel(new FlowLayout());
        frame.getContentPane().add(panelE, BorderLayout.CENTER);


        //Jlabel
        JLabel selectProductCategory = new JLabel("Select product Category");
        pa1.add(selectProductCategory);
        //Drop down menu
        String[] dropDownOptions = {"All", "Electronics", "Clothing"};//array containing the drop down options
        JComboBox menu = new JComboBox(dropDownOptions);
        menu.setMaximumSize(new Dimension(250, 25));
        pa1.add(menu);

//shopping cart button added to panel
        shoppingCartButton = new JButton("Shopping Cart");
        shoppingCartButton.setPreferredSize(new Dimension(150, 25));
        pa2.add(shoppingCartButton, BorderLayout.EAST);


//table
        ProductTableModel model = new ProductTableModel(store);
        table = new Table(model);
        table.setPreferredScrollableViewportSize(new Dimension((int) (table.getPreferredSize().getWidth() * 1.5), (table.getRowHeight() * 10)));
        table.getColumnModel().getColumn(4).setPreferredWidth(200);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setHorizontalScrollBarPolicy(scrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollPane.setVerticalScrollBarPolicy(scrollPane.VERTICAL_SCROLLBAR_ALWAYS);


        panelE.add(scrollPane);


        JPanel panelD = new JPanel(new BorderLayout());
        frame.getContentPane().add(panelD, BorderLayout.SOUTH);

        panelD.setLayout(new BorderLayout());
        JPanel panelDS = new JPanel(new BorderLayout());

        panelD.add(panelDS, BorderLayout.SOUTH);
        panelD.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));


        buttonAddToShoppingCart = new JButton("Add to  Shopping Cart ");
        buttonAddToShoppingCart.setPreferredSize(new Dimension(300, 35));
        JPanel panelDSouthFlow = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panelDS.add(panelDSouthFlow, BorderLayout.SOUTH);


        panelDSouthFlow.add(buttonAddToShoppingCart);
        JLabel selected = new JLabel("Selected Product- Details");
        selected.setPreferredSize(new Dimension(270, 30));
        productDetails = new JTextArea();
        productDetails.setEditable(false);
        productDetails.setPreferredSize(new Dimension(200, 350));


        JPanel flowPanelOnWest = new JPanel(new FlowLayout());
        flowPanelOnWest.setLayout(new BoxLayout(flowPanelOnWest, BoxLayout.Y_AXIS));

        panelDS.add(flowPanelOnWest, BorderLayout.WEST);
        productDetails.setLineWrap(true);
        productDetails.setWrapStyleWord(true);

        flowPanelOnWest.add(selected);
        flowPanelOnWest.add(productDetails);


//sorting table
        table.setRowSorter(new TableRowSorter(model));
        frame.setVisible(true);


        menu.addActionListener(new ComboBox());

        TableHandler tableHandler = new TableHandler();
        table.getSelectionModel().addListSelectionListener(new TableHandler());


        addToCart addTocart = new addToCart();
        buttonAddToShoppingCart.addActionListener(addTocart);

        viewCart viewCart = new viewCart();
        shoppingCartButton.addActionListener(viewCart);


    }


    private class TableHandler implements ListSelectionListener {
        public void valueChanged(ListSelectionEvent event) {
            int selectedRowNumber = table.getSelectedRow();
            Product product = ProductTableModel.getProductAtRow(selectedRowNumber, store);
            if (product instanceof Electronics)
                productDetails.setText(((Electronics) product).toString());
            else {
                productDetails.setText(((Clothing) product).toString());
            }
        }


    }

    private class ComboBox implements ActionListener {

        public void actionPerformed(ActionEvent event) {
            if (event.getSource() instanceof JComboBox) {
                JComboBox sb = (JComboBox) event.getSource();
                if (sb.getSelectedItem() != null && table != null) {
                    String category = (String) sb.getSelectedItem();
                    ProductTableModel model = (ProductTableModel) table.getModel();
                    model.updateTable(store, category);
                    table.setRowSorter(new TableRowSorter(model));

                }
            }
        }
    }

    private class addToCart implements ActionListener {

        public void actionPerformed(ActionEvent event) {
            if (event.getSource() instanceof JButton) {

                JButton addToShoppingCart = (JButton) event.getSource();
                if (addToShoppingCart.getText().equals("Add to Shopping Cart")) {
                    int selectedRowNumber = table.getSelectedRow();
                    Product selectedProduct = ProductTableModel.getProductAtRow(selectedRowNumber, store);
                    shoppingCart.getCart().add(selectedProduct);
                    selectedProduct.setNumberOfAvailableItems(selectedProduct.getNumberOfAvailableItems() - 1);


                    //if(selectedProduct.getNumberOfAvailableItems()<3){

                }
            }

        }
    }


    private class viewCart implements ActionListener {

        public void actionPerformed(ActionEvent event) {
            if (event.getSource() instanceof JButton) {

                JButton viewCart = (JButton) event.getSource();
                if (viewCart.getText().equals("Shopping Cart")) {
                    GUI2 gui2 = new GUI2();
                }
            }
        }
    }


}














































