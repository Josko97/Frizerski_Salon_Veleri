<template>
  <q-page class="row justify-center">
    <div class="column q-pa-md q-ma-lg bg-white rounded-borders shadow-2">
      <h4 class="text-center q-mb-md">Registracija</h4>

      <q-form @submit.prevent="submitForm" ref="formRef" class="q-gutter-md">
        <!-- Korisničko ime -->
        <q-input filled v-model="form.username" label="Korisničko ime" :rules="[
          val => !!val || 'Korisničko ime je obavezno',
          val => val.length <= 20 || 'Do 20 znakova'
        ]" lazy-rules />

        <!-- Ime -->
        <q-input filled v-model="form.ime" label="Ime" :rules="[
          val => !!val || 'Ime je obavezno'
        ]" lazy-rules />

        <!-- Prezime -->
        <q-input filled v-model="form.prezime" label="Prezime" :rules="[
          val => !!val || 'Prezime je obavezno'
        ]" lazy-rules />

        <!-- Email -->
        <q-input filled v-model="form.email" label="Email" type="email" :rules="[
          val => !!val || 'Email je obavezan',
          val => /.+@.+\..+/.test(val) || 'Neispravan email',
          val => val.length <= 50 || 'Do 50 znakova'
        ]" lazy-rules />

        <!-- Lozinka -->
        <q-input filled v-model="form.lozinka" label="Lozinka" type="password" :rules="[
          val => !!val || 'Lozinka je obavezna',
          val => val.length >= 6 || 'Najmanje 6 znakova',
          val => val.length <= 120 || 'Do 120 znakova'
        ]" lazy-rules />

        <!-- Potvrda lozinke -->
        <q-input filled v-model="form.confirmLozinka" label="Potvrdite lozinku" type="password" :rules="[
          val => !!val || 'Potvrdite lozinku',
          val => val === form.lozinka || 'Lozinke se ne podudaraju'
        ]" lazy-rules />

        <!-- Poruka o grešci -->
        <div v-if="errorMessage" class="text-negative q-mt-sm">
          {{ errorMessage }}
        </div>

        <!-- Submit gumb -->
        <div class="row justify-center q-mt-lg">
          <q-btn label="Registriraj se" type="submit" color="primary" :loading="loading" />
        </div>

        <!-- Link za login -->
        <div class="text-center q-mt-md">
          <router-link to="/login">Već imaš račun? Prijavi se</router-link>
        </div>
      </q-form>
    </div>
  </q-page>
  <q-btn label="Test Notify" color="primary" @click="$q.notify({ type: 'positive', message: 'Test radi!' })" />
</template>

<script setup>
import { ref, reactive, getCurrentInstance } from 'vue'
import { useQuasar } from 'quasar'
import { useRouter } from 'vue-router'

const form = reactive({
  username: '',
  ime: '',
  prezime: '',
  email: '',
  lozinka: '',
  confirmLozinka: ''
})

const formRef = ref(null)
const loading = ref(false)
const errorMessage = ref('')
const router = useRouter()
const { proxy } = getCurrentInstance()
const $q = useQuasar()

async function submitForm() {
  const valid = await formRef.value.validate()
  if (!valid) return

  loading.value = true
  errorMessage.value = ''

  const payload = {
    username: form.username,
    ime: form.ime,
    prezime: form.prezime,
    email: form.email,
    lozinka: form.lozinka
  }

  try {
    const res = await proxy.$api.post('/api/public/auth/register', payload)
    console.log('REGISTER SUCCESS:', res)
    $q.notify({ type: 'positive', message: res.data.message || 'Registracija uspješna!' })
    router.push("/login")
  } catch (err) {
    console.log("REGISTER ERROR:", err)
    if (err.response) {
      console.log("Error response:", err.response)
      console.log("Error response data:", err.response.data)
      console.log("Error response headers:", err.response.headers)
      console.log("Error response status:", err.response.status)
    } else if (err.request) {
      console.log("Error request:", err.request)
    } else {
      console.log("Error message:", err.message)
    }
    errorMessage.value = 'Greška pri registraciji. Pokušajte ponovno.'
  } finally {
    loading.value = false
  }
}

</script>

<style scoped>
.column {
  max-width: 400px;
  margin: 0 auto;
}

.rounded-borders {
  border-radius: 8px;
}
</style>
