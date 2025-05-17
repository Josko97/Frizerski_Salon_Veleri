package com.frizerskisalon.veleri.model;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

@Entity
@Data
@NoArgsConstructor
@Table(name = "termin")
public class Termin {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "termin_id")
	private Long terminId;

	@CreationTimestamp
	@Column(updatable = false, name = "datum_termina")
	private LocalDateTime datumTermina;

	@ManyToOne
	@JoinColumn(name = "usluga_id", nullable = false)
	private Usluga usluga;

	@ManyToOne
	@JoinColumn(name = "frizer_id", nullable = false)
	private Frizer frizer;

	@ManyToOne
	@JoinColumn(name = "korisnik_id")
	private Korisnik korisnik;



}
