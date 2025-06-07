<template>
  <q-page class="row justify-center">
    <div class="column q-pa-md q-ma-lg bg-white rounded-borders shadow-2">
      <h4 class="text-center q-mb-md">Prijava</h4>

      <q-form @submit.prevent="submitLogin" ref="loginForm" class="q-gutter-md">

        <!-- Korisničko ime -->
        <q-input filled v-model="form.username" label="Korisničko ime"
          :rules="[val => !!val || 'Korisničko ime je obavezno']" lazy-rules />

        <!-- Lozinka -->
        <q-input filled v-model="form.password" label="Lozinka" type="password"
          :rules="[val => !!val || 'Lozinka je obavezna']" lazy-rules />

        <!-- Ispis pogreške -->
        <div v-if="errorMessage" class="text-negative q-mt-sm">
          {{ errorMessage }}
        </div>

        <!-- Gumb za prijavu -->
        <div class="row justify-center q-mt-lg">
          <q-btn label="Prijavi se" type="submit" color="primary" :loading="loading" />
        </div>

        <!-- Link na registraciju -->
        <div class="text-center q-mt-md">
          <router-link to="/register">Nemaš račun? Registriraj se</router-link>
        </div>
      </q-form>
    </div>
  </q-page>
</template>

<script setup>
import { ref, reactive, getCurrentInstance } from 'vue'
import { useQuasar } from 'quasar'
import { useRouter } from 'vue-router'

const form = reactive({
  username: '',
  password: ''
})

const loginForm = ref(null)
const loading = ref(false)
const errorMessage = ref('')
const $q = useQuasar()
const router = useRouter()
const { proxy } = getCurrentInstance()

/**
 * Funkcija koja se pozove kad korisnik klikne "Prijavi se"
 */
async function submitLogin() {
  // 1) Validiraj polja (oba su obavezna)
  const valid = await loginForm.value.validate()
  if (!valid) {
    return
  }

  loading.value = true
  errorMessage.value = ''

  // 2) Pripremi payload prema LoginRequest modelu (username + password)
  const payload = {
    username: form.username,
    password: form.password
  }

  try {
    // 3) Pošalji POST na backend: /api/public/auth/signin
    const response = await proxy.$api.post('/api/public/auth/signin', payload)

    // 4) Ako je status 200, backend vraća LoginResponse: { username, roles, jwtToken }
    if (response.status === 200) {
      const data = response.data
      const token = data.jwtToken
      const user = data.username
      const roles = data.roles      // npr. ["ROLE_USER"]

      // 5) Spremi JWT negdje (localStorage, pinia, Vuex ili u Quasar store)
      //    Ovdje ćemo najjednostavnije localStorage:
      localStorage.setItem('authToken', token)
      localStorage.setItem('authUser', user)
      localStorage.setItem('authRoles', JSON.stringify(roles))

      try {
        const csrfResponse = await proxy.$api.get('/api/public/csrf-token')
        if (csrfResponse.data && csrfResponse.data.token) {
          localStorage.setItem('csrfToken', csrfResponse.data.token)
        }
      } catch (err) {
        console.error('Ne mogu dohvatiti CSRF token nakon prijave:', err)
      }

      // 7) Obavijesti korisnika i presmjeri negdje (npr. na početnu stranicu)
      $q.notify({
        type: 'positive',
        message: 'Prijava uspješna!'
      })
      router.push('/')  // ili na neku “protected” rutu, npr. /dashboard
    }
  }
  catch (err) {
    // Ako server vrati 401 ili neku grešku
    console.log('Login ERROR:', err)
    if (err.response) {
      // Ako je odgovor springa String (npr. "Neispravno korisničko ime ili lozinka")
      if (typeof err.response.data === 'string') {
        errorMessage.value = err.response.data
      }
      // Ako je JSON s poljem message
      else if (err.response.data.message) {
        errorMessage.value = err.response.data.message
      }
      else {
        errorMessage.value = `Greška ${err.response.status}: ${JSON.stringify(err.response.data)}`
      }
    }
    else {
      // Nema response (možda CORS, pogrešan URL ili backend nije pokrenut)
      errorMessage.value = 'Nije moguće spojiti se na server.'
    }
  }
  finally {
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
