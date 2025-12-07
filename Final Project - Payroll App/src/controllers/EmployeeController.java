package controllers;

import server.Employee;
import server.EmployeeDAO;

public class EmployeeController {

    public static void add(Employee e) {
        EmployeeDAO.insertEmployee(e);
    }

    public static void update(Employee e) {
        EmployeeDAO.updateEmployee(e);
    }

    public static void delete(int id) {
        EmployeeDAO.deleteEmployee(id);
    }

    public static Employee get(int id) {
        return EmployeeDAO.getEmployeeById(id);
    }
}
