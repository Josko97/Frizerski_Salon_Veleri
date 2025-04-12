package com.frizerskisalon.veleri.security.services;

import com.frizerskisalon.veleri.model.Korisnik;
import com.frizerskisalon.veleri.repository.KorisnikRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    KorisnikRepository korisnikRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Korisnik korisnik = korisnikRepository.findByEmail(email).orElseThrow(()->new UsernameNotFoundException("Korisnik sa navedenom email adresom nije pronaden! "+ email));
        return UserDetailsImpl.build(korisnik);
    }
}
