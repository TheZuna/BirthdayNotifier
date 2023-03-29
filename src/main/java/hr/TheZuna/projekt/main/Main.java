package hr.TheZuna.projekt.main;

import hr.TheZuna.projekt.baza.BazePodataka;
import hr.TheZuna.projekt.entitet.Prijatelj;
import hr.TheZuna.projekt.iznimke.DataSetException;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.util.List;

public class Main {
    public static void main(String[] args) {

        try {
            var ds = new BazePodataka();
            List<Prijatelj> osobe = ds.readPrijatelj();
            System.out.println(osobe.size());
            osobe.stream().forEach(a -> System.out.println(a.getIme() + " " + a.getRodendan()));

        } catch (DataSetException e) {
            var alert = new Alert(Alert.AlertType.ERROR, "Unable to connect to database", ButtonType.OK);
            alert.setTitle("Error");
            alert.showAndWait();
        }
    }
}