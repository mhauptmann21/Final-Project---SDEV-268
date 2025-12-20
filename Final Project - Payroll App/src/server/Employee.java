package server;

import java.util.List;

import org.bson.types.ObjectId;

public class Employee {
    public ObjectId mongoId;
    public String employeeId;
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

    private String payType;       // "SALARY" or "HOURLY"
    private Double hourlyRate;
    private List<Double> weeklyHours;
    private String medicalType;   // "FAMILY" or "SINGLE"

    private double grossPay;
    private double netPay;

    /* ---------- GETTERS ---------- */

    public String getId() { return employeeId; }
    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }

    public String getPayType() {
        return payType;
    }

    public double getBaseSalary() {
        return baseSalary != null ? baseSalary : 0.0;
    }


    public double getHourlyRate() {
        return hourlyRate != null ? hourlyRate : 0.0;
    }

    public List<Double> getWeeklyHours() {
        return weeklyHours;
    }

    public String getMedicalType() {
        return medicalType;
    }

    public int getDependents() {
        return dependents;
    }

    public double getGrossPay() {
        return grossPay;
    }

    public double getNetPay() {
        return netPay;
    }

    /* ---------- SETTERS ---------- */

    public void setId(String employeeId) { this.employeeId = employeeId; }
    public void setFirstName(String firstName) { this.firstName = firstName; }
    public void setLastName(String lastName) { this.lastName = lastName; }

    public void setGrossPay(double grossPay) {
        this.grossPay = grossPay;
    }

    public void setNetPay(double netPay) {
        this.netPay = netPay;
    }

    public void setPayType(String payType) { this.payType = payType; }
    public void setHourlyRate(Double hourlyRate) { this.hourlyRate = hourlyRate; }
    public void setBaseSalary(Double baseSalary) { this.baseSalary = baseSalary; }
    public void setMedicalType(String medicalType) { this.medicalType = medicalType; }
    
    public void setDependents(Integer dependents) {
        this.dependents = (dependents != null) ? dependents : 0;
    }

    public void setWeeklyHours(List<Double> weeklyHours) { this.weeklyHours = weeklyHours; }
}