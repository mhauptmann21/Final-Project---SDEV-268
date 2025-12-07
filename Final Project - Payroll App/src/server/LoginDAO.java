package server;

import java.sql.*;

public class LoginDAO {

    public Employee login(String username, String password) {
        String sql = "SELECT * FROM employees WHERE username = ? AND password = ?";

        try (Connection conn = Database.connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, username);
            stmt.setString(2, password);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Employee emp = new Employee();
                emp.employeeId = rs.getInt("employee_id");
                emp.firstName = rs.getString("first_name");
                emp.lastName = rs.getString("last_name");
                emp.email = rs.getString("email");
                emp.username = rs.getString("username");
                emp.password = rs.getString("password");
                emp.status = rs.getString("status");
                emp.department = rs.getString("department");
                emp.jobTitle = rs.getString("job_title");
                emp.payType = rs.getString("pay_type");
                return emp;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}

