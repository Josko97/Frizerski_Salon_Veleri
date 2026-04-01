Sustav za upravljanje frizerskim salonom (Veleri)

Aplikacija namijenjena digitalizaciji poslovanja frizerskog salona, omogućujući korisnicima pregled usluga i rezervaciju termina, dok administratorima (vlasnicima) pruža potpuni sustav za upravljanje terminima, uslugama i zaposlenicima.

🚀 Ključne funkcionalnosti

    Autentifikacija i Autorizacija: Implementiran sustav prijave i registracije s različitim razinama pristupa (Korisnik / Admin) putem Spring Security-ja.

    Upravljanje terminima: Korisnici mogu odabrati željenu uslugu, datum i vrijeme, dok sustav sprječava preklapanje termina.

    Administrativna ploča: Pregled svih rezervacija, uređivanje popisa usluga, cijena i trajanja tretmana.

    Baza podataka: Perzistencija podataka o klijentima, terminima i uslugama korištenjem MySQL baze.

🛠 Tehnologije

    Backend: Java, Spring Boot (Data JPA, Web, Security)

    Frontend: Vue.js 3

    Baza podataka: MySQL / H2 (za testiranje)

    Alati: Maven (upravljanje ovisnostima), Git

📋 Preduvjeti za pokretanje

Za pokretanje projekta lokalno, potrebno je imati instalirano:

    Java JDK 17 (ili novija verzija)

    Maven

    MySQL Server

🔧 Instalacija i pokretanje

    Klonirajte repozitorij:

    git clone https://github.com/Josko97/Frizerski_Salon_Veleri.git

    Konfigurirajte bazu podataka u src/main/resources/application.properties datoteci.

    Pokrenite aplikaciju putem Mavena:

    mvn spring-boot:run

    Otvorite preglednik i posjetite: http://localhost:9000
