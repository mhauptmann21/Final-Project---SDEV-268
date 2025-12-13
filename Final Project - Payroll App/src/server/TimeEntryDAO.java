package server;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TimeEntryDAO {

    // Convert a date string (yyyy-mm-dd) into the Monday of that week
    public static LocalDate getWeekStart(String dateStr) {
        if (dateStr == null || dateStr.isBlank()) {
            throw new IllegalArgumentException("dateStr cannot be null");
        }
        return LocalDate.parse(dateStr);
    }


    // Insert new time entry
    public static void insert(TimeEntry t) {
        LocalDate weekStart = getWeekStart(t.date);

        String checkSql = """
            SELECT entry_id, hours_worked, pto_hours 
            FROM time_entry 
            WHERE employee_id = ? AND week_start = ?
            """;

        String insertSql = """
            INSERT INTO time_entry (employee_id, week_start, hours_worked, pto_hours, is_locked)
            VALUES (?, ?, ?, ?, ?)
            """;

        String updateSql = """
            UPDATE time_entry SET hours_worked=?, pto_hours=? WHERE employee_id=? AND week_start=?
            """;

        try (Connection conn = Database.connect()) {
            // check if week already exsists
            PreparedStatement check = conn.prepareStatement(checkSql);
            check.setInt(1, t.employeeId);
            check.setDate(2, Date.valueOf(weekStart));

            ResultSet rs = check.executeQuery();
            if (rs.next()) {
                // update totals
                double newHours = rs.getDouble("Hours_worked") + t.hoursWorked;
                double newPTO = rs.getDouble("pto_hours") + t.ptoHours;

                PreparedStatement update = conn.prepareStatement(updateSql);
                update.setDouble(1, newHours);
                update.setDouble(1, newPTO);
                update.setInt(3, t.employeeId);
                update.setDate(4, Date.valueOf(weekStart));
                update.executeUpdate();
            } else {
                // insert the new week's totals
                PreparedStatement insert = conn.prepareStatement(insertSql);
                insert.setInt(1, t.employeeId);
                insert.setDate(2, Date.valueOf(weekStart));
                insert.setDouble(3, t.hoursWorked);
                insert.setDouble(4, t.ptoHours);
                insert.setBoolean(5, t.isLocked);
                insert.executeUpdate();
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Update entry
    public static void update(TimeEntry t) {
        LocalDate weekStart = getWeekStart(t.date);

        String sql = """
            UPDATE time_entry 
            SET hours_worked=?, pto_hours=?, is_locked=?
            WHERE employee_id=? AND week_start=?
            """;

        try (Connection conn = Database.connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setDouble(1, t.hoursWorked);
            stmt.setDouble(2, t.ptoHours);
            stmt.setBoolean(3, t.isLocked);
            stmt.setInt(4, t.employeeId);
            stmt.setDate(5, Date.valueOf(weekStart));

            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Delete entry
    public static void delete(int entryId) {
        try (Connection conn = Database.connect();
             PreparedStatement stmt = conn.prepareStatement("DELETE FROM time_entry WHERE entry_id=?")) {

            stmt.setInt(1, entryId);
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Get entries for a specific employee and week
    public static List<TimeEntry> getWeek(int employeeId, String startDate, String endDate) {
        LocalDate weekStart = getWeekStart(startDate);
        LocalDate weekEnd = getWeekStart(endDate);

        String sql = """
            SELECT * FROM time_entry
            WHERE employee_id=? AND week_start BETWEEN ? AND ?
            ORDER BY week_start ASC
            """;

        List<TimeEntry> entries = new ArrayList<>();

        try (Connection conn = Database.connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, employeeId);
            stmt.setDate(2, Date.valueOf(weekStart));
            stmt.setDate(3, Date.valueOf(weekEnd));

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                TimeEntry t = new TimeEntry();
                t.entryId = rs.getInt("entry_id");
                t.employeeId = rs.getInt("employee_id");
                t.date = rs.getDate("week_start").toString();
                t.hoursWorked = rs.getDouble("hours_worked");
                t.ptoHours = rs.getDouble("pto_hours");
                t.isLocked = rs.getBoolean("is_locked");
                entries.add(t);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return entries;
    }

    // Lock entries after payroll
    public static void lockWeek(int employeeId, String weekStart, String weekEnd) {
        String sql = """
            INSERT INTO time_entries_lock (employee_id, week_start, week_end, locked)
            VALUES (?, ?, ?, TRUE)
            ON DUPLICATE KEY UPDATE locked = TRUE
            """;

        try (Connection conn = Database.connect();
            PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, employeeId);
            stmt.setString(2, weekStart);
            stmt.setString(3, weekEnd);

            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // unlock entries if admin needs to change something
    public static void unlockWeek(int employeeId, String weekStart) {
        String sql = "UPDATE time_entries_lock SET locked = FALSE WHERE employee_id=? AND week_start=?";

        try (Connection conn = Database.connect();
            PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, employeeId);
            stmt.setString(2, weekStart);

            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public static double getWeeklyHours(int employeeId) {
        double totalHours = 0;
        String sql = "SELECT SUM(hours_worked) AS total FROM time_entry WHERE employee_id=? AND WEEK(date)=WEEK(CURDATE())";

        try (Connection conn = Database.connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, employeeId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                totalHours = rs.getDouble("total");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return totalHours;
    }

    public static boolean areEntriesLocked(int employeeId, String weekStart) {
        String sql = "SELECT locked FROM time_entries_lock WHERE employee_id = ? AND week_start = ?";
        boolean locked = false;

        try (Connection conn = Database.connect();
            PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, employeeId);
            stmt.setString(2, weekStart);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                locked = rs.getBoolean("locked");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return locked;
    }

}
