package view;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

public class Paycheck {
    private VBox panePaycheck;
    // vbox to hold calculated paycheck
    private VBox paycheckVBox = new VBox(10);
    // calcualte button
    Button calculateBTN = new Button("Calcualate Paycheck");
    
    public Paycheck() {
        paycheck();
    }

    public VBox getPaycheckPane() {
        return this.panePaycheck;
    }

    /* Create paycheck method */
    private void paycheck() {
        // Create vbox 
        panePaycheck = new VBox(10);
        // Set to center alignment
        panePaycheck.setAlignment(Pos.CENTER);

        // calculate button handler
        calculateBTN.setOnAction(e -> {
            handleCalculate();
        });

        //add button
        panePaycheck.getChildren().addAll(calculateBTN);

    }

    private void handleCalculate() {
        //TODO: calcualte current pay without locking
        //TODO: create elements to hold the paycheck info

        //update pane to show the paycheck
        panePaycheck.getChildren().setAll(paycheckVBox);

    }

    public void clearPrevious() {
        // show button
        panePaycheck.getChildren().setAll(calculateBTN);

        // clear paycheck vbox
        paycheckVBox.getChildren().clear();
    }
}
