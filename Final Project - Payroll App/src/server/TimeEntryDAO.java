package server;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TimeEntryDAO {
    
    public static List<Double> getWeeklyHours(int employeeId) {
        List<Double> hours = new ArrayList<>();

        String sql = "SELECT hours_worked FROM time_entry "
                     + "WHERE employee_id = ? ORDER BY date ASC LIMIT 7";
        
        try (Connection conn = Database.connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
             
            stmt.setInt(1, employeeId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                hours.add(rs.getDouble("hours_worked"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return hours;
    }

    public static void lockTimeEntries(int employeeId) {
        String sql = "UPDATE time_entry SET is_locked = TRUE WHERE employee_id = ?";

        try (Connection conn = Database.connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
             
            stmt.setInt(1, employeeId);
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static boolean areEntriesLocked(int employeeId) {
        String sql = "SELECT is_locked FROM time_entry WHERE employee_id = ?";

        try (Connection conn = Database.connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
             
            stmt.setInt(1, employeeId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                if (!rs.getBoolean("is_locked")) {
                    return false;
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return true;
    }
}   

