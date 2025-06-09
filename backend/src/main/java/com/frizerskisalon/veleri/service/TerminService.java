package com.frizerskisalon.veleri.service;

import com.frizerskisalon.veleri.model.Termin;
import org.springframework.http.ResponseEntity;

public interface TerminService {
    ResponseEntity<Termin> rezervacijaTermina(Termin termin);
}
