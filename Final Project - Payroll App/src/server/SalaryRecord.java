package server;

import org.bson.types.ObjectId;

public class SalaryRecord {
    public ObjectId mongoId;
    public ObjectId employeeId;
    public String salaryType;
    public double baseSalary;
    public String medical;
    public int dependents;
}
