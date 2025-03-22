package com.frizerskisalon.veleri.security;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class BCryptUtil {

	private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

	public String hashLozinka(String lozinka) {
		return passwordEncoder.encode(lozinka);
	}

	public boolean provjeriLozinku(String unesenaLozinka, String hashiranaLozinka) {
		return passwordEncoder.matches(unesenaLozinka, hashiranaLozinka);
	}
}
