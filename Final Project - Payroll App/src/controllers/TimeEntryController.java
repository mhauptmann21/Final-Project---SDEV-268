package controllers;

import server.TimeEntry;
import server.TimeEntryDAO;

import java.util.List;

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
}

