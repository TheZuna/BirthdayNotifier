package hr.TheZuna.projekt.baza;

import hr.TheZuna.projekt.entitet.Kolega;
import hr.TheZuna.projekt.entitet.Prijatelj;
import hr.TheZuna.projekt.iznimke.DataSetException;

import java.util.List;

public interface DataSetovi {

    List<Prijatelj> readPrijatelj() throws DataSetException;
    List<Kolega> readKolega() throws DataSetException;

    void createPrijatelj(Prijatelj prijatelj) throws DataSetException;
    void createKolega(Kolega kolega) throws DataSetException;

    void removePrijatelj(Prijatelj prijatelj) throws DataSetException;
    void removeKolega(Kolega kolega) throws DataSetException;

    void editPrijatelj(Prijatelj prijatelj) throws DataSetException;
    void editKolega(Kolega kolega) throws DataSetException;
}
