package view;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

public class PayrollGrid {
    private GridPane payrollGridPane = new GridPane();
    private Label hoursField = new Label();
    private Label ptoField = new Label();
    private Label grossField = new Label();
    private Label netField = new Label();
    
    public PayrollGrid() {
        payrollGrid();
    }

    public GridPane getPayrollGridPane() {
        return this.payrollGridPane;
    }

    private void payrollGrid() {
        //labels
        Label hoursLabel = new Label("Hours: ");
        Label ptoLabel = new Label("PTO: ");
        Label grossLabel = new Label("Gross Pay: ");
        Label netLabel = new Label("Net Pay: ");

        payrollGridPane.setHgap(50);
        payrollGridPane.setVgap(10);
        payrollGridPane.setPadding(new Insets(25, 25, 25, 25));

        payrollGridPane.add(hoursLabel, 0, 0);
        payrollGridPane.add(hoursField, 1, 0);
        payrollGridPane.add(ptoLabel, 0, 1);
        payrollGridPane.add(ptoField, 1, 1);
        payrollGridPane.add(grossLabel, 0, 2);
        payrollGridPane.add(grossField, 1, 2);
        payrollGridPane.add(netLabel, 0, 3);
        payrollGridPane.add(netField, 1, 3);
    }     

    public void updateTotals(double hours, double pto, double gross, double net) {
        hoursField.setText(String.format("%.2f", hours));
        ptoField.setText(String.format("%.2f", pto));
        grossField.setText("$" + String.format("%.2f", gross));
        netField.setText("$" + String.format("%.2f", net));
    }

}

