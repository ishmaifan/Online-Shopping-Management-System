package org.example;

import java.io.IOException;

public interface ShoppingManager {

     void addProductToSystem();

     void deleteProduct();

     void printProductList();

     void saveProductDetails() throws IOException, ClassNotFoundException;


}
