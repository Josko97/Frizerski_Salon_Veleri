package com.frizerskisalon.veleri.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Recenzija {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column
	private BigDecimal ocjena;

	@Column
	private String komentar;

	@Column
	private LocalDateTime datum;

	@ManyToOne
	@JoinColumn(name = "korisnik_id")
	private Korisnik korisnik;

	@ManyToOne
	@JoinColumn(name = "frizer_id")
	private Frizer frizer;

	@ManyToOne
	@JoinColumn(name = "termin_id")
	private Termin termin;

	public Recenzija() {
	}

	public Recenzija(BigDecimal ocjena, String komentar, LocalDateTime datum) {
		this.ocjena = ocjena;
		this.komentar = komentar;
		this.datum = datum;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public BigDecimal getOcjena() {
		return ocjena;
	}

	public void setOcjena(BigDecimal ocjena) {
		this.ocjena = ocjena;
	}

	public String getKomentar() {
		return komentar;
	}

	public void setKomentar(String komentar) {
		this.komentar = komentar;
	}

	public LocalDateTime getDatum() {
		return datum;
	}

	public void setDatum(LocalDateTime datum) {
		this.datum = datum;
	}

	public Korisnik getKorisnik() {
		return korisnik;
	}

	public void setKorisnik(Korisnik korisnik) {
		this.korisnik = korisnik;
	}

	public Frizer getFrizer() {
		return frizer;
	}

	public void setFrizer(Frizer frizer) {
		this.frizer = frizer;
	}

	public Termin getTermin() {
		return termin;
	}

	public void setTermin(Termin termin) {
		this.termin = termin;
	}

	
}
