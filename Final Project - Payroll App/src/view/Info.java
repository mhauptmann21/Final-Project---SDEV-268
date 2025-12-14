package view;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;
import javafx.scene.text.Font;

public class Info {
    private VBox paneInfo;
    
    public Info() {
        info();
    }

    public VBox getInfoPane() {
        return this.paneInfo;
    }

    /* Create search method */
    private void info() {
        // Create vbox 
        paneInfo = new VBox(20);
        // Set to center alignment
        paneInfo.setAlignment(Pos.CENTER);

        // create labels
        Label titleLabel = new Label("Time tracking and Payroll Application Information");
        Label infoLabel = new Label("This application allows employees to enter time, PTO, and calculate paychecks.  Admin's can add/edit/delete employee and time data as well as run payroll.");
        Label versionLabel = new Label("Version: 1.0.0");
        Label dateLabel = new Label("Latest update: December 19, 2025");
        Label creatorsLabel = new Label("Created by: Madison Hauptmann, Regine Benton, and Leah Boalich");

        // wrapping the info label
        infoLabel.setWrapText(true);
        // Setting the alignment of the infolabel
        infoLabel.setTextAlignment(TextAlignment.CENTER);
        // set max width of infolabel
        infoLabel.setMaxWidth(300);
        // make title larger and bold
        titleLabel.setFont(Font.font("Veranda", FontWeight.BOLD, 18));

        //add all labels to vbox
        paneInfo.getChildren().addAll(titleLabel, infoLabel, versionLabel, dateLabel, creatorsLabel);
    }
}
