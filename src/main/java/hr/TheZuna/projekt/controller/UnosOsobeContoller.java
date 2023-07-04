package hr.TheZuna.projekt.controller;

import hr.TheZuna.projekt.App;
import hr.TheZuna.projekt.entitet.Osoba;
import hr.TheZuna.projekt.entitet.Prijatelj;
import hr.TheZuna.projekt.entitet.Promjena;
import hr.TheZuna.projekt.iznimke.DataSetException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;


public class UnosOsobeContoller {
    @FXML
    private TextField imePrijatelja;
    @FXML
    private TextField prezimePrijatelja;
    @FXML
    private TextField emailPrijatelja;
    @FXML
    private DatePicker datumRodenjaPrijatelja;

    public void unesiPrijatelja() {
        ArrayList<String> messages = new ArrayList<>();

        if(imePrijatelja.getText().isBlank()){
            messages.add("Polje Ime Prijatelja je prazno! ");
        }
        if(prezimePrijatelja.getText().isBlank()){
            messages.add("Polje Prezime Prijatelja je prazno! ");
        }
        if(emailPrijatelja.getText().isBlank()){
            messages.add("Polje Email Prijatelja je prazno! ");
        }
        if(datumRodenjaPrijatelja.getValue() == null){
            messages.add("Polje Rodendan Prijatelja je prazno! ");
        }
        if (messages.size() == 0){
            System.out.println("NEMA ERRORA");
            try {
                Prijatelj prijatelj = new Prijatelj(
                        imePrijatelja.getText(),
                        prezimePrijatelja.getText(),
                        emailPrijatelja.getText(),
                        datumRodenjaPrijatelja.getValue()
                );
                App.getDataSet().createPrijatelj(prijatelj);
                var alert = new Alert(Alert.AlertType.INFORMATION, "Osoba je Unešena");
                alert.show();
                BorderPane root;
                try {
                    root =  (BorderPane) FXMLLoader.load(getClass().getResource("ispisOsoba.fxml"));
                    App.setMainPage(root);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                App.addToPromjene(new Promjena("UNOS", (Osoba) prijatelj, LocalDate.now()));
                System.out.println(App.getAllPromjene());
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
