package com.frizerskisalon.veleri.repository;


import com.frizerskisalon.veleri.model.Termin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface TerminRepository extends JpaRepository<Termin, Long> {


    List<Termin> findByFrizer_FrizerIdAndUsluga_UslugaIdAndDatumTermina(Long frizerId, Long uslugaId, String datumTermina);

    List<Termin> findByFrizer_FrizerIdAndDatumTermina(Long frizerId, String datumTermina);

}
