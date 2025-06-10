package com.frizerskisalon.veleri.controller;

import com.frizerskisalon.veleri.model.KontaktForma;
import com.frizerskisalon.veleri.service.impl.KontaktService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/public")
@CrossOrigin(origins = "http://localhost:9000")
public class KontaktController {

    private final KontaktService kontaktService;

    @Autowired
    public KontaktController(KontaktService kontaktService) {
        this.kontaktService = kontaktService;
    }

    @PostMapping("/kontakt")
    public ResponseEntity<?> sendContactForm(@Valid @RequestBody KontaktForma zahtjev) {
        kontaktService.obradaForme(zahtjev);
        return ResponseEntity.ok("Poruka uspješno poslana!");
    }
}
