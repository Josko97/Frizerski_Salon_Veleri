<template>
  <q-page class="q-pa-md bg-grey-1">
    <!-- Hero sekcija -->
    <div class="text-center q-mb-xl">
      <img
        src="https://cdn.quasar.dev/img/avatar.png"
        alt="Salon Logo"
        class="q-mb-md"
        style="width: 100px"
      />
      <h1 class="text-h3 text-primary">Frizerski Salon Elegance</h1>
      <p class="text-subtitle1 text-grey-7">Vaša kosa. Naš stil.</p>
      <q-btn color="primary" label="Rezerviraj termin" class="q-mt-md" to="/rezervacija" />
    </div>

    <!-- Usluge -->
    <div class="q-mb-xl">
      <h2 class="text-h5 q-mb-md text-primary">Naše usluge</h2>
      <div class="row q-col-gutter-md">
        <q-card class="col-xs-12 col-sm-6 col-md-4" flat bordered>
          <q-card-section>
            <div class="text-h6">Muško šišanje</div>
            <div class="text-caption text-grey">Profesionalno šišanje za muškarce</div>
          </q-card-section>
        </q-card>
        <q-card class="col-xs-12 col-sm-6 col-md-4" flat bordered>
          <q-card-section>
            <div class="text-h6">Žensko šišanje</div>
            <div class="text-caption text-grey">Kreativne frizure i njegovanje</div>
          </q-card-section>
        </q-card>
        <q-card class="col-xs-12 col-sm-6 col-md-4" flat bordered>
          <q-card-section>
            <div class="text-h6">Bojanje kose</div>
            <div class="text-caption text-grey">Moderne boje i tehnike</div>
          </q-card-section>
        </q-card>
      </div>
    </div>

    <!-- Galerija -->
    <div class="q-mb-xl">
      <h2 class="text-h5 q-mb-md text-primary">Galerija</h2>
      <q-carousel animated swipeable height="200px" v-model="slide" navigation padding>
        <q-carousel-slide
          v-for="n in 4"
          :key="n"
          :name="n"
          :img-src="`https://source.unsplash.com/800x400/?hair,${n}`"
        />
      </q-carousel>
    </div>

    <!-- Kontakt -->
    <div class="q-mb-xl">
      <h2 class="text-h5 text-primary">Kontakt</h2>
      <div class="q-mt-sm">
        <p><q-icon name="place" class="q-mr-sm" /> Adresa: Ulica Frizera 1, Rijeka</p>
        <p><q-icon name="call" class="q-mr-sm" /> Telefon: 091 123 4567</p>
        <p><q-icon name="email" class="q-mr-sm" /> Email: info@elegance.hr</p>
      </div>
    </div>
  </q-page>
</template>

<script setup>
import { ref } from 'vue'

const slide = ref(1)
</script>
