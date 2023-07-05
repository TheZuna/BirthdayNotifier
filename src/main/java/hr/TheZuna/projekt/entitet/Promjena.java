package hr.TheZuna.projekt.entitet;

import hr.TheZuna.projekt.users.User;

import java.io.Serializable;
import java.lang.reflect.MalformedParameterizedTypeException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Promjena implements Serializable {

    String tipPromijene;
    Osoba osoba;
    LocalDate vrijemePromjene;
    User user;

    //private static final long serialVersionUID = -5928444989612175375L;


    public Promjena(String tipPromijene, Osoba osoba, LocalDate vrijemePromjene, User user){
        this.tipPromijene = tipPromijene;
        this.osoba = osoba;
        this.vrijemePromjene = vrijemePromjene;
        this.user = user;
    }
    public String getPromjena(){
        return (vrijemePromjene + " " + tipPromijene + " " + osoba.getIme() + " " + osoba.getPrezime());
    }

    public String getTipPromijene() {
        return tipPromijene;
    }

    @Override
    public String toString() {
        return "Promjena{" +
                "tipPromijene='" + tipPromijene + '\'' +
                ", osoba=" + osoba +
                ", vrijemePromjene=" + vrijemePromjene +
                ", user=" + user +
                '}';
    }
}
