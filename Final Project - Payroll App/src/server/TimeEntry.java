package server;

import org.bson.types.ObjectId;

public class TimeEntry {
    public ObjectId mongoId;
    public int employeeId;
    public String date;
    public double hoursWorked;
    public double ptoHours;
    public boolean isLocked;
}
