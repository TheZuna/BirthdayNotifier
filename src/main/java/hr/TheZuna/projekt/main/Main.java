package hr.TheZuna.projekt.main;
import java.awt.*;
import java.awt.event.*;

import hr.TheZuna.projekt.App;
import hr.TheZuna.projekt.baza.BazePodataka;
import hr.TheZuna.projekt.entitet.Kolega;
import hr.TheZuna.projekt.entitet.Osoba;
import hr.TheZuna.projekt.entitet.Prijatelj;
import hr.TheZuna.projekt.iznimke.DataSetException;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {

        //System.out.println(Main.class.getResource("ispisOsoba.fxml").getPath());
        //System.out.println(getClass().getResource("."));


        /*try {
            var ds = new BazePodataka();
            List<Prijatelj> osobe = ds.readPrijatelj();
            System.out.println(osobe.size());
            osobe.stream().forEach(a -> System.out.println(a.getIme() + " " + a.getRodendan()));

            System.out.println("$$$$$$$$$$$$$$$$$$");

            //ds.createPrijatelj(new Prijatelj("Test2", "PrezimeTest2", "test2@gmail.com", LocalDate.of(2009, 2, 10)));
            List<Prijatelj> osobe2 = ds.readPrijatelj();
            osobe2.stream().forEach(a -> System.out.println(a.getIme() + " " + a.getRodendan()));
            popupMessage();

            //ds.removePrijatelj(new Prijatelj("Test2", "PrezimeTest2", "test2@gmail.com", LocalDate.of(2009, 2, 10)));

        } catch (DataSetException e) {
            System.out.println(e.getMessage());
        } catch (AWTException e) {
            throw new RuntimeException(e);
        }

         */
    }
    public static List<Osoba> osobeKojimaJeRodendan(){

        LocalDate currentdate = LocalDate.now();

        try{
            List<Osoba> osobeKojimaJeRodendan = new ArrayList<>();

            var ds = new BazePodataka();
            List<Kolega> kolege = ds.readKolega();
            List<Prijatelj> prijatelji = ds.readPrijatelj();

            List<Osoba> o = new ArrayList<>();
            o.addAll(kolege);
            o.addAll(prijatelji);



            osobeKojimaJeRodendan = o.stream().filter(osoba -> osoba.getRodendan().getMonth() == currentdate.getMonth() && osoba.getRodendan().getDayOfMonth() == currentdate.getDayOfMonth()).collect(Collectors.toList());

            return osobeKojimaJeRodendan;

        } catch (DataSetException e) {
            throw new RuntimeException(e);
        }
    }
    public static void popupMessage() throws AWTException{

        SystemTray tray = SystemTray.getSystemTray();

        Image image = Toolkit.getDefaultToolkit().createImage("");
        TrayIcon trayIcon = new TrayIcon(image);
        trayIcon.setImageAutoSize(true);
        trayIcon.setToolTip("Demo");
        tray.add(trayIcon);

        trayIcon.displayMessage("Danas je rodendan !!!", "Dei Perišić", TrayIcon.MessageType.INFO);
    }
}