package com.frizerskisalon.veleri.controller;

import com.frizerskisalon.veleri.model.Termin;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/termin")
public class TerminController {

    @PostMapping("/rezerviraj")
    public ResponseEntity<Termin> rezervirajTermin(@RequestBody Termin termin, @AuthenticationPrincipal UserDetails userDetails){
        return null;
    }
}
