package view;

import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Employee {
    private VBox paneEmployee;
    
    public Employee() {
        employee();
    }

    public VBox getEmployeePane() {
        return this.paneEmployee;
    }

    /* Create employee method */
    private void employee() {
        // Create vbox 
        paneEmployee = new VBox(10);
        // Set to center alignment
        paneEmployee.setAlignment(Pos.CENTER);
        // set width to largest child
        paneEmployee.setFillWidth(false);

        // Create labels
        Label employeeLabel = new Label("Employee Information");
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


        // Create text input fields
        // update these to predisplay the employee info
        TextField idField = new TextField();
        TextField departmentField = new TextField();
        TextField titleField = new TextField();
        TextField firstField = new TextField();
        TextField lastField = new TextField();
        TextField surField = new TextField();
        TextField statusField = new TextField();
        TextField dobField = new TextField();
        TextField genderField = new TextField();
        TextField payTypeField = new TextField();
        TextField emailField = new TextField();
        TextField address1Field = new TextField();
        TextField address2Field = new TextField();
        TextField cityField = new TextField();
        TextField stateField = new TextField();
        TextField zipField = new TextField();
        TextField photoField = new TextField();

        // create image
        //update with employee photo if it exists
        Image photo = new Image("./images/employee1.jpg"); 
        ImageView imgView = new ImageView();
        imgView.setImage(photo); 
        imgView.setFitWidth(50);
        imgView.setPreserveRatio(true);

        // Create update button
        Button updateButton = new Button("Update");
        // Add event handler
        updateButton.setOnAction(event -> {
            String id = idField.getText();
            String department = departmentField.getText();
            String title = titleField.getText();
            String first = firstField.getText();
            String last = lastField.getText();
            String sur = surField.getText();
            String status = statusField.getText();
            String dob = dobField.getText();
            String gender =genderField.getText();
            String payType = payTypeField.getText();
            String email = emailField.getText();
            String address1 = address1Field.getText();
            String address2 = address2Field.getText();
            String city = cityField.getText();
            String state = stateField.getText();
            String zip = zipField.getText();
            String newPhoto = photoField.getText();

            // replace with code to handle valid and invailid entry
        });

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

        // Add hboxes and button to grid
        paneEmployee.getChildren().addAll(employeeLabel, idBox, deptartmentBox, titleBox, firstBox, lastBox, surBox, statusBox, dobBox, genderBox, payTypeBox, emailBox, address1Box, address2Box, cityBox, stateBox, zipBox, photoHBox, updateButton);
    }
}
