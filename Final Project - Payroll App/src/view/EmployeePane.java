package view;

import controllers.EmployeeController;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import server.Employee;

public class EmployeePane {

    private VBox paneEmployee;
    private Label employeeLabel = new Label("Employee Information");

    private ImageView imgView = new ImageView();

    private TextField idField = new TextField();
    private TextField departmentField = new TextField();
    private TextField titleField = new TextField();
    private TextField firstField = new TextField();
    private TextField lastField = new TextField();
    private TextField statusField = new TextField();
    private TextField dobField = new TextField();
    private TextField payTypeField = new TextField();
    private TextField emailField = new TextField();
    private TextField usernameField = new TextField();
    private TextField passwordField = new TextField();

    private Employee currentEmployee;

    public EmployeePane() {
        buildPane();
    }

    public VBox getEmployeePane() {
        return paneEmployee;
    }

    /*  UI BUILD  */

    public void buildPane() {
        paneEmployee = new VBox(10);
        paneEmployee.setAlignment(Pos.CENTER);
        paneEmployee.setFillWidth(false);

        Image defaultPhoto = new Image(
                getClass().getResourceAsStream("/images/stockEmployee.jpg")
        );
        imgView.setImage(defaultPhoto);
        imgView.setFitWidth(60);
        imgView.setPreserveRatio(true);

        HBox idBox = fieldRow("Employee ID:", idField);
        HBox deptBox = fieldRow("Department:", departmentField);
        HBox titleBox = fieldRow("Job Title:", titleField);
        HBox firstBox = fieldRow("First Name:", firstField);
        HBox lastBox = fieldRow("Last Name:", lastField);
        HBox statusBox = fieldRow("Status:", statusField);
        HBox dobBox = fieldRow("Birth Date:", dobField);
        HBox payTypeBox = fieldRow("Pay Type:", payTypeField);
        HBox emailBox = fieldRow("Email:", emailField);
        HBox usernameBox = fieldRow("Username:", usernameField);
        HBox passwordBox = fieldRow("Password:", passwordField);

        VBox photoBox = new VBox(5, imgView);
        photoBox.setAlignment(Pos.CENTER);

        Button addButton = new Button("Add");
        Button updateButton = new Button("Update");
        Button deleteButton = new Button("Delete");

        addButton.setOnAction(e -> handleAdd());
        updateButton.setOnAction(e -> handleUpdate());
        deleteButton.setOnAction(e -> handleDelete());

        HBox buttonBox = new HBox(30, addButton, updateButton, deleteButton);
        buttonBox.setAlignment(Pos.CENTER);

        paneEmployee.getChildren().addAll(
                employeeLabel,
                idBox,
                deptBox,
                titleBox,
                firstBox,
                lastBox,
                statusBox,
                dobBox,
                payTypeBox,
                emailBox,
                usernameBox,
                passwordBox,
                photoBox,
                buttonBox
        );
    }

    public void setEmployee(Employee emp) {
        if (emp == null) {
            clearFields();
            return;
        }

        idField.setText(String.valueOf(emp.employeeId));
        departmentField.setText(emp.department);
        titleField.setText(emp.jobTitle);
        firstField.setText(emp.firstName);
        lastField.setText(emp.lastName);
        statusField.setText(emp.status);
        dobField.setText(emp.dateOfBirth);
        payTypeField.setText(emp.payType);
        emailField.setText(emp.email);
        usernameField.setText(emp.username);
        passwordField.setText(emp.password);

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
            employeeLabel.setText("Employee Information");
            return;
        }

        employeeLabel.setText("Employee: " + emp.firstName + " " + emp.lastName);
        idField.setText(String.valueOf(emp.employeeId));
        departmentField.setText(emp.department);
        titleField.setText(emp.jobTitle);
        firstField.setText(emp.firstName);
        lastField.setText(emp.lastName);
        statusField.setText(emp.status);
        dobField.setText(emp.dateOfBirth);
        payTypeField.setText(emp.payType);
        emailField.setText(emp.email);
        usernameField.setText(emp.username);
        passwordField.setText(emp.password);
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
        emp.jobTitle = titleField.getText();
        emp.firstName = firstField.getText();
        emp.lastName = lastField.getText();
        emp.status = statusField.getText();
        emp.dateOfBirth = dobField.getText();
        emp.payType = payTypeField.getText();
        emp.email = emailField.getText();
        emp.username = usernameField.getText();
        emp.password = passwordField.getText();
        

        return emp;
    }

    private void clearFields() {
        idField.clear();
        departmentField.clear();
        titleField.clear();
        firstField.clear();
        lastField.clear();
        statusField.clear();
        dobField.clear();
        payTypeField.clear();
        emailField.clear();
        usernameField.clear();
        passwordField.clear();
    }

    /*  ACTIONS  */

    private void handleAdd() {
        Employee emp = buildEmployeeFromFields();
        if (emp == null) return;

        EmployeeController.add(emp);
        clearFields();
    }

    private void handleUpdate() {
        if (currentEmployee == null) {
            showError("No employee selected.");
            return;
        }

        Employee updated = buildEmployeeFromFields();
        if (updated == null) return;

        EmployeeController.update(updated);
    }

    private void handleDelete() {
        if (currentEmployee == null) {
            showError("No employee selected.");
            return;
        }

        EmployeeController.delete(currentEmployee.employeeId);
        clearFields();
        currentEmployee = null;
    }

    private void showError(String msg) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText(null);
        alert.setContentText(msg);
        alert.showAndWait();
    }
}
