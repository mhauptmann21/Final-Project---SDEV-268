package controllers;

import server.LoginDAO;
import server.Employee;
import view.App;
import javafx.scene.control.Alert;

public class LoginController {

    public static void handleLogin(String username, String password) {

        LoginDAO loginDAO = new LoginDAO();
        Employee emp = loginDAO.login(username, password);

        if (emp == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Login Failed");
            alert.setHeaderText(null);
            alert.setContentText("Invalid username or password.");
            alert.showAndWait();
            return;
        }

        SessionController.setCurrentUser(emp);

        // Admin check
        if (emp.jobTitle != null && emp.jobTitle.equalsIgnoreCase("Admin")) {
            App.mainPane.setCenter(App.adminView.getAdminViewPane());
        } else {
            App.mainPane.setCenter(App.employeeView.getEmployeeViewPane());
        }

    }
}

