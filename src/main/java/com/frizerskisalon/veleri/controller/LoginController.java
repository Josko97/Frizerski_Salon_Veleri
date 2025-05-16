package com.frizerskisalon.veleri.controller;

import com.frizerskisalon.veleri.model.Korisnik;
import com.frizerskisalon.veleri.service.KorisnikService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/login")
public class LoginController {

    private final KorisnikService korisnikService;

    public LoginController(KorisnikService korisnikService) {
        this.korisnikService = korisnikService;
    }

    @PostMapping("/registracija")
    public ResponseEntity<String> registrirajKorisnika(@RequestBody Korisnik korisnik) {
        return korisnikService.registracijaKorisnika(korisnik);
    }
}
