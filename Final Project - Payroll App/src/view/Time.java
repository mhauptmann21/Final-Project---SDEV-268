package view;

import java.text.ChoiceFormat;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import model.Admin;
import server.Employee;
import javafx.collections.ObservableList;

public class Time {
    private VBox paneTime;
    
    public Time() {
        time();
    }

    public VBox getTimePane() {
        return this.paneTime;
    }

    /* Create time method */
    private void time() {
        // Create vbox 
        paneTime = new VBox(10);
        // Set to center alignment
        paneTime.setAlignment(Pos.CENTER);
        // set size to max width of child
        paneTime.setFillWidth(false);
        // add spacing
        paneTime.setSpacing(50);

        //Create labels
        Label employeeLabel = new Label();
        Label lockedLabel = new Label("Time Card Locked.  Editing Disabled.");
        Label sundayLabel = new Label("Sunday: ");
        Label mondayLabel = new Label("Monday: ");
        Label tuesdayLabel = new Label("Tuesday: ");
        Label wednesdayLabel = new Label("Wednesday: ");
        Label thursdayLabel = new Label("Thursday: ");
        Label fridayLabel = new Label("Friday: ");
        Label saturdayLabel = new Label("Saturday: ");

        // set min label size to 100
        sundayLabel.setMinWidth(100);
        mondayLabel.setMinWidth(100);
        tuesdayLabel.setMinWidth(100);
        wednesdayLabel.setMinWidth(100);
        thursdayLabel.setMinWidth(100);
        wednesdayLabel.setMinWidth(100);
        fridayLabel.setMinWidth(100);
        saturdayLabel.setMinWidth(100);

        //Create hours worked choice boxes
        ChoiceBox<Integer> sundayChoiceBox = new ChoiceBox<>();
        ChoiceBox<Integer> mondayChoiceBox = new ChoiceBox<>();
        ChoiceBox<Integer> tuesdayChoiceBox = new ChoiceBox<>();
        ChoiceBox<Integer> wednesdayChoiceBox = new ChoiceBox<>();
        ChoiceBox<Integer> thursdayChoiceBox = new ChoiceBox<>();
        ChoiceBox<Integer> fridayChoiceBox = new ChoiceBox<>();
        ChoiceBox<Integer> saturdayChoiceBox = new ChoiceBox<>();

        // add hours to choice boxes
        for (Integer i = 0; i <= 24; i++) {
            sundayChoiceBox.getItems().add(i);
            mondayChoiceBox.getItems().add(i);
            tuesdayChoiceBox.getItems().add(i);
            wednesdayChoiceBox.getItems().add(i);
            thursdayChoiceBox.getItems().add(i);
            fridayChoiceBox.getItems().add(i);
            saturdayChoiceBox.getItems().add(i);
        }

        // Instantiate list for pto or regular choice boxes
        ObservableList<String> regOrPTO = FXCollections.observableArrayList("Regular", "PTO"); 

        // Create regular or PTO pay choice boxes
        ChoiceBox<String> sundayPayTypeChoiceBox = new ChoiceBox<>(regOrPTO);
        ChoiceBox<String> mondayPayTypeChoiceBox = new ChoiceBox<>(regOrPTO);
        ChoiceBox<String> tuesdayPayTypeChoiceBox = new ChoiceBox<>(regOrPTO);
        ChoiceBox<String> wednesdayPayTypeChoiceBox = new ChoiceBox<>(regOrPTO);
        ChoiceBox<String> thursdayPayTypeChoiceBox = new ChoiceBox<>(regOrPTO);
        ChoiceBox<String> fridayPayTypeChoiceBox = new ChoiceBox<>(regOrPTO);
        ChoiceBox<String> saturdayPayTypeChoiceBox = new ChoiceBox<>(regOrPTO);

        // Set pto/regular choiceboxes initial values to regurlar
        sundayPayTypeChoiceBox.setValue("Regular");
        mondayPayTypeChoiceBox.setValue("Regular");
        tuesdayPayTypeChoiceBox.setValue("Regular");
        wednesdayPayTypeChoiceBox.setValue("Regular");
        thursdayPayTypeChoiceBox.setValue("Regular");
        fridayPayTypeChoiceBox.setValue("Regular");
        saturdayPayTypeChoiceBox.setValue("Regular");

        // create hboxes for each day
        HBox sundayBox = new HBox(20, sundayLabel, sundayPayTypeChoiceBox, sundayChoiceBox);
        HBox mondayBox = new HBox(20, mondayLabel, mondayPayTypeChoiceBox, mondayChoiceBox);
        HBox tuesdayBox = new HBox(20, tuesdayLabel, tuesdayPayTypeChoiceBox, tuesdayChoiceBox);
        HBox wednesdayBox = new HBox(20, wednesdayLabel, wednesdayPayTypeChoiceBox, wednesdayChoiceBox);
        HBox thursdayBox = new HBox(20, thursdayLabel, thursdayPayTypeChoiceBox, thursdayChoiceBox);
        HBox fridayBox = new HBox(20, fridayLabel, fridayPayTypeChoiceBox, fridayChoiceBox);
        HBox saturdayBox = new HBox(20, saturdayLabel, saturdayPayTypeChoiceBox, saturdayChoiceBox);

        // add color to odd day hbox
        sundayBox.getStyleClass().add("oddDay-hbox");
        tuesdayBox.getStyleClass().add("oddDay-hbox");
        thursdayBox.getStyleClass().add("oddDay-hbox");
        saturdayBox.getStyleClass().add("oddDay-hbox");

        // add color to even day hbox
        mondayBox.getStyleClass().add("evenDay-hbox");
        wednesdayBox.getStyleClass().add("evenDay-hbox");
        fridayBox.getStyleClass().add("evenDay-hbox");

        // create save button
        Button saveBTN = new Button("Save");
        // Add event handler
        saveBTN.setOnAction(event -> {
            // add code to save to database
        });

        // create vbox for the days and add hboxes
        VBox daysBox = new VBox(20, sundayBox, mondayBox, tuesdayBox, wednesdayBox, thursdayBox, fridayBox, saturdayBox);

        // instantiate users for testing
        // update to use the logged in user
        Admin admin = new Admin();
        Employee employee = new Employee();
        Employee currentUser = employee;

        // Get if user is admin 
        if (currentUser instanceof Admin) {
            // if no employee saved from search
            // prompt admin to search
            employeeLabel.setText("Search for an employee to view time card");
            // else already working with an employee, view their timecard and update label with the name        
        }
        // else current user is an employee
        else {
            // add users name to employee label
            // update with real name
            employeeLabel.setText("First Last");

            // set time defaults based on if hourly or salary
            defualtsByEmployeeType(currentUser, sundayChoiceBox, mondayChoiceBox, tuesdayChoiceBox, wednesdayChoiceBox, thursdayChoiceBox, fridayChoiceBox, saturdayChoiceBox);

            // overright defualts with previous entries
            savedTime(currentUser, sundayChoiceBox, mondayChoiceBox, tuesdayChoiceBox, wednesdayChoiceBox, thursdayChoiceBox, fridayChoiceBox, saturdayChoiceBox);
        }

        // if timecard not locked
        if (true) { // change to check if timecard locked (should be false not true, leaving true for testing)
            // add employee label, day box, save button to time pane
            paneTime.getChildren().addAll(employeeLabel, daysBox, saveBTN);
        } else {
            // disable all choice boxes and add emplyee label, day box, and locked label to time pane
            sundayChoiceBox.setDisable(true);
            mondayChoiceBox.setDisable(true);
            tuesdayChoiceBox.setDisable(true);
            wednesdayChoiceBox.setDisable(true);
            thursdayChoiceBox.setDisable(true);
            fridayChoiceBox.setDisable(true);
            saturdayChoiceBox.setDisable(true);
            sundayPayTypeChoiceBox.setDisable(true);
            mondayPayTypeChoiceBox.setDisable(true);
            tuesdayPayTypeChoiceBox.setDisable(true);
            wednesdayPayTypeChoiceBox.setDisable(true);
            thursdayPayTypeChoiceBox.setDisable(true);
            fridayPayTypeChoiceBox.setDisable(true);
            saturdayPayTypeChoiceBox.setDisable(true);
            saveBTN.setDisable(true);    

            // add employee label, locked label, day box to time pane
            paneTime.getChildren().addAll(employeeLabel, lockedLabel, daysBox);
        }
    }

    private void salary(ChoiceBox<Integer> monday, ChoiceBox<Integer> tuesday, ChoiceBox<Integer> wednesday, ChoiceBox<Integer> thursday, ChoiceBox<Integer> friday) {
        monday.setValue(8);
        tuesday.setValue(8);
        wednesday.setValue(8);
        thursday.setValue(8);
        friday.setValue(8);
    } 
    
    private void hourly(ChoiceBox<Integer> sunday, ChoiceBox<Integer> monday, ChoiceBox<Integer> tuesday, ChoiceBox<Integer> wednesday, ChoiceBox<Integer> thursday, ChoiceBox<Integer> friday, ChoiceBox<Integer> saturday) {
        sunday.setValue(0);
        monday.setValue(0);
        tuesday.setValue(0);
        wednesday.setValue(0);
        thursday.setValue(0);
        friday.setValue(0);
        saturday.setValue(0);
    }

    private void defualtsByEmployeeType(Employee employee, ChoiceBox<Integer> sunday, ChoiceBox<Integer> monday, ChoiceBox<Integer> tuesday, ChoiceBox<Integer> wednesday, ChoiceBox<Integer> thursday, ChoiceBox<Integer> friday, ChoiceBox<Integer> saturday) {
        //if salary 
        // check database to see if spelling needs corrected
        if(employee.payType == "Salary") {
            // default to regular pay 8 hours Mon - Fri
            salary(monday, tuesday, wednesday, thursday, friday);
        }
        // else employee is hourly, set all days to 0 hours worked
        else {
            hourly(sunday, monday, tuesday, wednesday, thursday, friday, saturday);
        }
    }

    private void savedTime(Employee employee, ChoiceBox<Integer> sunday, ChoiceBox<Integer> monday, ChoiceBox<Integer> tuesday, ChoiceBox<Integer> wednesday, ChoiceBox<Integer> thursday, ChoiceBox<Integer> friday, ChoiceBox<Integer> saturday) {
        // get previous time entered from database
        // instantiating monday entry for testing
        Integer mondayTime = 6;

        // if time already entered, update the choicebox for that day

        //Assuming only monday time entered for testing
        monday.setValue(mondayTime);
    }

    
}
