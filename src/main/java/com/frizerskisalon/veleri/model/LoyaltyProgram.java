package com.frizerskisalon.veleri.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class LoyaltyProgram {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column
	private Integer bodovi;

	@ManyToOne
	@JoinColumn(name = "korisnik_id")
	private Korisnik korisnik;

	public LoyaltyProgram() {
	}

	public LoyaltyProgram(Integer bodovi) {
		this.bodovi = bodovi;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getBodovi() {
		return bodovi;
	}

	public void setBodovi(Integer bodovi) {
		this.bodovi = bodovi;
	}

	public Korisnik getKorisnik() {
		return korisnik;
	}

	public void setKorisnik(Korisnik korisnik) {
		this.korisnik = korisnik;
	}

}
