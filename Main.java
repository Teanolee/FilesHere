//package project;
// ^ don't forget to change this

import java.util.ArrayList;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
//import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.scene.layout.FlowPane;
//import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.geometry.Orientation;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.layout.BorderPane;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.SimpleObjectProperty;
//import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.control.cell.CheckBoxTableCell;
//import javafx.scene.control.cell.PropertyValueFactory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**

***THINGS TO BE DONE***

- Login screen needs to read from customers.txt to log in
    - This needs to be implemented at the bottom under btn
    - We need the login info to disappear after logging out
        - From the TextFields, not the .txt
- Implement everything in Customer Book screen
    - Buy and Buy/Redeem 
    - Need to properly read from books.txt
    - Or Bookstore? Idk
- Implement everything in Admin Book Screen
    - Delete needs to be implemented
    - Add needs to write to book.txt or something 
- Implement everything in Admin Customer Screen
    - Delete needs to be implemented
    - Add needs to write to customers.txt or something 
***The select portion in both tables need to be properly implemented 

**/


public class Main extends Application{
    //Java variables --------------------------------------------------------------------
    //loads up starting classes and stuff
    Bookstore bookstore = new Bookstore();
    ArrayList<Customer> cList = new ArrayList<Customer>();
    ArrayList<Book> bList = new ArrayList<Book>();
    //NOT SURE HOW CHECKOUT GONNA BE IMPLEMENTED, SO CHANGE BOOKSTORE LATER 
    //currently uses arraylist, maybe just add em to the total when you select them ?
    ArrayList<Book> shoppingCart = new ArrayList<Book>(); //for totaling picked books
    
    int totalCost;
    //Customer stuff
    Customer curUser; //the current user using the account
    String dispUsername = "default";
    int dispPoints = 0;
    String dispStatus = "default";
    
    //labels
    Label custWelcome,custTC,custPrice;
        //--------------------------------------------------------------------------------------------------
    //Tables
    TableView<Book> custBookList;
    TableView<Book> adminBookList;
    TableView<Customer> customerList;
    
    //global instance variables 
    TextField username, password, name, price, addUsername, addPassword; 
    Button btn, btn2, btn3, logout,logoutAdmin, 
            buy, redeemBuy, add, back, back2, 
            delete, delete2, addCust;
    HBox button, button2, button3, logoutH, customerTop, 
            customerBottom, customerTop2, customerBottom2,
            customerMid2, adminBottom, adminMid, adminMidN,
            adminMidP, adminMidB, adminCustMid, adminCustBot,
            adminMidUser, adminMidPass, adminMidB2;

    VBox adminBook, adminCust;
    //for displaying purposes while I figure this shit out
    public void updateUser(Customer c){
       dispUsername = c.getUsername();
       dispPoints = c.getPoints();
       dispStatus = c.getStatus(); 
    }
    public void createButtons(){
            btn = new Button(); 
            btn2 = new Button(); 
            btn3 = new Button();
            logout = new Button(); 
            logoutAdmin = new Button();
            buy = new Button();
            redeemBuy = new Button();
            delete = new Button(); 
            delete2 = new Button();
            back = new Button(); 
            back2 = new Button(); 
            add = new Button(); 
            addCust = new Button();  
        }

    public void setButtonText(){

        btn.setText("Login"); 
        btn2.setText("Books"); 
        btn3.setText("Customers");
        logout.setText("Logout"); 
        logoutAdmin.setText("Logout"); 
        buy.setText("Buy");
        redeemBuy.setText("Redeem and Buy"); 
        delete.setText("Delete"); 
        back.setText("Back");
        add.setText("Add");
        //just in case
        delete2.setText("Delete");
        back2.setText("Back");
        addCust.setText("Add");

    }

    public void createHBox(){
        button = new HBox(); button2 = new HBox(); button3 = new HBox();
        logoutH = new HBox(); customerTop = new HBox(); customerBottom = new HBox(75);
        customerTop2 = new HBox(); customerMid2 = new HBox(); customerBottom2 = new HBox();

        adminMid = new HBox(50); adminMidN = new HBox(10);
        adminMidP = new HBox(10); adminMidB = new HBox(10);
        adminBottom = new HBox(75);

        adminCustMid = new HBox(50); adminMidUser = new HBox(10);
        adminMidPass = new HBox(10); adminMidB2 = new HBox(10);
        adminCustBot = new HBox(75); 
    }

    public void createTextFields(){
        username = new TextField(); 
        password = new TextField(); 
        name = new TextField(); 
        price = new TextField(); 
        addUsername = new TextField(); 
        addPassword = new TextField();
    }

    public void sceneOneCreation(FlowPane login){
        login.setAlignment(javafx.geometry.Pos.CENTER);
        button.setAlignment(javafx.geometry.Pos.CENTER_RIGHT);
        button.getChildren().addAll(btn);
        login.getChildren().addAll(new Label("Username"), username);
        login.getChildren().addAll(new Label("Password"), password);
        login.getChildren().addAll(button); 
    }

    public void sceneTwoCreation(FlowPane admin){
        admin.setAlignment(javafx.geometry.Pos.CENTER);
        button2.setAlignment(javafx.geometry.Pos.CENTER);
        button3.setAlignment(javafx.geometry.Pos.CENTER);
        logoutH.setAlignment(javafx.geometry.Pos.CENTER);

        button2.getChildren().addAll(btn2);
        button3.getChildren().addAll(btn3);
        logoutH.getChildren().addAll(logoutAdmin);
        admin.getChildren().addAll(button2, button3, logoutH);
    }

    public void sceneThreeCreation(BorderPane custStart,ArrayList<Book> bList){
        custWelcome = new Label("Welcome " + dispUsername+" you have: " + dispPoints + " Points. "
                + "Your Status is: " + dispStatus);
        
        custBookList = new TableView<>();
        custBookList.setEditable(true);//ALLOWS THE TABLE TO BE EDITABLE
        //BOOK TITLE COLLUMN
        TableColumn<Book, String> custCol1 = new TableColumn<>("Book Title");
        custCol1.setCellValueFactory(cellData -> 
                new SimpleStringProperty(cellData.getValue().getName()));
        //BOOK PRICE COLLUMN
        TableColumn<Book, Integer> custCol2 = new TableColumn<>("Book Price");
        custCol2.setCellValueFactory(cellData -> 
                new SimpleObjectProperty<>(cellData.getValue().getPrice()));
        //CHECKBOXES COLLUMN
        TableColumn<Book, Boolean> custCol3 = new TableColumn<>("Select");
        custCol3.setCellValueFactory(cellData -> //Adds Checkbox function
                cellData.getValue().availableProperty());
        //sets checkboxes
        custCol3.setCellFactory(CheckBoxTableCell.forTableColumn(custCol3));

        customerBottom.setAlignment(javafx.geometry.Pos.CENTER);
        customerTop.setAlignment(javafx.geometry.Pos.CENTER);

        custBookList.getColumns().addAll(custCol1, custCol2, custCol3);
        customerTop.getChildren().addAll(custWelcome);
        customerBottom.getChildren().addAll(buy, redeemBuy, logout);
        //BookTable.getChildren().addAll(custBookList);
        custStart.setTop(customerTop);
        custStart.setCenter(custBookList);
        custStart.setBottom(customerBottom);

        //JUST TEST DATA
       // books = FXCollections.observableArrayList(bList);
        /*
        ObservableList<Book> books = FXCollections.observableArrayList(
            new Book("Joe", 1, true),
            new Book("My", 2, false),
            new Book("Nuts", 3, true)
        );
        */
        //updates table with current books
        custBookList.setItems(FXCollections.observableArrayList(bList));
    }

    public void sceneFourCreation(BorderPane custBuy){
        custTC = new Label ("Total cost of Books: " + "XX");
        custPrice = new Label ("Total Points: " + "XX " + "Status: " + "XX");

        customerTop2.setAlignment(javafx.geometry.Pos.CENTER);
        customerBottom2.setAlignment(javafx.geometry.Pos.CENTER);
        customerMid2.setAlignment(javafx.geometry.Pos.CENTER);

        customerTop2.getChildren().addAll(custTC);
        customerMid2.getChildren().addAll(custPrice);
        customerBottom2.getChildren().addAll(logout);
        custBuy.setTop(customerTop2);
        custBuy.setCenter(customerMid2);
        custBuy.setBottom(customerBottom2);
    }

    public void sceneFiveCreation(BorderPane adminBookScreen,ArrayList<Book> bList){

        adminBook = new VBox(20);
        adminBookList = new TableView<>();
        adminBookList.setEditable(true);
        //BOOK TITLE
        TableColumn<Book, String> adminCol1 = new TableColumn<>("Book Title");
        adminCol1.setCellValueFactory(cellData -> 
                new SimpleStringProperty(cellData.getValue().getName()));
        //BOOK PRICE
        TableColumn<Book, Integer> adminCol2 = new TableColumn<>("Book Price");
        adminCol2.setCellValueFactory(cellData -> 
                new SimpleObjectProperty<>(cellData.getValue().getPrice()));
        //CHECKBOXES
        TableColumn<Book, Boolean> adminCol3 = new TableColumn<>("Select");
        adminCol3.setCellValueFactory(cellData -> //funny
                cellData.getValue().availableProperty());
        adminCol3.setCellFactory(CheckBoxTableCell.forTableColumn(adminCol3));

        adminBook.setAlignment(javafx.geometry.Pos.CENTER);
        adminBottom.setAlignment(javafx.geometry.Pos.CENTER);        
        adminMid.setAlignment(javafx.geometry.Pos.CENTER);  

        adminBookList.getColumns().addAll(adminCol1, adminCol2, adminCol3);
        adminMidN.getChildren().addAll(new Label("Book name"), name);
        adminMidP.getChildren().addAll(new Label("Book Price"), price);
        adminMidB.getChildren().add(add);
        adminMid.getChildren().addAll(adminMidN, adminMidP, adminMidB);
        adminBook.getChildren().add(adminMid);
        adminBottom.getChildren().addAll(delete, back);
        adminBookScreen.setTop(adminBookList);
        adminBookScreen.setCenter(adminBook);
        adminBookScreen.setBottom(adminBottom);

        //TEST DATA
        //ObservableList<Book> books = FXCollections.observableArrayList(bList);
        /*
        ObservableList<Book> books = FXCollections.observableArrayList(
            new Book("Joe", 1, true),
            new Book("My", 2, false),
            new Book("Nuts", 3, true)
        );
        */
        adminBookList.setItems(FXCollections.observableArrayList(bList));


    }

    public void sceneSixCreation(BorderPane adminCustScreen,ArrayList<Customer> cList){
        adminCust = new VBox(20);
        customerList = new TableView<>();
        customerList.setEditable(true);

        TableColumn<Customer, String> adminCustCol1 = new TableColumn<>("Username");
        adminCustCol1.setCellValueFactory(cellData -> 
                new SimpleStringProperty(cellData.getValue().getUsername()));
        TableColumn<Customer, String> adminCustCol2 = new TableColumn<>("Password");
        adminCustCol2.setCellValueFactory(cellData -> 
                new SimpleStringProperty(cellData.getValue().getPassword()));
        TableColumn<Customer, Integer> adminCustCol3 = new TableColumn<>("Points");
        adminCustCol3.setCellValueFactory(cellData ->
                new SimpleObjectProperty<>(cellData.getValue().getPoints()));
        TableColumn<Customer, Boolean> adminCustCol4 = new TableColumn<>("Select");
        adminCustCol4.setCellValueFactory(cellData -> 
                cellData.getValue().availableProperty());
          //sets checkboxes
        adminCustCol4.setCellFactory(CheckBoxTableCell.forTableColumn(adminCustCol4));

        adminCust.setAlignment(javafx.geometry.Pos.CENTER);
        adminCustBot.setAlignment(javafx.geometry.Pos.CENTER);        
        adminCustMid.setAlignment(javafx.geometry.Pos.CENTER);

        customerList.getColumns().addAll(adminCustCol1, adminCustCol2, adminCustCol3, adminCustCol4);
        adminMidUser.getChildren().addAll(new Label("Username"), addUsername);
        adminMidPass.getChildren().addAll(new Label("Password"), addPassword);
        adminMidB2.getChildren().add(addCust);
        adminCustMid.getChildren().addAll(adminMidUser, adminMidPass, adminMidB2);
        adminCust.getChildren().add(adminCustMid);
        adminCustBot.getChildren().addAll(delete2, back2);
        adminCustScreen.setTop(customerList);
        adminCustScreen.setCenter(adminCust);
        adminCustScreen.setBottom(adminCustBot);

        //TEST DATA
        //ObservableList<Customer> customers = FXCollections.observableArrayList(cList);
        /*
        ObservableList<Customer> customers = FXCollections.observableArrayList(
            new Customer("Joe", "mynuts213", 0),
            new Customer("My", "fatnuts", 1000),
            new Customer("Nuts", "cockandballs", 10000)
        );
        */
        customerList.setItems(FXCollections.observableArrayList(cList));
    }

    @Override
    public void start(Stage displayScreen) {
        //java stuff
        //loads save data
        bookstore.loadCustomers(cList);//loads from saved data
        bookstore.loadBooks(bList);
        bookstore.loadBooks(shoppingCart);
          
        //Creating Buttons
        createButtons();
        setButtonText();

        //Creating HBoxes and TextFields
        createHBox();
        createTextFields();

        //login Screen
        Label title = new Label("Welcome to the Bookstore App");
        FlowPane pane = new FlowPane(Orientation.VERTICAL, 5, 5, title); 

        //Admin Screen
        FlowPane pane2 = new FlowPane(Orientation.VERTICAL, 10, 10);     

        BorderPane pane3 = new BorderPane();            //Customer Start Screen
        BorderPane pane4 = new BorderPane();            //Customer Buy/Redeem Screen
        BorderPane pane5 = new BorderPane();            //Admin Book Screen
        BorderPane pane6 = new BorderPane();            //Admin Customer Screen

        //creating the panes and it's properties
        
        sceneOneCreation(pane);//login Screen
        sceneTwoCreation(pane2);//admin choice screen
        sceneThreeCreation(pane3,bList); //This is the Customer buy screen
        sceneFourCreation(pane4);//Customer Checkout 
        sceneFiveCreation(pane5,bList);//Admin Book
        sceneSixCreation(pane6,cList);//Admin Customer

        //different scenes 
        Scene loginScreen = new Scene(pane, 300, 250);
        Scene adminScreen = new Scene(pane2, 300, 250);
        Scene customerStartScreen = new Scene(pane3, 400, 350);
        Scene customerCostScreen = new Scene(pane4, 300, 250);
        Scene adminBookScreen = new Scene(pane5,600,475);
        Scene adminCustScreen = new Scene(pane6, 600, 475);

        //setting up the scenes (The important code) 
        displayScreen.setTitle("Bookstore");
        displayScreen.setScene(loginScreen);
        displayScreen.show();

        //button functions----------------------------------------------------------------------
        EventHandler<ActionEvent> loginEvent = new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent e){
               
                String user = username.getText();
                String pass = password.getText();
                username.clear(); //clears textfields
                password.clear();
                if(user.equals("admin") && pass.equals("admin")){
     
                    displayScreen.setScene(adminScreen);
                } else {
                    //Allows always transfer screens DELETE THIS LATER
                    //displayScreen.setScene(customerStartScreen); //FOR ACCESS PURPOSES DELETE LATER
                    ///* //CODE FOR LOGGIN IN WITH ACCOUNT
                    curUser = bookstore.findAccount(user,pass,cList);
                    //Customer temp = bookstore.findAccount(user,pass,cList);
                    if (curUser.getPoints() != -1){
                        //REMEMBER TO SAVE THE POINTS LATER
                        
                        updateUser(curUser);
                        custWelcome.setText("Welcome " + dispUsername+" you have: " + dispPoints + " Points. "
                + "Your Status is: " + dispStatus);
                        displayScreen.setScene(customerStartScreen);
                    }
                    
                    //*/
                }
            }
        };

        btn.setOnAction(loginEvent);

        //Book Button for admin
        btn2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                displayScreen.setScene(adminBookScreen);
            }
        });

        //Customer Button for admin
        btn3.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                displayScreen.setScene(adminCustScreen);
            }
        });
        //LOGGIN OUT
        logout.setOnAction(e -> {
            //prints out customers
            for (Customer c : cList){
                System.out.println(c);
            }
            //stores in file when done
            bookstore.storeData(cList,bList);
            displayScreen.setScene(loginScreen);
                });
        logoutAdmin.setOnAction(e -> {
            //stores in file when done
            bookstore.storeData(cList,bList);
            displayScreen.setScene(loginScreen);});

        back.setOnAction(e -> displayScreen.setScene(adminScreen));
        back2.setOnAction(e -> displayScreen.setScene(adminScreen));
        
        //Buy screen with CAD
        EventHandler<ActionEvent> moneyBuy = new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event) {
                //totals money needed
                totalCost = bookstore.totalCost(shoppingCart); //calculates total cost
                bookstore.checkout(curUser, shoppingCart);
                curUser.updateStatus();
                custTC.setText("Total cost of Books: "+totalCost);//changes respective labels
                custPrice.setText("Total Points: "+curUser.getPoints()+" Status: "+curUser.getStatus());
                
                displayScreen.setScene(customerCostScreen);
            }
        };
        
        buy.setOnAction(moneyBuy);//Purchase with CAD
        
//Buying with points
        EventHandler<ActionEvent> pointsBuy = new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event) {
                //totals money needed
                totalCost = 0; //calculates total cost
                bookstore.checkout(curUser, shoppingCart);
                curUser.updateStatus();
                custTC.setText("Total cost of Books: "+totalCost);//changes respective labels
                custPrice.setText("Total Points: "+curUser.getPoints()+" Status: "+curUser.getStatus());
                
                displayScreen.setScene(customerCostScreen);
            }
        };
        redeemBuy.setOnAction(pointsBuy);//Purchase with Points
        
        //Add customers for admin
        EventHandler<ActionEvent> custAdd = new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event) {
                String newUser = addUsername.getText();
                String newPass = addPassword.getText();
                Customer newCust = new Customer(newUser, newPass, 0);
                newCust.updateStatus();
                cList.add(newCust);
                //bookstore.storeData(cList, bList);
                //ObservableList<Customer> customers = FXCollections.observableArrayList(cList);
                customerList.setItems(FXCollections.observableArrayList(cList));
                //adminCustTop.getChildren().clear();
                //adminCustTop.getChildren().add(customerList);
            }
        };
        addCust.setOnAction(custAdd);

        //Add books for admin
        EventHandler<ActionEvent> bookAdd = new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event) {
                String bookName = name.getText();
                int bookPrice = Integer.parseInt(price.getText());
                
                Book newBook = new Book(bookName, bookPrice);
                bList.add(newBook);
                //bookstore.storeData(cList, bList);
                //ObservableList<Book> books = FXCollections.observableArrayList(bList);
                
                //Updates BookList for Admin and Customer
                adminBookList.setItems(FXCollections.observableArrayList(bList));
                custBookList.setItems(FXCollections.observableArrayList(bList));
                //adminTop.getChildren().clear();
                //adminTop.getChildren().add(adminBookList);
            }
        };
        add.setOnAction(bookAdd);
    }


    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}

