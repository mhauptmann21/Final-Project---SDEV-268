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
    private TextField surField = new TextField();
    private TextField statusField = new TextField();
    private TextField dobField = new TextField();
    private TextField genderField = new TextField();
    private TextField emailField = new TextField();
    private TextField usernameField = new TextField();
    private TextField passwordField = new TextField();
    private TextField address1Field = new TextField();
    private TextField address2Field = new TextField();
    private TextField cityField = new TextField();
    private TextField stateField = new TextField();
    private TextField zipField = new TextField();

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
        HBox surBox = fieldRow("Surname:", surField);
        HBox lastBox = fieldRow("Last Name:", lastField);
        HBox statusBox = fieldRow("Status:", statusField);
        HBox dobBox = fieldRow("Birth Date:", dobField);
        HBox genderBox = fieldRow("Gender:", genderField);
        HBox emailBox = fieldRow("Email:", emailField);
        HBox usernameBox = fieldRow("Username:", usernameField);
        HBox passwordBox = fieldRow("Password:", passwordField);
        HBox address1Box = fieldRow("Address:", address1Field);
        HBox address2Box = fieldRow("Address 2:", address2Field);
        HBox cityBox = fieldRow("City:", cityField);
        HBox stateBox = fieldRow("State:", stateField);
        HBox zipBox = fieldRow("Zip Code:", zipField);

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
                surBox,
                lastBox,
                statusBox,
                dobBox,
                genderBox,
                emailBox,
                usernameBox,
                passwordBox,
                address1Box,
                address2Box,
                cityBox,
                stateBox,
                zipBox,
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
        surField.setText(emp.surName);
        lastField.setText(emp.lastName);
        statusField.setText(emp.status);
        dobField.setText(emp.dateOfBirth);
        genderField.setText(emp.gender);
        emailField.setText(emp.email);
        usernameField.setText(emp.username);
        passwordField.setText(emp.password);
        address1Field.setText(emp.address1);
        address2Field.setText(emp.address2);
        cityField.setText(emp.city);
        stateField.setText(emp.state);
        zipField.setText(emp.zip);
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
        surField.setText(emp.surName);
        lastField.setText(emp.lastName);
        statusField.setText(emp.status);
        dobField.setText(emp.dateOfBirth);
        genderField.setText(emp.gender);
        emailField.setText(emp.email);
        usernameField.setText(emp.username);
        passwordField.setText(emp.password);
        address1Field.setText(emp.address1);
        address2Field.setText(emp.address2);
        cityField.setText(emp.city);
        stateField.setText(emp.state);
        zipField.setText(emp.zip);
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
        emp.surName = surField.getText();
        emp.lastName = lastField.getText();
        emp.status = statusField.getText();
        emp.dateOfBirth = dobField.getText();
        emp.gender = genderField.getText();
        emp.email = emailField.getText();
        emp.username = usernameField.getText();
        emp.password = passwordField.getText();
        emp.address1 = address1Field.getText();
        emp.address2 = address2Field.getText();
        emp.city = cityField.getText();
        emp.state = stateField.getText();
        emp.zip = zipField.getText();
        

        return emp;
    }

    private void clearFields() {
        idField.clear();
        departmentField.clear();
        titleField.clear();
        firstField.clear();
        surField.clear();
        lastField.clear();
        statusField.clear();
        dobField.clear();
        genderField.clear();
        emailField.clear();
        usernameField.clear();
        passwordField.clear();
        address1Field.clear();
        address2Field.clear();
        cityField.clear();
        stateField.clear();
        zipField.clear();
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
