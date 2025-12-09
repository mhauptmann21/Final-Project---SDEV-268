package view;

import controllers.RegisterController;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;

public class Register {

    private VBox paneRegister;

    private TextField firstNameField = new TextField();
    private TextField lastNameField = new TextField();
    private TextField emailField = new TextField();
    private TextField usernameField = new TextField();
    private PasswordField passwordField = new PasswordField();

    public Register() {
        createRegisterPane();
    }

    public VBox getRegisterPane() {
        return paneRegister;
    }

    private void createRegisterPane() {

        paneRegister = new VBox(10);
        paneRegister.setAlignment(Pos.CENTER);

        Label title = new Label("Create Account");

        firstNameField.setPromptText("First Name");
        lastNameField.setPromptText("Last Name");
        emailField.setPromptText("Email");
        usernameField.setPromptText("Username");
        passwordField.setPromptText("Password");

        firstNameField.setMaxWidth(300);
        lastNameField.setMaxWidth(300);
        emailField.setMaxWidth(300);
        usernameField.setMaxWidth(300);
        passwordField.setMaxWidth(300);

        Button registerButton = new Button("Register");
        registerButton.setOnAction(e ->
            RegisterController.handleRegister(
                firstNameField.getText(),
                lastNameField.getText(),
                emailField.getText(),
                usernameField.getText(),
                passwordField.getText()
            )
        );

        Button loginRedirectBtn = new Button("Already have an account? Login");
        loginRedirectBtn.setOnAction(e -> 
            App.mainPane.setCenter(App.login.getLoginPane())
        );

        paneRegister.getChildren().addAll(
            title,
            firstNameField,
            lastNameField,
            emailField,
            usernameField,
            passwordField,
            registerButton,
            loginRedirectBtn
        );
    }
}
