package hr.TheZuna.projekt.entitet;

import java.awt.*;
import java.math.BigDecimal;
import java.time.LocalDate;

public class Kolega extends Osoba{
    private String brTelefona;
    private LocalDate rodendan;

    public Kolega(String ime, String prezime, String brTelefona, LocalDate rodendan) {
        super(ime, prezime, rodendan);
        this.brTelefona = brTelefona;
    }

    public String getBrTelefona() {
        return brTelefona;
    }

    public void setBrTelefona(String brTelefona) {
        this.brTelefona = brTelefona;
    }
}
