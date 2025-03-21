package com.frizerskisalon.veleri.model;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

@Entity
public class Frizer {

	@Id
	private Long id;

	@Column
	private String broj_telefona;

	@Column
	private String specijalizacija;

	@OneToOne
	@JoinColumn(name = "id", referencedColumnName = "id", insertable = false, updatable = false)
	private Korisnik korisnik;

	@OneToMany(mappedBy = "frizer")
	private List<Recenzija> recenzije;

	public Frizer() {
	}

	public Frizer(String broj_telefona, String specijalizacija) {
		this.broj_telefona = broj_telefona;
		this.specijalizacija = specijalizacija;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getBroj_telefona() {
		return broj_telefona;
	}

	public void setBroj_telefona(String broj_telefona) {
		this.broj_telefona = broj_telefona;
	}

	public String getSpecijalizacija() {
		return specijalizacija;
	}

	public void setSpecijalizacija(String specijalizacija) {
		this.specijalizacija = specijalizacija;
	}

	public Korisnik getKorisnik() {
		return korisnik;
	}

	public void setKorisnik(Korisnik korisnik) {
		this.korisnik = korisnik;
	}

	public List<Recenzija> getRecenzije() {
		return recenzije;
	}

	public void setRecenzije(List<Recenzija> recenzije) {
		this.recenzije = recenzije;
	}
	
	

}
