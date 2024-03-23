package org.example;

import java.io.Serializable;

public abstract class Product  implements Serializable {

    private String productID;
    private String productName;

    private double price;

    private  int numberOfAvailableItems;
    private static  int productCount;

    public Product(String productID, String productName, double price,int numberOfAvailableItems) {
        this.productID = productID;
        this.productName = productName;
        this.price = price;
       this.numberOfAvailableItems=numberOfAvailableItems;



    }

    public String getProductID() {
        return productID;
    }

    public void setProductID(String productID) {
        this.productID = productID;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }





    public double getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getNumberOfAvailableItems() {
        return numberOfAvailableItems;
    }

    public void setNumberOfAvailableItems(int numberOfAvailableItems) {
        this.numberOfAvailableItems = numberOfAvailableItems;
    }

    public static int getProductCount() {
        return productCount;
    }

    public static void setProductCount(int productCount) {
        Product.productCount = productCount;
    }
public String  getCategory(){
      return   this.getClass().getSimpleName();
}
    @Override
    public String toString() {
        return
                "Product Id:"+ productID
                        + "\nCategory:"+this.getClass().getSimpleName()+
                        "\nName:"+ productName;
    }
}
