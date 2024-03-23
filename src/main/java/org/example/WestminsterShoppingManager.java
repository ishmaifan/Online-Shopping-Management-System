package org.example;

import GUI.*;

import java.io.*;
import java.util.*;

import static java.lang.System.in;


public  class WestminsterShoppingManager implements ShoppingManager {
    public static final int MAX_PRODUCTS_IN_SYSTEM = 50;

    private static ArrayList<Product> systemStore = new ArrayList<Product>();

    Scanner sc = new Scanner(in);

    public void displayOptions() throws IOException, ClassNotFoundException {
        boolean flag = true;
        while (flag) {

            try {
                System.out.print("\n------Welcome to the System------\n ENTER " + "COMMAND SHOWN\n1)To add product to system : 1\n2)To Delete Product: 2\n3)To print product list: 3\n4)To save data of products in the store : 4\n5)To exit application:0\n6)Retrieve Data from file:5\n7)Westminster Shopping Centre:6\nEnter command: ");


                int userInput = sc.nextInt();
                sc.nextLine();//clear buffer


                switch (userInput) {

                    case 1:
                        addProductToSystem();
                        break;

                    case 2:
                        deleteProduct();
                        break;

                    case 3:
                        printProductList();
                        break;
                    case 4:
                        saveProductDetails();
                        break;
                    case 0:
                        flag = false;
                        System.out.print("EXIT");
                        break;
                    case 5:
                        retrieveDataFromFile();
                        break;
                    case 6:
                   GUI1  gui1   =new GUI1();


                    default:
                        System.out.print("Invalid command");
                        break;


                }

            } catch (InputMismatchException e) {
                System.out.println("Enter valid integer");
                sc.next();
            }

        }

    }


    /**
     * Gets product details and adds the product to the System
     */
    @Override
    public void addProductToSystem() {
        try {
            if (systemStore.size() < 50) {
                System.out.print("Enter C Clothing and E for electronics : ");
                String type = sc.nextLine().toUpperCase();//convert user input to uppercase so that even if user enters in lowercase it will be converted.
                if (type.equals("C") || type.equals("E")) {
                    System.out.print("Enter product name: ");
                    String productName = sc.nextLine();


                    System.out.print("Enter product ID:  ");
                    String productID = sc.nextLine();

                    boolean isUnique = productIDIsUnique(productID);
                    if (isUnique==true) {


                        System.out.print("Enter number of items of the product:  ");
                        int numOfItems = sc.nextInt();

                        if (numOfItems < 0) {
                            System.out.print("\nPlease enter a valid integer greater than zero for number of items");
                        } else {

                            System.out.print("Enter product price:  ");
                            double productPrice = sc.nextDouble();
                            sc.nextLine();
                            if (productPrice < 0) {
                                System.out.print("\nPlease enter a valid integer greater than zero for product price ");
                            } else {


                                if (type.equals("E")) {
                                    System.out.print("Enter warranty period in number of months:  ");
                                    int warrantyPeriod = sc.nextInt();
                                    sc.nextLine();

                                    System.out.print("Enter product brand:  ");
                                    String brand = sc.nextLine();

                                    Electronics electronic = new Electronics(productID, productName, productPrice, warrantyPeriod, brand, numOfItems);
                                    systemStore.add((electronic));

                                } else {
                                    System.out.print("Enter clothing colour:  ");
                                    String colour = sc.nextLine();

                                    System.out.print("Enter clothing size(XS,S,M,L,XL,XXL):  ");
                                    String size = sc.nextLine();

                                    Clothing clothing = new Clothing(productID, productName, productPrice, size, colour, numOfItems);

                                    systemStore.add((clothing));
                                }


                            }


                        }

                    } else {
                        System.out.print("Please enter an unique product ID. The one entered is already used.");
                    }

                }else{
                    System.out.print("\nInvalid input. Enter either C for org.example.Clothing or E electronics\n");
                }

            } else {
                System.out.print("System space exceeded 50 products");

            }
        } catch (Exception e) {
            System.out.print("Enter valid input");
        }
    }

    int productCount = Product.getProductCount();
    /**
     * Remove
     */
    @Override
    public void  deleteProduct() {


        boolean found = false;

        System.out.println("Enter product ID to be deleted: ");
        String productID = sc.nextLine();
        for (int i = 0; i < systemStore.size(); i++) {
            if (systemStore.get(i).getProductID().equals(productID)) {
                found = true;
            Product deletedProduct= systemStore.get(i);
                systemStore.remove(deletedProduct);


                if (deletedProduct instanceof Electronics) {
                    Electronics e = ((Electronics) deletedProduct);
                    System.out.println("Deleted Product \nElectronics \nProduct Name: " + e.getProductName() + "\nProductID: " + e.getProductID() + "\nPrice:" + e.getPrice() + "\nBrand: " + e.getBrand() + "\nNumber of  products left in the system: " + systemStore.size()+ "\nWarranty: " + e.getWarrantyPeriod());
                } else {
                    Clothing c = ((Clothing) deletedProduct);
                    System.out.println("Deleted Product\nClothing\nProduct Name: " + c.getProductName() + "\nProductID: " + c.getProductID() + "\nPrice:" + c.getPrice() + "\nSize: " + c.getSize() + "\nNumber of products left in the system: " +systemStore.size()+   "\nColour: " + c.getColor());
                }


            }
        }
        if (!found) {
            System.out.println("org.example.Product not found");

        }

    }


    @Override
    public void printProductList() {
        Comparator<Product> compare = new Comparator<Product>() {

            public int compare(Product i, Product j) {
                if (i.getProductID().compareTo(j.getProductID()) > 0) {
                    return 1;
                } else {
                    return -1;
                }
            }
        };

        Collections.sort(systemStore, compare);
        for (int k = 0; k < systemStore.size(); k++) {
            if (systemStore.get(k) instanceof Electronics) {
                Electronics electronicItem = (Electronics) systemStore.get(k); //downcasting
                System.out.println("\nProduct :Electronics \nProduct Name: " + systemStore.get(k).getProductName() + "\nProductID: " + systemStore.get(k).getProductID() + "\nProduct price: " + systemStore.get(k).getPrice() + "\nNumber of electronic products : " + electronicItem.getNumberOfAvailableItems() + "\nWarranty Period: " + electronicItem.getWarrantyPeriod() + "\nBrand: " + electronicItem.getBrand() + "\n\n");
            } else {
                Clothing clothingItem = (Clothing) systemStore.get(k);
                System.out.println("\nProduct :Clothing \nProduct Name:" + systemStore.get(k).getProductName() + "\nProduct ID: " + systemStore.get(k).getProductID() + "\nProduct Price: " + systemStore.get(k).getPrice() + "\nNumber of available items: " + clothingItem.getNumberOfAvailableItems() + "\nColour: " + clothingItem.getColor() + "\nSize:" + clothingItem.getSize());


            }

        }
    }


    @Override
    public void saveProductDetails() throws IOException, ClassNotFoundException {
      /*  File myDirectory = new File("Stock Details.txt");
        FileWriter writer = new FileWriter("Stock Details.txt");
        for (org.example.Product p : systemStore) {
            if (p instanceof org.example.Electronics) {
                writer.write(p.getProductName() + "," + p.getProductID() + "," + p.getPrice() + "," + ((org.example.Electronics) p).getWarrantyPeriod() + "," + ((org.example.Electronics) p).getBrand() + "\n");
            } else {
                writer.write(p.getProductName() + "," + p.getProductID() + "," + p.getPrice() + "," + ((org.example.Clothing) p).getSize() + "," + ((org.example.Clothing) p).getColor() + "\n");
            }



        }
        writer.close();*/

        try {
            FileOutputStream fileOut = new FileOutputStream("ProductDetails.dat");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            for (Product p : systemStore) {
                out.writeObject(p);

            }
            out.close();
            fileOut.close();
            System.out.print("Data saved in to file\n");
        } catch (IOException e) {
            System.out.println("IO exception");
        }
    }

    public void retrieveDataFromFile() throws IOException, ClassNotFoundException {
     /* try{  BufferedReader bufferedReader=new BufferedReader(new FileReader("Stock Details.txt"));
            Scanner s=new Scanner(bufferedReader);
            s.useDelimiter(",");
            while(s.hasNext()){
                String productName= s.next();
                if(productName.equals("electronics")){
                    String productID=s.next();
                    Double  price=Double.parseDouble(s.next());

                    Integer warranty=Integer.parseInt(s.next());

                    String brand=s.next();
                    org.example.Electronics electronicItem= new org.example.Electronics(productID,productName,price,warranty,brand);
                    systemStore.add(electronicItem);
                    System.out.println("org.example.Product:"+productName+"\nProductID: "+productID+"\nPrice:"+price+"\nWarranty: "+warranty+"\nBrand: "+brand);
                }else{
                    String productNameC=s.next();
                    String productID=s.next();
                    Double  price=Double.parseDouble((s.next()));
                    String size=s.next();
                    String color=s.next();
                    org.example.Clothing clothing =new org.example.Clothing(productID,productName,price,size,color);
                    systemStore.add(clothing);
                    System.out.println("org.example.Product:"+productName+"\nProductID: "+productID+"\nPrice:"+price+"\nSize: "+size+"\nColour: "+color);
                }

            }

s.close();
        }catch( FileNotFoundException e) {
          System.out.println("File not found");
      }


*/
        try {
            Product p = null;

            FileInputStream filein = new FileInputStream("ProductDetails.dat");
            ObjectInputStream in = new ObjectInputStream(filein);


            p = (Product) in.readObject();

            while (p != null) {
                systemStore.add(p);


                if (p instanceof Electronics) {
                    System.out.println("\norg.example.Electronics\norg.example.Product:" + p.getProductName() + "\nProductID: " + p.getProductID() + "\nPrice:" + p.getPrice() + "\nWarranty: " + ((Electronics) p).getWarrantyPeriod() + "\nBrand: " + ((Electronics) p).getBrand() + "\nNumber of items available: " + ((Electronics) p).getNumberOfAvailableItems());
                } else {


                    System.out.println("\norg.example.Clothing\norg.example.Product:" + p.getProductName() + "\nProductID: " + p.getProductID() + "\nPrice:" + p.getPrice() + "\nSize: " + ((Clothing) p).getSize() + "\nColour: " + ((Clothing) p).getColor() + "\nNumber of items available: " + p.getNumberOfAvailableItems());
                }
                p = (Product) in.readObject();
            }
            in.close();
            filein.close();

        } catch (EOFException e) {
            System.out.println("\nEnd of file reached");
        } catch (IOException E) {
            System.out.println("IO Exception ");

        }catch(ClassNotFoundException e){

        }
    }


    public boolean productIDIsUnique(String ID) {
        boolean isUnique = true;
        for (Product item : systemStore) {
            if (item.getProductID().equals(ID)) {
                 isUnique = false;
            }

        }
        return isUnique;
    }


    public static ArrayList<Product> getSystemStore() {
        return systemStore;
    }
}



























