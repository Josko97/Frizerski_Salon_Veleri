package com.frizerskisalon.veleri.service;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.frizerskisalon.veleri.model.Korisnik;
import com.frizerskisalon.veleri.repository.KorisnikRepository;
import com.frizerskisalon.veleri.security.BCryptUtil;

@Component
public class KorisnikService {

	private final KorisnikRepository korisnikRepository;
	private final BCryptUtil bcryptUtil;

	public KorisnikService(KorisnikRepository korisnikRepository, BCryptUtil bcryptUtil) {
		super();
		this.korisnikRepository = korisnikRepository;
		this.bcryptUtil = bcryptUtil;
	}

	public ResponseEntity<String> registracijaKorisnika(Korisnik korisnik) {
		if (korisnik == null || korisnik.getEmail() == null) {
			return ResponseEntity.badRequest().body("Korisnik ili email ne smiju biti null!");
		}

		if (korisnikRepository.findByEmail(korisnik.getEmail()).isPresent()) {
			return ResponseEntity.badRequest().body("Korisnik je već registriran!");
		}

		korisnik.setLozinka(bcryptUtil.hashLozinka(korisnik.getLozinka()));

		korisnikRepository.save(korisnik);
		return ResponseEntity.ok("Korisnik uspješno registriran!");
	}

	public ResponseEntity<List<Korisnik>> listaKorisnika() {
		return ResponseEntity.ok(korisnikRepository.findAll());
	}

}
