package view;

import server.Employee;
import javafx.geometry.Pos;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

public class AdminView {
    private BorderPane paneAdminView = new BorderPane();
    private VBox vboxCenter = new VBox(30);
    private Salary salary;
    private EmployeePane employeePane;
    private Time time = new Time();
    private Search search = new Search();
    private AdminHome adminHome;
    private AdminNavBar adminNavBar = new AdminNavBar();
    private Info info = new Info();
    private Employee currentEmployee = null;

    public AdminView() {
        adminView();
    }

    public BorderPane getAdminViewPane() {
        return this.paneAdminView;
    }

    public VBox getVboxCenter() {
        return this.vboxCenter;
    }

    public Salary getSalaryPage() {
        return this.salary;
    }   

    public EmployeePane getEmployeePane() {
        return this.employeePane;
    }   

    public Time getTimePage() {
        return this.time;
    }   
    
    public Search getSearchPage() {
        return this.search;
    }   

    public AdminNavBar getAdminNavBarPage() {
        return this.adminNavBar;
    }   

    public AdminHome getAdminHomePage() {
        return this.adminHome;
    }

    public Info getInfoPage() {
        return this.info;
    }

    public void setCurrentEmployee(Employee emp) {
        this.currentEmployee = emp;
    }

    public Employee getCurrentEmployee() {
        return this.currentEmployee;
    }

    public boolean hasCurrentEmployee() {
        return this.currentEmployee != null;
    }

    /* Create admin view method */
    private void adminView() {
        // Initialize panes
        this.employeePane = new EmployeePane(this);
        this.salary = new Salary(this);
        this.adminHome = new AdminHome(paneAdminView);

        // Add admin home to vbox
        vboxCenter.getChildren().add(adminHome.getAdminHomePane());
        // Center children
        vboxCenter.setAlignment(Pos.CENTER);
        // add some padding to vbox
        vboxCenter.setStyle("-fx-padding: 30;");    

        // Create scroll pane to hold center items
        ScrollPane scrollPaneCenter = new ScrollPane();
        // add vbox to scroll pane
        scrollPaneCenter.setContent(vboxCenter);
        // make panable
        scrollPaneCenter.setPannable(true);
        // set fit to width and height
        scrollPaneCenter.setFitToWidth(true);
        scrollPaneCenter.setFitToHeight(true);

        // add scroll pane to admin veiw pane
        paneAdminView.setCenter(scrollPaneCenter);
        // add search bar to admin view pane
        paneAdminView.setTop(search.getSearchPane());
        // add admin nav bar to left of admin view pane
        paneAdminView.setLeft(adminNavBar.getAdminNavBar());
    }
}
