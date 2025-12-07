package view;

import javafx.geometry.Pos;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import server.Employee;
import javafx.scene.control.Alert;

public class Salary {
    private VBox paneSalary;
    private final AdminView adminView;
    private Label salaryLabel = new Label("Salary Data");
    // Create text fields
    TextField departmentText = new TextField();
    TextField jobTypeText = new TextField();
    TextField firstText = new TextField();
    TextField lastText = new TextField();
    TextField statusText = new TextField();
    TextField hireDateText = new TextField();
    TextField salaryTypeText = new TextField();
    TextField baseSalaryText = new TextField();
    TextField medicalText = new TextField();
    TextField dependentsText = new TextField();
    
    public Salary(AdminView adminView) {
        this.adminView = adminView;
        salary();
    }

    public VBox getSalaryPane() {
        return this.paneSalary;
    }

    /* Create salary method */
    private void salary() {
        // Create vbox 
        paneSalary = new VBox(10);
        // Set to center alignment
        paneSalary.setAlignment(Pos.CENTER);
        // set size to max width of child
        paneSalary.setFillWidth(false);

        // Create labels
        
        Label departmentLabel = new Label("Department:");
        Label jobTypeLabel = new Label("Job Type Title:");   
        Label firstLabel = new Label("First Name:");
        Label lastLabel = new Label("Last Name:");
        Label statusLabel = new Label("Status:");
        Label hireDateLabel = new Label("Hire Date:");
        Label salaryTypeLabel = new Label("Salary Type:");
        Label baseSalaryLabel = new Label("Base Salary:");
        Label medicalLabel = new Label("Medical:");
        Label dependentsLabel = new Label("Dependents:");

        // set label min width to 100
        departmentLabel.setMinWidth(100);
        jobTypeLabel.setMinWidth(100);
        firstLabel.setMinWidth(100);
        lastLabel.setMinWidth(100);
        statusLabel.setMinWidth(100);
        hireDateLabel.setMinWidth(100);
        salaryTypeLabel.setMinWidth(100);
        baseSalaryLabel.setMinWidth(100);
        medicalLabel.setMinWidth(100);
        dependentsLabel.setMinWidth(100);

        // populate text fields
        populateSalaryFields();

        // Create hboxes for data
        HBox departmentHbox = new HBox();
        HBox jobTypeHbox = new HBox();
        HBox firstHbox = new HBox();
        HBox lastHbox = new HBox();
        HBox statusHbox = new HBox();
        HBox hireDateHbox = new HBox();
        HBox salaryTypeHbox = new HBox();
        HBox baseSalaryHbox = new HBox();
        HBox medicalHbox = new HBox();
        HBox dependentsHbox = new HBox();

        //add odd-day styling to hboxes
        departmentHbox.getStyleClass().add("odd-day");
        firstHbox.getStyleClass().add("odd-day");
        statusHbox.getStyleClass().add("odd-day");
        salaryTypeHbox.getStyleClass().add("odd-day");
        medicalHbox.getStyleClass().add("odd-day");

        //add even-day styling to hboxes
        jobTypeHbox.getStyleClass().add("even-day");
        lastHbox.getStyleClass().add("even-day");
        hireDateHbox.getStyleClass().add("even-day");
        baseSalaryHbox.getStyleClass().add("even-day");
        dependentsHbox.getStyleClass().add("even-day");

        // add label and text to corresponding hbox
        departmentHbox.getChildren().addAll(departmentLabel, departmentText);
        jobTypeHbox.getChildren().addAll(jobTypeLabel, jobTypeText);
        firstHbox.getChildren().addAll(firstLabel, firstText);
        lastHbox.getChildren().addAll(lastLabel, lastText);
        statusHbox.getChildren().addAll(statusLabel, statusText);
        hireDateHbox.getChildren().addAll(hireDateLabel, hireDateText);
        salaryTypeHbox.getChildren().addAll(salaryTypeLabel, salaryTypeText);
        baseSalaryHbox.getChildren().addAll(baseSalaryLabel, baseSalaryText);
        medicalHbox.getChildren().addAll(medicalLabel, medicalText);
        dependentsHbox.getChildren().addAll(dependentsLabel, dependentsText);

        // create update button
        Button updateButton = new Button("Update");
        // set on action for update button
        updateButton.setOnAction(e -> {
            handleUpdateButton();
        });

        // Create vbox
        VBox vbox = new VBox(10);
        // Set alignment to center
        vbox.setAlignment(Pos.CENTER);
        // Add hboxes to grid
        vbox.getChildren().addAll(salaryLabel, departmentHbox, jobTypeHbox, firstHbox, lastHbox, statusHbox, hireDateHbox, salaryTypeHbox, baseSalaryHbox, medicalHbox, dependentsHbox, updateButton);

        // Add vbox to grid
        paneSalary.getChildren().add(vbox);
    }

    private void populateSalaryFields() {
        Employee emp = adminView.getCurrentEmployee();
        if (emp != null) {
            // update salary label
            salaryLabel.setText("Salary Data for " + emp.firstName + " " + emp.lastName);

            // populate text fields
            departmentText.setText(emp.department);
            jobTypeText.setText(emp.jobTitle);
            firstText.setText(emp.firstName);
            lastText.setText(emp.lastName);
            statusText.setText(emp.status);
            hireDateText.setText(emp.dateHired);
            salaryTypeText.setText(emp.payType);
            baseSalaryText.setText(String.valueOf(emp.baseSalary));
            medicalText.setText(String.valueOf(emp.medical));
            dependentsText.setText(String.valueOf(emp.dependents)); 
        }
    }   

    private void handleUpdateButton() {
        Employee emp = adminView.getCurrentEmployee();
        if (emp != null) {
            //TODO: add validation for text fields
            // probably best handled by setters in Employee class then change below code to use setters

            // Update employee data from text fields
            emp.department = departmentText.getText();
            emp.jobTitle = jobTypeText.getText();
            emp.firstName = firstText.getText();
            emp.lastName = lastText.getText();
            emp.status = statusText.getText();
            emp.dateHired = hireDateText.getText();
            emp.payType = salaryTypeText.getText();
            emp.baseSalary = Double.parseDouble(baseSalaryText.getText());
            emp.medical = medicalText.getText();
            emp.dependents = Integer.parseInt(dependentsText.getText());

            // TODO: add code here to update database with new employee data
        } else {
            // No employee selected, show alert
            App.noEmployeeAlert.getNoEmployeeAlert().showAndWait();
        }
    }   
}
