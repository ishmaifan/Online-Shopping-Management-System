package GUI;


import org.example.Clothing;
import org.example.Electronics;
import org.example.Product;
import org.example.ShoppingCart;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class ShoppingCartTableModel extends AbstractTableModel {
    String[] columnNames = {"Product", "Quantity", "Price(£)"};
ArrayList<Product> shoppingCart;

    public ShoppingCartTableModel(ArrayList<Product> shoppingCart) {
        this.shoppingCart = shoppingCart;
    }


    @Override
    public int getColumnCount() {
        return columnNames.length;

    }

    @Override
    public int getRowCount() {
        return shoppingCart.size();

    }


    public Object getValueAt(int row, int col) {
        Object t = null;
        if (col == 0) {
            if (shoppingCart.get(row) instanceof Electronics) {
                t = shoppingCart.get(row).getProductID() + "\n" + shoppingCart.get(row).getProductName() + "\n" + ((Electronics) shoppingCart.get(row)).getBrand() + "," + ((Electronics) shoppingCart.get(row)).getWarrantyPeriod() + " weeks";
            } else {
                t = shoppingCart.get(row).getProductID() + "\n" + shoppingCart.get(row).getProductName() + "\n" + ((Clothing) shoppingCart.get(row)).getSize() + "," + ((Clothing) shoppingCart.get(row)).getColor();

            }

        } else if (col == 1) {
            t = shoppingCart.get(row).getNumberOfAvailableItems();

        } else if (col == 2) {
            t = shoppingCart.get(row).getPrice() + "\u00A3";
        }

        return t;
    }


    //column name in JTable
    public String getColumnName(int col) {
        return columnNames[col];
    }

    //get the class of the column
    public Class getColumnClass(int col) {
        if (col == 0) {
            return String.class;
        } else if (col == 1) {
            return Integer.class;

        } else {
            return String.class;
        }


    }
}

