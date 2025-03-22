package com.frizerskisalon.veleri.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.frizerskisalon.veleri.model.Korisnik;

@Repository
public interface KorisnikRepository extends JpaRepository<Korisnik, Long> {

	Optional<Korisnik> findByEmail(String email);
}
