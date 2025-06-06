<template>
  <q-page class="q-pa-md" style="background: linear-gradient(to bottom, #f8f9fa, #e9ecef);">
    <!-- Login forma -->
    <div class="text-center q-mb-xl" style="background: #ffffff; padding: 20px; border-radius: 8px; box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);">
      <img
        src="../assets/logo.png"
        alt="Salon Logo"
        class="q-mb-md"
        style="width: 150px; border-radius: 50%; box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);"
      />
      <h1 class="text-h4 text-primary font-bold">Prijava</h1>
      <p class="text-subtitle1 text-grey-7">Unesite svoje korisničke podatke</p>

      <!-- Form -->
      <q-form @submit="onSubmit" class="q-mt-lg">
        <q-input
          v-model="credentials.email"
          label="Email"
          type="email"
          outlined
          dense
          class="q-mb-md"
          :rules="[val => !!val || 'Email je obavezan.', val => /.+@.+\..+/.test(val) || 'Unesite ispravan email.']"
        />
        <q-input
          v-model="credentials.lozinka"
          label="Lozinka"
          type="password"
          outlined
          dense
          class="q-mb-md"
          :rules="[val => !!val || 'Lozinka je obavezna.']"
        />
        <q-btn
          type="submit"
          label="Prijavite se"
          color="primary"
          class="q-mt-md rounded-lg"
          :disable="loading"
        />
      </q-form>
      <p class="text-caption q-mt-md">
        Nemate račun? <q-btn flat label="Registrirajte se" to="/register" color="primary" class="text-decoration-none" />
      </p>
    </div>
  </q-page>
</template>

<script setup>
import { ref } from 'vue';
import { useUsersStore } from '../stores/usersStore';

const credentials = ref({
  email: '',
  lozinka: '',
});

const loading = ref(false);

const usersStore = useUsersStore();

const onSubmit = async () => {
  loading.value = true;
  console.log('Podaci za prijavu:', credentials.value);

  try {
    await usersStore.login(credentials.value);
    alert('Prijava uspješna!');
  } catch (error) {
    alert(`Došlo je do greške: ${error.message}`);
  } finally {
    loading.value = false;
  }
};
</script>
