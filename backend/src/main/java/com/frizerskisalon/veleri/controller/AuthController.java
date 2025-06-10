package com.frizerskisalon.veleri.controller;

import com.frizerskisalon.veleri.model.Korisnik;
import com.frizerskisalon.veleri.model.Role;
import com.frizerskisalon.veleri.model.Uloga;
import com.frizerskisalon.veleri.repository.RoleRepository;
import com.frizerskisalon.veleri.security.jwt.JwtUtils;
import com.frizerskisalon.veleri.security.request.LoginRequest;
import com.frizerskisalon.veleri.security.response.LoginResponse;
import com.frizerskisalon.veleri.service.KorisnikService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/public")
@CrossOrigin(origins = "http://localhost:9000")
public class AuthController {

    @Autowired
    JwtUtils jwtUtils;

    @Autowired
    KorisnikService korisnikService;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    AuthenticationManager authenticationManager;


    @PostMapping("/auth/signin")
    public ResponseEntity<?> authenticateUser(@RequestBody LoginRequest loginRequest) {
        Authentication authentication;
        try {
            authentication = authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
        } catch (AuthenticationException exception) {
            Map<String, Object> map = new HashMap<>();
            map.put("message", "Bad credentials");
            map.put("status", false);
            return new ResponseEntity<Object>(map, HttpStatus.NOT_FOUND);
        }

        SecurityContextHolder.getContext().setAuthentication(authentication);

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        String jwtToken = jwtUtils.generateTokenFromUsername(userDetails);

        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());

        LoginResponse response = new LoginResponse(userDetails.getUsername(), roles, jwtToken);

        return ResponseEntity.ok(response);
    }

    @PostMapping("/auth/register")
    public ResponseEntity<Map<String, String>> registrirajKorisnika(@RequestBody Korisnik korisnik) {
        Optional<Role> optionalRole = roleRepository.findByNazivUloge(Uloga.ROLE_KORISNIK);


            Role korisnickaUloga = optionalRole.get();


        korisnik.setRole(korisnickaUloga);
        return korisnikService.registracijaKorisnika(korisnik);
    }

}
