package controllers;

import server.Employee;

public class SessionController {

    private static Employee currentUser;

    public static void setCurrentUser(Employee user) {
        currentUser = user;
    }

    public static Employee getCurrentUser() {
        return currentUser;
    }
}
