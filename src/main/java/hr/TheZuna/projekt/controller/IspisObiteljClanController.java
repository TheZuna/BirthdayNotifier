package hr.TheZuna.projekt.controller;

import hr.TheZuna.projekt.App;
import hr.TheZuna.projekt.entitet.Kolega;
import hr.TheZuna.projekt.entitet.ObiteljClan;
import hr.TheZuna.projekt.iznimke.DataSetException;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;

import java.io.IOException;
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
    private TableColumn<ObiteljClan, String> adresaObiteljClanColumn;

    @FXML
    public void initialize() {
        imeObiteljClanColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getIme()));
        prezimeObiteljClanColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getPrezime()));
        datumRodenjaObiteljClanColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getRodendan().format(App.DATE_FORMAT_FULL)));
        adresaObiteljClanColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getAdresa()));

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


        } catch (DataSetException ex) {
            System.out.println(ex.getMessage());
        }

    }
    /*
    @FXML
    public void prikazEditaObiteljskogClana() {
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

     */
}
