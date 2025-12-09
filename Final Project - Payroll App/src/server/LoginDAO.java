package server;

import java.sql.*;
import security.SecurityModule;

public class LoginDAO {

    public Employee login(String username, String password) {

        String sql = "SELECT * FROM employees WHERE username = ?";

        try (Connection conn = Database.connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, username);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                String storedHash = rs.getString("password");
                String providedHash = SecurityModule.md5Hash(password);

                //Compare hashed entered password to stored hash
                if (providedHash.equals(storedHash)) {
                    Employee emp = new Employee();
                    emp.employeeId = rs.getInt("employee_id");
                    emp.firstName = rs.getString("first_name");
                    emp.lastName = rs.getString("last_name");
                    emp.email = rs.getString("email");
                    emp.username = rs.getString("username");
                    emp.status = rs.getString("status");
                    emp.department = rs.getString("department");
                    emp.jobTitle = rs.getString("job_title");
                    emp.payType = rs.getString("pay_type");

                    return emp;
                } else {
                    // Password does NOT match
                    return null;
                }

                
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}

