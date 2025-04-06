package com.frizerskisalon.veleri.service;

import com.frizerskisalon.veleri.model.Korisnik;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface KorisnikService {

    ResponseEntity<String> registracijaKorisnika(Korisnik korisnik);

    ResponseEntity<List<Korisnik>> listaKorisnika();

    ResponseEntity<Korisnik> getKorisnikById(Long id);

    ResponseEntity<String> deleteKorisnikById(Long id);
}
