package hr.TheZuna.projekt.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import hr.TheZuna.projekt.App;
import hr.TheZuna.projekt.users.User;
import hr.TheZuna.projekt.users.UserAuthentication;
import hr.TheZuna.projekt.users.UserRole;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;

import javax.sound.midi.Soundbank;
import javax.swing.text.html.Option;

public class UserAuthenticationController {

    public static Optional<User> currentUser = Optional.empty();

    @FXML
    private TextField username;
    @FXML
    private TextField password;
    /*
    @FXML
    public void initialize (){
        try{
            users = UserAuthentication.readUsersFromFile();
        }catch (IOException ex){
            System.out.println("Nije pravilno ocitao users.txt");
        }
    }

     */
    public void login() throws IOException {
        Map<String, String> users = UserAuthentication.readUsersFromFile();
        Alert a = new Alert(Alert.AlertType.NONE);
        if (users.containsKey(username.getText())) {
            String storedPassword = users.get(username.getText());
            if (password.getText().equals(storedPassword)) {
                System.out.println("Login successful!");
                BorderPane root;
                try {
                    root =  (BorderPane) FXMLLoader.load(getClass().getResource("PrikazRodendana.fxml"));

                    App.setMainPage(root);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if(username.getText().equals("admin")){
                    currentUser = Optional.of(new User(username.getText(), password.getText(), UserRole.ADMIN));
                }else {
                    currentUser = Optional.of(new User(username.getText(), password.getText(), UserRole.USER));
                }

            } else {
                a.setAlertType(Alert.AlertType.WARNING);
                a.setContentText("Incorrect password");

                // show the dialog
                a.show();
                System.out.println("Incorrect password!");
            }
        } else {
            System.out.println("Username not found!");
        }

    }
    public static User getCurrentUser(){
        if(currentUser.isPresent()){
            return currentUser.get();
        }else {
            System.out.println("username nije upisan");
        }
        return currentUser.get();
    }
}
