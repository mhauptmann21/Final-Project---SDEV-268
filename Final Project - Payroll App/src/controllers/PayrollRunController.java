package controllers;

import server.Employee;
import server.EmployeeDAO;
import server.PayrollService;
import server.TimeEntryDAO;

public class PayrollRunController {
    
    public static Employee getEmployee(int id) {
        return EmployeeDAO.getEmployeeById(id);
    }

    public static void runPayroll(int employeeId, String startDate, String endDate) {
        PayrollService.calculatePayroll(employeeId, startDate, endDate);

        // Lock time entries for that week
        TimeEntryDAO.lockWeek(employeeId, startDate, endDate);
    }
}
