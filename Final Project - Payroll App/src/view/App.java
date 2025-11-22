package view;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class App extends Application {
    // Create GUI
    public static BorderPane mainPane = new BorderPane();

    // Override the start method in the Application class
    @Override
    public void start(Stage primaryStage) {
        // Add padding to main pain
        mainPane.setPadding(new Insets(20));
      

        // Create hbox to hold center items for border pane
        HBox hboxCenter = new HBox(30);

        // Add hbox to border pane
        mainPane.setCenter(hboxCenter);

        // Create a scene and place it in the stage
        Scene scene = new Scene(mainPane);
        // Set the stage title
        primaryStage.setTitle("Payroll Application"); 
        // Place the scene in the stage
        primaryStage.setScene(scene);
        // Set primary stage min size
        primaryStage.setMinHeight(775);
        primaryStage.setMinWidth(900);
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
