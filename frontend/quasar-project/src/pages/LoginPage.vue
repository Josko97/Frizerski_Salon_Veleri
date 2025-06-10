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
        <h1 class="text-h5 text-primary">Prijava</h1>
        <p class="text-subtitle1 text-grey-7">Dobrodošli natrag!</p>
      </div>

      <q-form @submit.prevent="submitLogin" ref="loginForm" class="q-gutter-lg">
        <q-input
          filled
          v-model="form.username"
          label="Korisničko ime"
          :rules="[val => !!val || 'Korisničko ime je obavezno']"
          style="box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1); border-radius: 8px;"
          prepend-icon="person"
        />
        <q-input
          filled
          v-model="form.password"
          label="Lozinka"
          type="password"
          :rules="[val => !!val || 'Lozinka je obavezna']"
          style="box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1); border-radius: 8px;"
          prepend-icon="lock"
        />
        <div v-if="errorMessage" class="text-negative q-my-sm">
          {{ errorMessage }}
        </div>

        <div class="row justify-center q-mt-lg">
          <q-btn
            label="Prijavi se"
            type="submit"
            color="primary"
            class="shadow-2 hover-grow"
            :loading="loading"
            style="border-radius: 8px; width: 100%; max-width: 300px;"
          />
        </div>

        <div class="text-center q-mt-md">
          <router-link to="/register" class="text-primary text-decoration-none">Nemaš račun? Registriraj se</router-link>
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
  password: ''
});

const loginForm = ref(null);
const loading = ref(false);
const errorMessage = ref('');
const $q = useQuasar();
const router = useRouter();
const { proxy } = getCurrentInstance();

async function submitLogin() {
  const valid = await loginForm.value.validate();
  if (!valid) return;

  loading.value = true;
  errorMessage.value = '';

  const payload = {
    username: form.username,
    password: form.password
  };

  try {
    const response = await proxy.$api.post('/api/public/auth/signin', payload);

    if (response.status === 200) {
      const data = response.data;
      localStorage.setItem('authToken', data.jwtToken);
      localStorage.setItem('authUser', data.username);
      localStorage.setItem('authRoles', JSON.stringify(data.roles));

      $q.notify({
        type: 'positive',
        message: 'Prijava uspješna!'
      });
      router.push('/');
    }
  } catch (err) {
    errorMessage.value =
      err.response?.data?.message || 'Greška pri prijavi. Pokušajte ponovno.';
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
