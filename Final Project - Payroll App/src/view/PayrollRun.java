package view;

import controllers.PayrollRunController;
import server.Employee;
import javafx.geometry.Pos;
import javafx.scene.layout.VBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import java.time.LocalDate;
import java.time.DayOfWeek;

public class PayrollRun {
    
    private VBox pane;
    
    public PayrollRun() {
        createUI();
    }
    
    public VBox getPane() {
        return pane;
    }

    private void createUI() {
        pane = new VBox(20);
        pane.setAlignment(Pos.CENTER);

        Label title = new Label("Run Payroll");
        
        TextField employeeIdField = new TextField();
        employeeIdField.setPromptText("Enter Employee ID");

        Button findEmployeeBtn = new Button("Load Employee");
        Label employeeInfo = new Label("");

        // WEEK RANGE
        TextField weekStartField = new TextField();
        weekStartField.setPromptText("Period Start (YYYY-MM-DD)");

        TextField weekEndField = new TextField();
        weekEndField.setPromptText("Period End (YYYY-MM-DD)");

        Label resultsLabel = new Label("");

        Button payrollBtn = new Button("Run Payroll");

        // Load employee info
        findEmployeeBtn.setOnAction(e -> {
            try {
                int id = Integer.parseInt(employeeIdField.getText());
                Employee emp = PayrollRunController.getEmployee(id);

                if (emp == null) {
                    employeeInfo.setText("Employee not found.");
                } else {
                    employeeInfo.setText(emp.firstName + " " + emp.lastName +
                                         " | " + emp.department + " | " + emp.jobTitle);

                    // Auto-fill week range for convenience
                    String[] week = getCurrentWeekRange();
                    weekStartField.setText(week[0]);
                    weekEndField.setText(week[1]);
                }

            } catch (Exception ex) {
                employeeInfo.setText("Invalid ID.");
            }
        });

        // Run payroll
        payrollBtn.setOnAction(e -> {
            try {
                int id = Integer.parseInt(employeeIdField.getText());
                String start = weekStartField.getText();
                String end = weekEndField.getText();

                PayrollRunController.runPayroll(id, start, end);

                resultsLabel.setText("Payroll Successfully Calculated and Saved!\nTime Entries Locked.");

            } catch (Exception ex) {
                resultsLabel.setText("Error running payroll.");
            }
        });

        pane.getChildren().addAll(
            title,
            employeeIdField,
            findEmployeeBtn,
            employeeInfo,
            weekStartField,
            weekEndField,
            payrollBtn,
            resultsLabel
        );
    }


    // Helper to auto-fill current week
    private String[] getCurrentWeekRange() {
        LocalDate today = LocalDate.now();
        LocalDate sunday = today.with(DayOfWeek.SUNDAY);
        LocalDate saturday = today.with(DayOfWeek.SATURDAY);

        return new String[]{
            sunday.toString(),
            saturday.toString()
        };
    }
}

