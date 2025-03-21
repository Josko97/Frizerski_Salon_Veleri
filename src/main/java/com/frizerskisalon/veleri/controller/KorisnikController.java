package com.frizerskisalon.veleri.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.frizerskisalon.veleri.model.Korisnik;

@RestController
public class KorisnikController {

	@PostMapping("/registracija")
	public ResponseEntity<String> registrirajKorisnika(@RequestBody Korisnik korisnik) {
		try {
			korisnikService.registrirajKorisnika(korisnik);
			return new ResponseEntity<>("Korisnik uspješno registriran", HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

}
