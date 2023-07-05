package hr.TheZuna.projekt.controller;

import hr.TheZuna.projekt.App;
import hr.TheZuna.projekt.entitet.*;
import hr.TheZuna.projekt.iznimke.DataSetException;
import hr.TheZuna.projekt.util.LogLevel;
import hr.TheZuna.projekt.util.RadnjaLoga;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

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
        Kolega selectedKolega = kolegeTableView.getSelectionModel().getSelectedItem();
        if (selectedKolega != null) {
            Alert confirmDialog = new Alert(Alert.AlertType.CONFIRMATION);
            confirmDialog.setTitle("Potvrda");
            confirmDialog.setHeaderText("Jeste li sigurni da želite izbrisati odabranog kolegu?");
            confirmDialog.setContentText("Kliknite OK za potvrdu ili Cancel za odustajanje.");

            Optional<ButtonType> result = confirmDialog.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                try {
                    App.getDataSet().removeKolega(selectedKolega);
                    App.log(new LogEntry(selectedKolega), " ", LogLevel.INFO, RadnjaLoga.REMOVE);
                    App.addToPromjene(new Promjena("REMOVE", (Osoba) selectedKolega, LocalDate.now(), App.getCurrentUser()));

                    var alert = new Alert(Alert.AlertType.INFORMATION, "Osoba je Izbrisana");
                    alert.show();

                    BorderPane root;
                    try {
                        root = (BorderPane) FXMLLoader.load(getClass().getResource("IspisKolega.fxml"));
                        App.setMainPage(root);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } catch (DataSetException ex) {
                    System.out.println(ex.getMessage());
                }
            }
        } else {
            var alert = new Alert(Alert.AlertType.WARNING, "Niste odabrali kolegu za brisanje.");
            alert.setTitle("Upozorenje");
            alert.show();
        }
    }

    @FXML
    public void prikazEditaKolege() {
        Kolega selectedKolega = kolegeTableView.getSelectionModel().getSelectedItem();
        if(selectedKolega != null){
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("editKolega.fxml"));


                EditKolegaController controller = new EditKolegaController(kolegeTableView.getSelectionModel().getSelectedItem());
                loader.setController(controller);

                BorderPane root = loader.load();
                App.setMainPage(root);


            } catch (IOException e) {
                e.printStackTrace();
            }
        }else{
            var alert = new Alert(Alert.AlertType.WARNING, "Niste odabrali kolegu za uređivanje.");
            alert.setTitle("Upozorenje");
            alert.show();
        }

    }

    //public static Prijatelj odabraniPrijatelj = prijateljTableView.getSelectionModel().getSelectedItem();
    public Kolega getOdabraniKolega() {
        Kolega odabraniKolega = kolegeTableView.getSelectionModel().getSelectedItem();
        return odabraniKolega;
    }
}
