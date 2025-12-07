package view;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.layout.BorderPane;

public class AdminHome {

    private VBox paneAdminHome;
    private BorderPane root; // reference to main layout

    // Constructor requires the root BorderPane from App
    public AdminHome(BorderPane root) {
        this.root = root;
        adminHome();
    }

    public VBox getAdminHomePane() {
        return this.paneAdminHome;
    }

    /* Create admin home method */
    private void adminHome() {
        paneAdminHome = new VBox(20);
        paneAdminHome.setAlignment(Pos.CENTER);

        Label welcomeLabel = new Label("Welcome Admin!");

        // Create Payroll Button
        Button payrollRunBtn = new Button("Run Payroll");

        // HANDLE CLICK
        payrollRunBtn.setOnAction(e -> {
            PayrollRun pr = new PayrollRun();
            root.setCenter(pr.getPane());
        });

        // Add to layout
        paneAdminHome.getChildren().addAll(welcomeLabel, payrollRunBtn);
    }
}
