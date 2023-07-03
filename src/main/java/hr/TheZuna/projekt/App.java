package hr.TheZuna.projekt;

import hr.TheZuna.projekt.baza.BazePodataka;
import hr.TheZuna.projekt.baza.DataSetovi;
import hr.TheZuna.projekt.controller.UserAuthenticationController;
import hr.TheZuna.projekt.entitet.Promjena;
import hr.TheZuna.projekt.iznimke.DataSetException;
import hr.TheZuna.projekt.users.User;
import hr.TheZuna.projekt.users.UserAuthentication;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;


public class App extends Application {
    private static DataSetovi dataSetovi;
    private static Stage stage;
    private static User currentUsesr = null;

    private static List<Promjena> promjene = new ArrayList<>();


    public static final DateTimeFormatter DATE_FORMAT_SHORT = DateTimeFormatter.ofPattern("d.M.yyyy.");
    public static final DateTimeFormatter DATE_TIME_FORMAT_SHORT = DateTimeFormatter.ofPattern("d.M.yyyy. H:mm");
    public static final DateTimeFormatter TIME_FORMAT_SHORT = DateTimeFormatter.ofPattern("H:mm");
    public static final DateTimeFormatter DATE_FORMAT_FULL = DateTimeFormatter.ofPattern("dd.MM.yyyy.");
    public static final DateTimeFormatter DATE_TIME_FORMAT_FULL = DateTimeFormatter.ofPattern("dd.MM.yyyy. HH:mm");
    public static final DateTimeFormatter TIME_FORMAT_FULL = DateTimeFormatter.ofPattern("HH:mm");
    @Override
    public void start(Stage primaryStage) throws IOException {

        System.out.println(getClass().getResource(".---"));

        //User currentUser = UserAuthenticationController.getCurrentUser();
        //System.out.println(currentUser.getRole().toString());

        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("controller/loginPage.fxml"));

        //URL url = new File("src/main/java/hr/TheZuna/projekt/controller/PrikazRodendana.fxml").toURI().toURL();
        //FXMLLoader fxmlLoader = FXMLLoader.load(getClass().getResource("/controller/menu.fxml"));
        stage = primaryStage;

        //URL iconUrl = getClass().getResource("/birthday.png");
        //Image icon = new Image(iconUrl.toExternalForm());
        //stage.getIcons().add(icon);

        stage.getIcons().add(new Image(App.class.getResourceAsStream("birthday.png")));

        Scene scene = new Scene(fxmlLoader.load(), 400, 500);
        stage.setScene(scene);
        stage.show();
    }
    public static void main(String[] args) {
        try(var data = new BazePodataka()){
            dataSetovi = data;
            launch();

        }catch (DataSetException ex){
            var upozorenje = new Alert(Alert.AlertType.ERROR, "Connection failed to database", ButtonType.OK);
            upozorenje.setTitle("Error");
            upozorenje.showAndWait();
        }
    }
    public static DataSetovi getDataSet() {return dataSetovi;}
    public static Stage getStage(){return stage;};
    public static void setMainPage(BorderPane root) {
        Scene scene = new Scene(root,400,500);
        stage.setScene(scene);
        stage.show();
    }
    public static void addToPromjene(Promjena promjena){
        promjene.add(promjena);
    }
    public static List<Promjena> getAllPromjene(){
        return promjene;
    }
    public static void setCurrentUsesr(User user){
        currentUsesr = user;
    }
    public static User getCurrentUsesr(){
        return currentUsesr;
    }
}
