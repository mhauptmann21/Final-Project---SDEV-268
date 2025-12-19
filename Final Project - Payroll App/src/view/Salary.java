package view;

import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import server.Employee;
import server.SalaryDAO;
import server.SalaryRecord;

public class Salary {

    private VBox paneSalary;
    private Label salaryLabel = new Label("Employee Information");

    private TextField idField = new TextField();
    private TextField JobTypeField = new TextField();
    private TextField departmentField = new TextField();
    private TextField firstField = new TextField();
    private TextField lastField = new TextField();
    private TextField statusField = new TextField();
    private TextField hireDateField = new TextField();
    private TextField salaryTypeField = new TextField();
    private TextField baseSalaryField = new TextField();
    private TextField medicalField = new TextField();
    private TextField dependentsField = new TextField();

    private Employee currentEmployee;

    public Salary() {
        buildPane();
    }

    public VBox getSalaryPane() {
        return paneSalary;
    }

    /*  UI BUILD  */

    public void buildPane() {
        paneSalary = new VBox(10);
        paneSalary.setAlignment(Pos.CENTER);
        paneSalary.setFillWidth(false);

        HBox idBox = fieldRow("Employee ID:", idField);
        HBox jobTypeBox = fieldRow("Job Type:", JobTypeField);
        HBox deptBox = fieldRow("Department:", departmentField);
        HBox firstBox = fieldRow("First Name:", firstField);
        HBox lastBox = fieldRow("Last Name:", lastField);
        HBox statusBox = fieldRow("Status:", statusField);
        HBox hireDateBox = fieldRow("Hire Date:", hireDateField);
        HBox salaryTypeBox = fieldRow("Salary Type:", salaryTypeField);
        HBox baseSalaryBox = fieldRow("Base Salary:", baseSalaryField);
        HBox medicalBox = fieldRow("Medical:", medicalField);
        HBox dependentsyBox = fieldRow("Dependents:", dependentsField);

        Button updateButton = new Button("Update");

        updateButton.setOnAction(e -> handleUpdate());

        HBox buttonBox = new HBox(30, updateButton);
        buttonBox.setAlignment(Pos.CENTER);

        paneSalary.getChildren().addAll(
                salaryLabel,
                idBox,
                jobTypeBox,
                deptBox,
                firstBox,
                lastBox,
                statusBox,
                hireDateBox,
                salaryTypeBox,
                baseSalaryBox,
                medicalBox,
                dependentsyBox,
                buttonBox
        );

    }

    public void setSalary(Employee emp) {
        if (emp == null) {
            clearFields();
            return;
        }

        idField.setText(String.valueOf(emp.employeeId));
        JobTypeField.setText(emp.jobTitle);
        departmentField.setText(emp.department);
        firstField.setText(emp.firstName);
        lastField.setText(emp.lastName);
        statusField.setText(emp.status);
        hireDateField.setText(emp.dateHired);
        salaryTypeField.setText(emp.salaryType);
        baseSalaryField.setText(String.valueOf(emp.baseSalary));
        medicalField.setText(emp.medical);
        dependentsField.setText(String.valueOf(emp.dependents));
    }

    private HBox fieldRow(String labelText, TextField field) {
        Label label = new Label(labelText);
        label.setMinWidth(120);
        field.setMaxWidth(250);
        return new HBox(10, label, field);
    }

    /* DATA HANDLING */

    public void displayEmployee(Employee emp) {
        clearFields();
        currentEmployee = emp;

        if (emp == null) {
            salaryLabel.setText("Salary Information");
            return;
        }

        salaryLabel.setText("Employee: " + emp.firstName + " " + emp.lastName);
        idField.setText(String.valueOf(emp.employeeId));
        JobTypeField.setText(emp.jobTitle);
        departmentField.setText(emp.department);
        firstField.setText(emp.firstName);
        lastField.setText(emp.lastName);
        statusField.setText(emp.status);
        hireDateField.setText(emp.hireDate);
        salaryTypeField.setText(emp.salaryType);
        baseSalaryField.setText(String.valueOf(emp.baseSalary));
        medicalField.setText(emp.medical);
        dependentsField.setText(String.valueOf(emp.dependents));
    }

    private Employee buildEmployeeFromFields() {
        Employee emp = new Employee();

        try {
            emp.employeeId = Integer.parseInt(idField.getText());
        } catch (NumberFormatException e) {
            showError("Employee ID must be a number.");
            return null;
        }

        emp.department = departmentField.getText();
        emp.jobTitle = JobTypeField.getText();
        emp.firstName = firstField.getText();
        emp.lastName = lastField.getText();
        emp.status = statusField.getText();
        emp.hireDate = hireDateField.getText();
        emp.salaryType = salaryTypeField.getText();
        emp.baseSalary = Double.parseDouble(baseSalaryField.getText());
        emp.medical = medicalField.getText();
        emp.dependents = Integer.parseInt(dependentsField.getText());

        return emp;
    }

    private void clearFields() {
        JobTypeField.clear();
        departmentField.clear();
        firstField.clear();
        lastField.clear();
        statusField.clear();
        hireDateField.clear();
        salaryTypeField.clear();
        baseSalaryField.clear();
        medicalField.clear();
        dependentsField.clear();
    }

    /*  ACTIONS  */

    private void handleUpdate() {
        if (currentEmployee == null) {
            showError("No employee selected.");
            return;
        }

        Employee updated = buildEmployeeFromFields();
        if (updated == null) return;

        SalaryDAO.update(updated);
    }

    private void showError(String msg) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText(null);
        alert.setContentText(msg);
        alert.showAndWait();
    }
}