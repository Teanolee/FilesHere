package project;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.File;
import java.io.FileWriter;

public class Bookstore {
    
    //logging in, returns true if they are there
    public Customer findAccount(String username,String password,ArrayList<Customer> customers){
       Customer failedCustomer = new Customer("Failed","Failed",-1);
       for (Customer c : customers){
           if (c.CheckLogIn(username,password)){
               return c;
           }
       } 
       return failedCustomer;
    }
    
    //reading book file
    public void loadBooks(ArrayList<Book> books){
        try{
            //System.out.println(new File(".").getAbsolutePath());
            File f = new File("books.txt");
            
            //Makes the file if it doesnt exist
            if (f.createNewFile()) {
            System.out.println("File created: " + f.getName());
            } else {
            System.out.println("File already exists.");
            }
            //reading files contents
            Scanner s = new Scanner(f);
            while (s.hasNextLine()){
                String data = s.nextLine();
                //System.out.println(data);
                String input[]= data.split(",");
                Book b = new Book(input[0],(Integer.valueOf(input[1])));//name,price no.
                books.add(b);
            }
            s.close();
        }catch(FileNotFoundException e){
            System.out.println("File not found");
            
        }catch(IOException f){
            System.out.println("Error occured");
        } 
     }
     //reading customer files
    public  void loadCustomers(ArrayList<Customer> customers){
        
        try{
            //System.out.println(new File(".").getAbsolutePath());
            File f = new File("customers.txt");
            
            //Makes the file if it doesnt exist
            if (f.createNewFile()) {
            System.out.println("File created: " + f.getName());
            } else {
            System.out.println("File already exists.");
            }
            //reading files contents
            Scanner s = new Scanner(f);
            while (s.hasNextLine()){
                String data = s.nextLine();
                //System.out.println(data);
                String input[]= data.split(",");
                Customer c = new Customer(input[0],input[1],(Integer.valueOf(input[2])));//username,password,points
                c.updateStatus(); // updates status based on points
                customers.add(c);
            }
            s.close();
        }catch(FileNotFoundException e){
            System.out.println("File not found");
            
        }catch(IOException f){
            System.out.println("Error occured");
        }
        
      
    }
    //writing to files
    //format var,var,var [no spaces when putting integers]
    
    public void storeData(ArrayList<Customer> customers,ArrayList<Book> books){
        try{
            FileWriter cW = new FileWriter("customers.txt");
            FileWriter bW = new FileWriter("books.txt");
            for(Customer c : customers){
                cW.write(c.toString()+"\n");
            }
            for(Book b:books){
                bW.write(b.toString()+"\n");
            }
            
            cW.close();
            bW.close();
        } catch(IOException e){
           System.out.println("Error"); 
        }
        
    }
    
    public Customer addCustomer(String username,String password){
        Customer c = new Customer(username,password,0);//default new customers have 0 points
        c.updateStatus();
        return c;
    }
    
    public Book addBook(String name,int price){
        //price is either 0  1   2   3 
        //               50 100 200 5000
        Book b = new Book(name,price);
        return b;
    }
    //sums total cost of purchased books
    public int totalCost(ArrayList<Book> books){
        int total = 0;
        for (Book b : books){
            total += b.getPrice();
        }
        return total;
    }
    //points calculations (1 CAD = 10 points
    public int earnedPoints(int sum){
        return sum*10;
    }
    //redeems points (100 points = 1 
     //mainly managing thier points
    public int redeemPoints(Customer c,int total){
       int points = c.getPoints();
       int finalTotal = total - (points/100);
       if (finalTotal<0){
           c.setPoints(-1*finalTotal*100);//removes used points 
       }else{
           c.setPoints(0);
       }
       return 0;//totalCost
    }
    //Earned points for checkout
    private int calculateEarnedPoints(int sum){
        return sum*10;
    }
    //handles checking out the customer 
    // Returns the total cost to be paid.
       public int checkoutCash(Customer c, ArrayList<Book> cart) {
        int total = totalCost(cart);
        if (total > 0) { // Only add points if something was actually bought
             c.setPoints(c.getPoints() + calculateEarnedPoints(total)); // Adds earned points to total points
             c.updateStatus(); // Update status based on new points total
        }
        // In a real system, payment processing would happen here.
        return total; // The amount the customer needs to pay
    }

    //  Checkout using ONLY points.
    // Succeeds ONLY if the customer has enough points to cover the ENTIRE cost.
    // If successful, deducts the points and updates status.
    // If unsuccessful, makes no changes to the customer's account.
    // Returns true if the purchase was successful (paid fully with points), false otherwise.
    public boolean checkoutRedeem(Customer c, ArrayList<Book> cart) {
        int total = totalCost(cart);
        if (total == 0) {
            System.out.println("Cart is empty. Nothing to redeem.");
            return true; // Nothing to buy, consider it successful (or neutral).
        }

        int pointsAvailable = c.getPoints(); // Get current points
        int valueOfPoints = pointsAvailable / 100; // Calculate $ value of points

        System.out.println("Attempting redeem: Cart Total=$" + total + ", Points Value=$" + valueOfPoints + " ("+pointsAvailable+" points)"); // Debugging

        if (valueOfPoints >= total) {
            int pointsToUse = total * 100; 
            c.setPoints(pointsAvailable - pointsToUse); 
            c.updateStatus(); // Update status based on new points total
            System.out.println("Redeem successful. Points remaining: " + c.getPoints()); // Debugging
            return true; // Purchase successful using points
        } else {
            System.out.println("Redeem failed: Insufficient points."); // Debugging
            return false; // Purchase failed due to insufficient points
        }
    }
}
