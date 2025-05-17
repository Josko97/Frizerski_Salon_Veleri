package com.frizerskisalon.veleri.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Table(name = "loyalty_program")
public class LoyaltyProgram {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "loyalty_id")
	private Long loyaltyId;

	@Column(name = "bodovi")
	private Integer bodovi;

	@OneToOne
	@JoinColumn(name = "korisnik_id")
	private Korisnik korisnik;

	public LoyaltyProgram(Integer bodovi) {
		this.bodovi = bodovi;
	}



}
