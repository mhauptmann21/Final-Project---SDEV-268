package view;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class App extends Application {
    // Create GUI
    public static BorderPane mainPane = new BorderPane();
    public static Login login = new Login();
    public static AdminView adminView = new AdminView();
    public static EmployeeView employeeView = new EmployeeView();
    public static NoEmployeeAlert noEmployeeAlert = new NoEmployeeAlert();

    // Override the start method in the Application class
    @Override
    public void start(Stage primaryStage) {
        // Add padding to main pain
        mainPane.setPadding(new Insets(10));
        
        // Add login to main pane
        mainPane.setCenter(login.getLoginPane());

        // Create exit button
        Button exitButton = new Exit().getExitButton();
        // Set min width
        exitButton.setMinWidth(150);
        // Add exit button to bottom left of main pane
        mainPane.setBottom(exitButton);
        BorderPane.setAlignment(exitButton, javafx.geometry.Pos.CENTER_LEFT);

        // Create a scene and place it in the stage
        Scene scene = new Scene(mainPane);
        // Add css to scene
        scene.getStylesheets().add(getClass().getResource("styles.css").toExternalForm());
        // Set the stage title
        primaryStage.setTitle("Payroll Application"); 
        // Place the scene in the stage
        primaryStage.setScene(scene);
        // Set primary stage min size
        primaryStage.setMinHeight(775);
        primaryStage.setMinWidth(900);
        // Center on screen
        primaryStage.centerOnScreen();
        // Display the stage
        primaryStage.show();
    }

    /**
    * The main method is only needed for the IDE with limited JavaFX support. Not needed running from the command line.
    */
    public static void main(String[] args) {
        Application.launch(args);
  }
}
