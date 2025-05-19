package com.frizerskisalon.veleri.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
@Data
@NoArgsConstructor
@Table(name = "korisnik", uniqueConstraints = {
		@UniqueConstraint(columnNames = "email")
})
public class Korisnik{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "korisnik_id")
	private Long korisnikId;

	@NotBlank
	@Size(max = 20)
	@Column(name = "username")
	private String username;

	@NotBlank
	@Size(max = 20)
	@Column(name = "ime")
	private String ime;

	@NotBlank
	@Column(name = "prezime")
	private String prezime;

	@NotBlank
	@Size(max = 50)
	@Email
	@Column(name = "email")
	private String email;

	@Size(max = 120)
	@Column(name = "lozinka")
	@JsonIgnore
	private String lozinka;

	private boolean accountNonLocked = true;

	private boolean accountNonExpired = true;

	private boolean credentialsNonExpired = true;

	private boolean enabled = true;

	private LocalDate credentialsExpiryDate;

	private LocalDate accountExpiryDate;

	private String twoFactorSecret;

	private boolean isTwoFactorEnabled = false;

	private String signUpMethod;

	@ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE})
	@JoinColumn(name = "role_id", referencedColumnName = "role_id")
	@JsonBackReference
	@ToString.Exclude
	private Role role;

	@CreationTimestamp
	@Column(updatable = false)
	private LocalDateTime createdDate;

	@UpdateTimestamp
	private LocalDateTime updatedDate;

	@OneToMany(mappedBy = "korisnik", cascade = CascadeType.ALL)
	private List<Termin> termini;

	@OneToMany(mappedBy = "korisnik")
	private List<Recenzija> recenzije;

	@OneToOne(mappedBy = "korisnik")
	private LoyaltyProgram loyaltyProgram;

	@OneToMany(mappedBy = "korisnik")
	private List<Placanje> placanja;



	public Korisnik(String ime, String prezime, String email, String lozinka, String username) {
		this.ime = ime;
		this.prezime = prezime;
		this.email = email;
		this.lozinka = lozinka;
		this.username = username;
	}

	public Korisnik(String ime, String prezime, String email, String username) {
		this.ime = ime;
		this.prezime = prezime;
		this.email = email;
		this.username = username;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Korisnik)) return false;
		return korisnikId != null && korisnikId.equals(((Korisnik) o).getKorisnikId());
	}

	@Override
	public int hashCode() {
		return getClass().hashCode();
	}

}
