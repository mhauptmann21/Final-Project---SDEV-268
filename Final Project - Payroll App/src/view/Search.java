package view;

import javafx.scene.layout.HBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import server.Employee;

public class Search {
    private HBox paneSearch;
    private Employee foundEmployee = null;
    private TextField firstTextField = new TextField();
    private TextField lastTextField = new TextField();
    
    public Search() {
        search();
    }

    public Employee getFoundEmployee() {
        return this.foundEmployee;
    } 

    public HBox getSearchPane() {
        return this.paneSearch;
    }

    /* Create search method */
    private void search() {
        // Create hbox 
        paneSearch = new HBox(10);
        // Set to center alignment
        paneSearch.setAlignment(Pos.CENTER);
        // add color to search bar
        paneSearch.getStyleClass().add("search-hbox");

        // labels
        Label firstLabel = new Label("First Name: ");
        Label lastLabel = new Label("Last Name: ");
        // search button
        Button searchButton = new Button("Search");
        // event hanlder
        searchButton.setOnAction(event -> {
            handleSearch();
        });

        // add items to search pane
        paneSearch.getChildren().addAll(firstLabel, firstTextField, lastLabel, lastTextField, searchButton);
    }

    private void clearSearchFields() {
        firstTextField.clear();
        lastTextField.clear();
    }

    private void clearFoundEmployee() {
        this.foundEmployee = null;
    }

    private boolean isSearchFieldsEmpty() {
        return firstTextField.getText().isEmpty() && lastTextField.getText().isEmpty();
    }   

    private boolean isEmployeeFound() {
        return this.foundEmployee != null;
    }

    private void displayEmployeeNotFoundMessage() {         
        // show alert
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Employee Not Found");
        alert.setHeaderText(null);
        alert.setContentText("No employee found with the given first and last name.");
        alert.showAndWait();
    }

    private void displayEmptyFieldsMessage() {         
        // show alert
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Empty Fields");
        alert.setHeaderText(null);
        alert.setContentText("Please enter both first and last names to search.");
        alert.showAndWait();
    }

    private void displayEmployeeFoundMessage() {         
        // show alert
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Employee Found");
        alert.setHeaderText(null);
        alert.setContentText("Employee found: " + this.foundEmployee.firstName + " " + this.foundEmployee.lastName);
        alert.showAndWait();
    }   

    private void handleSearch() {
        // clear previous results
        clearFoundEmployee();

        if (isSearchFieldsEmpty()) {
            displayEmptyFieldsMessage();
            return;
        }

        // get input
        String firstInput = firstTextField.getText().trim();
        String lastInput = lastTextField.getText().trim();

        // replace with search result
        this.foundEmployee = controllers.EmployeeController.getByName(firstInput, lastInput);
        
        // show messages
        if (isEmployeeFound()) {
            displayEmployeeFoundMessage();
         
        } else {
            displayEmployeeNotFoundMessage();
        }

        // set current employee in AdminView
        App.adminView.setCurrentEmployee(this.foundEmployee);

        // clear fields after search
        clearSearchFields();
    }   
}
