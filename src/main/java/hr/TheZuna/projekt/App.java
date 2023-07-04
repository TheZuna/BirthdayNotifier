package hr.TheZuna.projekt;

import hr.TheZuna.projekt.baza.BazePodataka;
import hr.TheZuna.projekt.baza.DataSetovi;
import hr.TheZuna.projekt.entitet.*;
import hr.TheZuna.projekt.iznimke.DataSetException;
import hr.TheZuna.projekt.iznimke.OsobaMladaOd18;
import hr.TheZuna.projekt.users.User;
import hr.TheZuna.projekt.util.LogLevel;
import hr.TheZuna.projekt.util.RadnjaLoga;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.Collectors;

import javafx.stage.WindowEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class App extends Application {
    private static List<Osoba> slavljenici = new CopyOnWriteArrayList<>();
    static Logger logger = LoggerFactory.getLogger(App.class);
    private static DataSetovi dataSetovi;
    private static Stage stage;
    private static User currentUser = null;

    private static List<Promjena> promjene = new ArrayList<>();
    private static final Object lock = new Object();
    private static Lock slavljeniciLock = new ReentrantLock();


    public static final DateTimeFormatter DATE_FORMAT_SHORT = DateTimeFormatter.ofPattern("d.M.yyyy.");
    public static final DateTimeFormatter DATE_TIME_FORMAT_SHORT = DateTimeFormatter.ofPattern("d.M.yyyy. H:mm");
    public static final DateTimeFormatter TIME_FORMAT_SHORT = DateTimeFormatter.ofPattern("H:mm");
    public static final DateTimeFormatter DATE_FORMAT_FULL = DateTimeFormatter.ofPattern("dd.MM.yyyy.");
    public static final DateTimeFormatter DATE_TIME_FORMAT_FULL = DateTimeFormatter.ofPattern("dd.MM.yyyy. HH:mm");
    public static final DateTimeFormatter TIME_FORMAT_FULL = DateTimeFormatter.ofPattern("HH:mm");
    @Override
    public void start(Stage primaryStage) throws IOException {

        System.out.println(getClass().getResource(".---"));
        logger.info("Aplikacije je pocela");

        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("controller/loginPage.fxml"));

        stage = primaryStage;


        stage.getIcons().add(new Image(App.class.getResourceAsStream("birthday.png")));

        Scene scene = new Scene(fxmlLoader.load(), 400, 500);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();


        Thread loadSlavljeniciThread = new Thread(() -> {
            while (true){
                try{
                    slavljeniciLock.lock();
                    slavljenici = getAllSlavljenici();
                    System.out.println("Spremljeno");
                    slavljeniciLock.unlock();
                    Thread.sleep(1000);
                }catch (DataSetException ex){
                    ex.printStackTrace();
                }catch (InterruptedException ex){
                    ex.printStackTrace();
                }
                //System.out.println(slavljenici);
            }
        });
        loadSlavljeniciThread.start();
        try{
            System.out.println(getAllSlavljenici());
        }catch (DataSetException ex){
            ex.printStackTrace();
        }

        Thread readingSlavljeniciThread = new Thread(() -> {
           while (true){
               try {
                   slavljeniciLock.lock();
                   //System.out.println(slavljenici.get(slavljenici.size()-1));
                   final StringBuilder slavljenickiMessage = new StringBuilder();
                   for(Osoba o : slavljenici){
                       slavljenickiMessage.append(o.getIme()).append(" ").append(o.getPrezime()).append("\n");
                   }
                   Platform.runLater(() -> {
                       var alert = new Alert(Alert.AlertType.INFORMATION, "Danas je rodenan : \n" + slavljenickiMessage );
                       alert.show();
                   });
                   slavljeniciLock.unlock();
                   Thread.sleep(10000);
               }catch (InterruptedException ex){
                   ex.printStackTrace();
               }

           }
        });
        readingSlavljeniciThread.start();

        primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent e) {
                Platform.exit();
                System.exit(0);
            }
        });
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
    public static <T> void log(T osoba, String poruka, LogLevel logLevel, RadnjaLoga radnja) {
        switch (logLevel) {
            case INFO:
                logger.info(poruka + " " + radnja + " " + osoba.toString());
                break;
            case WARNING:
                logger.warn(poruka + " " + radnja + " " + osoba.toString());
                break;
            case ERROR:
                logger.error(poruka + " " + radnja + " " + osoba.toString());
                break;
            default:
                logger.info(poruka + " " + radnja + " " + osoba.toString());
                break;
        }
    }
    public static List<Osoba> getAllSlavljenici() throws DataSetException {
        LocalDate currentdate = LocalDate.now();

        List<Kolega> k = dataSetovi.readKolega();
        List<Prijatelj> p  = dataSetovi.readPrijatelj();
        List<ObiteljClan> o = dataSetovi.readObiteljskiClan();

        List<Osoba> sveOsobe = new ArrayList<>();
        sveOsobe.addAll(k);
        sveOsobe.addAll(p);
        sveOsobe.addAll(o);

        return sveOsobe.stream().filter(osoba -> osoba.getRodendan().getMonth() == currentdate.getMonth() && osoba.getRodendan().getDayOfMonth() == currentdate.getDayOfMonth()).collect(Collectors.toList());
    }
}
