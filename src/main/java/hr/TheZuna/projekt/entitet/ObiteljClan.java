package hr.TheZuna.projekt.entitet;

import java.time.LocalDate;

public class ObiteljClan extends Osoba{
    private String ime, prezime, adresa;
    private LocalDate rodendan;

    public ObiteljClan(String ime, String prezime, String adresa, LocalDate rodendan) {
        super(ime, prezime, rodendan);
        this.adresa = adresa;
    }
    public String getAdresa() {
        return adresa;
    }

    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }

    public static class Builder{
        private String ime, prezime, adresa;
        private LocalDate rodendan;

        public Builder withIme(String ime){
            this.ime = ime;
            return this;
        }
        public Builder withPrezime(String prezime) {
            this.prezime = prezime;
            return this;
        }
        public Builder withAdresa(String adresa) {
            this.adresa = adresa;
            return this;
        }
        public Builder withRodendan(LocalDate rodendan) {
            this.rodendan = rodendan;
            return this;
        }

        public ObiteljClan build() {
            return new ObiteljClan(ime, prezime, adresa, rodendan);
        }
    }

}
