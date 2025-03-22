package com.frizerskisalon.veleri.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.frizerskisalon.veleri.model.Korisnik;
import com.frizerskisalon.veleri.service.KorisnikService;

@RestController
@RequestMapping("/frizerskisalon")
public class KorisnikController {

	private final KorisnikService korisnikService;

	public KorisnikController(KorisnikService korisnikService) {
		this.korisnikService = korisnikService;
	}

	@PostMapping("/registracija")
	public ResponseEntity<String> registrirajKorisnika(@RequestBody Korisnik korisnik) {
		return korisnikService.registracijaKorisnika(korisnik);
	}

	@GetMapping("/korisnik")
	public ResponseEntity<List<Korisnik>> listaKorisnika() {
		return korisnikService.listaKorisnika();
	}

}
