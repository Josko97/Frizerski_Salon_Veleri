package com.frizerskisalon.veleri.model;

import java.math.BigDecimal;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Usluga {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column
	private String naziv;

	@Column
	private BigDecimal cijena;

	@Column
	private int trajanje;

	@OneToMany(mappedBy = "usluga")
	private List<Termin> termini;

	@OneToMany(mappedBy = "usluga")
	private List<Placanje> placanja;

	public Usluga() {
	}

	public Usluga(String naziv, BigDecimal cijena, int trajanje) {
		this.naziv = naziv;
		this.cijena = cijena;
		this.trajanje = trajanje;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public BigDecimal getCijena() {
		return cijena;
	}

	public void setCijena(BigDecimal cijena) {
		this.cijena = cijena;
	}

	public int getTrajanje() {
		return trajanje;
	}

	public void setTrajanje(int trajanje) {
		this.trajanje = trajanje;
	}

	public List<Termin> getTermini() {
		return termini;
	}

	public void setTermini(List<Termin> termini) {
		this.termini = termini;
	}

	public List<Placanje> getPlacanja() {
		return placanja;
	}

	public void setPlacanja(List<Placanje> placanja) {
		this.placanja = placanja;
	}

}
