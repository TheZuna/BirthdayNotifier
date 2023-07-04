package hr.TheZuna.projekt.controller;

import hr.TheZuna.projekt.App;
import hr.TheZuna.projekt.entitet.Prijatelj;
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
import java.util.ArrayList;

public class EditOsobaController {

    Prijatelj prijateljZaEditat;
    public EditOsobaController( Prijatelj prijatelj){
        this.prijateljZaEditat = prijatelj;
    }

    @FXML
    private TextField imePrijatelja;
    @FXML
    private TextField prezimePrijatelja;
    @FXML
    private TextField emailPrijatelja;
    @FXML
    private DatePicker datumRodenjaPrijatelja;
    @FXML
    private Button editButton;

    public void initialize(){
        System.out.println(prijateljZaEditat);
        System.out.println(prijateljZaEditat.getIme());
        imePrijatelja.setText(prijateljZaEditat.getIme());
        editButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                editPrijatelja();
            }
        });
    }

    public void editPrijatelja() {
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
                App.getDataSet().editPrijatelj(prijateljZaEditat, new Prijatelj(
                        imePrijatelja.getText(),
                        prezimePrijatelja.getText(),
                        emailPrijatelja.getText(),
                        datumRodenjaPrijatelja.getValue()
                ));
                var alert = new Alert(Alert.AlertType.INFORMATION, "Osoba je Editana");
                alert.show();
                App.log(prijateljZaEditat, " ", LogLevel.INFO, RadnjaLoga.EDIT);

                BorderPane root;
                try {
                    root =  (BorderPane) FXMLLoader.load(getClass().getResource("ispisOsoba.fxml"));
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
            App.log(prijateljZaEditat, " ", LogLevel.ERROR, RadnjaLoga.EDIT);
        }
    }
}
