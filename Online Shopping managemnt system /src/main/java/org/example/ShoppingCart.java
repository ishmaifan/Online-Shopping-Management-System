package org.example;

import org.example.Product;

import java.util.ArrayList;

public class ShoppingCart {
   private  static  ArrayList<Product> cart= new ArrayList<>();

    public ShoppingCart() {

    }

    /**
     * adds products to the cart
     * @param item
     */
    public void addToCart(Product item){
       cart.add(item);
   }

    /**
     * removes item from cart
     * @param item
     */
   public void removeItem(Product item){
       cart.remove(item);
   }

    /**
     *
     * @return the total of the products prices in the user's cart
     * @throws NullPointerException
     */
   public double calculateTotal()throws NullPointerException{
       double total =0;
       for(Product item  :cart){
       total+= item.getPrice();
       }
       return total;
   }

    public  ArrayList<Product> getCart() {
        return cart;
    }
}
