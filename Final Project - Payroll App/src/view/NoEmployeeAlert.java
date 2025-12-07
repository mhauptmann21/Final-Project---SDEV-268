package view;

import javafx.scene.control.Alert;

public class NoEmployeeAlert {
    Alert noEmployeeAlert;

    public NoEmployeeAlert() {
        noEmployeeAlert();
    }

    public Alert getNoEmployeeAlert() {
        return this.noEmployeeAlert;
    }

    private void noEmployeeAlert() {
        noEmployeeAlert = new Alert(Alert.AlertType.ERROR);
        noEmployeeAlert.setTitle("Error");
        noEmployeeAlert.setHeaderText("No Employee Selected");
        noEmployeeAlert.setContentText("Please search for an employee.");
    }
}
