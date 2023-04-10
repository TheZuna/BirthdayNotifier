package hr.TheZuna.projekt.entitet;

import java.time.LocalDate;

public class Prijatelj {
    private String ime, prezime, email;
    private LocalDate rodendan;

    public Prijatelj(String ime, String prezime, String email, LocalDate rodendan) {
        this.ime = ime;
        this.prezime = prezime;
        this.email = email;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getRodendan() {
        return rodendan;
    }

    public void setRodendan(LocalDate rodendan) {
        this.rodendan = rodendan;
    }
}
