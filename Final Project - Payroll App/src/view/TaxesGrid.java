package view;

import java.util.List;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import server.Employee;

public class TaxesGrid {
    private GridPane taxesGridPane = new GridPane();
      // text field for employee tax data
    private Label employeeMedicalField = new Label();
    private Label employeeDependentField = new Label();
    private Label employeeStateTaxesField = new Label();
    private Label employeeFederalTaxesField = new Label();
    private Label employeeMedicareField = new Label();
    private Label employeeSocSecField = new Label();

    // text field for employer tax data
    private Label employerMedicalField = new Label();
    private Label employerDependentField = new Label();
    private Label employerStateTaxesField= new Label();
    private Label employerFederalTaxesField = new Label();
    private Label employerMedicareField = new Label();
    private Label employerSocSecField = new Label();
    
    public TaxesGrid() {
        taxesGrid();
    }

    public GridPane getTaxesGridPane() {
        return this.taxesGridPane;
    }

    private String format(double value) {
        return String.format("%.2f", value);
    }


    private void taxesGrid() {
       
        // labels for paycheck data
        Label medicalLabel = new Label("Medical Cost: ");
        Label dependentLabel = new Label("Dependent Stipend: ");
        Label stateTaxesLabel = new Label("State Taxes: ");
        Label federalTaxesLabel = new Label("Federal Taxes: ");
        Label medicareLabel = new Label("Medicare Tax: ");
        Label socSecLabel = new Label("Social Security Tax: ");
        Label employeeCostLabel = new Label("Employee Cost");
        Label employerCostLabel = new Label("Employer Cost");

        // taxes grid
        taxesGridPane.setHgap(50);
        taxesGridPane.setVgap(10);
        taxesGridPane.setPadding(new Insets(25, 25, 25, 25));
        taxesGridPane.add(employeeCostLabel, 1, 0);
        taxesGridPane.add(employerCostLabel, 2, 0);
        taxesGridPane.add(medicalLabel, 0, 1);
        taxesGridPane.add(employeeMedicalField, 1, 1);
        taxesGridPane.add(employerMedicalField, 2, 1);
        taxesGridPane.add(dependentLabel, 0, 2);
        taxesGridPane.add(employeeDependentField, 1, 2);
        taxesGridPane.add(employerDependentField, 2, 2);
        taxesGridPane.add(stateTaxesLabel, 0, 3);
        taxesGridPane.add(employeeStateTaxesField, 1, 3);
        taxesGridPane.add(employerStateTaxesField, 2, 3);
        taxesGridPane.add(federalTaxesLabel, 0, 4);
        taxesGridPane.add(employeeFederalTaxesField, 1, 4);
        taxesGridPane.add(employerFederalTaxesField, 2, 4);
        taxesGridPane.add(medicareLabel, 0, 5);
        taxesGridPane.add(employeeMedicareField, 1, 5);
        taxesGridPane.add(employerMedicareField, 2, 5);
        taxesGridPane.add(socSecLabel, 0, 6);
        taxesGridPane.add(employeeSocSecField, 1, 6);
        taxesGridPane.add(employerSocSecField, 2, 6);
    }

    public void updateTotals(List<Employee> employees) {

        double employeeFederal = 0;
        double employeeState = 0;
        double employeeMedicare = 0;
        double employeeSS = 0;

        double employerFederal = 0;
        double employerState = 0;
        double employerMedicare = 0;
        double employerSS = 0;

        for (Employee emp : employees) {
            double gross = emp.getGrossPay();

            // Employee deductions
            employeeFederal += gross * 0.12;
            employeeState += gross * 0.05;
            employeeMedicare += gross * 0.0145;
            employeeSS += gross * 0.062;

            // Employer contributions (usually same rates, except no federal)
            employerState += gross * 0.05;
            employerMedicare += gross * 0.0145;
            employerSS += gross * 0.062;
        }

        // Employee side
        employeeFederalTaxesField.setText("$" + format(employeeFederal));
        employeeStateTaxesField.setText("$" + format(employeeState));
        employeeMedicareField.setText("$" + format(employeeMedicare));
        employeeSocSecField.setText("$" + format(employeeSS));

        // Employer side
        employerFederalTaxesField.setText("$0.00"); // employers don't pay federal income tax
        employerStateTaxesField.setText("$" + format(employerState));
        employerMedicareField.setText("$" + format(employerMedicare));
        employerSocSecField.setText("$" + format(employerSS));

        // Medical & dependent (placeholder logic)
        employeeMedicalField.setText("$0.00");
        employerMedicalField.setText("$0.00");
        employeeDependentField.setText("$0.00");
        employerDependentField.setText("$0.00");
    }


}
