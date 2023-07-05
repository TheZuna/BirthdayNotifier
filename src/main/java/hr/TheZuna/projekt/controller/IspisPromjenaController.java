package hr.TheZuna.projekt.controller;

import hr.TheZuna.projekt.App;
import hr.TheZuna.projekt.entitet.Prijatelj;
import hr.TheZuna.projekt.entitet.Promjena;
import hr.TheZuna.projekt.iznimke.DataSetException;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;

import java.io.IOException;
import java.util.List;

public class IspisPromjenaController {
    private List<Promjena> svePromjene;

    @FXML
    private TableView<Promjena> promjenaTableView;
    @FXML
    private TableColumn<Promjena, String> promjenaColumn;
    @FXML
    public void initialize (){
        //System.out.println("INITIALIZEED ############");
        //System.out.println(App.getPromjene());
        svePromjene = App.getPromjene();
        promjenaColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().toString()));
        promjenaTableView.setItems(FXCollections.observableList(svePromjene));
    }
    public void refresh(){
        svePromjene = App.getPromjene();
        BorderPane root;
        try {
            root =  (BorderPane) FXMLLoader.load(getClass().getResource("ispisPromjena.fxml"));
            App.setMainPage(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
