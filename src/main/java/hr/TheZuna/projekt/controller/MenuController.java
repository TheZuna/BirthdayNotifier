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
            root =  (BorderPane)FXMLLoader.load(getClass().getResource("ispisOsoba.fxml"));

             App.setMainPage(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    public void ispisiKolege() {
        BorderPane root;
        try {
            root =  (BorderPane)FXMLLoader.load(getClass().getResource("IspisKolega.fxml"));
            App.setMainPage(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    public void homeScreen(){
        BorderPane root;
        try {
            root =  (BorderPane)FXMLLoader.load(getClass().getResource("PrikazRodendana.fxml"));
            App.setMainPage(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
