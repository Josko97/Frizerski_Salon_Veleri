<template>
  <q-page class="q-pa-md" style="background: linear-gradient(to bottom, #f8f9fa, #e9ecef);">
    <!-- Register forma -->
    <div class="text-center q-mb-xl" style="background: #ffffff; padding: 20px; border-radius: 8px; box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);">
      <img
        src="../assets/logo.png"
        alt="Salon Logo"
        class="q-mb-md"
        style="width: 150px; border-radius: 50%; box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);"
      />
      <h1 class="text-h4 text-primary font-bold">Registracija</h1>
      <p class="text-subtitle1 text-grey-7">Kreirajte svoj korisnički račun</p>

      <!-- Form -->
      <q-form @submit="onSubmit" class="q-mt-lg">
        <q-input
          v-model="korisnik.ime"
          label="Ime"
          outlined
          dense
          class="q-mb-md"
          :rules="[val => !!val || 'Ime je obavezno.']"
        />
        <q-input
          v-model="korisnik.prezime"
          label="Prezime"
          outlined
          dense
          class="q-mb-md"
          :rules="[val => !!val || 'Prezime je obavezno.']"
        />
        <q-input
          v-model="korisnik.email"
          label="Email"
          type="email"
          outlined
          dense
          class="q-mb-md"
          :rules="[val => !!val || 'Email je obavezan.', val => /.+@.+\..+/.test(val) || 'Unesite ispravan email.']"
        />
        <q-input
          v-model="korisnik.lozinka"
          label="Lozinka"
          type="password"
          outlined
          dense
          class="q-mb-md"
          :rules="[val => !!val || 'Lozinka je obavezna.', val => val.length >= 6 || 'Lozinka mora imati barem 6 znakova.']"
        />
        <q-btn
          type="submit"
          label="Registriraj se"
          color="primary"
          class="q-mt-md rounded-lg"
          :disable="loading"
        />
      </q-form>
      <p class="text-caption q-mt-md">
        Već imate račun? <q-btn flat label="Prijavite se" to="/login" color="primary" class="text-decoration-none" />
      </p>
    </div>
  </q-page>
</template>

<script setup>
import { ref } from 'vue';
import usersStore from '../stores/usersStore'

const korisnik = ref({
  ime: '',
  prezime: '',
  email: '',
  lozinka: '',
});

const loading = ref(false);

const onSubmit = () => {
  loading.value = true;
  console.log('Podaci za registraciju:', korisnik.value);
  usersStore.registracija(korisnik)

  // Simulacija zahtjeva za registraciju
  setTimeout(() => {
    loading.value = false;
    alert('Registracija uspješna!');
  }, 1500);
};
</script>
