package hr.TheZuna.projekt.controller;

import hr.TheZuna.projekt.App;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.BorderPane;

import java.io.File;
import java.io.IOException;
import java.net.URL;

public class MenuController {
    @FXML
    public void ispisiOsobe() {
        BorderPane root;
        try {
            URL url = new File("src/main/java/hr/TheZuna/projekt/controller/ispisOsoba.fxml").toURI().toURL();
            root = (BorderPane) FXMLLoader.load(url);
             App.setMainPage(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
