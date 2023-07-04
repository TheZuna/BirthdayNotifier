package hr.TheZuna.projekt.controller;

import hr.TheZuna.projekt.App;
import hr.TheZuna.projekt.entitet.Prijatelj;
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

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;


public class IspisOsobaController {

    private List<Prijatelj> sviPrijatelji;

    @FXML
    private TableView<Prijatelj> prijateljTableView;
    @FXML
    private TableColumn<Prijatelj, String> prezimePrijateljaColumn;
    @FXML
    private TableColumn<Prijatelj, String> imePrijateljaColumn;
    @FXML
    private TableColumn<Prijatelj, String> datumRodenjaPrijateljaColumn;
    @FXML
    public void initialize (){
        imePrijateljaColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getIme()));
        prezimePrijateljaColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getPrezime()));
        datumRodenjaPrijateljaColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getRodendan().format(App.DATE_FORMAT_FULL)));

        try {
            sviPrijatelji = App.getDataSet().readPrijatelj();
        }catch (DataSetException ex){
            System.out.println(ex.getMessage());
        }
        prijateljTableView.setItems(FXCollections.observableList(sviPrijatelji));
    }
    @FXML
    public void prikazUnosaPrijatelja() {
        BorderPane root;
        try {
            root = (BorderPane)FXMLLoader.load(getClass().getResource("unosOsoba.fxml"));
            App.setMainPage(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    public void removeOdabraniPrijatelj() {
        try{
            Prijatelj selectedPrijatelj = prijateljTableView.getSelectionModel().getSelectedItem();
            App.getDataSet().removePrijatelj(selectedPrijatelj);
            var alert = new Alert(Alert.AlertType.INFORMATION, "Osoba je Izbrisana");
            alert.show();

            BorderPane root;
            try {
                root =  (BorderPane) FXMLLoader.load(getClass().getResource("ispisOsoba.fxml"));
                App.setMainPage(root);
            } catch (IOException e) {
                e.printStackTrace();
            }
            App.log(selectedPrijatelj, " ", LogLevel.INFO, RadnjaLoga.REMOVE);

        }catch (DataSetException ex){
            System.out.println(ex.getMessage());
        }

    }
    @FXML
    public void prikazEditaPrijatelja(){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("editOsoba.fxml"));


            EditOsobaController controller = new EditOsobaController(prijateljTableView.getSelectionModel().getSelectedItem());
            loader.setController(controller);

            BorderPane root = loader.load();
            App.setMainPage(root);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Tu sam puko Edit prijatelja");
        }
    }
    //public static Prijatelj odabraniPrijatelj = prijateljTableView.getSelectionModel().getSelectedItem();
    public Prijatelj getOdabraniPrijatelj(){
        Prijatelj odabraniPrijatelj = prijateljTableView.getSelectionModel().getSelectedItem();
        return odabraniPrijatelj;
    }
}
