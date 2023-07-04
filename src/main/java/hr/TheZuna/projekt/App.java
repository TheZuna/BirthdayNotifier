package hr.TheZuna.projekt;

import hr.TheZuna.projekt.baza.BazePodataka;
import hr.TheZuna.projekt.baza.DataSetovi;
import hr.TheZuna.projekt.entitet.Promjena;
import hr.TheZuna.projekt.iznimke.DataSetException;
import hr.TheZuna.projekt.users.User;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.*;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;


public class App extends Application {
    private static DataSetovi dataSetovi;
    private static Stage stage;
    private static User currentUser = null;

    private static List<Promjena> promjene = new ArrayList<>();
    private static final Object lock = new Object();


    public static final DateTimeFormatter DATE_FORMAT_SHORT = DateTimeFormatter.ofPattern("d.M.yyyy.");
    public static final DateTimeFormatter DATE_TIME_FORMAT_SHORT = DateTimeFormatter.ofPattern("d.M.yyyy. H:mm");
    public static final DateTimeFormatter TIME_FORMAT_SHORT = DateTimeFormatter.ofPattern("H:mm");
    public static final DateTimeFormatter DATE_FORMAT_FULL = DateTimeFormatter.ofPattern("dd.MM.yyyy.");
    public static final DateTimeFormatter DATE_TIME_FORMAT_FULL = DateTimeFormatter.ofPattern("dd.MM.yyyy. HH:mm");
    public static final DateTimeFormatter TIME_FORMAT_FULL = DateTimeFormatter.ofPattern("HH:mm");
    @Override
    public void start(Stage primaryStage) throws IOException {

        System.out.println(getClass().getResource(".---"));

        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("controller/loginPage.fxml"));

        stage = primaryStage;


        stage.getIcons().add(new Image(App.class.getResourceAsStream("birthday.png")));

        Scene scene = new Scene(fxmlLoader.load(), 400, 500);
        stage.setScene(scene);
        stage.show();

        /*
        Thread writerThread = new Thread(() -> {
            while (true) {
                synchronized (lock) {
                    // Serialize the changes
                    serializeChanges();
                }
            }
        });
        writerThread.start();
         */
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
        synchronized (lock){
            promjene.add(promjena);
            System.out.println("New change added: " + promjena.getTipPromijene());
        }
    }
    public static List<Promjena> getAllPromjene(){
        return promjene;
    }
    public static void setCurrentUser(User user){
        currentUser = user;
    }
    public static User getCurrentUser(){
        return currentUser;
    }

    private static void serializeChanges() {
        try (FileOutputStream fileOut = new FileOutputStream("changes.bin");
             ObjectOutputStream objectOut = new ObjectOutputStream(fileOut)) {
            objectOut.writeObject(promjene);
            System.out.println("Changes serialized.");
            Thread.sleep(1000);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    public static Object getPromjeneLock() {
        return lock;
    }
}
