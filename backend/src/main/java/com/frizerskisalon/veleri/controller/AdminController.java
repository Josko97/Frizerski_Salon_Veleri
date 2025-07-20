package com.frizerskisalon.veleri.controller;

import com.frizerskisalon.veleri.model.DTO.KorisnikDTO;
import com.frizerskisalon.veleri.model.DTO.TerminDTO;
import com.frizerskisalon.veleri.model.Korisnik;
import com.frizerskisalon.veleri.model.Termin;
import com.frizerskisalon.veleri.repository.TerminRepository;
import com.frizerskisalon.veleri.service.KorisnikService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/private")
@CrossOrigin(origins = "http://localhost:9000")
public class AdminController {

    @Autowired
    TerminRepository terminRepository;

    private final KorisnikService korisnikService;

    public AdminController(KorisnikService korisnikService) {
        this.korisnikService = korisnikService;
    }

    @GetMapping("/admin/lista-korisnika")
    public ResponseEntity<List<KorisnikDTO>> listaKorisnika() {
        return korisnikService.listaKorisnika();
    }

    @DeleteMapping("/admin/delete-korisnik/{id}")
    public ResponseEntity<String> deleteKorisnik(@PathVariable Long id){
        return korisnikService.deleteKorisnikById(id);
    }

    @PutMapping("/admin/azuriraj-rolu")
    public ResponseEntity<String> azurirajRoluKorisnika(@RequestParam Long korisnikId, @RequestParam String nazivUloge){
        return korisnikService.azurirajRoluKorisnika(korisnikId, nazivUloge);
    }

    @GetMapping("/admin/lista-termina")
    public ResponseEntity<List<TerminDTO>> listaTermina() {
        List<Termin> termini = terminRepository.findAll();
        List<TerminDTO> dto = termini.stream().map(TerminDTO::new).toList();
        return ResponseEntity.ok(dto);
    }
}
