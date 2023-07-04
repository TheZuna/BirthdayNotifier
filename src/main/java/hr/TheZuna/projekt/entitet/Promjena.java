package hr.TheZuna.projekt.entitet;

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


    public Promjena(String tipPromijene, Osoba osoba, LocalDate vrijemePromjene){
        this.tipPromijene = tipPromijene;
        this.osoba = osoba;
        this.vrijemePromjene = vrijemePromjene;
    }
    public String getPromjena(){
        return (vrijemePromjene + " " + tipPromijene + " " + osoba.getIme() + " " + osoba.getPrezime());
    }

    public String getTipPromijene() {
        return tipPromijene;
    }
}
