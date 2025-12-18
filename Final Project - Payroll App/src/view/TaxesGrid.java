package view;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

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
}
