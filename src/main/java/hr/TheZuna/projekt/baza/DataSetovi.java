package hr.TheZuna.projekt.baza;

import hr.TheZuna.projekt.entitet.Kolega;
import hr.TheZuna.projekt.entitet.ObiteljClan;
import hr.TheZuna.projekt.entitet.Prijatelj;
import hr.TheZuna.projekt.iznimke.DataSetException;
import javafx.scene.chart.PieChart;

import java.io.ObjectInputStream;
import java.util.List;

public interface DataSetovi {

    List<Prijatelj> readPrijatelj() throws DataSetException;
    List<Kolega> readKolega() throws DataSetException;
    List<ObiteljClan> readObiteljskiClan() throws DataSetException;


    void createPrijatelj(Prijatelj prijatelj) throws DataSetException;
    void createKolega(Kolega kolega) throws DataSetException;
    void createObiteljskiClan(ObiteljClan obiteljClan) throws DataSetException;


    void removePrijatelj(Prijatelj prijatelj) throws DataSetException;
    void removeKolega(Kolega kolega) throws DataSetException;
    void removeObiteljskiClan(ObiteljClan obiteljClan) throws DataSetException;


    void editPrijatelj(Prijatelj prijatelj) throws DataSetException;
    void editKolega(Kolega stariKolega, Kolega noviKolega) throws DataSetException;
}
