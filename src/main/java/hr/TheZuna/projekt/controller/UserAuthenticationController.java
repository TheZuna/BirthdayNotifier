package hr.TheZuna.projekt.controller;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.Map;
import java.util.Optional;

import hr.TheZuna.projekt.App;
import hr.TheZuna.projekt.entitet.LogEntry;
import hr.TheZuna.projekt.iznimke.IncorrectPasswordException;
import hr.TheZuna.projekt.users.User;
import hr.TheZuna.projekt.users.UserAuthentication;
import hr.TheZuna.projekt.users.UserRole;
import hr.TheZuna.projekt.users.Md5Hashing;
import hr.TheZuna.projekt.util.LogLevel;
import hr.TheZuna.projekt.util.RadnjaLoga;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;

public final class UserAuthenticationController implements Md5Hashing {

    public static Optional<User> currentUser = Optional.empty();

    @FXML
    private TextField username;
    @FXML
    private PasswordField password;

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
    public void login() throws IOException, NoSuchAlgorithmException {
        Map<String, String> users = UserAuthentication.readUsersFromFile();
        Alert a = new Alert(Alert.AlertType.NONE);
        if (users.containsKey(username.getText())) {
            String storedPassword = users.get(username.getText());
            if ((getHash(password.getText()).equals(storedPassword))){
                System.out.println("Login successful!");
                BorderPane root;
                if(username.getText().equals("admin")){
                    try{
                        root =  (BorderPane) FXMLLoader.load(getClass().getResource("PrikazRodendana.fxml"));
                        App.setMainPage(root);
                    }catch (IOException e){
                        e.printStackTrace();
                    }
                    App.setCurrentUser(new User(username.getText(), password.getText(), UserRole.ADMIN));
                    App.log(new LogEntry(new User(username.getText(), password.getText(), UserRole.ADMIN)), " ", LogLevel.INFO, RadnjaLoga.LOGIN);
                }else {
                    try{
                        root =  (BorderPane) FXMLLoader.load(getClass().getResource("PrikazRodendana2.fxml"));
                        App.setMainPage(root);
                    }catch (IOException e){
                        e.printStackTrace();
                    }
                    App.setCurrentUser(new User(username.getText(), password.getText(), UserRole.USER));
                    App.log(new LogEntry(new User(username.getText(), password.getText(), UserRole.ADMIN)), " ", LogLevel.INFO, RadnjaLoga.LOGIN);
                }

            } else {
                a.setAlertType(Alert.AlertType.WARNING);
                a.setContentText("Incorrect password");

                // show the dialog
                a.show();
                System.out.println("Incorrect password!");
                try{
                    throw new IncorrectPasswordException("Krivi password");
                }catch (IncorrectPasswordException ex){
                    App.log(new LogEntry(new User(username.getText(), password.getText(), null)), "krivi passowrd ", LogLevel.INFO, RadnjaLoga.LOGIN);
                }
            }
        } else {
            System.out.println("Username not found!");
            a.setAlertType(Alert.AlertType.WARNING);
            a.setContentText("Username not found");

            // show the dialog
            a.show();
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
