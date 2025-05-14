package com.frizerskisalon.veleri.controller;

import com.frizerskisalon.veleri.model.Korisnik;
import com.frizerskisalon.veleri.service.KorisnikService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
@PreAuthorize("hasRole('ROLE_ADMIN')")
public class AdminController {

    private final KorisnikService korisnikService;

    public AdminController(KorisnikService korisnikService) {
        this.korisnikService = korisnikService;
    }

    @GetMapping
    public ResponseEntity<List<Korisnik>> listaKorisnika() {
        return korisnikService.listaKorisnika();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteKorisnik(@PathVariable Long id){
        return korisnikService.deleteKorisnikById(id);
    }

    @PutMapping("/azuriraj-rolu")
    public ResponseEntity<String> azurirajRoluKorisnika(@RequestParam Long korisnikId, @RequestParam String nazivUloge){
        return korisnikService.azurirajRoluKorisnika(korisnikId, nazivUloge);
    }
}
