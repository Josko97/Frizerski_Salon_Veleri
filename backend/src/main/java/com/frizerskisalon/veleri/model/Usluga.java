package com.frizerskisalon.veleri.model;

import java.math.BigDecimal;
import java.util.List;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Table(name = "usluga")
public class Usluga {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "usluga_id")
	private Long uslugaId;

	@Column(name = "naziv")
	private String naziv;

	@Column(name = "cijena")
	private BigDecimal cijena;

	@Column(name = "trajanje")
	private int trajanje;

	@OneToMany(mappedBy = "usluga")
	private List<Termin> termini;

	@OneToMany(mappedBy = "usluga")
	private List<Placanje> placanja;

	public Usluga(String naziv, BigDecimal cijena, int trajanje) {
		this.naziv = naziv;
		this.cijena = cijena;
		this.trajanje = trajanje;
	}


}
