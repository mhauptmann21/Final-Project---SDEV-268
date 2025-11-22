package server;

public class Employee {

    public int employeeId;
    public String firstName;
    public String lastName;
    public String status;
    public String payType;
    public double baseSalary;
    public String medical;
    public int dependents;
    public String dateOfBirth;
    public String dateHired;
    public String email;
    public String department;
    public String jobTitle;

    public Employee(int employeeId, String firstName, String lastName, String status,
                    String payType, double baseSalary, String medical, int dependents,
                    String dateOfBirth, String dateHired, String email,
                    String department, String jobTitle) {

        this.employeeId = employeeId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.status = status;
        this.payType = payType;
        this.baseSalary = baseSalary;
        this.medical = medical;
        this.dependents = dependents;
        this.dateOfBirth = dateOfBirth;
        this.dateHired = dateHired;
        this.email = email;
        this.department = department;
        this.jobTitle = jobTitle;
    }
}

