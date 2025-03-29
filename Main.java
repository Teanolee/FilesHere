package project;
// ^ don't forget to change this

import java.util.ArrayList;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.scene.layout.FlowPane;
import javafx.geometry.Insets;
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
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.collections.FXCollections;

public class Main extends Application{
    
    //Java variables --------------------------------------------------------------------
    //loads up starting classes and stuff
    Bookstore bookstore = new Bookstore();
    ArrayList<Customer> cList = new ArrayList<Customer>();
    ArrayList<Book> bList = new ArrayList<Book>();
    ArrayList<Book> shoppingCart = new ArrayList<Book>(); //for totaling picked books
    int totalCost;
    //Customer stuff
    Customer curUser; //the current user using the account
    String dispUsername = "default";
    int dispPoints = 0;
    String dispStatus = "default";
    
    //labels
    Label custWelcome,custTC,custPrice,errorLabel,errorLabel1;
     
    //Tables
    TableView<Book> custBookList;
    TableView<Book> adminBookList;
    TableView<Customer> customerList;
    
    //global instance variables 
    TextField username, password, name, price, addUsername, addPassword; 
    Button btn, btn2, btn3, logout,logoutAdmin,logoutCust, 
            buy, redeemBuy, add, back, back2,back3, 
            delete, delete2, addCust;
    HBox button, button2, button3, logoutH, customerMid,
            customerBottom, custBot1, custBot2, custBot3, customerTop2, customerBottom2,
            customerMid2, adminBottom, adminTop, adminMid, adminMidN,
            adminMidP, adminMidB, adminCustTop, adminCustMid, adminCustBot,
            adminMidUser, adminMidPass, adminMidB2;
    
    VBox buyText, topContainer;
  
    //RESET
    public void reset(){
        shoppingCart.clear();
        adminBookList.setItems(FXCollections.observableArrayList(bList));
        custBookList.setItems(FXCollections.observableArrayList(bList));
        customerList.setItems(FXCollections.observableArrayList(cList));
        bookstore.storeData(cList,bList);
        errorLabel.setText("");
        errorLabel1.setText("");
    }
    
    //for displaying purposes while I figure this shit out
    public void updateUser(Customer c){
       dispUsername = c.getUsername();
       dispPoints = c.getPoints();
       dispStatus = c.getStatus(); 
    }
    
    //Adds selected Books to a list
    public void purchaseBooks(ArrayList<Book> books,ArrayList<Book> addList){
        for (Book b: books){
            if(b.getAvailable()){
                
                addList.add(b);
            }
        }
    }
    
    //Deletes Books from a List
    public void deleteBooks(ArrayList<Book> books){
        for(Book b: books){
            if(b.getAvailable()){
                adminBookList.getItems().remove(b);
                books.remove(b);//removes all books in books from delList
            }
        }
    }
    
    //Deletes Customers from a List
    public void deleteCustomers(ArrayList<Customer> customers){
        for(Customer c: customers){
            if(c.getAvailable()){
                customerList.getItems().remove(c);
                customers.remove(c);//removes all books in books from delList
            }
        }
    }
    
    //Method to clear all book selections
    public void clearBookSelections(ArrayList<Book> books) {
        for (Book book : books) {
            book.setAvailable(false);
        }
    }
    
    //Method to clear all customer selections
    public void clearCustomerSelections(ArrayList<Customer> cust) {
        for (Customer customer : cust) {
            customer.setAvailable(false);
        }
    }
    
    //creating the buttons
    public void createButtons(){
            btn = new Button(); 
            btn2 = new Button(); 
            btn3 = new Button();
            logout = new Button(); 
            logoutAdmin = new Button();
            logoutCust = new Button();
            buy = new Button();
            redeemBuy = new Button();
            delete = new Button(); 
            delete2 = new Button();
            back = new Button(); 
            back2 = new Button(); 
            back3 = new Button();
            add = new Button(); 
            addCust = new Button();  
        }

    //setting button text
    public void setButtonText(){

        btn.setText("Login"); 
        btn2.setText("Books"); 
        btn3.setText("Customers");
        logout.setText("Logout"); 
        logoutAdmin.setText("Logout"); 
        logoutCust.setText("Logout");
        buy.setText("Buy");
        redeemBuy.setText("Redeem and Buy"); 
        delete.setText("Delete"); 
        back.setText("Back");
        add.setText("Add");
        //just in case
        delete2.setText("Delete");
        back2.setText("Back");
        back3.setText("Back");
        addCust.setText("Add");

    }

    //creating HBoxes
    public void createHBox(){
        button = new HBox(); button2 = new HBox(); button3 = new HBox();
        logoutH = new HBox(); customerBottom = new HBox(50);
        customerTop2 = new HBox(); customerMid2 = new HBox(); customerBottom2 = new HBox(50);
        customerMid = new HBox(); custBot1 = new HBox(); custBot2 = new HBox(); custBot3 = new HBox();

        adminMid = new HBox(50); adminMidN = new HBox(10);
        adminMidP = new HBox(10); adminMidB = new HBox(10);
        adminBottom = new HBox(75); adminTop = new HBox(500);

        adminCustMid = new HBox(50); adminMidUser = new HBox(10);
        adminMidPass = new HBox(10); adminMidB2 = new HBox(10);
        adminCustBot = new HBox(75); adminCustTop = new HBox();
    }

    //cretaing textfields
    public void createTextFields(){
        username = new TextField(); 
        password = new TextField(); 
        name = new TextField(); 
        price = new TextField(); 
        addUsername = new TextField(); 
        addPassword = new TextField();
        errorLabel = new Label("");
        errorLabel1 = new Label("");// Initialize error label
        errorLabel1.setStyle("-fx-text-fill: red;");
        errorLabel.setStyle("-fx-text-fill: red;"); // Set text color to red
    }

    //creating login screen
    public void sceneOneCreation(FlowPane login){
        login.setAlignment(javafx.geometry.Pos.CENTER);
        button.setAlignment(javafx.geometry.Pos.CENTER_RIGHT);
        button.getChildren().addAll(btn);
        login.getChildren().addAll(new Label("Username"), username);
        login.getChildren().addAll(new Label("Password"), password);
        login.getChildren().addAll(button); 
        login.getChildren().add(errorLabel);
    }

    //creating admin screen
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

    //creating customer book screen
    public void sceneThreeCreation(BorderPane custStart,ArrayList<Book> bList){

        custBookList = new TableView<>();
        custWelcome = new Label("Welcome " + dispUsername+" you have: " + dispPoints + " Points. "
                + "Your Status is: " + dispStatus);
        
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
        
        topContainer = new VBox(5); // 5 is spacing between elements
        topContainer.setAlignment(javafx.geometry.Pos.CENTER);
        topContainer.getChildren().addAll(custWelcome, errorLabel1);
    
        customerMid.setAlignment(javafx.geometry.Pos.CENTER);
        customerBottom.setAlignment(javafx.geometry.Pos.CENTER);
    
        custBookList.getColumns().addAll(custCol1, custCol2, custCol3);
        custBot1.getChildren().add(buy);
        custBot2.getChildren().add(redeemBuy);
        custBot3.getChildren().add(logoutCust);
        customerBottom.getChildren().addAll(custBot1, custBot2, custBot3);
        customerMid.getChildren().add(custBookList);
        customerBottom.setPadding(new Insets(10, 0, 10, 0));

        custBookList.setMaxHeight(300);
        custBookList.setPrefWidth(600);
        custBookList.setColumnResizePolicy(adminBookList.CONSTRAINED_RESIZE_POLICY);


        // Use the topContainer instead of just customerTop
        custStart.setTop(topContainer);
        custStart.setBottom(customerBottom);
        custStart.setCenter(customerMid);
    
        //updates table with current books
        custBookList.setItems(FXCollections.observableArrayList(bList));
    }

    //creating customer purchase screen
    public void sceneFourCreation(BorderPane custBuy){
        
        buyText = new VBox();
        custTC = new Label ("Total cost of Books: " + "XX");
        custPrice = new Label ("Total Points: " + "XX " + "Status: " + "XX");

        customerTop2.setAlignment(javafx.geometry.Pos.CENTER);
        customerBottom2.setAlignment(javafx.geometry.Pos.CENTER);
        customerMid2.setAlignment(javafx.geometry.Pos.CENTER);
        buyText.setAlignment(javafx.geometry.Pos.CENTER);
        
        customerTop2.getChildren().add(custTC);
        customerMid2.getChildren().add(custPrice);
        customerBottom2.getChildren().addAll(back3, logout); // <-- Add back3 to bottom
        
        buyText.getChildren().addAll(customerTop2, customerMid2, customerBottom2);

        customerTop2.setPadding(new Insets(10, 0, 10, 0));
        customerMid2.setPadding(new Insets(10, 0, 10, 0));
        customerBottom2.setPadding(new Insets(10, 0, 10, 0));

        custBuy.setCenter(buyText);
    }

    //creating admin book screen
    public void sceneFiveCreation(BorderPane adminBookScreen,ArrayList<Book> bList){

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
        
        adminTop.setAlignment(javafx.geometry.Pos.CENTER);
        adminBottom.setAlignment(javafx.geometry.Pos.CENTER);        
        adminMid.setAlignment(javafx.geometry.Pos.CENTER);  
        
        adminBookList.getColumns().addAll(adminCol1, adminCol2, adminCol3);
        adminMidN.getChildren().addAll(new Label("Book name"), name);
        adminMidP.getChildren().addAll(new Label("Book Price"), price);
        adminMidB.getChildren().add(add);
        adminMid.getChildren().addAll(adminMidN, adminMidP, adminMidB);
        adminBottom.getChildren().addAll(delete, back);
        
        //setting max table display height/width
        adminBookList.setMaxHeight(300);
        adminBookList.setPrefWidth(600);
        adminBookList.setColumnResizePolicy(adminBookList.CONSTRAINED_RESIZE_POLICY);
        
        //padding for Middle and Bottom
        adminMid.setPadding(new Insets(10, 0, 0, 0));
        adminBottom.setPadding(new Insets(0, 0, 10, 0));
        
        adminBookScreen.setCenter(adminMid);
        adminBookScreen.setBottom(adminBottom);

        adminBookList.setItems(FXCollections.observableArrayList(bList));


    }   

    //creating admin customer screen
    public void sceneSixCreation(BorderPane adminCustScreen,ArrayList<Customer> cList){

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

        adminCustTop.setAlignment(javafx.geometry.Pos.CENTER);
        adminCustBot.setAlignment(javafx.geometry.Pos.CENTER);        
        adminCustMid.setAlignment(javafx.geometry.Pos.CENTER);

        customerList.getColumns().addAll(adminCustCol1, adminCustCol2, adminCustCol3, adminCustCol4);
        adminMidUser.getChildren().addAll(new Label("Username"), addUsername);
        adminMidPass.getChildren().addAll(new Label("Password"), addPassword);
        adminMidB2.getChildren().add(addCust);
        adminCustMid.getChildren().addAll(adminMidUser, adminMidPass, adminMidB2);
        adminCustBot.getChildren().addAll(delete2, back2);
        
        //setting max table display height/width
        customerList.setMaxHeight(300);
        customerList.setPrefWidth(600);
        customerList.setColumnResizePolicy(adminBookList.CONSTRAINED_RESIZE_POLICY);

        //padding for Middle and Bottom
        adminCustMid.setPadding(new Insets(10, 0, 0, 0));
        adminCustBot.setPadding(new Insets(0, 0, 10, 0));
        
        adminCustScreen.setCenter(adminCustMid);
        adminCustScreen.setBottom(adminCustBot);

        customerList.setItems(FXCollections.observableArrayList(cList));
    }

    @Override
    public void start(Stage displayScreen) {
        //java stuff
        //loads save data
        bookstore.loadCustomers(cList);
        bookstore.loadBooks(bList);
        
        //Creating Buttons
        createButtons();
        setButtonText();

        //Creating HBoxes and TextFields
        createHBox();
        createTextFields();

        //login Screen
        Label title = new Label("--Welcome to the Bookstore App--");
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
        adminTop.getChildren().add(adminBookList);
        pane5.setTop(adminTop);
        
        sceneSixCreation(pane6,cList);//Admin Customer
        adminCustTop.getChildren().add(customerList);
        pane6.setTop(adminCustTop);
        
        //different scenes 
        Scene loginScreen = new Scene(pane, 300, 250);
        Scene adminScreen = new Scene(pane2, 300, 250);
        Scene customerStartScreen = new Scene(pane3, 400, 350);
        Scene customerCostScreen = new Scene(pane4, 300, 250);
        Scene adminBookScreen = new Scene(pane5,600,380);
        Scene adminCustScreen = new Scene(pane6, 600, 380);

        //setting up the scenes (The important code) 
        displayScreen.setTitle("Bookstore");
        displayScreen.setScene(loginScreen);
        displayScreen.show();
        
        //button functions----------------------------------------------------------------------
        
        //handling login events
        EventHandler<ActionEvent> loginEvent = new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent e){
               
                String user = username.getText();
                String pass = password.getText();
                username.clear(); //clears textfields
                password.clear();
                if(user.equals("admin") && pass.equals("admin")){
                    reset();
                    displayScreen.setScene(adminScreen);
                } else {
                    
                    curUser = bookstore.findAccount(user,pass,cList);
                    if (curUser.getPoints() != -1){
                        updateUser(curUser);
                        custWelcome.setText("Welcome " + dispUsername + " you have: " + dispPoints + " Points. "
                        + "Your Status is: " + dispStatus);
                        custBookList.setItems(FXCollections.observableArrayList(bList));
                        displayScreen.setScene(customerStartScreen);
                        
                    }else{
                         errorLabel.setText("Username or Password is incorrect");       
                                }    
                    
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
                reset();
                adminCustTop.getChildren().clear();
                adminCustTop.getChildren().add(customerList);
                displayScreen.setScene(adminCustScreen);
            }
        });
        
        //LOGGIN OUT
        logout.setOnAction(e -> {
            reset();//resets everything
            clearBookSelections(bList);
            displayScreen.setScene(loginScreen);
                });
        
        logoutAdmin.setOnAction(e -> {
            reset();
            //stores in file when done
            bookstore.storeData(cList,bList);
            //clearBookSelections(bList);
            displayScreen.setScene(loginScreen);});
        
        logoutCust.setOnAction(e -> {
            reset();//resets everything
            clearBookSelections(bList);
            displayScreen.setScene(loginScreen);});

        //admin book screen
        back.setOnAction(e -> {
            clearBookSelections(bList);
            displayScreen.setScene(adminScreen);       
            });
        
        //admin cust screen
        back2.setOnAction(e -> {
            clearCustomerSelections(cList);
            displayScreen.setScene(adminScreen);});
        
        //Buy screen with CAD
        EventHandler<ActionEvent> moneyBuy = new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event) {
                //finds books selected
                purchaseBooks(bList,shoppingCart);
                //totals money needed
                totalCost = bookstore.totalCost(shoppingCart); //calculates total cost
              
                bookstore.checkoutCash(curUser, shoppingCart);
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
        //Finds Books purchased
        purchaseBooks(bList,shoppingCart);
        boolean redeemSuccessful = bookstore.checkoutRedeem(curUser, shoppingCart);
        
        if (redeemSuccessful) {
            // Clear any previous error
            errorLabel1.setText("");
            // Purchase successful using points
            totalCost = 0; // Cost paid by customer is $0
            // Update labels for the results screen
            custTC.setText("Total cost of Books: $0 (Paid with points)");
            // Points and status were already updated within checkoutRedeem
            custPrice.setText("Points Remaining: "+curUser.getPoints()+" Status: "+curUser.getStatus());
            // Proceed to the confirmation screen
            clearBookSelections(bList);
            displayScreen.setScene(customerCostScreen);
        } else {
            errorLabel1.setText("Insufficient points to redeem for the selected books."); 
            shoppingCart.clear();
        }
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
                
                //clearing the textfields
                addUsername.clear();
                addPassword.clear();
                
                //updates customerlist for Admin
                customerList.setItems(FXCollections.observableArrayList(cList));
                adminCustTop.getChildren().clear();
                adminCustTop.getChildren().add(customerList);
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
                
                //clearing textfields
                name.clear();
                price.clear();
                
                //Updates BookList for Admin and Customer
                adminBookList.setItems(FXCollections.observableArrayList(bList));
                custBookList.setItems(FXCollections.observableArrayList(bList));
                adminTop.getChildren().clear();
                adminTop.getChildren().add(adminBookList);
            }
        };
        add.setOnAction(bookAdd);
        
        //Delete Button for Books
        EventHandler<ActionEvent> bookDel = new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event){
                deleteBooks(bList);
                adminBookList.setItems(FXCollections.observableArrayList(bList));
                custBookList.setItems(FXCollections.observableArrayList(bList));
            }
        };
        delete.setOnAction(bookDel); //delete button for Books
        
        //Customer Back Button
        back3.setOnAction(e -> {
            reset();
            errorLabel1.setText("");
            updateUser(curUser);
            custWelcome.setText("Welcome " + dispUsername + " you have: " + dispPoints + " Points. "
                        + "Your Status is: " + dispStatus);
            clearBookSelections(bList);
            displayScreen.setScene(customerStartScreen); // Go back to Customer Start Screen
        });     
        
        //Delete Button for Customers
        EventHandler<ActionEvent> custDel = new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event){
                deleteCustomers(cList);
                customerList.setItems(FXCollections.observableArrayList(cList));
            }
        };
        delete2.setOnAction(custDel);
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
