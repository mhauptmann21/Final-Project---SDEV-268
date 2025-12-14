package view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import server.Employee;
import server.TimeEntry;
import controllers.SessionController;
import server.TimeEntryDAO;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;

public class Time {
    private VBox paneTime;

    private Label weekLabel;
    private Button nextWeekButton;
    private Button prevWeekButton;
    private int employeeId;

    // ChoiceBoxes for each day
    private ChoiceBox<Integer> sundayHours;
    private ChoiceBox<Integer> mondayHours;
    private ChoiceBox<Integer> tuesdayHours;
    private ChoiceBox<Integer> wednesdayHours;
    private ChoiceBox<Integer> thursdayHours;
    private ChoiceBox<Integer> fridayHours;
    private ChoiceBox<Integer> saturdayHours;

    private ChoiceBox<String> sundayType;
    private ChoiceBox<String> mondayType;
    private ChoiceBox<String> tuesdayType;
    private ChoiceBox<String> wednesdayType;
    private ChoiceBox<String> thursdayType;
    private ChoiceBox<String> fridayType;
    private ChoiceBox<String> saturdayType;

    private Label lockedLabel;
    private Button saveButton;

    public Time() {
        paneTime = new VBox(20);
        paneTime.setPadding(new Insets(25));
        paneTime.setAlignment(Pos.TOP_CENTER);
        // set size to max width of child
        paneTime.setFillWidth(false);

        currentWeekStart = LocalDate.now().with(DayOfWeek.MONDAY);

        buildUI();

    }

    public VBox getTimePane() {
        return this.paneTime;
    }

    private LocalDate currentWeekStart;
    

    private void buildUI() {
        Employee user = SessionController.getCurrentUser();
        if (user == null) {
            paneTime.getChildren().add(new Label("ERROR: No logged in user."));
            return;
        }

        this.employeeId = user.employeeId;


        currentWeekStart = LocalDate.now().with(DayOfWeek.MONDAY);

        Label title = new Label("Weekly Time Card");
        title.setStyle("-fx-font-size: 22px; -fx-font-weight: bold;");
        Label employeeLabel = new Label(user.firstName + " " + user.lastName);
        weekLabel = new Label();

        prevWeekButton = new Button("< Previous Week");
        nextWeekButton = new Button("Next Week >");

        nextWeekButton.setOnAction(e -> {
            currentWeekStart = currentWeekStart.plusWeeks(1);
            updateWeekLabel();
            loadSavedWeek();
        });

        prevWeekButton.setOnAction(e -> {
            currentWeekStart = currentWeekStart.minusWeeks(1);
            updateWeekLabel();
            loadSavedWeek();
        });



        lockedLabel = new Label();

        // Initialize ChoiceBoxes
        sundayHours = new ChoiceBox<>();
        mondayHours = new ChoiceBox<>();
        tuesdayHours = new ChoiceBox<>();
        wednesdayHours = new ChoiceBox<>();
        thursdayHours = new ChoiceBox<>();
        fridayHours = new ChoiceBox<>();
        saturdayHours = new ChoiceBox<>();

        sundayType = new ChoiceBox<>();
        mondayType = new ChoiceBox<>();
        tuesdayType = new ChoiceBox<>();
        wednesdayType = new ChoiceBox<>();
        thursdayType = new ChoiceBox<>();
        fridayType = new ChoiceBox<>();
        saturdayType = new ChoiceBox<>();

        ObservableList<String> regOrPTO = FXCollections.observableArrayList("Regular", "PTO");
        ChoiceBox<Integer>[] hoursBoxes = new ChoiceBox[]{sundayHours, mondayHours, tuesdayHours, wednesdayHours, thursdayHours, fridayHours, saturdayHours};
        ChoiceBox<String>[] typeBoxes = new ChoiceBox[]{sundayType, mondayType, tuesdayType, wednesdayType, thursdayType, fridayType, saturdayType};

        for (ChoiceBox<Integer> cb : hoursBoxes) {
            for (int i = 0; i <= 24; i++) cb.getItems().add(i);
            cb.setValue(0); // default
        }
        for (ChoiceBox<String> cb : typeBoxes) {
            cb.setItems(regOrPTO);
            cb.setValue("Regular");
        }

        // Create day labels and HBoxes
        HBox sundayBox = new HBox(20, new Label("Sunday: "), sundayType, sundayHours);
        HBox mondayBox = new HBox(20, new Label("Monday: "), mondayType, mondayHours);
        HBox tuesdayBox = new HBox(20, new Label("Tuesday: "), tuesdayType, tuesdayHours);
        HBox wednesdayBox = new HBox(20, new Label("Wednesday: "), wednesdayType, wednesdayHours);
        HBox thursdayBox = new HBox(20, new Label("Thursday: "), thursdayType, thursdayHours);
        HBox fridayBox = new HBox(20, new Label("Friday: "), fridayType, fridayHours);
        HBox saturdayBox = new HBox(20, new Label("Saturday: "), saturdayType, saturdayHours);

        // WEEK HEADER ROW
        HBox weekHeader = new HBox(15, prevWeekButton, weekLabel, nextWeekButton);
        weekHeader.setAlignment(Pos.CENTER);

        // add color to odd day hbox
        sundayBox.getStyleClass().add("oddDay-hbox");
        tuesdayBox.getStyleClass().add("oddDay-hbox");
        thursdayBox.getStyleClass().add("oddDay-hbox");
        saturdayBox.getStyleClass().add("oddDay-hbox");

        // add color to even day hbox
        mondayBox.getStyleClass().add("evenDay-hbox");
        wednesdayBox.getStyleClass().add("evenDay-hbox");
        fridayBox.getStyleClass().add("evenDay-hbox");

        VBox daysBox = new VBox(10, sundayBox, mondayBox, tuesdayBox, wednesdayBox, thursdayBox, fridayBox, saturdayBox);

        // Save button
        saveButton = new Button("Save");
        saveButton.setOnAction(e -> saveWeek());

        paneTime.getChildren().addAll(title, employeeLabel, weekHeader, daysBox, saveButton);

    }

    private void saveWeek() {
        double totalHours = 0;
        double totalPTO = 0;

        ChoiceBox<Integer>[] hoursBoxes = new ChoiceBox[]{sundayHours, mondayHours, tuesdayHours, wednesdayHours, thursdayHours, fridayHours, saturdayHours};
        ChoiceBox<String>[] typeBoxes = new ChoiceBox[]{sundayType, mondayType, tuesdayType, wednesdayType, thursdayType, fridayType, saturdayType};

        for (int i = 0; i < 7; i++) {
            int hours = hoursBoxes[i].getValue();
            if ("PTO".equals(typeBoxes[i].getValue())) totalPTO += hours;
            else totalHours += hours;
        }

        TimeEntry t = new TimeEntry();
        t.employeeId = employeeId;

        String weekStartStr = currentWeekStart.toString();
        t.date = weekStartStr;

        t.hoursWorked = totalHours;
        t.ptoHours = totalPTO;
        t.isLocked = false;

        List<TimeEntry> existing = TimeEntryDAO.getWeek(employeeId, weekStartStr, weekStartStr);
        if (existing.isEmpty()) TimeEntryDAO.insert(t);
        else {
            t.employeeId = existing.get(0).employeeId;
            TimeEntryDAO.update(t);
        }

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText("Saved");
        alert.setContentText("Weekly hours have been saved.");
        alert.showAndWait();

        // go back to main screen
        EmployeeView dashboard = new EmployeeView();
        App.mainPane.setCenter(dashboard.getEmployeeViewPane());

        
    }

    private void loadSavedWeek() {
        String weekStartStr = currentWeekStart.toString();

        
        List<TimeEntry> list = TimeEntryDAO.getWeek(employeeId, weekStartStr, weekStartStr);
        boolean locked = TimeEntryDAO.areEntriesLocked(employeeId, currentWeekStart.toString());

        saveButton.setDisable(locked);

        ChoiceBox<Integer>[] hoursBoxes = new ChoiceBox[]{
            sundayHours, mondayHours, tuesdayHours, wednesdayHours, thursdayHours, fridayHours, saturdayHours
        };
        ChoiceBox<String>[] typeBoxes = new ChoiceBox[]{
            sundayType, mondayType, tuesdayType, wednesdayType, thursdayType, fridayType, saturdayType
        };

        for (ChoiceBox<Integer> cb : hoursBoxes) cb.setDisable(locked);
        for (ChoiceBox<String> cb : typeBoxes) cb.setDisable(locked);
        lockedLabel.setText(locked ? "THIS WEEK IS LOCKED." : "");

        if (!list.isEmpty()) {
            TimeEntry t = list.get(0);
            double totalHours = t.hoursWorked;
            double totalPTO = t.ptoHours;

            // If you don't store per-day hours, just set totals in first 5 weekdays
            for (int i = 0; i < 7; i++) {
                if ("PTO".equals(typeBoxes[i].getValue())) {
                    hoursBoxes[i].setValue((int) totalPTO); // or divide evenly
                    typeBoxes[i].setValue("PTO");
                } else {
                    hoursBoxes[i].setValue((int) totalHours); // or divide evenly
                    typeBoxes[i].setValue("Regular");
                }
            }
        } else {
            // Default values
            for (ChoiceBox<Integer> cb : hoursBoxes) cb.setValue(0);
            for (ChoiceBox<String> cb : typeBoxes) cb.setValue("Regular");
        }
    }

    private void updateWeekLabel() {
        LocalDate end = currentWeekStart.plusDays(6);
        weekLabel.setText("Week of " + currentWeekStart + " to " + end);
    }


} 