package controllers;

import view.App;

import javafx.scene.control.Alert;
import security.SecurityModule;
import server.EmployeeDAO;

public class RegisterController {

    public static void handleRegister(
            String firstName,
            String lastName,
            String email,
            String username,
            String password
    ) {

        if (firstName.isEmpty() || lastName.isEmpty() ||
            email.isEmpty() || username.isEmpty() ||
            password.isEmpty()) {

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Registration Error");
            alert.setHeaderText(null);
            alert.setContentText("All fields must be filled.");
            alert.showAndWait();
            return;
        }

        boolean success = EmployeeDAO.insertNewEmployee(
                firstName, lastName, email, username, password
        );

        if (success) {

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setHeaderText(null);
            alert.setContentText("Account created! You may now login.");
            alert.showAndWait();

            App.mainPane.setCenter(App.register.getRegisterPane());
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Registration Error");
            alert.setHeaderText(null);
            alert.setContentText("Username already exists or database error.");
            alert.showAndWait();
        }
    }
}
