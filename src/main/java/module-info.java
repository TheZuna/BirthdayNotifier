module hr.TheZuna.projekt {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires javafx.graphics;
    requires java.desktop;
    requires org.slf4j;

    exports hr.TheZuna.projekt.controller;
    opens hr.TheZuna.projekt.controller to javafx.fxml;
    exports hr.TheZuna.projekt;
    opens hr.TheZuna.projekt to javafx.fxml;
    exports hr.TheZuna.projekt.users;
    opens hr.TheZuna.projekt.users to javafx.fxml;
}