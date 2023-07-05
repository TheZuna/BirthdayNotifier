package hr.TheZuna.projekt.controller;

import hr.TheZuna.projekt.App;
import hr.TheZuna.projekt.entitet.Kolega;
import hr.TheZuna.projekt.entitet.Osoba;
import hr.TheZuna.projekt.entitet.Prijatelj;
import hr.TheZuna.projekt.entitet.Promjena;
import hr.TheZuna.projekt.iznimke.DataSetException;
import hr.TheZuna.projekt.util.LogLevel;
import hr.TheZuna.projekt.util.RadnjaLoga;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;

public class EditKolegaController {
    Kolega kolegaZaEditat;
    public EditKolegaController(Kolega kolega){
        this.kolegaZaEditat = kolega;
    }

    @FXML
    private Button editButton;

    @FXML
    private TextField imeKolege;
    @FXML
    private TextField prezimeKolege;
    @FXML
    private TextField brTelefonaKolege;
    @FXML
    private DatePicker datumRodenjaKolege;


    public void initialize(){
        System.out.println(kolegaZaEditat);
        System.out.println(kolegaZaEditat.getIme());
        imeKolege.setText(kolegaZaEditat.getIme());
        prezimeKolege.setText(kolegaZaEditat.getPrezime());
        brTelefonaKolege.setText(kolegaZaEditat.getBrTelefona());
        datumRodenjaKolege.setValue(kolegaZaEditat.getRodendan());
        editButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                editKolegu();
            }
        });
    }

    public void editKolegu() {
        ArrayList<String> messages = new ArrayList<>();

        if(imeKolege.getText().isBlank()){
            messages.add("Polje Ime Kolege je prazno! ");
        }
        if(prezimeKolege.getText().isBlank()){
            messages.add("Polje Prezime Kolege je prazno! ");
        }
        if(brTelefonaKolege.getText().isBlank()){
            messages.add("Polje Email Kolege je prazno! ");
        }
        if(datumRodenjaKolege.getValue() == null){
            messages.add("Polje Rodendan Kolege je prazno! ");
        }
        if (messages.size() == 0){
            System.out.println("NEMA ERRORA");
            try {
                Kolega kolega = new Kolega(
                        imeKolege.getText(),
                        prezimeKolege.getText(),
                        brTelefonaKolege.getText(),
                        datumRodenjaKolege.getValue());
                App.getDataSet().editKolega(kolegaZaEditat, kolega);

                App.log(kolega, " ", LogLevel.INFO, RadnjaLoga.EDIT);
                App.addToPromjene(new Promjena("EDIT", (Osoba) kolega, LocalDate.now(), App.getCurrentUser()));

                var alert = new Alert(Alert.AlertType.INFORMATION, "Osoba je Editana");
                App.log(kolegaZaEditat, " ", LogLevel.INFO, RadnjaLoga.EDIT);
                alert.show();
                BorderPane root;
                try {
                    root =  (BorderPane) FXMLLoader.load(getClass().getResource("IspisKolega.fxml"));
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
            App.log(kolegaZaEditat, " ", LogLevel.ERROR, RadnjaLoga.EDIT);
        }
    }
}
