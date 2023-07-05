package hr.TheZuna.projekt.controller;

import hr.TheZuna.projekt.App;
import hr.TheZuna.projekt.entitet.Kolega;
import hr.TheZuna.projekt.entitet.ObiteljClan;
import hr.TheZuna.projekt.entitet.Osoba;
import hr.TheZuna.projekt.entitet.Promjena;
import hr.TheZuna.projekt.iznimke.DataSetException;
import hr.TheZuna.projekt.util.LogLevel;
import hr.TheZuna.projekt.util.RadnjaLoga;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

public class UnosObiteljskiClanController {
    @FXML
    private TextField imeObiteljClanColumn;
    @FXML
    private TextField prezimeObiteljClanColumn;
    @FXML
    private TextField adresaObiteljClanColumn;
    @FXML
    private DatePicker datumRodenjaObiteljClanColumn;

    public void unesiObiteljskogClana() {
        ArrayList<String> messages = new ArrayList<>();

        if(imeObiteljClanColumn.getText().isBlank()){
            messages.add("Polje Ime Obiteljskog clana je prazno! ");
        }
        if(prezimeObiteljClanColumn.getText().isBlank()){
            messages.add("Polje Prezime Obiteljskog clana je prazno! ");
        }
        if(adresaObiteljClanColumn.getText().isBlank()){
            messages.add("Polje adresa Obiteljskog clana je prazno! ");
        }
        if(datumRodenjaObiteljClanColumn.getValue() == null){
            messages.add("Polje Rodendan Obiteljskog clana je prazno! ");
        }
        if (messages.size() == 0){
            System.out.println("NEMA ERRORA");
            try {
                ObiteljClan clan = new ObiteljClan.Builder()
                        .withIme(imeObiteljClanColumn.getText())
                        .withPrezime(prezimeObiteljClanColumn.getText())
                        .withAdresa(adresaObiteljClanColumn.getText())
                        .withRodendan(datumRodenjaObiteljClanColumn.getValue())
                        .build();

                App.getDataSet().createObiteljskiClan(clan);
                App.log(clan, " ", LogLevel.INFO, RadnjaLoga.UNOS);
                App.addToPromjene(new Promjena("UNOS", (Osoba) clan, LocalDate.now(), App.getCurrentUser()));

                var alert = new Alert(Alert.AlertType.INFORMATION, "Osoba je Unešena");
                alert.show();
                BorderPane root;
                try {
                    root =  (BorderPane) FXMLLoader.load(getClass().getResource("ispisObiteljClan.fxml"));
                    App.setMainPage(root);
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }catch (DataSetException ex ){
                ex.getMessage();
            }
        }else {
            System.out.println("ERROR");
            String mAlert = String.join("\n", messages);
            var alert = new Alert(Alert.AlertType.ERROR, mAlert);
            alert.setTitle("Error");
            alert.show();
        }
    }
}
