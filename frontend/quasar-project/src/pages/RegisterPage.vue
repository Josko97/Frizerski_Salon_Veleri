<template>
  <q-page
    class="row justify-center"
    style="
      background: url('../assets/background.jpg') no-repeat center center fixed;
      background-size: cover;
      min-height: 100vh;
    "
  >
    <q-card
      class="shadow-2"
      style="
        width: 500px;
        max-width: 90%;
        padding: 24px;
        border-radius: 12px;
        background: linear-gradient(145deg, rgba(255, 255, 255, 0.95), rgba(240, 240, 240, 0.9));
        backdrop-filter: blur(10px);
      "
    >
      <div class="text-center q-mb-lg">
        <img
          src="../assets/logo.png"
          alt="Salon Logo"
          style="
            width: 100px;
            border-radius: 50%;
            margin-bottom: 16px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
          "
        />
        <h1 class="text-h5 text-primary">Registracija</h1>
        <p class="text-subtitle1 text-grey-7">Kreirajte svoj korisnički račun</p>
      </div>

      <q-form @submit.prevent="submitForm" ref="formRef" class="q-gutter-lg">
        <q-input
          filled
          v-model="form.username"
          label="Korisničko ime"
          :rules="[val => !!val || 'Korisničko ime je obavezno', val => val.length <= 20 || 'Do 20 znakova']"
          style="box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1); border-radius: 8px;"
          prepend-icon="person"
        />
        <q-input
          filled
          v-model="form.ime"
          label="Ime"
          :rules="[val => !!val || 'Ime je obavezno']"
          style="box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1); border-radius: 8px;"
          prepend-icon="badge"
        />
        <q-input
          filled
          v-model="form.prezime"
          label="Prezime"
          :rules="[val => !!val || 'Prezime je obavezno']"
          style="box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1); border-radius: 8px;"
          prepend-icon="badge-account-horizontal"
        />
        <q-input
          filled
          v-model="form.email"
          label="Email"
          type="email"
          :rules="[val => !!val || 'Email je obavezan', val => /.+@.+\..+/.test(val) || 'Neispravan email']"
          style="box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1); border-radius: 8px;"
          prepend-icon="email"
        />
        <q-input
          filled
          v-model="form.lozinka"
          label="Lozinka"
          type="password"
          :rules="[val => !!val || 'Lozinka je obavezna', val => val.length >= 6 || 'Najmanje 6 znakova']"
          style="box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1); border-radius: 8px;"
          prepend-icon="lock"
        />
        <q-input
          filled
          v-model="form.confirmLozinka"
          label="Potvrdite lozinku"
          type="password"
          :rules="[val => !!val || 'Potvrdite lozinku', val => val === form.lozinka || 'Lozinke se ne podudaraju']"
          style="box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1); border-radius: 8px;"
          prepend-icon="lock-check"
        />

        <div v-if="errorMessage" class="text-negative q-my-sm">
          {{ errorMessage }}
        </div>

        <div class="row justify-center q-mt-lg">
          <q-btn
            label="Registriraj se"
            type="submit"
            color="primary"
            class="shadow-2 hover-grow"
            :loading="loading"
            style="border-radius: 8px; width: 100%; max-width: 300px;"
          />
        </div>

        <div class="text-center q-mt-md">
          <router-link to="/login" class="text-primary text-decoration-none">Već imaš račun? Prijavi se</router-link>
        </div>
      </q-form>
    </q-card>
  </q-page>
</template>

<script setup>
import { ref, reactive, getCurrentInstance } from 'vue';
import { useQuasar } from 'quasar';
import { useRouter } from 'vue-router';

const form = reactive({
  username: '',
  ime: '',
  prezime: '',
  email: '',
  lozinka: '',
  confirmLozinka: ''
});

const formRef = ref(null);
const loading = ref(false);
const errorMessage = ref('');
const router = useRouter();
const { proxy } = getCurrentInstance();
const $q = useQuasar();

async function submitForm() {
  const valid = await formRef.value.validate();
  if (!valid) return;

  loading.value = true;
  errorMessage.value = '';

  const payload = {
    username: form.username,
    ime: form.ime,
    prezime: form.prezime,
    email: form.email,
    lozinka: form.lozinka
  };

  try {
    const res = await proxy.$api.post('/api/public/auth/register', payload);
    $q.notify({ type: 'positive', message: res.data.message || 'Registracija uspješna!' });
    router.push('/login');
  } catch (err) {
    errorMessage.value = err.response?.data?.message || 'Greška pri registraciji. Pokušajte ponovno.';
  } finally {
    loading.value = false;
  }
}
</script>

<style scoped>
.text-negative {
  color: #e53935;
}

.hover-grow:hover {
  transform: scale(1.05);
  transition: transform 0.2s ease-in-out;
}
</style>
