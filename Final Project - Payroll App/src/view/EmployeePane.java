package view;

import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import server.Employee;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class EmployeePane {
    private VBox paneEmployee;
    private final AdminView adminView;
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
    private TextField payTypeField = new TextField();
    private TextField emailField = new TextField();
    private TextField address1Field = new TextField();
    private TextField address2Field = new TextField();
    private TextField cityField = new TextField();
    private TextField stateField = new TextField();
    private TextField zipField = new TextField();
    private TextField photoField = new TextField();
    private String id;
    private String department;
    private String title;
    private String first;
    private String last;
    private String sur;
    private String status;
    private String dob;
    private String gender;  
    private String payType;
    private String email;
    private String address1;
    private String address2;
    private String city;
    private String state;
    private String zip;
    private String photo;
    
    public EmployeePane(AdminView adminView) {
        this.adminView = adminView;
        employeePane();
    }

    public VBox getEmployeePane() {
        return this.paneEmployee;
    }

    private void setEmployeePhoto(Image newPhoto) {
        imgView.setImage(newPhoto);
    }

    /* Create employee method */
    private void employeePane() {
        // Create vbox 
        paneEmployee = new VBox(10);
        // Set to center alignment
        paneEmployee.setAlignment(Pos.CENTER);
        // set width to largest child
        paneEmployee.setFillWidth(false);

        // Create labels
        Label idLabel = new Label("Employee ID: ");
        Label departmentLabel = new Label("Department: ");   
        Label titleLabel = new Label("Job Title: ");
        Label firstLabel = new Label("First Name: ");
        Label lastLabel = new Label("Last Name: ");   
        Label surLabel = new Label("Sur Name: ");
        Label statusLabel = new Label("Status: ");
        Label dobLabel = new Label("Birth Date: ");
        Label genderLabel = new Label("Gender: ");
        Label payTypeLabel = new Label("Pay Type: ");
        Label emailLabel = new Label("Email: ");
        Label address1Label = new Label("Address: ");
        Label address2Label = new Label("Address:");
        Label cityLabel = new Label("City: ");
        Label stateLabel = new Label("State: ");
        Label zipLabel = new Label("Zip Code: ");
        Label photoLabel = new Label("Photo: ");

        // set min label size to 100
        idLabel.setMinWidth(100);
        departmentLabel.setMinWidth(100);
        titleLabel.setMinWidth(100);
        firstLabel.setMinWidth(100);
        lastLabel.setMinWidth(100);
        surLabel.setMinWidth(100);
        statusLabel.setMinWidth(100);
        dobLabel.setMinWidth(100);
        genderLabel.setMinWidth(100);
        payTypeLabel.setMinWidth(100);
        emailLabel.setMinWidth(100);
        address1Label.setMinWidth(100);
        address2Label.setMinWidth(100);
        cityLabel.setMinWidth(100);
        stateLabel.setMinWidth(100);
        zipLabel.setMinWidth(100);
        photoLabel.setMinWidth(100);

        
        // Update text fields to predisplay the employee info if available
        if (adminView.hasCurrentEmployee()) {
            updateEmployeePaneFromSearch(adminView.getCurrentEmployee());
        }

        // create image and add to view        
        Image employeePhoto = new Image("./images/stockEmployee.jpg"); 
        imgView.setImage(employeePhoto); 
        imgView.setFitWidth(50);
        imgView.setPreserveRatio(true);

        // Create add button
        Button addButton = new Button("Add");
        // Add event handler
        addButton.setOnAction(event -> {
            handleAddEmployee();
        });

        // Create update button
        Button updateButton = new Button("Update");
        // Add event handler
        updateButton.setOnAction(event -> {
            handleUpdateEmployee(App.adminView.getCurrentEmployee());
        });

        Button deleteButton = new Button("Delete");
        // Add event handler
        deleteButton.setOnAction(event -> { 
            handleDeleteEmployee(App.adminView.getCurrentEmployee());
        });

        // set button min width
        addButton.setMinWidth(75);
        updateButton.setMinWidth(75);
        deleteButton.setMinWidth(75);

        //create hboxes for labels and fields
        HBox idBox = new HBox(idLabel, idField);
        HBox deptartmentBox = new HBox(departmentLabel, departmentField);
        HBox titleBox = new HBox(titleLabel, titleField);
        HBox firstBox = new HBox(firstLabel, firstField);
        HBox lastBox = new HBox(lastLabel, lastField);
        HBox surBox = new HBox(surLabel, surField);
        HBox statusBox = new HBox(statusLabel, statusField);
        HBox dobBox = new HBox(dobLabel, dobField);
        HBox genderBox = new HBox(genderLabel, genderField);
        HBox payTypeBox = new HBox(payTypeLabel, payTypeField);
        HBox emailBox = new HBox(emailLabel, emailField);
        HBox address1Box = new HBox(address1Label, address1Field);
        HBox address2Box = new HBox(address2Label, address2Field);
        HBox cityBox = new HBox(cityLabel, cityField);
        HBox stateBox = new HBox(stateLabel, stateField);
        HBox zipBox = new HBox(zipLabel, zipField);

        // create boxes for photo
        VBox photoVBox = new VBox(photoField, imgView);
        HBox photoHBox = new HBox(photoLabel, photoVBox);

        // create hbox for buttons
        HBox buttonBox = new HBox(50, addButton, updateButton, deleteButton);
        buttonBox.setAlignment(Pos.CENTER);

        // Add hboxes and button to grid
        paneEmployee.getChildren().addAll(employeeLabel, idBox, deptartmentBox, titleBox, firstBox, lastBox, surBox, statusBox, dobBox, genderBox, payTypeBox, emailBox, address1Box, address2Box, cityBox, stateBox, zipBox, photoHBox, buttonBox);
    }

    private void getEnteredData() {
        this.id = idField.getText();
        this.department = departmentField.getText();
        this.title = titleField.getText();
        this.first = firstField.getText();
        this.last = lastField.getText();
        this.sur = surField.getText();
        this.status = statusField.getText();
        this.dob = dobField.getText();
        this.gender = genderField.getText();
        this.payType = payTypeField.getText();
        this.email = emailField.getText();
        this.address1 = address1Field.getText();            
        this.address2 = address2Field.getText();
        this.city = cityField.getText();
        this.state = stateField.getText();
        this.zip = zipField.getText();
        this.photo = photoField.getText();
    }

    private void clearData() {
        this.id = "";
        this.department = "";
        this.title = "";
        this.first = "";
        this.last = "";
        this.sur = "";
        this.status = "";
        this.dob = "";
        this.gender = "";
        this.payType = "";
        this.email = "";
        this.address1 = "";            
        this.address2 = "";
        this.city = "";
        this.state = "";
        this.zip = "";
        this.photo = "";    
    }

    private void clearFields() {
        employeeLabel.setText("Employee Information");
        idField.clear();
        departmentField.clear();
        titleField.clear();
        firstField.clear();
        lastField.clear();
        surField.clear();
        statusField.clear();
        dobField.clear();
        genderField.clear();
        payTypeField.clear();
        emailField.clear();
        address1Field.clear();            
        address2Field.clear();
        cityField.clear();
        stateField.clear();
        zipField.clear();
        photoField.clear();

        // reset image to default
        Image defaultPhoto = new Image("./images/stockEmployee.jpg"); 
        setEmployeePhoto(defaultPhoto);

        // clear data variables
        clearData();
    }

    public void updateEmployeePaneFromSearch(Employee emp) {
        clearFields();
        clearData();
        setFieldsFromSearchResult(emp);
        getEnteredData();
    }

    //TODO: not all fields are in Employee class, need to address that
    private void setFieldsFromSearchResult(Employee emp) {
        if (emp != null) {
            // update employee label
            employeeLabel.setText("Employee Information for " + emp.firstName + " " + emp.lastName);
            // populate text fields
            idField.setText(String.valueOf(emp.employeeId));
            departmentField.setText(emp.department);
            titleField.setText(emp.jobTitle);
            firstField.setText(emp.firstName);
            lastField.setText(emp.lastName);
            surField.setText(""); // Assuming 'sur' is not in Employee class            
            statusField.setText(emp.status);
            dobField.setText(emp.dateOfBirth);
            genderField.setText(""); // Assuming 'gender' is not in Employee class      
            payTypeField.setText(emp.payType);
            emailField.setText(emp.email);
            // Address fields are not in Employee class, so setting them to empty
            address1Field.setText("");            
            address2Field.setText("");
            cityField.setText("");
            stateField.setText("");
            zipField.setText("");
            photoField.setText(""); // Assuming photo path is not in Employee class   
        }
    }

    // TODO: not all requrired fields are in Employee class, need to address that
    private void handleUpdateEmployee(Employee emp) {
        if (emp == null) {
            // add alert for no employee selected
            App.noEmployeeAlert.getNoEmployeeAlert().showAndWait();
            return;
        }
        // get entered data
        getEnteredData();

        //TODO: update the Employee object with the entered data
        // probably would be better to add setters to Employee class but this does work
        // Also should probably add validation before updating

        emp.employeeId = Integer.parseInt(this.id);
        emp.department = this.department;
        emp.jobTitle = this.title;
        emp.firstName = this.first;
        emp.lastName = this.last;
        emp.status = this.status;
        emp.dateOfBirth = this.dob;
        emp.payType = this.payType;
        emp.email = this.email;

        //TODO: add: sur, gender, address1, address2, city, state, zip, photo

    }

    private void handleAddEmployee() {
        // get entered data
        getEnteredData();       

        //create new Employee object and populate it
        Employee newEmp = new Employee();
        newEmp.employeeId = Integer.parseInt(this.id);
        newEmp.department = this.department;
        newEmp.jobTitle = this.title;
        newEmp.firstName = this.first;
        newEmp.lastName = this.last;
        newEmp.status = this.status;
        newEmp.dateOfBirth = this.dob;
        newEmp.payType = this.payType;
        newEmp.email = this.email;  
        //TODO: add missing fields

        //TODO: add logic to save new employee to database

        //clear fields after adding
        clearFields();
    } 
    
    private void handleDeleteEmployee(Employee emp) {
        if (emp != null) {  
            //TODO: add logic to delete employee from database

            //clear fields after deleting
            clearFields();  
        } else {
            // add alert for no employee selected
            App.noEmployeeAlert.getNoEmployeeAlert().showAndWait();
        }
    }  
}
