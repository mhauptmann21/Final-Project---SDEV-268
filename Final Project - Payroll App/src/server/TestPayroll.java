package server;

import java.sql.Time;

public class TestPayroll {
    
    public static void main(String[] args) {
        
        int employee_id = 1;

        System.out.println("---- PAYROLL TEST START ----");

        // 1. Load Employee
        Employee emp = EmployeeDAO.getEmployeeById(employee_id);
        if (emp == null) {
            System.out.println("Employee not found.");
            return;
        }
        System.out.println("Employee Loaded: " + emp.firstName + " " + emp.lastName);

        // 2. Load Time Entries
        var hours = TimeEntryDAO.getWeeklyHours(employee_id);
        System.out.println("Hours Loaded: " + hours);

        // 3. Run payroll calculation
        System.out.println("Calculating Payroll...");
        PayrollService.calculatePayroll(employee_id);

        System.out.println("Payroll calculation complete.");

        // 4. Confirm time entry lock
        boolean locked = TimeEntryDAO.areEntriesLocked(employee_id);
        System.out.println("Time Entries Locked: " + locked);

        System.out.println("---- PAYROLL TEST END ----");
    }
}
