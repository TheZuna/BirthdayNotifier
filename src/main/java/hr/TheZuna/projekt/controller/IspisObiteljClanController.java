package hr.TheZuna.projekt.controller;

import hr.TheZuna.projekt.App;
import hr.TheZuna.projekt.entitet.Kolega;
import hr.TheZuna.projekt.entitet.ObiteljClan;
import hr.TheZuna.projekt.entitet.Osoba;
import hr.TheZuna.projekt.entitet.Promjena;
import hr.TheZuna.projekt.iznimke.DataSetException;
import hr.TheZuna.projekt.util.LogLevel;
import hr.TheZuna.projekt.util.RadnjaLoga;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

public class IspisObiteljClanController {

    private List<ObiteljClan> sviObiteljClan;

    @FXML
    private TableView<ObiteljClan> obiteljClanTableView;
    @FXML
    private TableColumn<ObiteljClan, String> prezimeObiteljClanColumn;
    @FXML
    private TableColumn<ObiteljClan, String> imeObiteljClanColumn;
    @FXML
    private TableColumn<ObiteljClan, String> datumRodenjaObiteljClanColumn;

    @FXML
    public void initialize() {
        imeObiteljClanColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getIme()));
        prezimeObiteljClanColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getPrezime()));
        datumRodenjaObiteljClanColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getRodendan().format(App.DATE_FORMAT_FULL)));

        try {
            sviObiteljClan = App.getDataSet().readObiteljskiClan();
        } catch (DataSetException ex) {
            System.out.println(ex.getMessage());
        }
        obiteljClanTableView.setItems(FXCollections.observableList(sviObiteljClan));
    }

    @FXML
    public void prikazUnosaObiteljskogClana() {
        BorderPane root;
        try {
            root =  (BorderPane) FXMLLoader.load(getClass().getResource("unosObiteljskogClana.fxml")); ////
            App.setMainPage(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @FXML
    public void removeObiteljskogClana() {
        try {
            ObiteljClan selectedObiteljskiClan = obiteljClanTableView.getSelectionModel().getSelectedItem();
            App.getDataSet().removeObiteljskiClan(selectedObiteljskiClan);

            App.log(selectedObiteljskiClan, " ", LogLevel.INFO, RadnjaLoga.REMOVE);
            App.addToPromjene(new Promjena("REMOVE", (Osoba) selectedObiteljskiClan, LocalDate.now(), App.getCurrentUser()));

            var alert = new Alert(Alert.AlertType.INFORMATION, "Osoba je Izbrisana");
            alert.show();
            BorderPane root;
            try {
                root =  (BorderPane) FXMLLoader.load(getClass().getResource("ispisObiteljClan.fxml"));
                App.setMainPage(root);
            } catch (IOException e) {
                e.printStackTrace();
            }

        } catch (DataSetException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    public void prikazEditaObiteljskogClana() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("editObiteljClan.fxml"));


            EditObiteljClanController controller = new EditObiteljClanController(obiteljClanTableView.getSelectionModel().getSelectedItem());
            loader.setController(controller);

            BorderPane root = loader.load();
            App.setMainPage(root);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
