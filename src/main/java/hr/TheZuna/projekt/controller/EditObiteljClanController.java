package hr.TheZuna.projekt.controller;

import hr.TheZuna.projekt.App;
import hr.TheZuna.projekt.entitet.ObiteljClan;
import hr.TheZuna.projekt.entitet.Prijatelj;
import hr.TheZuna.projekt.iznimke.DataSetException;
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

public class EditObiteljClanController {

    ObiteljClan clanZaEditat;
    public EditObiteljClanController( ObiteljClan obiteljClan){
        this.clanZaEditat = obiteljClan;
    }

    @FXML
    private TextField imeObiteljClan;
    @FXML
    private TextField prezimeObiteljClan;
    @FXML
    private TextField adresaObiteljClan;
    @FXML
    private DatePicker datumRodenjaPrijatelja;
    @FXML
    private Button editButton;

    public void initialize(){
        System.out.println(clanZaEditat);
        System.out.println(clanZaEditat.getIme());
        imeObiteljClan.setText(clanZaEditat.getIme());
        editButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                editObiteljskiClan();
            }
        });
    }

    public void editObiteljskiClan() {
        ArrayList<String> messages = new ArrayList<>();

        if(imeObiteljClan.getText().isBlank()){
            messages.add("Polje Ime Člana obitelji je prazno! ");
        }
        if(prezimeObiteljClan.getText().isBlank()){
            messages.add("Polje Prezime Člana obitelji je prazno! ");
        }
        if(adresaObiteljClan.getText().isBlank()){
            messages.add("Polje Adresa Člana obitelji je prazno! ");
        }
        if(datumRodenjaPrijatelja.getValue() == null){
            messages.add("Polje Rodendan Člana obitelji je prazno! ");
        }
        if (messages.size() == 0){
            System.out.println("NEMA ERRORA");
            try {
                App.getDataSet().editObiteljClan(clanZaEditat, new ObiteljClan(
                        imeObiteljClan.getText(),
                        prezimeObiteljClan.getText(),
                        adresaObiteljClan.getText(),
                        datumRodenjaPrijatelja.getValue()
                ));
                var alert = new Alert(Alert.AlertType.INFORMATION, "Osoba je Editana");
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