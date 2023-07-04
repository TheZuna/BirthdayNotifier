package hr.TheZuna.projekt.controller;

import hr.TheZuna.projekt.App;
import hr.TheZuna.projekt.entitet.Kolega;
import hr.TheZuna.projekt.entitet.Prijatelj;
import hr.TheZuna.projekt.iznimke.DataSetException;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;

public class IspisKolegaController {

    private List<Kolega> sviKolege;

    @FXML
    private TableView<Kolega> kolegeTableView;
    @FXML
    private TableColumn<Kolega, String> prezimeKolegeColumn;
    @FXML
    private TableColumn<Kolega, String> imeKolegeColumn;
    @FXML
    private TableColumn<Kolega, String> datumRodenjaKolegeColumn;
    @FXML
    private TableColumn<Kolega, String> brTelefonaKolegeColumn;

    @FXML
    public void initialize() {
        imeKolegeColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getIme()));
        prezimeKolegeColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getPrezime()));
        datumRodenjaKolegeColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getRodendan().format(App.DATE_FORMAT_FULL)));
        brTelefonaKolegeColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getBrTelefona().toString()));

        try {
            sviKolege = App.getDataSet().readKolega();
        } catch (DataSetException ex) {
            System.out.println(ex.getMessage());
        }
        kolegeTableView.setItems(FXCollections.observableList(sviKolege));
    }

    @FXML
    public void prikazUnosaKolege() {
        BorderPane root;
        try {
            root =  (BorderPane)FXMLLoader.load(getClass().getResource("unosKolege.fxml"));
            App.setMainPage(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void removeOdabraniKolege() {
        try {
            Kolega selectedKolega = kolegeTableView.getSelectionModel().getSelectedItem();
            App.getDataSet().removeKolega(selectedKolega);
            var alert = new Alert(Alert.AlertType.INFORMATION, "Osoba je Izbrisana");
            alert.show();
            BorderPane root;
            try {
                root =  (BorderPane)FXMLLoader.load(getClass().getResource("IspisKolega.fxml"));
                App.setMainPage(root);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (DataSetException ex) {
            System.out.println(ex.getMessage());
        }

    }

    @FXML
    public void prikazEditaKolege() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("editKolega.fxml"));


            EditKolegaController controller = new EditKolegaController(kolegeTableView.getSelectionModel().getSelectedItem());
            loader.setController(controller);

            BorderPane root = loader.load();
            App.setMainPage(root);


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //public static Prijatelj odabraniPrijatelj = prijateljTableView.getSelectionModel().getSelectedItem();
    public Kolega getOdabraniKolega() {
        Kolega odabraniKolega = kolegeTableView.getSelectionModel().getSelectedItem();
        return odabraniKolega;
    }

}
