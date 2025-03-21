package com.frizerskisalon.veleri.model;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class Termin {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column
	private LocalDateTime datum_vrijeme;

	@ManyToOne
	@JoinColumn(name = "usluga_id", nullable = false)
	private Usluga usluga;

	@ManyToOne
	@JoinColumn(name = "frizer_id", nullable = false)
	private Frizer frizer;

	@ManyToOne
	@JoinColumn(name = "korisnik_id")
	private Korisnik korisnik;

	@OneToMany(mappedBy = "termin")
	private List<Placanje> placanja;

	@OneToMany(mappedBy = "termin")
	private List<Recenzija> recenzije;

	public Termin() {
	}

	public Termin(LocalDateTime datum_vrijeme) {
		this.datum_vrijeme = datum_vrijeme;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDateTime getDatum_vrijeme() {
		return datum_vrijeme;
	}

	public void setDatum_vrijeme(LocalDateTime datum_vrijeme) {
		this.datum_vrijeme = datum_vrijeme;
	}

	public Usluga getUsluga() {
		return usluga;
	}

	public void setUsluga(Usluga usluga) {
		this.usluga = usluga;
	}

	public Frizer getFrizer() {
		return frizer;
	}

	public void setFrizer(Frizer frizer) {
		this.frizer = frizer;
	}

	public Korisnik getKorisnik() {
		return korisnik;
	}

	public void setKorisnik(Korisnik korisnik) {
		this.korisnik = korisnik;
	}

	public List<Placanje> getPlacanja() {
		return placanja;
	}

	public void setPlacanja(List<Placanje> placanja) {
		this.placanja = placanja;
	}

	public List<Recenzija> getRecenzije() {
		return recenzije;
	}

	public void setRecenzije(List<Recenzija> recenzije) {
		this.recenzije = recenzije;
	}

}
