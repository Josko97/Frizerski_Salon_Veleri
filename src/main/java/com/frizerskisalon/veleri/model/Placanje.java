package com.frizerskisalon.veleri.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Placanje {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column
	private BigDecimal iznos;

	@Column
	private LocalDateTime datum;

	@Enumerated(EnumType.STRING)
	private NacinPlacanja nacinPlacanja;

	@Enumerated(EnumType.STRING)
	private Status status;

	@Column
	private int bodovi_koristeni;

	@Column
	private int bodovi_zaradeni;

	@ManyToOne
	@JoinColumn(name = "korisnik_id")
	private Korisnik korisnik;

	@ManyToOne
	@JoinColumn(name = "termin_id")
	private Termin termin;

	@ManyToOne
	@JoinColumn(name = "usluga_id")
	private Usluga usluga;

	public Placanje() {
	}

	public Placanje(BigDecimal iznos, LocalDateTime datum, NacinPlacanja nacinPlacanja, Status status,
			int bodovi_koristeni, int bodovi_zaradeni) {
		this.iznos = iznos;
		this.datum = datum;
		this.nacinPlacanja = nacinPlacanja;
		this.status = status;
		this.bodovi_koristeni = bodovi_koristeni;
		this.bodovi_zaradeni = bodovi_zaradeni;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public BigDecimal getIznos() {
		return iznos;
	}

	public void setIznos(BigDecimal iznos) {
		this.iznos = iznos;
	}

	public LocalDateTime getDatum() {
		return datum;
	}

	public void setDatum(LocalDateTime datum) {
		this.datum = datum;
	}

	public NacinPlacanja getNacinPlacanja() {
		return nacinPlacanja;
	}

	public void setNacinPlacanja(NacinPlacanja nacinPlacanja) {
		this.nacinPlacanja = nacinPlacanja;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public int getBodovi_koristeni() {
		return bodovi_koristeni;
	}

	public void setBodovi_koristeni(int bodovi_koristeni) {
		this.bodovi_koristeni = bodovi_koristeni;
	}

	public int getBodovi_zaradeni() {
		return bodovi_zaradeni;
	}

	public void setBodovi_zaradeni(int bodovi_zaradeni) {
		this.bodovi_zaradeni = bodovi_zaradeni;
	}

	public Korisnik getKorisnik() {
		return korisnik;
	}

	public void setKorisnik(Korisnik korisnik) {
		this.korisnik = korisnik;
	}

	public Termin getTermin() {
		return termin;
	}

	public void setTermin(Termin termin) {
		this.termin = termin;
	}

	public Usluga getUsluga() {
		return usluga;
	}

	public void setUsluga(Usluga usluga) {
		this.usluga = usluga;
	}
	

}
