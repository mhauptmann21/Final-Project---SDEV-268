## Payroll App:
This project is a payroll management application created using Java, JavaFx, and MongoDB.

This project uses a modular design, meaning different parts of the program are separated into folders based on their purpose. This makes the application easier to maintain, update, and debug.

## Project Structure:
The project is organized into the following main components:

App.java - starts the application
view/ - Contains the graphical user interface (GUI) created with JavaFx
controllers/ - Includes helper functions such as payroll calculations and input validation.  Handles employee data and payroll-related information
server/ - Stores employee records and generated payroll reports

## Features:
GUI using JavaFx
Payroll calculations based on employee data
Organized and modular code structure
Error handling for user input

Requirements:
Java SDK 25 or newer
JavaFx 25 or newer
Mongo 5.5.1

How to Run the Program:
Open Zipped Reposoity File in VS Code
Add Mongo and JavaFX to Java Project Referenced Libraries
Update lauch.json to include:

"type": "java",
            "name": "App",
            "request": "launch",
            "vmArgs": "--module-path /"your path and javafx version"/lib --add-modules javafx.controls,javafx.fxml",
            "mainClass": "view.App",

Add settings.json with:


{
  "java.project.referencedLibraries": [
    "lib/**/*.jar",
    include paths to javafx and mongodb libraries
  ]
}

Navigate to src/view/App.java
Press Run Java button (Play Button)


Design Overview:
This application uses a modular design by separating the user interface, payroll logic, and utility functions into different modules. This structure makes the code easier to understand, maintain, and update as the project grows.
