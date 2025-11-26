package view;

import javafx.geometry.Pos;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;

public class Login {
    private VBox paneLogin;
    
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

        // Create text input fields
        TextField usernameField = new TextField();
        PasswordField passwordField = new PasswordField();

        // set fields max width to 300
        usernameField.setMaxWidth(300);
        passwordField.setMaxWidth(300);

        // Create user type choice box
        ChoiceBox<String> choiceBox = new ChoiceBox<>();
        // Add admin and employee types
        choiceBox.getItems().addAll("Employee", "Admin");
        // Set defualt to employee
        choiceBox.setValue("Employee");
        // Add event handler
        choiceBox.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            // add code to handle differences between types
        });

        // Create login button
        Button loginButton = new Button("Login");
        // Add event handler
        loginButton.setOnAction(event -> {
            String username = usernameField.getText();
            String password = passwordField.getText();

            // replace with code to handle valid and invailid entry
        });

        // Add labels, text fields, and button to grid
        paneLogin.getChildren().addAll(loginLabel, usernameLabel, usernameField, passwordLabel, passwordField, choiceBox, loginButton);
    }
}
