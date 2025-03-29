package project;  //remember to change or something 

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;

/**
 *
 * @author 
 */
/* CUSTOMER */

public class Customer extends User {
    int points;//points used to redeem books
    String status;
    private final SimpleBooleanProperty available = new SimpleBooleanProperty();

    public Customer(String username,String password, int points){
        this.username = username;
        this.password = password;
        this.points = points;
        this.available.set(false);
    }

    //Handles Status of customer
    //Gold  >=1000 points
    //Silver  < 1000 points
    public void updateStatus(){
        if (points >= 1000){
            status = "Gold";
        } else {
            status = "Silver";
        }
    }
    
    public int getPoints(){
        return points;
    }
    public void setPoints(int num){
        points = num;
    }
    public String getStatus(){
        return status;
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
        return ""+username+","+password+","+points+","+status;
    }
}
