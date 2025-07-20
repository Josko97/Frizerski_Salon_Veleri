package com.frizerskisalon.veleri.service;

import com.frizerskisalon.veleri.model.DTO.KorisnikDTO;
import com.frizerskisalon.veleri.model.Korisnik;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

public interface KorisnikService {

    public ResponseEntity<Map<String, String>> registracijaKorisnika(Korisnik korisnik);

    ResponseEntity<List<KorisnikDTO>> listaKorisnika();

    ResponseEntity<KorisnikDTO> getKorisnikById(Long id);

    ResponseEntity<String> deleteKorisnikById(Long id);

    ResponseEntity<Korisnik> azurirajKorisnika(Long id, Korisnik korisnik);

    ResponseEntity<String> azurirajRoluKorisnika(Long korisnikId, String nazivUloge);


}
