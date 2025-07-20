package com.frizerskisalon.veleri.model.DTO;

import com.frizerskisalon.veleri.model.Termin;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TerminDTO {
    private Long terminId;
    private String datumTermina;
    private String vrijeme;
    private String uslugaNaziv;
    private String frizerIme;
    private String frizerPrezime;
    private String korisnikIme;
    private String korisnikPrezime;

    public TerminDTO(Termin t) {
        this.terminId = t.getTerminId();
        this.datumTermina = t.getDatumTermina();
        this.vrijeme = t.getVrijeme();
        this.uslugaNaziv = t.getUsluga() != null ? t.getUsluga().getNaziv() : "";
        this.frizerIme = t.getFrizer() != null && t.getFrizer().getKorisnik() != null ? t.getFrizer().getKorisnik().getIme() : "";
        this.frizerPrezime = t.getFrizer() != null && t.getFrizer().getKorisnik() != null ? t.getFrizer().getKorisnik().getPrezime() : "";
        this.korisnikIme = t.getKorisnik() != null ? t.getKorisnik().getIme() : "";
        this.korisnikPrezime = t.getKorisnik() != null ? t.getKorisnik().getPrezime() : "";
    }
}
