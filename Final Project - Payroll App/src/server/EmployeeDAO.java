package server;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EmployeeDAO {

    public static Employee getEmployeeById(int employeeId) {

        String sql = "SELECT employee_id, first_name, last_name, status, pay_type, base_salary, " +
                     "medical, dependents, date_of_birth, date_hired, email, department, job_title " +
                     "FROM employees WHERE employee_id = ?";

        try (Connection conn = Database.connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, employeeId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Employee(
                    rs.getInt("employee_id"),
                    rs.getString("first_name"),
                    rs.getString("last_name"),
                    rs.getString("status"),
                    rs.getString("pay_type"),
                    rs.getDouble("base_salary"),
                    rs.getString("medical"),
                    rs.getInt("dependents"),
                    rs.getString("date_of_birth"),
                    rs.getString("date_hired"),
                    rs.getString("email"),
                    rs.getString("department"),
                    rs.getString("job_title")
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null; // employee not found
    }
}

