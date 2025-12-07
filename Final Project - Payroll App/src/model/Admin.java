package model;

import server.Employee;

public class Admin extends Employee{
    // Attributes
    private Boolean isAdmin = true;

    // Constructor
    public Admin() {
        super();
    }

    // Getter
    public Boolean getIsAdmin() {
        return this.isAdmin;
    }
}
