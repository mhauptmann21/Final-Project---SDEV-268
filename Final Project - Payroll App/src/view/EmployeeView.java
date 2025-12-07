package view;

import javafx.geometry.Pos;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

public class EmployeeView {
    private BorderPane paneEmployeeView = new BorderPane();

    public EmployeeView() {
        employeeView();
    }

    public BorderPane getEmployeeViewPane() {
        return this.paneEmployeeView;
    }

    /* Create admin view method */
    private void employeeView() {
        // instantiate pages
        Time time = new Time();
        EmployeeNavBar employeeNavBar = new EmployeeNavBar();
        // add paycheck calculator page

        // Create vbox to hold center items for employee view pane
        VBox vboxCenter = new VBox(30);

        /* 
        // Either add employee home or default to time card view to vbox
        vboxCenter.getChildren().add();
        */
        // Center children
        vboxCenter.setAlignment(Pos.CENTER);

        // add center vbox to admin veiw pane
        paneEmployeeView.setCenter(vboxCenter);
        // add employee nav bar to left of employee view pane
        paneEmployeeView.setLeft(employeeNavBar.getEmployeeNavBar());
    }
}
