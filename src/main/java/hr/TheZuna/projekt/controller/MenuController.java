package hr.TheZuna.projekt.controller;

import hr.TheZuna.projekt.App;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.BorderPane;

import java.io.IOException;

public class MenuController {
    @FXML
    public void ispisiOsobe() {
        BorderPane root;
        try {
            root = (BorderPane) FXMLLoader.load(
                    getClass().getResource("/ispisOsoba.fxml"));
             App.setMainPage(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
