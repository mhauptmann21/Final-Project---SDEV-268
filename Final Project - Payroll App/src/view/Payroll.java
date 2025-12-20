package view;

import java.util.List;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import server.Employee;
import server.PayrollCalculator;

public class Payroll {
    private VBox panePayroll;
    private PayrollGrid payrollGrid = new PayrollGrid();
    private TaxesGrid taxesGrid = new TaxesGrid();

    private List<Employee> employees;

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }
    
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
        Button printBTN = new Button("Approve and Print Report");

        //create event handlers
        calculatePayrollBTN.setOnAction(e -> { 
            calculatePayroll();
        });
        printBTN.setOnAction(e -> {
            printReport();
        });
;
        //add to vbox
        panePayroll.getChildren().addAll(payrollLabel, calculatePayrollBTN, taxesGrid.getTaxesGridPane(), payrollGrid.getPayrollGridPane(), printBTN);

    }


    public void calculatePayroll() {

        double totalHours = 0;
        double totalPTO = 0;
        double totalGross = 0;
        double totalNet = 0;

        for (Employee emp : employees) {

            double grossPay = PayrollCalculator.calculateGrossPay(
                    emp.getPayType(),
                    emp.getBaseSalary(),
                    emp.getHourlyRate(),
                    emp.getWeeklyHours()
            );

            double netPay = PayrollCalculator.calculateNetPay(
                    grossPay,
                    emp.getMedicalType(),
                    emp.getDependents()
            );

            emp.setGrossPay(grossPay);
            emp.setNetPay(netPay);

            totalGross += grossPay;
            totalNet += netPay;
        }

        // Update grids with calculated values
        payrollGrid.updateTotals(totalHours, totalPTO, totalGross, totalNet);
        taxesGrid.updateTotals(employees);
    }

    private void printReport() {

        if (employees == null || employees.isEmpty()) {
            System.out.println("No payroll to print");
            return;
        }

        System.out.println("===== PAYROLL REPORT =====");

        for (Employee emp : employees) {
            System.out.println(
                emp.getFirstName() + " " + emp.getLastName() +
                " | Gross: $" + String.format("%.2f", emp.getGrossPay()) +
                " | Net: $" + String.format("%.2f", emp.getNetPay())
            );
        }

        System.out.println("==========================");
    }


}
