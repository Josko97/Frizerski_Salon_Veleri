<template>
  <q-layout view="lHh Lpr lFf">
   <!-- HEADER -->
<q-header elevated class="bg-primary text-white">
  <q-toolbar>
    <q-toolbar-title> Frizerski Salon Elegance </q-toolbar-title>
    <q-list style="display:flex;">
      <!-- Gumb za Home -->
      <q-item to="/" clickable v-ripple style="color:white">
        <q-item-section avatar>
          <q-icon name="home" />
        </q-item-section>
        <q-item-section> Home </q-item-section>
      </q-item>

      <!-- Gumb za Rezervacija -->
      <q-item to="/rezervacija" clickable v-ripple style="color:white">
        <q-item-section avatar>
          <q-icon name="event" />
        </q-item-section>
        <q-item-section> Rezervacija </q-item-section>
      </q-item>

      <!-- Gumb za O nama -->
      <q-item to="/o-nama" clickable v-ripple style="color:white">
        <q-item-section avatar>
          <q-icon name="info" />
        </q-item-section>
        <q-item-section> O nama </q-item-section>
      </q-item>

      <q-item to="/registracija" clickable v-ripple style="color:white">
        <q-item-section avatar>
          <q-icon name="register" />
        </q-item-section>
        <q-item-section> Registracija </q-item-section>
      </q-item>
    </q-list>
  </q-toolbar>
</q-header>


    <!-- DRAWER / SIDE MENU -->


    <!-- GLAVNI SADRŽAJ -->
    <q-page-container>
      <router-view />
    </q-page-container>

    <!-- FOOTER -->
    <q-footer class="bg-primary text-white text-center q-pa-sm">
      © 2025 Frizerski Salon Elegance. Sva prava pridržana.
    </q-footer>
  </q-layout>
</template>

<script setup>



</script>
