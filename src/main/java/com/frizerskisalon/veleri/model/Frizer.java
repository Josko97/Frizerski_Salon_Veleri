package com.frizerskisalon.veleri.model;

import java.util.List;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Table(name = "frizer")
public class Frizer {

	@Id
	@Column(name = "firzer_id")
	private Long frizerId;

	@Column(name = "kontakt")
	private String kontakt;

	@Column(name = "specijalizacija")
	private String specijalizacija;

	@OneToOne
	@JoinColumn(name = "frizer_id", referencedColumnName = "korisnik_id", insertable = false, updatable = false)
	private Korisnik korisnik;

	@OneToMany(mappedBy = "frizer")
	private List<Recenzija> recenzije;

	public Frizer(String kontakt, String specijalizacija) {
		this.kontakt = kontakt;
		this.specijalizacija = specijalizacija;
	}

}
