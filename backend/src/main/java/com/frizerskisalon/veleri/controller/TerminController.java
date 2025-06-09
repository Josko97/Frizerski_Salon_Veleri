package com.frizerskisalon.veleri.controller;

import com.frizerskisalon.veleri.model.Frizer;
import com.frizerskisalon.veleri.model.Korisnik;
import com.frizerskisalon.veleri.model.Termin;
import com.frizerskisalon.veleri.model.Usluga;
import com.frizerskisalon.veleri.repository.FrizerRepository;
import com.frizerskisalon.veleri.repository.KorisnikRepository;
import com.frizerskisalon.veleri.repository.TerminRepository;
import com.frizerskisalon.veleri.repository.UslugaRepository;
import com.frizerskisalon.veleri.service.TerminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/private")
@CrossOrigin(origins = "http://localhost:9000", allowCredentials = "true")
public class TerminController {

    @Autowired
    KorisnikRepository korisnikRepository;

    @Autowired
    TerminRepository terminRepository;

    @Autowired
    private FrizerRepository frizerRepository;

    @Autowired
    TerminService terminService;

    @Autowired
    private UslugaRepository uslugaRepository;

    @PostMapping("/termin/rezerviraj/usluga/{uslugaId}/frizer/{frizerId}")
    public ResponseEntity<Termin> rezervirajTermin(
            @PathVariable Long uslugaId,
            @PathVariable Long frizerId,
            @RequestBody Termin termin,
            @AuthenticationPrincipal UserDetails userDetails) {

        // Nađi korisnika iz tokena
        Optional<Korisnik> korisnik = korisnikRepository.findByUsername(userDetails.getUsername());
        Optional<Usluga> usluga = uslugaRepository.findById(uslugaId);
        Optional<Frizer> frizer = frizerRepository.findById(frizerId);

        if (korisnik.isEmpty()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        if (usluga.isEmpty() || frizer.isEmpty()) {
            return ResponseEntity.badRequest().build(); // ili možeš poslati custom poruku
        }

        termin.setKorisnik(korisnik.get());
        termin.setUsluga(usluga.get());
        termin.setFrizer(frizer.get());

        return terminService.rezervacijaTermina(termin);
    }




    @GetMapping("/usluge")
    public List<Usluga> getUsluge() {
        return uslugaRepository.findAll();
    }



    @GetMapping("/frizeri")
    public List<Frizer> getFrizeri() {
        return frizerRepository.findAll();
    }

    @GetMapping("/termini/zauzeti")
    public List<String> zauzetiTermini(
            @RequestParam Long frizerId,
            @RequestParam Long uslugaId,
            @RequestParam String datum) {

        List<Termin> zauzeti = terminRepository
                .findByFrizer_FrizerIdAndUsluga_UslugaIdAndDatumTermina(frizerId, uslugaId, datum);

        return zauzeti.stream()
                .map(Termin::getVrijeme)
                .collect(Collectors.toList());
    }



}
