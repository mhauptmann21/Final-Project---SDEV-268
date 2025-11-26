package view;

import javafx.geometry.Pos;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.control.Label;
import javafx.scene.text.*;

public class Salary {
    private VBox paneSalary;
    
    public Salary() {
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
        Label salaryLabel = new Label("Salary Data");
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
        departmentLabel.setMinWidth(100);

        // Create text nodes
        Text departmentText = new Text();
        Text jobTypeText = new Text();
        Text firstText = new Text();
        Text lastText = new Text();
        Text statusText = new Text();
        Text hireDateText = new Text();
        Text salaryTypeText = new Text();
        Text baseSalaryText = new Text();
        Text medicalText = new Text();
        Text dependentsText = new Text();

        // add method to populate text nodes

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

        // Create vbox
        VBox vbox = new VBox(10);
        // Add hboxes to grid
        vbox.getChildren().addAll(salaryLabel, departmentHbox, jobTypeHbox, firstHbox, lastHbox, statusHbox, hireDateHbox, salaryTypeHbox, baseSalaryHbox, medicalHbox, dependentsHbox);

        // Add vbox to grid
        paneSalary.getChildren().add(vbox);
    }
}
