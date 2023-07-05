package hr.TheZuna.projekt.entitet;

import java.io.Serializable;
import java.time.LocalDate;

public class Prijatelj extends Osoba implements Serializable {
    private String ime, prezime, email;
    private LocalDate rodendan;

    public Prijatelj(String ime, String prezime, String email, LocalDate rodendan) {
        super(ime, prezime, rodendan);
        this.email = email;
    }
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
