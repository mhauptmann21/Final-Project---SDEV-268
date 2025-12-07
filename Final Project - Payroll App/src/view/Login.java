package view;

import javafx.geometry.Pos;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.HBox;
import javafx.scene.control.Alert;

public class Login {
    private VBox paneLogin;
    // Create text input fields
    private TextField usernameField = new TextField();
    private PasswordField passwordField = new PasswordField();
    // Create user type choice box
    private ChoiceBox<String> choiceBox = new ChoiceBox<>();
    // Create user type attribute defualts to employee
    private String userType;
    
    public Login() {
        login();
    }

    public VBox getLoginPane() {
        return this.paneLogin;
    }

    /* Create login method */
    private void login() {
        // Create vbox 
        paneLogin = new VBox(10);
        // Set to center alignment
        paneLogin.setAlignment(Pos.CENTER);

        // Create labels
        Label loginLabel = new Label("Login");
        Label usernameLabel = new Label("Username:");   
        Label passwordLabel = new Label("Password:");

        // set fields max width to 300
        usernameField.setMaxWidth(300);
        passwordField.setMaxWidth(300);

   
        // Add admin and employee types to choice box
        choiceBox.getItems().addAll("Employee", "Admin");
        // Set defualt to employee
        choiceBox.setValue("Employee");
        // update userType attribute
        userType = choiceBox.getValue();
        // Add event handler to update userType attribute on selection change   
        choiceBox.setOnAction(event -> {
            userType = choiceBox.getValue();
        });

        // Create login button
        Button loginButton = new Button("Login");
        // Add event handler
        loginButton.setOnAction(event -> {
            handleLogin();
        });

        // Create Vbox for buttons
        VBox buttonBox = new VBox(50);
        // Center align
        buttonBox.setAlignment(Pos.CENTER);
        // Add buttons to hbox
        buttonBox.getChildren().addAll(loginButton);


        // Add labels, text fields, and button to grid
        paneLogin.getChildren().addAll(loginLabel, usernameLabel, usernameField, passwordLabel, passwordField, choiceBox, buttonBox);
    }
    private void handleLogin() {
        // get entered username and password
        String username = usernameField.getText();
        String password = passwordField.getText();

        // validate credentials
        // TODO: Replace with actual validation logic 
        boolean isValid = true; 
    
        // if valid, load appropriate view based on userType
        if (isValid) {
            if (userType.equals("Admin")) {
                App.mainPane.setCenter(App.adminView.getAdminViewPane());
            } else {
                App.mainPane.setCenter(App.employeeView.getEmployeeViewPane());
            } 
        } else {
            // show error popup
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Login Error");
            alert.setHeaderText(null);
            alert.setContentText("Invalid username or password.");
            alert.showAndWait();
        }
    }   
}
