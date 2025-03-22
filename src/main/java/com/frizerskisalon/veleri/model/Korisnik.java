package com.frizerskisalon.veleri.model;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

@Entity
public class Korisnik{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column
	private String ime;

	@Column
	private String prezime;

	@Column
	private String email;

	@Column
	private String lozinka;

	@Enumerated(EnumType.STRING)
	private Uloga uloga;

	@OneToMany(mappedBy = "korisnik", cascade = CascadeType.ALL)
	private List<Termin> termini;

	@OneToMany(mappedBy = "korisnik")
	private List<Recenzija> recenzije;

	@OneToOne(mappedBy = "korisnik")
	private LoyaltyProgram loyaltyProgram;

	@OneToMany(mappedBy = "korisnik")
	private List<Placanje> placanja;

	@OneToOne
	@JoinColumn(name = "frizer_id")
	private Frizer frizer;

	public Korisnik() {

	}

	public Korisnik(String ime, String prezime, String email, String lozinka, Uloga uloga) {
		this.ime = ime;
		this.prezime = prezime;
		this.email = email;
		this.lozinka = lozinka;
		this.uloga = uloga;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getIme() {
		return ime;
	}

	public void setIme(String ime) {
		this.ime = ime;
	}

	public String getPrezime() {
		return prezime;
	}

	public void setPrezime(String prezime) {
		this.prezime = prezime;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getLozinka() {
		return lozinka;
	}

	public void setLozinka(String lozinka) {
		this.lozinka = lozinka;
	}

	public Uloga getUloga() {
		return uloga;
	}

	public void setUloga(Uloga uloga) {
		this.uloga = uloga;
	}

	public List<Termin> getTermini() {
		return termini;
	}

	public void setTermini(List<Termin> termini) {
		this.termini = termini;
	}

	public List<Recenzija> getRecenzije() {
		return recenzije;
	}

	public void setRecenzije(List<Recenzija> recenzije) {
		this.recenzije = recenzije;
	}

	public LoyaltyProgram getLoyaltyProgram() {
		return loyaltyProgram;
	}

	public void setLoyaltyProgram(LoyaltyProgram loyaltyProgram) {
		this.loyaltyProgram = loyaltyProgram;
	}

	public List<Placanje> getPlacanja() {
		return placanja;
	}

	public void setPlacanja(List<Placanje> placanja) {
		this.placanja = placanja;
	}

	public Frizer getFrizer() {
		return frizer;
	}

	public void setFrizer(Frizer frizer) {
		this.frizer = frizer;
	}

}
