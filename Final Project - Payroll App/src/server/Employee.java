package server;

import org.bson.types.ObjectId;

public class Employee {
    public ObjectId mongoId;
    public int employeeId;
    public String firstName;
    public String lastName;
    public String status;            // Active / Terminated
    public String payType;           // HOURLY / SALARY
    public double baseSalary;        // Salary or hourly rate
    public String medical;           // SINGLE / FAMILY
    public int dependents;
    public String dateOfBirth;
    public String dateHired;
    public String email;
    public String username;
    public String password;
    public String department;
    public String jobTitle;
    public String hashedPassword;
    public String hireDate;
}


