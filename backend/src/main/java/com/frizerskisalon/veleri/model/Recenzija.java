package com.frizerskisalon.veleri.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

@Entity
@Data
@NoArgsConstructor
@Table(name = "recenzija")
public class Recenzija {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "recenzija_id")
	private Long recenzijaId;

	@Column(name = "ocjena")
	private BigDecimal ocjena;

	@Column(name = "komentar")
	private String komentar;

	@CreationTimestamp
	@Column(name = "datum_recenzije", updatable = false)
	private LocalDateTime datumRecenzije;

	@ManyToOne
	@JoinColumn(name = "korisnik_id")
	private Korisnik korisnik;

	@ManyToOne
	@JoinColumn(name = "frizer_id")
	private Frizer frizer;

	@OneToOne
	@JoinColumn(name = "termin_id")
	private Termin termin;


	public Recenzija(BigDecimal ocjena, String komentar, LocalDateTime datum) {
		this.ocjena = ocjena;
		this.komentar = komentar;
		this.datumRecenzije = datum;
	}

}
