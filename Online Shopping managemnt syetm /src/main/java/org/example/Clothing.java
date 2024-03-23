package org.example;

import java.io.Serializable;

public class Clothing extends Product {
    private String size;
    private String color;


    public Clothing(String productID, String productName, double price, String size, String color,int numberOfAvailableItems) {
        super(productID, productName, price, numberOfAvailableItems);
        this.size = size;
        this.color = color;


    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }



    @Override
    public String toString() {

        return super.toString()+
                "\nSize:" + size +
                "\nColor:" + color
                ;
    }
}
