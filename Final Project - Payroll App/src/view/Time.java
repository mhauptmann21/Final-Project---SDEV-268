package view;

import javafx.collections.FXCollections;
import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import server.Employee;
import javafx.collections.ObservableList;

import server.TimeEntry;
import  controllers.TimeEntryController;
import java.time.LocalDate;
import java.time.DayOfWeek;
import java.util.List;

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

        // TODO: Replace this with actual logged-in user from App once ready
        Employee currentUser = new Employee();
        currentUser.firstName = "Test";
        currentUser.lastName = "User";
        currentUser.payType = "Hourly";   // or "Salary"

        // create save button
        Button saveBTN = new Button("Save");
        // Add event handler
        saveBTN.setOnAction(event -> {
            saveWeek(
                currentUser,
                sundayChoiceBox, mondayChoiceBox, tuesdayChoiceBox, wednesdayChoiceBox,
                thursdayChoiceBox, fridayChoiceBox, saturdayChoiceBox,
                sundayPayTypeChoiceBox, mondayPayTypeChoiceBox, tuesdayPayTypeChoiceBox,
                wednesdayPayTypeChoiceBox, thursdayPayTypeChoiceBox, fridayPayTypeChoiceBox,
                saturdayPayTypeChoiceBox
            );
        });

        // create vbox for the days and add hboxes
        VBox daysBox = new VBox(20, sundayBox, mondayBox, tuesdayBox, wednesdayBox, thursdayBox, fridayBox, saturdayBox);

        boolean isAdmin = false; // placeholder


        // Get if user is admin 
         if (isAdmin) {
            employeeLabel.setText("Search for an employee to view time card");
        } else {
            employeeLabel.setText(currentUser.firstName + " " + currentUser.lastName);

            // Apply defaults based on salary/hourly
            defaultsByEmployeeType(
                currentUser,
                sundayChoiceBox, mondayChoiceBox, tuesdayChoiceBox,
                wednesdayChoiceBox, thursdayChoiceBox, fridayChoiceBox, saturdayChoiceBox
            );

            loadSavedTime(
                currentUser,
                sundayChoiceBox, mondayChoiceBox, tuesdayChoiceBox,
                wednesdayChoiceBox, thursdayChoiceBox, fridayChoiceBox, saturdayChoiceBox,
                sundayPayTypeChoiceBox, mondayPayTypeChoiceBox, tuesdayPayTypeChoiceBox,
                wednesdayPayTypeChoiceBox, thursdayPayTypeChoiceBox, fridayPayTypeChoiceBox,
                saturdayPayTypeChoiceBox
            );

        }

        // if timecard not locked
        if (true) { // change to check if timecard locked (should be false not true, leaving true for testing)
            // add employee label, day box, save button to time pane
            paneTime.getChildren().addAll(employeeLabel, daysBox, saveBTN);
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

    private void defaultsByEmployeeType(Employee employee, ChoiceBox<Integer> sunday, ChoiceBox<Integer> monday, ChoiceBox<Integer> tuesday, ChoiceBox<Integer> wednesday, ChoiceBox<Integer> thursday, ChoiceBox<Integer> friday, ChoiceBox<Integer> saturday) {
        //if salary 
        // check database to see if spelling needs corrected
        if("Salary".equals(employee.payType)) {
            // default to regular pay 8 hours Mon - Fri
            salary(monday, tuesday, wednesday, thursday, friday);
        }
        // else employee is hourly, set all days to 0 hours worked
        else {
            hourly(sunday, monday, tuesday, wednesday, thursday, friday, saturday);
        }
    }

    private void saveWeek(
        Employee e,
        ChoiceBox<Integer> sun, ChoiceBox<Integer> mon, ChoiceBox<Integer> tue,
        ChoiceBox<Integer> wed, ChoiceBox<Integer> thu, ChoiceBox<Integer> fri,
        ChoiceBox<Integer> sat,
        ChoiceBox<String> sunType, ChoiceBox<String> monType, ChoiceBox<String> tueType,
        ChoiceBox<String> wedType, ChoiceBox<String> thuType, ChoiceBox<String> friType,
        ChoiceBox<String> satType
    ) {
        String[] week = getWeekRange();
        LocalDate start = LocalDate.parse(week[0]);

        saveDay(e, start.plusDays(0), sun, sunType);
        saveDay(e, start.plusDays(1), mon, monType);
        saveDay(e, start.plusDays(2), tue, tueType);
        saveDay(e, start.plusDays(3), wed, wedType);
        saveDay(e, start.plusDays(4), thu, thuType);
        saveDay(e, start.plusDays(5), fri, friType);
        saveDay(e, start.plusDays(6), sat, satType);

        System.out.println("Time entries saved!");
    }

    private void saveDay(Employee e, LocalDate date, ChoiceBox<Integer> hours, ChoiceBox<String> type) {
        TimeEntry t = new TimeEntry();
        t.employeeId = e.employeeId;
        t.date = date.toString();

        if(type.getValue().equals("PTO")) {
            t.hoursWorked = 0;
            t.ptoHours = hours.getValue();
        } else {
            t.hoursWorked = hours.getValue();
            t.ptoHours = 0;
        }

        t.isLocked = false;

        TimeEntryController.add(t);
    }

    private void loadSavedTime(
        Employee employee,
        ChoiceBox<Integer> sunday,
        ChoiceBox<Integer> monday,
        ChoiceBox<Integer> tuesday,
        ChoiceBox<Integer> wednesday,
        ChoiceBox<Integer> thursday,
        ChoiceBox<Integer> friday,
        ChoiceBox<Integer> saturday,
        ChoiceBox<String> sundayType,
        ChoiceBox<String> mondayType,
        ChoiceBox<String> tuesdayType,
        ChoiceBox<String> wednesdayType,
        ChoiceBox<String> thursdayType,
        ChoiceBox<String> fridayType,
        ChoiceBox<String> saturdayType
    ) {
        String[] week = getWeekRange();
        List<TimeEntry> entries = TimeEntryController.getWeek(
            employee.employeeId,
            week[0],
            week[1]
        );

        for (TimeEntry t : entries) {
            LocalDate date = LocalDate.parse(t.date);
            DayOfWeek day = date.getDayOfWeek();

            switch (day) {
                case SUNDAY -> {
                    sunday.setValue((int)t.hoursWorked);
                    sundayType.setValue(t.ptoHours > 0 ? "PTO" : "Regular");
                }
                case MONDAY -> {
                monday.setValue((int)t.hoursWorked);
                mondayType.setValue(t.ptoHours > 0 ? "PTO" : "Regular");
                }
                case TUESDAY -> {
                    tuesday.setValue((int)t.hoursWorked);
                    tuesdayType.setValue(t.ptoHours > 0 ? "PTO" : "Regular");
                }
                case WEDNESDAY -> {
                    wednesday.setValue((int)t.hoursWorked);
                    wednesdayType.setValue(t.ptoHours > 0 ? "PTO" : "Regular");
                }
                case THURSDAY -> {
                    thursday.setValue((int)t.hoursWorked);
                    thursdayType.setValue(t.ptoHours > 0 ? "PTO" : "Regular");
                }
                case FRIDAY -> {
                    friday.setValue((int)t.hoursWorked);
                    fridayType.setValue(t.ptoHours > 0 ? "PTO" : "Regular");
                }
                case SATURDAY -> {
                    saturday.setValue((int)t.hoursWorked);
                    saturdayType.setValue(t.ptoHours > 0 ? "PTO" : "Regular");
                }
            }
        }
    }

    private String[] getWeekRange() {
        LocalDate today = LocalDate.now();

        LocalDate sunday = today.with(DayOfWeek.SUNDAY);
        LocalDate saturday = today.with(DayOfWeek.SATURDAY);

        return new String[] {
            sunday.toString(),
            saturday.toString()
        };
    }

    
}
