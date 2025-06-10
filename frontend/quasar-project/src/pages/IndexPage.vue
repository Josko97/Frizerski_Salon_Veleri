<template>
  <q-page class="q-pa-none">

    <!-- HERO sekcija s pozadinskom slikom -->
    <div
      class="column items-center justify-center q-pa-md hero-section"
      style="min-height: 60vh; border-radius: 0 0 40px 40px; box-shadow: 0 8px 32px 0 rgba(31,38,135,.08); background-size: cover; background-position: center;">
      <div class="hero-text-wrapper">
        <div class="hero-background-overlay">
          <div class="text-h1 hero-title q-mb-sm">
            Frizerski salon <span class="elegant-font">Elegance</span>
          </div>
          <div class="text-h5 hero-subtitle q-mb-sm">
            Dobrodošli! Ovdje njegujemo Vašu kosu s ljubavlju i stilom.<br>
            Prepustite se stručnim rukama našeg tima i uživajte u vrhunskom iskustvu.
          </div>
        </div>
      </div>
      <q-btn
        color="lime-1"
        text-color="brown-4"
        label="Rezerviraj termin"
        to="/rezervacija"
        size="xl"
        unelevated
        class="q-mt-md hero-button" />
    </div>

    <!-- INFO sekcija -->
    <q-card flat bordered class="footer-info-card">
      <div class="footer-info-row">
        <!-- O nama -->
        <div class="footer-info-left">
          <div class="q-mb-md footer-title">O nama</div>
          <div>
            Naš tim iskusnih frizera nudi šišanje, bojanje, njegu kose, svečane frizure i još mnogo toga.<br>
            Pratimo najnovije trendove, koristimo vrhunske proizvode i uvijek slušamo Vaše želje.<br>
            Rezervirajte svoj termin i prepustite se profesionalcima!
          </div>
        </div>

        <!-- Kontakt -->
        <div class="footer-info-right">
          <div class="q-mb-sm"><b>Radno vrijeme:</b><br>
            Pon - Pet: 08:00 - 20:00<br>
            Subota: 08:00 - 14:00<br>
            Nedjelja: Zatvoreno
          </div>
          <div class="q-mb-sm">
            <b>Adresa:</b> Ulica Frizera 12, 51000 Rijeka<br>
            <b>Telefon:</b> 051/123-456<br>
            <b>Email:</b> info@elegance.hr
          </div>
          <q-separator class="q-my-sm" />
          <div class="footer-contact-icons">
            <span class="text-grey">Pratite nas:</span>
            <q-btn flat round icon="mdi-facebook" @click="goTo('https://facebook.com/elegance')" class="q-ml-sm" color="primary" />
            <q-btn flat round icon="mdi-instagram" @click="goTo('https://instagram.com/elegance')" class="q-ml-sm" color="primary" />
          </div>
        </div>
      </div>
    </q-card>

    <!-- Galerija -->
    <div class="gallery-section q-mt-xl q-pa-md">
      <div class="text-h5 gallery-title q-mb-md">
        Galerija
      </div>
      <div class="row justify-center q-gutter-md">
        <div class="col-auto">
          <img :src="muskoImg" alt="Muško šišanje" class="gallery-image" />
        </div>
        <div class="col-auto">
          <img :src="zenskoImg" alt="Žensko šišanje" class="gallery-image" />
        </div>
        <div class="col-auto">
          <img :src="bojanjeImg" alt="Bojanje kose" class="gallery-image" />
        </div>
      </div>
    </div>

  </q-page>
</template>

<script setup>
import 'src/css/FooterInfoCard.css'

// Import slika iz src/assets
import muskoImg from 'src/assets/musko.jpg'
import zenskoImg from 'src/assets/zensko.jpg'
import bojanjeImg from 'src/assets/bojanje.jpg'

function goTo(url) {
  window.open(url, '_blank')
}
</script>

<style>
@import url('https://fonts.googleapis.com/css2?family=Playfair+Display&display=swap');

/* HERO stilovi */
.hero-section {
  background: url('/assets/hero-background.jpg'); /* samo slika, bez gradijenta */
  background-size: cover;
  background-position: center;
  position: relative;
  padding-top: 1rem;
}


.hero-text-wrapper {
  text-align: center;
  margin-top: 0;
}

.hero-background-overlay {
  background: rgba(255, 255, 255, 0.8);
  border-radius: 15px;
  padding: 1.5rem;
  box-shadow: 0px 4px 16px rgba(0, 0, 0, 0.25);
  display: inline-block;
  text-align: center;
}

.hero-title {
  color: #5D4037;
  font-size: 3.5rem;
  font-weight: bold;
  margin-bottom: 0.5rem;
  line-height: 1.2;
  text-shadow: 0px 2px 4px rgba(0, 0, 0, 0.2);
}

.elegant-font {
  font-family: 'Playfair Display', serif;
  font-style: italic;
  font-weight: 700;
  color: #6b4c3b;
}

.hero-subtitle {
  color: #5D4037;
  font-size: 1.6rem;
  max-width: 800px;
  margin: 0 auto 1.2rem auto;
  line-height: 1.8;
  text-shadow: 0px 2px 4px rgba(0, 0, 0, 0.2);
}

.hero-button {
  font-size: 1.4rem;
  font-weight: bold;
  background: rgba(255, 255, 255, 0.9);
  border-radius: 50px;
  box-shadow: 0 4px 16px rgba(0, 0, 0, 0.3);
  color: #333;
  margin-top: 6rem;
}

/* Footer */
.footer-info-card {
  background-color: #fff;
  padding: 1.5rem;
  border-radius: 20px;
  margin: 0.5rem auto;
  max-width: 1200px;
  box-shadow: 0 12px 24px rgba(0, 0, 0, 0.15);
  color: #5D4037;
}

.footer-info-row {
  display: flex;
  justify-content: space-between;
  align-items: flex-start;
  flex-wrap: wrap;
}

.footer-info-left,
.footer-info-right {
  flex: 1;
  min-width: 280px;
  margin: 0.5rem;
}

.footer-title {
  font-size: 1.6rem;
  font-weight: bold;
  color: #5D4037;
  text-shadow: 0px 2px 4px rgba(0, 0, 0, 0.2);
}

.footer-contact-icons {
  display: flex;
  align-items: center;
}

.text-grey {
  color: #6c757d;
}

/* Galerija */
.gallery-section {
  max-width: 1200px;
  margin: 2rem auto;
  text-align: center;
  color: #5D4037;
}

.gallery-title {
  font-weight: bold;
  text-shadow: 0px 2px 4px rgba(0, 0, 0, 0.2);
}

.gallery-image {
  width: 150px;    /* isti fiksni širinski format */
  height: 150px;   /* ista visina */
  object-fit: cover; /* da se slika prilagodi bez izobličenja */
  border-radius: 10px;
  box-shadow: 0 4px 12px rgba(0, 0, 0, 0.15);
}

</style>
