package com.frizerskisalon.veleri.model;


import jakarta.persistence.Entity;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class KontaktForma {

    @NotBlank(message = "Ime je obavezno")
    private String Ime;

    @NotBlank(message = "Email je obavezan")
    @Email(message = "Neispravan email")
    private String email;

    @NotBlank(message = "Poruka je obavezna")
    private String poruka;
}
