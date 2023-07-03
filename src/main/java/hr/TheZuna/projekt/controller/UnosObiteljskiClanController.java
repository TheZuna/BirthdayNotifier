package hr.TheZuna.projekt.controller;

import hr.TheZuna.projekt.App;
import hr.TheZuna.projekt.entitet.Kolega;
import hr.TheZuna.projekt.entitet.ObiteljClan;
import hr.TheZuna.projekt.iznimke.DataSetException;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

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
                App.getDataSet().createObiteljskiClan(
                        new ObiteljClan.Builder()
                                .withIme(imeObiteljClanColumn.getText())
                                .withPrezime(prezimeObiteljClanColumn.getText())
                                .withAdresa(adresaObiteljClanColumn.getText())
                                .withRodendan(datumRodenjaObiteljClanColumn.getValue())
                                .build()
                );
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
