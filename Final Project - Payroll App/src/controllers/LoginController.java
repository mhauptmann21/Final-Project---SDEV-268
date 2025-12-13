package controllers;

import server.LoginDAO;
import server.Employee;
import view.App;
import view.Login;
import javafx.scene.control.Alert;

public class LoginController {

    public static void handleLogin(String username, String password, String userType) {

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

         if ("Admin".equalsIgnoreCase(userType)
                && "Admin".equalsIgnoreCase(emp.jobTitle)) {

            App.mainPane.setCenter(App.adminView.getAdminViewPane());
        } else if ("Employee".equalsIgnoreCase(userType)) {
            App.mainPane.setCenter(App.employeeView.getEmployeeViewPane());
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Login Failed");
            alert.setHeaderText(null);
            alert.setContentText("Wrong user type.");
            alert.showAndWait();
        }

    }
}

