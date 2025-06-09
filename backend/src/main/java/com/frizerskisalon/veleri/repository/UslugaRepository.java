package com.frizerskisalon.veleri.repository;

import com.frizerskisalon.veleri.model.Usluga;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UslugaRepository extends JpaRepository<Usluga, Long> {
}
