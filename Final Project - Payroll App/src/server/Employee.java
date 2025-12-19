package server;

import org.bson.types.ObjectId;

public class Employee {
    public ObjectId mongoId;
    public int employeeId;
    public String firstName;
    public String surName;
    public String lastName;
    public String status;            // Active / Terminated
    public String dateOfBirth;
    public String gender;
    public String dateHired;
    public String email;
    public String username;
    public String password;
    public String department;
    public String jobTitle;
    public String hashedPassword;
    public String hireDate;
    public String address1;
    public String address2;
    public String city;
    public String state;
    public String zip;

    public String salaryType;
    public Double baseSalary;
    public String medical;
    public int dependents;
}


