package com.frizerskisalon.veleri.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import com.frizerskisalon.veleri.model.Korisnik;
import com.frizerskisalon.veleri.service.KorisnikService;

@RestController
@RequestMapping("/korisnik")
public class KorisnikController {

	private final KorisnikService korisnikService;

	public KorisnikController(KorisnikService korisnikService) {
		this.korisnikService = korisnikService;
	}

	@PostMapping("/registracija")
	public ResponseEntity<String> registrirajKorisnika(@RequestBody Korisnik korisnik) {
		return korisnikService.registracijaKorisnika(korisnik);
	}

	@GetMapping
	public ResponseEntity<List<Korisnik>> listaKorisnika() {
		return korisnikService.listaKorisnika();
	}

	@GetMapping("/{id}")
	public ResponseEntity<Korisnik> getKorisnikById(@PathVariable Long id){
			return korisnikService.getKorisnikById(id);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteKorisnik(@PathVariable Long id){
		return korisnikService.deleteKorisnikById(id);
	}

}
