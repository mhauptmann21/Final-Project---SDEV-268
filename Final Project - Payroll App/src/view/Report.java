package view;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;


public class Report {
    private VBox paneReport;
    
    public Report() {
        report();
    }

    public VBox getReportPane() {
        return this.paneReport;
    }

    /* Create report method */
    private void report() {
        // Create vbox 
        paneReport = new VBox(50);
        // Set to center alignment
        paneReport.setAlignment(Pos.CENTER);

        // create buttons
        Button createReportBTN = new Button("Create HR Report");
        Button printBTN = new Button("Print HR Report");

        // event handler
        createReportBTN.setOnAction(e -> {
            createReport();
        });

        printBTN.setOnAction(e -> {
            printReport();
        });

        // add buttons to vbox
        paneReport.getChildren().addAll(createReportBTN, printBTN);
    }

    private void createReport() {
        //TODO: create report with gross and net payroll
    }

    private void printReport() {
        //TODO: print the report
    }
}
