package hr.TheZuna.projekt.entitet;

import java.time.LocalDate;

public class Osoba {

    private String ime, prezime;
    private LocalDate rodendan;

    public Osoba(String ime, String prezime, LocalDate rodendan) {
        this.ime = ime;
        this.prezime = prezime;
        this.rodendan = rodendan;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public LocalDate getRodendan() {
        return rodendan;
    }

    public void setRodendan(LocalDate rodendan) {
        this.rodendan = rodendan;
    }
    @Override
    public String toString() {
        return "Osoba{" +
                "Ime=" + ime +
                ", Prezime='" + prezime + '\'' +
                '}';
    }
}
