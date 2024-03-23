package org.example;

import org.example.Product;

public class Electronics extends Product {

    private int warrantyPeriod;
    private String brand;


    public Electronics(String productID, String productName,double price, int warrantyPeriod, String brand,  int numberOfAvailableItems) {
        super(productID, productName, price, numberOfAvailableItems);
        this.warrantyPeriod = warrantyPeriod;
        this.brand = brand;


    }

    public int getWarrantyPeriod() {
        return warrantyPeriod;
    }

    public void setWarrantyPeriod(int warrantyPeriod) {
        this.warrantyPeriod = warrantyPeriod;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }





    @Override
    public String toString() {

        return super.toString()+
                "\nWarranty (weeks):"+warrantyPeriod+
                "\nBrand: "+brand;
    }
}
