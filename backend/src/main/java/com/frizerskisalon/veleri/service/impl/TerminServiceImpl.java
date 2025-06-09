package com.frizerskisalon.veleri.service.impl;

import com.frizerskisalon.veleri.model.Termin;
import com.frizerskisalon.veleri.repository.TerminRepository;
import com.frizerskisalon.veleri.service.TerminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class TerminServiceImpl implements TerminService {

    @Autowired
    TerminRepository terminRepository;

    @Override
    public ResponseEntity<Termin> rezervacijaTermina(Termin termin) {
        if (termin == null) {
            return ResponseEntity.badRequest().body(termin);
        }

        terminRepository.save(termin);
        return ResponseEntity.ok(termin);
    }
}
