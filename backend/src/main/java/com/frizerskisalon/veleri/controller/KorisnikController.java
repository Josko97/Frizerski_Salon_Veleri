package com.frizerskisalon.veleri.controller;

import java.util.List;

import com.frizerskisalon.veleri.model.DTO.KorisnikDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import com.frizerskisalon.veleri.model.Korisnik;
import com.frizerskisalon.veleri.service.KorisnikService;

@RestController
@RequestMapping("/api/private")
public class KorisnikController {

	private final KorisnikService korisnikService;

	public KorisnikController(KorisnikService korisnikService) {
		this.korisnikService = korisnikService;
	}


	@PutMapping("/korisnik/{korisnikId}")
	public ResponseEntity<Korisnik> azurirajKorisnika(@PathVariable Long korisnikId, @RequestBody Korisnik korisnik){
		return korisnikService.azurirajKorisnika(korisnikId, korisnik);
	}

	@GetMapping("/korisnik/{korisnikId}")
	public ResponseEntity<KorisnikDTO> getKorisnikById(@PathVariable Long id){
			return korisnikService.getKorisnikById(id);
	}

	@DeleteMapping("/korisnik/{korisnikId}")
	public ResponseEntity<String> deleteKorisnik(@PathVariable Long id){
		return korisnikService.deleteKorisnikById(id);
	}

}
