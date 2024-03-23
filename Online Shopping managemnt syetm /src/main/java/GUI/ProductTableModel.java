package GUI;
import org.example.*;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.util.ArrayList;

public class ProductTableModel extends AbstractTableModel {
    String[] columnNames = {"ProductID", "Name", "Category", "Price(£)", "Info"};
    ArrayList<Product> productsList;

    public ProductTableModel(ArrayList<Product> store) {
        productsList = store;
    }


    public void updateTable(ArrayList<Product> store, String Category) {
        if (Category.equals("All")) {
            productsList = store;


        } else {
            productsList = new ArrayList<Product>();
            for (Product p : store) {
                if (p.getCategory().equals(Category)) {
                    productsList.add(p);
                }
            }

        }
        fireTableDataChanged();//notifies of change


    }


    @Override
    public int getColumnCount() {
        return columnNames.length;

    }

    @Override
    public int getRowCount() {
        return productsList.size();

    }
    //gets data from object for each column
    //int row is product index in the arraylist of products

    public Object getValueAt(int row, int col) {
        Object t = null;
        if (col == 0) {
            t = productsList.get(row).getProductID();

        } else if (col == 1) {
            t = productsList.get(row).getProductName();

        } else if (col == 2) {
            t = (productsList.get(row).getClass()).getSimpleName();//to get the name of the class only
        } else if (col == 3) {
            t = productsList.get(row).getPrice();
        } else {
            if (productsList.get(row) instanceof Electronics) {
                t = ((Electronics) productsList.get(row)).getBrand() + " , " + ((Electronics) productsList.get(row)).getWarrantyPeriod() + " weeks warranty";
            } else {
                t = ((Clothing) productsList.get(row)).getSize() + " , " + ((Clothing) productsList.get(row)).getColor();
            }

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
            return String.class;

        } else if (col == 2) {
            return String.class;
        } else if (col == 3) {
            return Double.class;
        } else {
            return String.class;
        }

    }

    public static Product getProductAtRow(int selectedRow, ArrayList<Product> productsList) {
        try {
            return productsList.get(selectedRow);


        } catch (IndexOutOfBoundsException e) {
            return null;
        }
    }
}




