module hr.java.projekt {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires javafx.graphics;
    requires java.desktop;

    exports hr.TheZuna.projekt.controller;
    opens hr.TheZuna.projekt.controller to javafx.fxml;
    exports hr.TheZuna.projekt;
    opens hr.TheZuna.projekt to javafx.fxml;
}