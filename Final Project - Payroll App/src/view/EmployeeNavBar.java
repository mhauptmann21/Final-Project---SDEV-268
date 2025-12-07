package view;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

public class EmployeeNavBar {
            private VBox employeeNavBar;
    
    public EmployeeNavBar() {
        employeeNavBar();
    }

    public VBox getEmployeeNavBar() {
        return this.employeeNavBar;
    }

    /* Create navBar method */
    private void employeeNavBar() {
        // Create vbox 
        employeeNavBar = new VBox(40);
        // Set to center alignment
        employeeNavBar.setAlignment(Pos.CENTER);

        // Create buttons
        Button timeBTN = new Button("Time Card");
        Button paycheckBTN = new Button("Paycheck");

        // Set buttons min width
        timeBTN.setMinWidth(150);
        paycheckBTN.setMinWidth(150);

        // Create button event handlers
        timeBTN.setOnAction(event -> {
            App.employeeView.getEmployeeViewPane().setCenter(new Time().getTimePane());
        });
        /* 
        paycheckBTN.setOnAction(event -> {
            App.employeeView.getEmployeeViewPane().setCenter();// update with paycheck pane
        });
        */

        // Add to vbox
        employeeNavBar.getChildren().addAll(timeBTN, paycheckBTN);
    }
}
