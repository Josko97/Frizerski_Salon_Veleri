package com.frizerskisalon.veleri.security;

import com.frizerskisalon.veleri.model.Korisnik;
import com.frizerskisalon.veleri.model.Role;
import com.frizerskisalon.veleri.model.Uloga;
import com.frizerskisalon.veleri.repository.KorisnikRepository;
import com.frizerskisalon.veleri.repository.RoleRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

import java.time.LocalDate;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests((requests) -> requests
                .anyRequest().authenticated());
        http.csrf(AbstractHttpConfigurer::disable);
        http.httpBasic(withDefaults());
        return http.build();
    }

    @Bean
    public CommandLineRunner initData(RoleRepository roleRepository, KorisnikRepository userRepository) {
        return args -> {
            Role userRole = roleRepository.findByNazivUloge(Uloga.ROLE_KORISNIK)
                    .orElseGet(() -> roleRepository.save(new Role(Uloga.ROLE_KORISNIK)));

            Role adminRole = roleRepository.findByNazivUloge(Uloga.ROLE_ADMIN)
                    .orElseGet(() -> roleRepository.save(new Role(Uloga.ROLE_ADMIN)));

            if (!userRepository.existsByEmail("marko@example.com")) {
                Korisnik user1 = new Korisnik("marko", "prgic", "marko@example.com", "{noop}password1");
                user1.setAccountNonLocked(false);
                user1.setAccountNonExpired(true);
                user1.setCredentialsNonExpired(true);
                user1.setEnabled(true);
                user1.setCredentialsExpiryDate(LocalDate.now().plusYears(1));
                user1.setAccountExpiryDate(LocalDate.now().plusYears(1));
                user1.setTwoFactorEnabled(false);
                user1.setSignUpMethod("email");
                user1.setRole(userRole);
                userRepository.save(user1);
            }

            if (!userRepository.existsByEmail("josko@example.com")) {
                Korisnik admin = new Korisnik("josko", "barbir", "josko@example.com", "{noop}adminPass");
                admin.setAccountNonLocked(true);
                admin.setAccountNonExpired(true);
                admin.setCredentialsNonExpired(true);
                admin.setEnabled(true);
                admin.setCredentialsExpiryDate(LocalDate.now().plusYears(1));
                admin.setAccountExpiryDate(LocalDate.now().plusYears(1));
                admin.setTwoFactorEnabled(false);
                admin.setSignUpMethod("email");
                admin.setRole(adminRole);
                userRepository.save(admin);
            }
        };
    }


}
