package com.frizerskisalon.veleri.service.impl;

import java.util.List;
import java.util.Optional;

import com.frizerskisalon.veleri.model.DTO.KorisnikDTO;
import com.frizerskisalon.veleri.model.Role;
import com.frizerskisalon.veleri.model.Uloga;
import com.frizerskisalon.veleri.repository.RoleRepository;
import com.frizerskisalon.veleri.service.KorisnikService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.frizerskisalon.veleri.model.Korisnik;
import com.frizerskisalon.veleri.repository.KorisnikRepository;

@Component
public class KorisnikServiceImpl implements KorisnikService {

	private final KorisnikRepository korisnikRepository;
	private final RoleRepository roleRepository;
	private final PasswordEncoder passwordEncoder;

	public KorisnikServiceImpl(KorisnikRepository korisnikRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
		super();
		this.korisnikRepository = korisnikRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
	}

	@Override
	public ResponseEntity<String> registracijaKorisnika(Korisnik korisnik) {
		if (korisnik == null || korisnik.getEmail() == null) {
			return ResponseEntity.badRequest().body("Korisnik ili email ne smiju biti null!");
		}

		if (korisnikRepository.findByEmail(korisnik.getEmail()).isPresent()) {
			return ResponseEntity.badRequest().body("Korisnik je već registriran!");
		}

		korisnik.setLozinka(p);

		korisnikRepository.save(korisnik);
		return ResponseEntity.ok("Korisnik uspješno registriran!");
	}

	@Override
	public ResponseEntity<List<Korisnik>> listaKorisnika() {

		return ResponseEntity.ok(korisnikRepository.findAll());
	}

	@Override
	public ResponseEntity<KorisnikDTO> getKorisnikById(Long id) {

		Korisnik korisnik = korisnikRepository.findById(id).orElseThrow();
		return new ResponseEntity<>(convertToDTO(korisnik), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<String> deleteKorisnikById(Long id) {
		korisnikRepository.deleteById(id);
		return ResponseEntity.ok("Korisnik Izbrisan!");
	}

	@Override
	public ResponseEntity<Korisnik> azurirajKorisnika(Long id, Korisnik korisnik) {
		Optional<Korisnik> korisnikIzBaze = korisnikRepository.findById(id);

        korisnikIzBaze.ifPresent(value -> korisnik.setKorisnikId(value.getKorisnikId()));
		return new ResponseEntity<>(korisnikRepository.save(korisnik), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<String> azurirajRoluKorisnika(Long korisnikId, String nazivUloge) {
		Korisnik korisnik = korisnikRepository.findById(korisnikId).orElseThrow(() -> new RuntimeException("Korisnik nije pronaden!"));
		Uloga uloga = Uloga.valueOf(nazivUloge);
		Role role = roleRepository.findByNazivUloge(uloga).orElseThrow(() -> new RuntimeException("Uloga nije pronadena!"));
		korisnik.setRole(role);
		korisnikRepository.save(korisnik);
		return new ResponseEntity<>(role.toString(), HttpStatus.OK);
	}

	private KorisnikDTO convertToDTO(Korisnik korisnik){
		return new KorisnikDTO(
				korisnik.getKorisnikId(),
				korisnik.getIme(),
				korisnik.getPrezime(),
				korisnik.getEmail(),
				korisnik.isAccountNonLocked(),
				korisnik.isAccountNonExpired(),
				korisnik.isCredentialsNonExpired(),
				korisnik.isEnabled(),
				korisnik.getCredentialsExpiryDate(),
				korisnik.getAccountExpiryDate(),
				korisnik.getTwoFactorSecret(),
				korisnik.isTwoFactorEnabled(),
				korisnik.getSignUpMethod(),
				korisnik.getRole(),
				korisnik.getCreatedDate(),
				korisnik.getUpdatedDate()
		);
	}

}
