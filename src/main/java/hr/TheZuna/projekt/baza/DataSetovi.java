package hr.TheZuna.projekt.baza;

import hr.TheZuna.projekt.entitet.Prijatelj;
import hr.TheZuna.projekt.iznimke.DataSetException;

import java.util.List;

public interface DataSetovi {

    List<Prijatelj> readPrijatelj() throws DataSetException;

    void createPrijatelj(Prijatelj prijatelj) throws DataSetException;
    void removePrijatelj(Prijatelj prijatelj) throws DataSetException;
    void editPrijatelj(Prijatelj prijatelj) throws DataSetException;
}
