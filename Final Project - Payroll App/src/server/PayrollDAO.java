package server;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PayrollDAO {

    public static void insertPayroll(
        int employeeId,
        String periodStart,
        String periodEnd,
        double grossPay,
        double medicalDeduction,
        double dependentsStipend,
        double stateTax,
        double federalEmp,
        double federalEmployer,
        double ssEmp,
        double ssEmployer,
        double medicareEmp,
        double medicareEmployer,
        double netPay
    ) {
        String sql = "INSERT INTO payroll (employee_id, period_start, period_end, gross_pay, " + 
                "medical_deduction, dependents_stipend, state_tax, federal_tax_emp, federal_tax_employer, " +
                "social_sec_emp, social_sec_employer, medicare_emp, medicare_employer, net_pay) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        
        try (Connection conn = Database.connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
             
            stmt.setInt(1, employeeId);
            stmt.setString(2, periodStart);
            stmt.setString(3, periodEnd);
            stmt.setDouble(4, grossPay);
            stmt.setDouble(5, medicalDeduction);
            stmt.setDouble(6, dependentsStipend);
            stmt.setDouble(7, stateTax);
            stmt.setDouble(8, federalEmp);
            stmt.setDouble(9, federalEmployer);
            stmt.setDouble(10, ssEmp);
            stmt.setDouble(11, ssEmployer);
            stmt.setDouble(12, medicareEmp);
            stmt.setDouble(13, medicareEmployer);
            stmt.setDouble(14, netPay);

            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
}
