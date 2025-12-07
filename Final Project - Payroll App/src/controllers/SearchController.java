package controllers;

import server.Employee;

public class SearchController {
    public static Employee findEmployeeById(int id) {
        return server.EmployeeDAO.getEmployeeById(id);
    }
}
