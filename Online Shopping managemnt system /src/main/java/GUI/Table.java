package GUI;

import org.example.*;


import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;
import java.awt.*;
import java.util.ArrayList;


public class Table extends JTable {
    ArrayList<Product> store = WestminsterShoppingManager.getSystemStore();

    public Table(ProductTableModel model) {
        super();
        this.setModel(model);
        TableColumnModel tableColumnModel = getColumnModel();
        limitedStockWarning limitedStockWarning = new limitedStockWarning();
        setDefaultRenderer(Object.class, new limitedStockWarning());
        setDefaultRenderer(Double.class, new limitedStockWarning());
        setShowGrid(true);


        setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        setFont(new Font("Times", Font.PLAIN, 11));
        setShowHorizontalLines(true);
        setShowVerticalLines(true);

    }


    //to make the font colour red if less than 3 items are available
    private class limitedStockWarning extends DefaultTableCellRenderer {
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int col) {

            Component cell = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, col);

                String productID = (String) table.getModel().getValueAt(row, 0);



            for (Product p : store) {
                if (productID.equals(p.getProductID())) {
                    if (p.getNumberOfAvailableItems() < 3) {
                        cell.setForeground(Color.RED);
                    } else {
                        cell.setForeground(Color.BLACK);
                    }
                }

            }

            return cell;
        }
    }
}





