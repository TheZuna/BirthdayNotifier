package hr.TheZuna.projekt.controller;

import hr.TheZuna.projekt.App;
import hr.TheZuna.projekt.entitet.Kolega;
import hr.TheZuna.projekt.iznimke.DataSetException;
import hr.TheZuna.projekt.users.User;
import hr.TheZuna.projekt.users.UserAuthentication;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ListAllUsersController {

    private List<String> sviUseri;

    @FXML
    private TableView<String> usersTableView;
    @FXML
    private TableColumn<String, String> usersColumn;



    public void initialize(){

        try {
            Map<String, String> usersMap = UserAuthentication.readUsersFromFile();
            sviUseri = new ArrayList<>(usersMap.keySet());

            usersColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue()));
            usersTableView.setItems(FXCollections.observableList(sviUseri));
        }catch (IOException ex){
            System.out.println(ex.getMessage());
        }
    }
}
