package view;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class App extends Application {

    public static BorderPane mainPane = new BorderPane();
    public static Login login = new Login();
    public static AdminView adminView;
    public static EmployeeView employeeView = new EmployeeView();
    public static NoEmployeeAlert noEmployeeAlert = new NoEmployeeAlert();

    @Override
    public void start(Stage primaryStage) {

        adminView = new AdminView(); // IMPORTANT!

        mainPane.setPadding(new Insets(10));
        mainPane.setCenter(login.getLoginPane());

        Button exitButton = new Exit().getExitButton();
        exitButton.setMinWidth(150);
        mainPane.setBottom(exitButton);

        Scene scene = new Scene(mainPane);
        scene.getStylesheets().add(getClass().getResource("styles.css").toExternalForm());

        primaryStage.setTitle("Payroll Application");
        primaryStage.setScene(scene);
        primaryStage.setMinHeight(775);
        primaryStage.setMinWidth(900);
        primaryStage.centerOnScreen();
        primaryStage.show();
    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}

