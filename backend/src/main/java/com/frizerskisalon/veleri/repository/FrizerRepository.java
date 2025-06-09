package com.frizerskisalon.veleri.repository;

import com.frizerskisalon.veleri.model.Frizer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FrizerRepository extends JpaRepository<Frizer, Long> {
}
