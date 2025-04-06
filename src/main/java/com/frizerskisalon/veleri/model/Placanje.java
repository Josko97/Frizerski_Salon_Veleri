package com.frizerskisalon.veleri.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.cglib.core.Local;

@Entity
@Data
@NoArgsConstructor
@Table(name = "placanje")
public class Placanje {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "placanje_id")
	private Long placanjeId;

	@Column(name = "iznos")
	private BigDecimal iznos;

	@CreationTimestamp
	@Column(updatable = false)
	private LocalDateTime datumPlacanja;

	@Enumerated(EnumType.STRING)
	@Column(name = "nacin_placanja")
	private NacinPlacanja nacinPlacanja;

	@Enumerated(EnumType.STRING)
	@Column(name = "status")
	private Status status;

	@Column(name = "bodovi_koristeni")
	private int bodoviKoristeni;

	@Column(name = "bodovi_zaradeni")
	private int bodoviZaradeni;

	@ManyToOne
	@JoinColumn(name = "korisnik_id")
	private Korisnik korisnik;

	@OneToOne
	@JoinColumn(name = "termin_id")
	private Termin termin;

	@ManyToOne
	@JoinColumn(name = "usluga_id")
	private Usluga usluga;


	public Placanje(BigDecimal iznos, NacinPlacanja nacinPlacanja, Status status,
			int bodoviKoristeni, int bodoviZaradeni) {
		this.iznos = iznos;
		this.nacinPlacanja = nacinPlacanja;
		this.status = status;
		this.bodoviKoristeni = bodoviKoristeni;
		this.bodoviZaradeni = bodoviZaradeni;
	}

}
