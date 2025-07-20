<template>
  <q-layout view="hHh lpR fFf">
    <!-- POZADINSKA SLIKA I OVERLAY -->
    <div class="bg-blur"></div>
    <div class="bg-overlay"></div>

    <!-- Gornja navigacijska traka -->
    <q-header elevated class="bg-lime-1 text-dark">
      <q-toolbar>
        <!-- Lijeva strana: navigacijski linkovi -->
        <q-toolbar-title class="row items-center no-wrap">
          <q-btn flat to="/" label="Home" class="q-mr-sm" />
          <q-btn flat to="/rezervacija" label="Rezervacija" />
          <q-btn flat to="/kontakt" label="Kontakt" />
          <q-btn v-if="userRoles.some(role => role.includes('ADMIN'))" flat to="/admin" label="Admin" />
        </q-toolbar-title>

        <!-- Desna strana: Prijava ili Odjava -->
        <div class="q-ml-auto">
          <q-btn v-if="!isAuthenticated" flat to="/login" label="Prijava" />
          <q-btn v-else flat label="Odjava" @click="logout" />
        </div>
      </q-toolbar>
    </q-header>

    <!-- Glavni sadržaj -->
    <q-page-container>
      <router-view />
    </q-page-container>
  </q-layout>
</template>

<script setup>
import { ref, onMounted, onBeforeUnmount, watch } from 'vue'
import { useRouter, useRoute } from 'vue-router'

// Koristi pravi ključ iz localStorage (authToken iz login/registracije)
const TOKEN_KEY = 'authToken'
const isAuthenticated = ref(!!localStorage.getItem(TOKEN_KEY))
const userRoles = ref([])
const router = useRouter()
const route = useRoute()
// Osiguraj da se prikaz gumba osvježava i kad se promijeni localStorage (npr. u drugom tabu)
function checkAuth() {
  isAuthenticated.value = !!localStorage.getItem(TOKEN_KEY)
}

onMounted(() => {
  window.addEventListener('storage', checkAuth)
})

onBeforeUnmount(() => {
  window.removeEventListener('storage', checkAuth)
})

// Osvježi svaki put kad se promijeni ruta
watch(
  () => route.fullPath,
  () => {
    isAuthenticated.value = !!localStorage.getItem(TOKEN_KEY)
  }
)

// Logout funkcija
const logout = () => {
  localStorage.removeItem(TOKEN_KEY)
  localStorage.removeItem('authUser')
  localStorage.removeItem('authRoles')
  isAuthenticated.value = false
  userRoles.value = []
  router.push('/login')
}


onMounted(() => {
  checkAuth()
  loadRoles()
  window.addEventListener('storage', () => {
    checkAuth()
    loadRoles()
  })
})

onBeforeUnmount(() => {
  window.removeEventListener('storage', checkAuth)
})

watch(
  () => route.fullPath,
  () => {
    checkAuth()
    loadRoles()
  }
)


function loadRoles() {
  const roles = localStorage.getItem('authRoles')
  if (roles) {
    try {
      userRoles.value = JSON.parse(roles)
    } catch {
      userRoles.value = []
    }
  } else {
    userRoles.value = []
  }
}

// OVAJ dio koristiš nakon login-a na LoginPage.vue:
// localStorage.setItem('authToken', token)
// isAuthenticated.value = true
</script>

<style>
.bg-blur {
  position: fixed;
  z-index: 0;
  width: 100vw;
  height: 100vh;
  left: 0;
  top: 0;
  background: url('src/assets/Elegance.png') center center / cover no-repeat;
  filter: blur(0px) brightness(1);
  opacity: 0.8;
  pointer-events: none;
}

.bg-overlay {
  position: fixed;
  z-index: 1;
  width: 100vw;
  height: 100vh;
  left: 0;
  top: 0;
  background: rgba(255, 255, 255, 0.32);
  pointer-events: none;
}

.q-layout,
.q-page-container {
  position: relative;
  z-index: 2;
}
</style>
