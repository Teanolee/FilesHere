package project;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
/* @author */

public class Book{
    private String name;
    private int price;
    private final SimpleBooleanProperty available = new SimpleBooleanProperty();
    //int[] prices = {50,100,200,500}; // all possible prices for a book (0->3)
    //for no specified checkmark
    public Book(String name,int price){
        this.name = name;
        this.price = price;
        this.available.set(false);
    }
    //for specified checkmark
    public Book(String name, int price, boolean available){
        this.name = name;
        this.price = price;
        this.available.set(available);
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }
    public int getPrice(){
        return price;
    }
    public void setPrice(int price){
        this.price = price;
    }

    // Getter for available (modified)
    public boolean getAvailable() {
        return available.get();
    }

    // Setter for available
    public void setAvailable(boolean available) {
        this.available.set(available);
    }

    // Property getter to bind with the table cell
    public BooleanProperty availableProperty() {
        return available;
    } 
    
    @Override
    public String toString(){
        return ""+name+","+price;
    }
}
