package server;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TimeEntryDAO {

    // Insert new time entry
    public static void insert(TimeEntry t) {
        String sql = """
            INSERT INTO time_entry (employee_id, date, hours_worked, pto_hours, is_locked) 
            VALUES (?, ?, ?, ?, ?)
            """;

        try (Connection conn = Database.connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, t.employeeId);
            stmt.setString(2, t.date);
            stmt.setDouble(3, t.hoursWorked);
            stmt.setDouble(4, t.ptoHours);
            stmt.setBoolean(5, t.isLocked);

            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Update entry
    public static void update(TimeEntry t) {
        String sql = """
            UPDATE time_entry 
            SET date=?, hours_worked=?, pto_hours=?, is_locked=?
            WHERE entry_id=?
            """;

        try (Connection conn = Database.connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, t.date);
            stmt.setDouble(2, t.hoursWorked);
            stmt.setDouble(3, t.ptoHours);
            stmt.setBoolean(4, t.isLocked);
            stmt.setInt(5, t.entryId);

            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Delete entry
    public static void delete(int entryId) {
        try (Connection conn = Database.connect();
             PreparedStatement stmt = conn.prepareStatement("DELETE FROM timeentry WHERE entry_id=?")) {

            stmt.setInt(1, entryId);
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Get entries for a specific employee and week
    public static List<TimeEntry> getWeek(int employeeId, String startDate, String endDate) {
        String sql = """
            SELECT * FROM time_entry
            WHERE employee_id=? AND date BETWEEN ? AND ?
            ORDER BY date ASC
            """;

        List<TimeEntry> entries = new ArrayList<>();

        try (Connection conn = Database.connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, employeeId);
            stmt.setString(2, startDate);
            stmt.setString(3, endDate);

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                TimeEntry t = new TimeEntry();
                t.entryId = rs.getInt("entry_id");
                t.employeeId = rs.getInt("employee_id");
                t.date = rs.getString("date");
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
    public static void lockWeek(int employeeId, String start, String end) {
        String sql = """
            UPDATE time_entry 
            SET is_locked = TRUE 
            WHERE employee_id=? AND date BETWEEN ? AND ?
            """;

        try (Connection conn = Database.connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, employeeId);
            stmt.setString(2, start);
            stmt.setString(3, end);

            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
