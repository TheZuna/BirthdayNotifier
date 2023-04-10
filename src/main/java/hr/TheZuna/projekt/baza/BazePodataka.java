package hr.TheZuna.projekt.baza;

import hr.TheZuna.projekt.entitet.Prijatelj;
import hr.TheZuna.projekt.iznimke.DataSetException;
import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;

import javax.xml.xpath.XPathEvaluationResult;
import java.io.Closeable;
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
                    prijatelji.add(noviPrijatelj);
                }
            }
            return prijatelji;

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
    public void removePrijatelj(Prijatelj prijatelj) throws DataSetException{
        String sql = "DELETE FROM osoba WHERE ime = ? AND prezime = ?";
        try{
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, prijatelj.getIme());
            statement.setString(2, prijatelj.getPrezime());
            statement.execute();
        }catch (SQLException ex){
            System.out.println(ex.getMessage());
            throw new DataSetException(ex);
        }
    }
    @Override
    public void editPrijatelj(Prijatelj prijatelj) throws DataSetException{

    }
}
