package com.frizerskisalon.veleri.service.impl;

import com.frizerskisalon.veleri.model.KontaktForma;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class KontaktService {

    @Autowired
    private JavaMailSender mailSender;

    public void obradaForme(KontaktForma zahtjev) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo("frizerskielegance@gmail.com"); // Ovdje ide tvoja adresa
        message.setSubject("Nova poruka sa kontakt forme");
        message.setText(
                "Ime: " + zahtjev.getIme() + "\n" +
                        "Email: " + zahtjev.getEmail() + "\n" +
                        "Poruka:\n" + zahtjev.getPoruka()
        );

        mailSender.send(message);
    }
}
