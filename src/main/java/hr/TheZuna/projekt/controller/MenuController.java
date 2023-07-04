package hr.TheZuna.projekt.controller;

import hr.TheZuna.projekt.App;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.BorderPane;

import javax.swing.*;
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
    public void ispisiOsobeForUser() {
        BorderPane root;
        try {
            root =  (BorderPane)FXMLLoader.load(getClass().getResource("ispisOsoba2.fxml"));

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
    public void ispisiKolegeForUser() {
        BorderPane root;
        try {
            root =  (BorderPane)FXMLLoader.load(getClass().getResource("IspisKolega2.fxml"));
            App.setMainPage(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    public void ispisObiteljskogClana(){
        BorderPane root;
        try{
            root = (BorderPane)FXMLLoader.load(getClass().getResource("ispisObiteljClan.fxml"));
            App.setMainPage(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    public void ispisObiteljskogClanaForUser(){
        BorderPane root;
        try{
            root = (BorderPane)FXMLLoader.load(getClass().getResource("ispisObiteljClan2.fxml"));
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
    @FXML
    public void homeScreenForUser(){
        BorderPane root;
        try {
            root =  (BorderPane)FXMLLoader.load(getClass().getResource("PrikazRodendana.fxml"));
            App.setMainPage(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    public void showAllUsersScreen(){
        BorderPane root;
        try {
            root =  (BorderPane)FXMLLoader.load(getClass().getResource("listAllUsers.fxml"));
            App.setMainPage(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
