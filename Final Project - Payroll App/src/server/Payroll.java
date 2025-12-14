package server;

import org.bson.types.ObjectId;

public class Payroll {
    public ObjectId mongoId;
    public int employeeId;
    public String periodStart;
    public String periodEnd;
    public double grossPay;
    public double medicalDeduction;
    public double dependentsStipend;
    public double stateTax;
    public double federalTaxEmp;
    public double federalTaxEmployer;
    public double socialSecEmp;
    public double socialSecEmployer;
    public double medicareEmp;
    public double medicareEmployer;
    public double netPay;
}

