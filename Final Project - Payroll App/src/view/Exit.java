package view;

import javafx.scene.control.Button;

public class Exit {
        private Button exitButton;
    
    public Exit() {
        exit();
    }

    public Button getExitButton() {
        return this.exitButton;
    }

    /* Create exit method */
    private void exit() {
        // Create button
        exitButton = new Button("Exit");
        // Add event handler
        exitButton.setOnAction(event -> {
            System.exit(0);
        }); 
    }
}
