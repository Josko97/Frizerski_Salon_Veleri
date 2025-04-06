package com.frizerskisalon.veleri.service.impl;

import java.util.List;
import java.util.Optional;

import com.frizerskisalon.veleri.service.KorisnikService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.frizerskisalon.veleri.model.Korisnik;
import com.frizerskisalon.veleri.repository.KorisnikRepository;
import com.frizerskisalon.veleri.security.BCryptUtil;

@Component
public class KorisnikServiceImpl implements KorisnikService {

	private final KorisnikRepository korisnikRepository;
	private final BCryptUtil bcryptUtil;

	public KorisnikServiceImpl(KorisnikRepository korisnikRepository, BCryptUtil bcryptUtil) {
		super();
		this.korisnikRepository = korisnikRepository;
		this.bcryptUtil = bcryptUtil;
	}

	@Override
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

	@Override
	public ResponseEntity<List<Korisnik>> listaKorisnika() {

		return ResponseEntity.ok(korisnikRepository.findAll());
	}

	@Override
	public ResponseEntity<Korisnik> getKorisnikById(Long id) {
		Optional<Korisnik> korisnik = korisnikRepository.findById(id);
		if(korisnik.isPresent()) {
			return ResponseEntity.ok(korisnik.get());
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@Override
	public ResponseEntity<String> deleteKorisnikById(Long id) {
		korisnikRepository.deleteById(id);
		return ResponseEntity.ok("Korisnik Izbrisan!");
	}

}
