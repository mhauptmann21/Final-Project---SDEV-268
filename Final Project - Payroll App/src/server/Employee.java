package server;

public class Employee {
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

    // Optional fields not in DB yet (UI fields)
    public String gender;
    public String address1;
    public String address2;
    public String city;
    public String state;
    public String zip;
    public String photo;
}


