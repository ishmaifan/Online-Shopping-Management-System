package GUI;

import javax.swing.*;
import java.awt.*;

public class ShoppingCartTable extends JTable {
    ShoppingCartTable(ShoppingCartTableModel model){
        super();
        setShowGrid(true);
        this.setModel(model);
        setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        setFont(new Font("Times", Font.PLAIN, 11));
        setShowHorizontalLines(true);
        setShowVerticalLines(true);


    }


}
