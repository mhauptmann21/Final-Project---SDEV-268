package server;

import java.util.List;

public class PayrollService {

    public static void calculatePayroll(int employeeId, String periodStart, String periodEnd) {

        // Load employees
        Employee emp = EmployeeDAO.getEmployeeById(employeeId);
        List<Int> hours = TimeEntryDAO.getWeeklyHours(employeeId);

        // Gross Pay
        double gross = PayrollCalculator.calculateGrossPay(
            emp.payType,
            emp.baseSalary,
            hours
        );

        // Pretax Deductions
        double medical = PayrollCalculator.calculateMedical(emp.medical);
        double stipend = PayrollCalculator.calculateDependentStipend(emp.dependents);

        // Taxable Income
        double taxable = gross - medical + stipend;

        // Employee Taxes
        double state = PayrollCalculator.calculateStateTax(taxable);
        double federal = PayrollCalculator.calculateFederalTax(taxable);
        double ss = PayrollCalculator.calculateSocialSecurity(taxable);
        double medicare = PayrollCalculator.calculateMedicare(taxable);

        // Employer Taxes
        double federalEmployer = PayrollCalculator.calculateFederalTax(taxable);
        double ssEmployer = PayrollCalculator.calculateSocialSecurity(taxable);
        double medicareEmployer = PayrollCalculator.calculateMedicare(taxable);

        // Net Pay
        double net = gross - (medical + stipend + state + federal + ss + medicare);

        // Save to DB
        PayrollDAO.insertPayroll(
            employeeId,
            "2025-01-01",
            "2025-01-07",
            gross,
            medical,
            stipend,
            state,
            federal,
            federalEmployer,
            ss,
            ssEmployer,
            medicare,
            medicareEmployer,
            net
        );

        // Lock Time Entries
        TimeEntryDAO.lockWeek(employeeId, periodStart, periodEnd);

    }
    
}
