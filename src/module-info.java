module ProjectZakatOOP {
    requires javafx.controls;
    requires javafx.fxml;

    opens ZonaZakat.fxmlFiles;
    opens ZonaZakat.controller;
    opens ZonaZakat.main;
}