package hr.TheZuna.projekt.entitet;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Kolega extends Osoba{
    private BigDecimal brTelefona;
    private LocalDate rodendan;

    public Kolega(String ime, String prezime, BigDecimal brTelefona, LocalDate rodendan) {
        super(ime, prezime, rodendan);
        this.brTelefona = brTelefona;
    }

    public BigDecimal getBrTelefona() {
        return brTelefona;
    }

    public void setBrTelefona(BigDecimal brTelefona) {
        this.brTelefona = brTelefona;
    }
}
