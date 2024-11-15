package hr.TheZuna.projekt.baza;

import hr.TheZuna.projekt.entitet.Kolega;
import hr.TheZuna.projekt.entitet.ObiteljClan;
import hr.TheZuna.projekt.entitet.Prijatelj;
import hr.TheZuna.projekt.iznimke.DataSetException;
import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;

import javax.xml.xpath.XPathEvaluationResult;
import java.io.Closeable;
import java.math.BigDecimal;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class BazePodataka implements DataSetovi, Closeable {
    private final Connection connection;


    public BazePodataka() throws DataSetException {
        try {
            connection = DriverManager.getConnection("jdbc:h2:tcp://localhost/~/java-tvz-2023-novi", "student", "student");
        } catch (SQLException e) {
            throw new DataSetException(e);
        }
    }

    @Override
    public void close() {
        try{
            connection.close();
        }catch (SQLException exception){}
    }


    public List<Kolega> readKolega() throws DataSetException {
        var query = "SELECT * FROM KOLEGA;";
        try(var statement = connection.prepareStatement(query)){
            statement.execute();

            var kolega = new ArrayList<Kolega>();

            try(var results = statement.getResultSet()){
                while (results.next()){
                    String ime = results.getString("ime");
                    String prezime = results.getString("prezime");
                    String brTelefona = results.getString("brTelefona");
                    LocalDate rodendan = results.getDate("datum_rodjenja").toLocalDate();
                    Kolega noviKolega = new Kolega(ime, prezime, brTelefona, rodendan);
                    noviKolega.setId(results.getInt("ID"));
                    kolega.add(noviKolega);
                }
            }
            return kolega;

        }catch (SQLException e){
            throw new DataSetException(e);
        }
    }

    @Override
    public List<Prijatelj> readPrijatelj() throws DataSetException {
        var query = "SELECT * FROM OSOBA;";
        try(var statement = connection.prepareStatement(query)){
            statement.execute();

            var prijatelji = new ArrayList<Prijatelj>();

            try(var results = statement.getResultSet()){
                while (results.next()){
                    String ime = results.getString("ime");
                    String prezime = results.getString("prezime");
                    String email = results.getString("email");
                    LocalDate rodendan = results.getDate("datum_rodjenja").toLocalDate();
                    Prijatelj noviPrijatelj = new Prijatelj(ime, prezime, email, rodendan);
                    noviPrijatelj.setId(results.getInt("ID"));
                    prijatelji.add(noviPrijatelj);
                }
            }
            return prijatelji;

        }catch (SQLException e){
            throw new DataSetException(e);
        }
    }
    @Override
    public List<ObiteljClan> readObiteljskiClan() throws DataSetException {
        var query = "SELECT * FROM OBITELJSKI_CLAN;";
        try(var statement = connection.prepareStatement(query)){
            statement.execute();

            var obiteljskiClanovi = new ArrayList<ObiteljClan>();

            try(var results = statement.getResultSet()){
                while (results.next()){
                    String ime = results.getString("ime");
                    String prezime = results.getString("prezime");
                    LocalDate rodendan = results.getDate("datum_rodjenja").toLocalDate();
                    String adresa = results.getString("datum_rodjenja");

                    ObiteljClan noviObiteljskiClan = new ObiteljClan.Builder()
                            .withIme(ime)
                            .withPrezime(prezime)
                            .withRodendan(rodendan)
                            .withAdresa(adresa)
                            .build();

                    noviObiteljskiClan.setId(results.getInt("ID"));
                    obiteljskiClanovi.add(noviObiteljskiClan);
                }
            }
            return obiteljskiClanovi;

        }catch (SQLException e){
            throw new DataSetException(e);
        }
    }


    @Override
    public void createPrijatelj(Prijatelj prijatelj) throws DataSetException {
        String sql = "INSERT INTO osoba (ime , prezime, email, datum_rodjenja) VALUES (?, ?, ?, ?)";
        try{
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, prijatelj.getIme());
            statement.setString(2, prijatelj.getPrezime());
            statement.setString(3, prijatelj.getEmail());
            statement.setDate(4, Date.valueOf(prijatelj.getRodendan()));

            statement.execute();

        }catch (SQLException ex){
            System.out.println(ex.getMessage());
            throw new DataSetException(ex);
        }
    }
    @Override
    public void createKolega(Kolega kolega) throws DataSetException {
        String sql = "INSERT INTO kolega (ime , prezime, brTelefona, datum_rodjenja) VALUES (?, ?, ?, ?)";
        try{
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, kolega.getIme());
            statement.setString(2, kolega.getPrezime());
            statement.setString(3, kolega.getBrTelefona().toString());
            statement.setDate(4, Date.valueOf(kolega.getRodendan()));

            statement.execute();

        }catch (SQLException ex){
            System.out.println(ex.getMessage());
            throw new DataSetException(ex);
        }
    }

    @Override
    public void createObiteljskiClan(ObiteljClan obiteljClan) throws DataSetException {
        String sql = "INSERT INTO OBITELJSKI_CLAN (ime , prezime, datum_rodjenja, adresa) VALUES (?, ?, ?, ?)";
        try{
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, obiteljClan.getIme());
            statement.setString(2, obiteljClan.getPrezime());
            statement.setDate(3, Date.valueOf(obiteljClan.getRodendan()));
            statement.setString(4, obiteljClan.getAdresa());

            statement.execute();

        }catch (SQLException ex){
            System.out.println(ex.getMessage());
            throw new DataSetException(ex);
        }
    }

    @Override
    public void removePrijatelj(Prijatelj prijatelj) throws DataSetException{
        String sql = "DELETE FROM osoba WHERE id = ?";
        try{
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, prijatelj.getId());
            statement.execute();
        }catch (SQLException ex){
            System.out.println(ex.getMessage());
            throw new DataSetException(ex);
        }
    }
    @Override
    public void removeKolega(Kolega kolega) throws DataSetException{
        String sql = "DELETE FROM kolega WHERE id = ?";
        try{
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, kolega.getId());
            statement.execute();
        }catch (SQLException ex){
            System.out.println(ex.getMessage());
            throw new DataSetException(ex);
        }
    }
    @Override
    public void removeObiteljskiClan(ObiteljClan obiteljClan) throws DataSetException{
        String sql = "DELETE FROM OBITELJSKI_CLAN WHERE id = ?";
        try{
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, obiteljClan.getPrezime());
            statement.execute();
        }catch (SQLException ex){
            System.out.println(ex.getMessage());
            throw new DataSetException(ex);
        }
    }

    @Override
    public void editPrijatelj(Prijatelj startiPrijatelj, Prijatelj noviPrijatelj) throws DataSetException{
        String sql = "UPDATE OSOBA  SET IME = ?, PREZIME = ?, EMAIL = ?, DATUM_RODJENJA = ? WHERE ID = ?";

        try{
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, noviPrijatelj.getIme());
            statement.setString(2, noviPrijatelj.getPrezime());
            statement.setString(3, noviPrijatelj.getEmail());
            statement.setDate(4, Date.valueOf(noviPrijatelj.getRodendan()));
            statement.setInt(5, startiPrijatelj.getId());
            statement.executeUpdate();

        }catch (SQLException ex){
            System.out.println(ex.getMessage());
            throw new DataSetException(ex);
        }
    }

    @Override
    public void editKolega(Kolega stariKolega, Kolega noviKolega ) throws DataSetException{
        String sql = "UPDATE KOLEGA SET IME = ?, PREZIME = ?, BRTELEFONA = ?, DATUM_RODJENJA = ? WHERE ID = ?";

        try{
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, noviKolega.getIme());
            statement.setString(2, noviKolega.getPrezime());
            statement.setString(3, noviKolega.getBrTelefona());
            statement.setDate(4, Date.valueOf(noviKolega.getRodendan()));
            statement.setInt(5, stariKolega.getId());
            statement.executeUpdate();

        }catch (SQLException ex){
            System.out.println(ex.getMessage());
            throw new DataSetException(ex);
        }
    }
    @Override
    public void editObiteljClan(ObiteljClan stariClan, ObiteljClan noviClan) throws DataSetException{
        String sql = "UPDATE OBITELJSKI_CLAN SET IME = ?, PREZIME = ?, ADRESA = ?, DATUM_RODJENJA = ? WHERE ID = ?";

        try{
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, noviClan.getIme());
            statement.setString(2, noviClan.getPrezime());
            statement.setString(3, noviClan.getAdresa());
            statement.setDate(4, Date.valueOf(noviClan.getRodendan()));
            statement.setInt(5, stariClan.getId());
            statement.executeUpdate();

        }catch (SQLException ex){
            System.out.println(ex.getMessage());
            throw new DataSetException(ex);
        }
    }
}
