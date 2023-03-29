package hr.TheZuna.projekt;

import hr.TheZuna.projekt.baza.BazePodataka;
import hr.TheZuna.projekt.baza.DataSetovi;
import hr.TheZuna.projekt.iznimke.DataSetException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;


public class App extends Application {
    private static DataSetovi dataSetovi;
    private static Stage stage;

    @Override
    public void start(Stage primaryStage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("/menu.fxml"));
        stage = primaryStage;
        stage.setScene(new Scene(fxmlLoader.load(), 400, 500));
        stage.show();
    }
    public static void main(String[] args) {
        try(var data = new BazePodataka()){
            dataSetovi = data;
            launch();

        }catch (DataSetException ex){
            var upozorenje = new Alert(Alert.AlertType.ERROR, "Connection failed to database", ButtonType.OK);
            upozorenje.setTitle("Error");
            upozorenje.showAndWait();
        }
    }
    public static DataSetovi getDataSet() {return dataSetovi;}
    public static Stage getStage(){return stage;};
    public static void setMainPage(BorderPane root) {
        Scene scene = new Scene(root,400,500);
        stage.setScene(scene);
        stage.show();
    }

}
