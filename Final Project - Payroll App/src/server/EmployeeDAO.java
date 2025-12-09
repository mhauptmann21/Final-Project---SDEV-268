package server;

import java.sql.*;
import java.time.LocalDate;

import security.SecurityModule;

public class EmployeeDAO {

    // INSERT EMPLOYEE
    public static void insertEmployee(Employee e) {
        String sql = """
            INSERT INTO employees (
                first_name, last_name, status, pay_type, base_salary, medical,
                dependents, date_of_birth, date_hired, email, username,
                department, job_title, password
            ) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
            """;

        try (Connection conn = Database.connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, e.firstName);
            stmt.setString(2, e.lastName);
            stmt.setString(3, e.status);
            stmt.setString(4, e.payType);
            stmt.setDouble(5, e.baseSalary);
            stmt.setString(6, e.medical);
            stmt.setInt(7, e.dependents);
            stmt.setString(8, e.dateOfBirth);
            stmt.setString(9, e.dateHired);
            stmt.setString(10, e.email);
            stmt.setString(11, e.username);
            stmt.setString(12, e.department);
            stmt.setString(13, e.jobTitle);
            stmt.setString(14, SecurityModule.md5Hash(e.password));
            
            stmt.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    // Adding a new employee from registering
    public static boolean insertNewEmployee(
            String firstName,
            String lastName,
            String email,
            String username,
            String password
    ) {
        Employee e = new Employee();

        e.firstName = firstName;
        e.lastName = lastName;
        e.email = email;
        e.username = username;
        e.password = password;

        // REQUIRED defaults so INSERT works
        e.status = "ACTIVE";
        e.payType = "SALARY";          // or HOURLY
        e.baseSalary = 0.00;           // default
        e.medical = "SINGLE";          // enum
        e.dependents = 0;
        e.dateOfBirth = "2000-01-01";  // TEMP default
        e.dateHired = LocalDate.now().toString();
        e.department = "NONE";
        e.jobTitle = "New Hire";

        try {
            insertEmployee(e);
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }


    // UPDATE EMPLOYEE
    public static void updateEmployee(Employee e) {
        String sql = """
            UPDATE employees
            SET first_name=?, last_name=?, status=?, pay_type=?, base_salary=?,
                medical=?, dependents=?, date_of_birth=?, date_hired=?, email=?,
                department=?, job_title=?, hashedPassword=?
            WHERE employee_id=?
            """;

        try (Connection conn = Database.connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, e.firstName);
            stmt.setString(2, e.lastName);
            stmt.setString(3, e.status);
            stmt.setString(4, e.payType);
            stmt.setDouble(5, e.baseSalary);
            stmt.setString(6, e.medical);
            stmt.setInt(7, e.dependents);
            stmt.setString(8, e.dateOfBirth);
            stmt.setString(9, e.dateHired);
            stmt.setString(10, e.email);
            stmt.setString(11, e.department);
            stmt.setString(12, e.jobTitle);
            stmt.setInt(13, e.employeeId);

            stmt.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    // DELETE EMPLOYEE
    public static void deleteEmployee(int id) {
        try (Connection conn = Database.connect();
             PreparedStatement stmt = conn.prepareStatement("DELETE FROM employees WHERE employee_id=?")) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    // GET BY ID
    public static Employee getEmployeeById(int id) {
        String sql = "SELECT * FROM employees WHERE employee_id=?";

        try (Connection conn = Database.connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Employee e = new Employee();
                e.employeeId = rs.getInt("employee_id");
                e.firstName = rs.getString("first_name");
                e.lastName = rs.getString("last_name");
                e.status = rs.getString("status");
                e.payType = rs.getString("pay_type");
                e.baseSalary = rs.getDouble("base_salary");
                e.medical = rs.getString("medical");
                e.dependents = rs.getInt("dependents");
                e.dateOfBirth = rs.getString("date_of_birth");
                e.dateHired = rs.getString("date_hired");
                e.email = rs.getString("email");
                e.department = rs.getString("department");
                e.jobTitle = rs.getString("job_title");

                return e;
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return null;
    }

    // GET BY NAME
    public static Employee getEmployeeByName(String first, String last) {
    String sql = "SELECT * FROM employee WHERE first_name = ? AND last_name = ?";
    
    try (Connection conn = Database.connect();
         PreparedStatement stmt = conn.prepareStatement(sql)) {

        stmt.setString(1, first);
        stmt.setString(2, last);

        ResultSet rs = stmt.executeQuery();

        if (rs.next()) {
            Employee emp = new Employee();
            emp.employeeID = rs.getInt("employee_id");
            emp.firstName = rs.getString("first_name");
            emp.lastName = rs.getString("last_name");
            emp.position = rs.getString("position");
            emp.payType = rs.getString("pay_type");
            emp.hourlyRate = rs.getDouble("hourly_rate");
            emp.salary = rs.getDouble("salary");
            emp.department = rs.getString("department");
            emp.isAdmin = rs.getBoolean("is_admin");
            return emp;
        }

    } catch (Exception e) {
        e.printStackTrace();
    }
    return null;
}

}
