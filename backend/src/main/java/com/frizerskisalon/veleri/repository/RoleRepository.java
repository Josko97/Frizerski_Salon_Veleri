package com.frizerskisalon.veleri.repository;

import com.frizerskisalon.veleri.model.Role;
import com.frizerskisalon.veleri.model.Uloga;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.beans.JavaBean;
import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByNazivUloge(Uloga uloga);
}
