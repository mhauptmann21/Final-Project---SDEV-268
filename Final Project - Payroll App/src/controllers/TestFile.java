package controllers;

import server.LoginDAO;
import server.Employee;
import view.App;
import view.Login;
import javafx.concurrent.Task;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressIndicator;

public class TestFile {
    public static Employee emp = null;

    public static void handleLogin(String username, String password, String userType) {

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

    public static void connectToServer(String username, String password, String userType) {
    // Show a progress indicator (the spinning wheel)
    ProgressIndicator progressIndicator = new ProgressIndicator();

    Task<Employee> serverTask = new Task<>() {
            @Override
            protected Employee call() throws Exception {
                // !! This runs in a background thread !!
                // Perform your network connection and data retrieval here
                // e.g., Socket connection, HTTP request, etc.
                
                LoginDAO loginDAO = new LoginDAO();
                Employee emp = loginDAO.login(username, password);
                
                return emp;
            }
        };

        serverTask.setOnSucceeded(event -> {
            // !! This runs back on the JavaFX Application Thread !!
            progressIndicator.setVisible(false); // Hide indicator
            emp = serverTask.getValue();
            handleLogin(username, password, userType);
            
        });

        serverTask.setOnFailed(event -> {
            // !! This runs back on the JavaFX Application Thread !!
            progressIndicator.setVisible(false); // Hide indicator
            Throwable e = serverTask.getException();
            Alert alert = new Alert(Alert.AlertType.ERROR, "Connection Failed: " + e.getMessage());
            alert.showAndWait();
            e.printStackTrace();
        });

        // Start the task in a new background thread
        Thread thread = new Thread(serverTask);
        thread.setDaemon(true); // Daemon threads don't prevent the application from exiting
        thread.start();
    }
}