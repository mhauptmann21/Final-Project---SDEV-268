package view;

import javafx.geometry.Pos;
import javafx.scene.layout.VBox;
import javafx.scene.control.Button;

public class AdminNavBar {
        private VBox adminNavBar;
    
    public AdminNavBar() {
        adminNavBar();
    }

    public VBox getAdminNavBar() {
        return this.adminNavBar;
    }

    /* Create navBar method */
    private void adminNavBar() {
        // Create vbox 
        adminNavBar = new VBox(40);
        // Set to center alignment
        adminNavBar.setAlignment(Pos.CENTER);

        // Create buttons
        Button employeeBTN = new Button("Employee Demographics");
        Button salaryBTN = new Button("Salary Data");
        Button payrollBTN = new Button("Payroll");
        Button timeBTN = new Button("Time Cards");
        Button reportsBTN = new Button("Reports");
        Button infoBTN = new Button("Application Information");

        // Set buttons min width
        employeeBTN.setMinWidth(150);
        salaryBTN.setMinWidth(150);
        payrollBTN.setMinWidth(150);
        timeBTN.setMinWidth(150);
        reportsBTN.setMinWidth(150);
        infoBTN.setMinWidth(150);

        // Create button event handlers
        employeeBTN.setOnAction(event -> {
            App.adminView.getVboxCenter().getChildren().setAll(App.adminView.getEmployeePane().getEmployeePane());
        });
        salaryBTN.setOnAction(event -> {
            App.adminView.getVboxCenter().getChildren().setAll(App.adminView.getSalaryPage().getSalaryPane());
        });
        // add payroll button event handler
        payrollBTN.setOnAction(event -> {
            App.adminView.getVboxCenter().getChildren().setAll(App.adminView.getPayrollPage().getPayrollPane());
        });
        timeBTN.setOnAction(event -> {
            App.adminView.getVboxCenter().getChildren().setAll(App.adminView.getTimePage().getTimePane());
        });
        // add reports button event handler
        reportsBTN.setOnAction(event -> { 
            App.adminView.getVboxCenter().getChildren().setAll(App.adminView.getReportPage().getReportPane());  
        });
        // add info button event handler
        infoBTN.setOnAction(event -> {
            App.adminView.getVboxCenter().getChildren().setAll(App.adminView.getInfoPage().getInfoPane());
        });

        // Add to vbox
        adminNavBar.getChildren().addAll(employeeBTN, salaryBTN, payrollBTN, timeBTN, reportsBTN, infoBTN);
    }
}
