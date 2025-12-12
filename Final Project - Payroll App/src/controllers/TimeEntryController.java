package controllers;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;

import server.Employee;
import server.TimeEntry;
import server.TimeEntryDAO;

public class TimeEntryController {

    public static void add(TimeEntry t) {
        TimeEntryDAO.insert(t);
    }

    public static void update(TimeEntry t) {
        TimeEntryDAO.update(t);
    }
    
    public static void delete(int entryId) {
        TimeEntryDAO.delete(entryId);
    }

    public static List<TimeEntry> getWeek(int employeeId, String start, String end) {
        return TimeEntryDAO.getWeek(employeeId, start, end);
    }

    public static void lockWeek(int employeeId, String start, String end) {
        TimeEntryDAO.lockWeek(employeeId, start, end);
    }

    public void loadWeek() {

        Employee user = SessionController.getCurrentUser();

        LocalDate today = LocalDate.now();
        LocalDate weekStart = today.with(DayOfWeek.MONDAY);
        LocalDate weekEnd = today.with(DayOfWeek.SUNDAY);

        List<TimeEntry> entries = TimeEntryDAO.getWeek(
                user.employeeId,
                weekStart.toString(),
                weekEnd.toString()
        );

        boolean locked = TimeEntryDAO.areEntriesLocked(
                user.employeeId,
                weekStart.toString()
        );

        // fill UI fields
        for (TimeEntry t : entries) {
            switch (t.date) {
                case String d when d.equals(weekStart.plusDays(0).toString()) ->
                    mondayHours.setText(String.valueOf(t.hoursWorked));
                case String d when d.equals(weekStart.plusDays(1).toString()) ->
                    tuesdayHours.setText(String.valueOf(t.hoursWorked));
                case String d when d.equals(weekStart.plusDays(2).toString()) ->
                    wednesdayHours.setText(String.valueOf(t.hoursWorked));
                case String d when d.equals(weekStart.plusDays(3).toString()) ->
                    thursdayHours.setText(String.valueOf(t.hoursWorked));
                case String d when d.equals(weekStart.plusDays(4).toString()) ->
                    fridayHours.setText(String.valueOf(t.hoursWorked));
                case String d when d.equals(weekStart.plusDays(5).toString()) ->
                    saturdayHours.setText(String.valueOf(t.hoursWorked));
                case String d when d.equals(weekStart.plusDays(6).toString()) ->
                    sundayHours.setText(String.valueOf(t.hoursWorked));
            }
        }

        // disable if locked
        if (locked) {
            disableAllInputs();
            saveButton.setDisable(true);
        } else {
            enableAllInputs();
            saveButton.setDisable(false);
        }
    }


    private void disableAllInputs() {
        mondayHours.setDisable(true);
        mondayPto.setDisable(true);
    }
}

