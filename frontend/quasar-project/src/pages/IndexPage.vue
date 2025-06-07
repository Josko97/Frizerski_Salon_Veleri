<template>
  <q-page class="q-pa-none">

    <!-- HERO sekcija -->
    <div class="column items-center justify-center q-pa-xl"
      style="min-height: 88vh; border-radius: 0 0 40px 40px; box-shadow: 0 8px 32px 0 rgba(31,38,135,.08);">
      <div class="text-h1 text-primary text-center q-mb-md">Frizerski salon Elegance</div>
      <div class="text-h5 text-center q-mb-xl" style="max-width: 700px;">
        Dobrodošli! Ovdje njegujemo Vašu kosu s ljubavlju i stilom.<br>
        Prepustite se stručnim rukama našeg tima i uživajte u vrhunskom iskustvu.
      </div>
      <q-btn color="lime-1" text-color="brown-4" label="Rezerviraj termin" to="/rezervacija" size="xl" class="q-mb-xl" unelevated
        style="font-size: 1.4rem;" />
    </div>

    <!-- INFO sekcija s vlastitim CSS-om -->
    <q-card flat bordered class="footer-info-card">
      <div class="footer-info-row">
        <!-- Lijeva strana: O nama -->
        <div class="footer-info-left">
          <div class="q-mb-md footer-title">O nama</div>
          <div>
            Naš tim iskusnih frizera nudi šišanje, bojanje, njegu kose, svečane frizure i još mnogo toga.<br>
            Pratimo najnovije trendove, koristimo vrhunske proizvode i uvijek slušamo Vaše želje.<br>
            Rezervirajte svoj termin i prepustite se profesionalcima!
          </div>
        </div>
        <!-- Desna strana: Kontakt, radno vrijeme, društvene mreže -->
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
            <q-btn flat round  icon="mdi-facebook" @click="goTo('https://facebook.com/elegance')"
              class="q-ml-sm" color="primary" />
            <q-btn flat round  icon="mdi-instagram" @click="goTo('https://instagram.com/elegance')"
              class="q-ml-sm" color="primary" />
          </div>
        </div>
      </div>
    </q-card>

  </q-page>
</template>

<script setup>
import 'src/css/FooterInfoCard.css' // putanja prema tvom CSS-u

function goTo(url) {
  window.open(url, '_blank')
}
</script>
