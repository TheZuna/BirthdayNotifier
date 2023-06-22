package hr.TheZuna.projekt.controller;

import hr.TheZuna.projekt.App;
import hr.TheZuna.projekt.baza.BazePodataka;
import hr.TheZuna.projekt.baza.DataSetovi;
import hr.TheZuna.projekt.entitet.Kolega;
import hr.TheZuna.projekt.entitet.Osoba;
import hr.TheZuna.projekt.entitet.Prijatelj;
import hr.TheZuna.projekt.iznimke.DataSetException;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class PrikazRodendanaController {

    List<Osoba> osobeKojimaJeRodendan = osobeKojimaJeRodendan();

    @FXML
    private Label rodendanLabel;

    @FXML
    private BorderPane bp;

    @FXML
    private VBox vbox;

    @FXML
    public void initialize (){

        int numLabels = osobeKojimaJeRodendan.size();

        bp.setMargin(vbox, new Insets(0, 0, 50, 0));

        vbox.setAlignment(Pos.CENTER);

        for (int i = 0; i < numLabels; i++) {
            Label label = new Label(osobeKojimaJeRodendan.get(i).getIme() + " " + osobeKojimaJeRodendan.get(i).getPrezime());
            //VBox.setMargin(label, new Insets(0, 0, initialMargin - i * marginStep, 0));
            vbox.getChildren().add(label);
        }

        VBox.setMargin(vbox, new Insets(0, 0, 400, 100));
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







}
