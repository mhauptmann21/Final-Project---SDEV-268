package view;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class Payroll {
    private VBox panePayroll;
    
    public Payroll() {
        payroll();
    }

    public VBox getPayrollPane() {
        return this.panePayroll;
    }

    /* Create payroll method */
    private void payroll() {
        // Create vbox 
        panePayroll = new VBox(30);
        // Set to center alignment
        panePayroll.setAlignment(Pos.CENTER);

        // create label
        Label payrollLabel = new Label("Run Payroll for all Employees");
        // create buttons
        Button calculatePayrollBTN = new Button("Calculate Payroll");
        //create event handler
        calculatePayrollBTN.setOnAction(e -> { 
            calculatePayroll();
        });

        //add to vbox
        panePayroll.getChildren().addAll(payrollLabel, calculatePayrollBTN);

    }


    private void calculatePayroll() {
        //TODO: lock all employee timecards
        //TODO: calculate gross payroll
        //TODO: calculate net payroll

    }
}
