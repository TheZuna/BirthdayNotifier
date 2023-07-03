package hr.TheZuna.projekt.main;
import java.awt.*;
import java.awt.event.*;

import hr.TheZuna.projekt.App;
import hr.TheZuna.projekt.baza.BazePodataka;
import hr.TheZuna.projekt.entitet.*;
import hr.TheZuna.projekt.iznimke.DataSetException;
import hr.TheZuna.projekt.users.UserAuthentication;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) throws IOException, DataSetException {

        List<Promjena> promjene = new ArrayList<>();

        Promjena promjena = new Promjena("EDIT", new Osoba("Pero", "Peric", LocalDate.of(1997, 9, 5)), LocalDate.now());

        promjene.add(promjena);

        promjene.forEach(a -> System.out.println(a.getPromjena()));




        /*

        Map mapaPromjena = promjene.getPromjene();

        mapaPromjena.forEach((k, v) -> System.out.println(k + " " + v));


        ObiteljClan o = new ObiteljClan.Builder()
                .withIme("Gvico")
                .withPrezime("Spigelski")
                .build();

        System.out.println(o.getIme());

        Kolega k = new Kolega("Mirko", "Badalić","3023023202", LocalDate.of(2003, 10, 5));

        var ds = new BazePodataka();

        ObiteljClan obiteljClan = new ObiteljClan("Obitelj1", "ObiteljPrezim1", "Trg 65", LocalDate.of(1998, 10, 4));
        ds.createObiteljskiClan(obiteljClan);








        Map<String, String> map = UserAuthentication.readUsersFromFile();
        for(String s : map.keySet()){
            System.out.println(s);
        }
        for (String s : map.values()){
            System.out.println(s);
        }

        //System.out.println(Main.class.getResource("ispisOsoba.fxml").getPath());
        //System.out.println(getClass().getResource("."));


        try {
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