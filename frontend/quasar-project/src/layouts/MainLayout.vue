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
        </q-toolbar-title>

        <!-- Desna strana: Prijava ili Odjava -->
        <div class="q-ml-auto">
          <q-btn
            v-if="!isAuthenticated"
            flat
            to="/login"
            label="Prijava"
          />
          <q-btn
            v-else
            flat
            label="Odjava"
            @click="logout"
          />
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
import { ref } from 'vue'
import { useRouter } from 'vue-router'

// Ovo je samo privremena logika — u pravom appu koristiš auth servis
const isAuthenticated = ref(!!localStorage.getItem('token'))
const router = useRouter()

const logout = () => {
  localStorage.removeItem('token')
  isAuthenticated.value = false
  router.push('/login')
}
</script>

<style>
.bg-blur {
  position: fixed;
  z-index: 0;
  width: 100vw;
  height: 100vh;
  left: 0; top: 0;
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
  left: 0; top: 0;
  background: rgba(255,255,255,0.32);
  pointer-events: none;
}
.q-layout,
.q-page-container {
  position: relative;
  z-index: 2;
}
</style>
