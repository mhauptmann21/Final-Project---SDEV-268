package view;

import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.control.Label;

public class AdminHome {
    private VBox paneAdminHome;
    
    public AdminHome() {
        adminHome();
    }

    public VBox getAdminHomePane() {
        return this.paneAdminHome;
    }

    /* Create admin home method */
    private void adminHome() {
        // Create vbox 
        paneAdminHome = new VBox(10);
        // Set to center alignment
        paneAdminHome.setAlignment(Pos.CENTER);
        // set size to max width of child
        //paneAdminHome.setFillWidth(false);

        //Label
        Label welcomeLabel = new Label("Welcome Admin!");

        // add label to admin home pane
        paneAdminHome.getChildren().addAll(welcomeLabel);
    }
}
