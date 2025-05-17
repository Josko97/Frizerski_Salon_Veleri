<template>
  <q-layout view="lHh Lpr lFf">
    <!-- HEADER -->
    <q-header elevated class="bg-primary text-white">
      <q-toolbar>
        <q-btn
          flat
          dense
          round
          icon="menu"
          @click="leftDrawerOpen = !leftDrawerOpen"
          aria-label="Menu"
        />

        <q-toolbar-title> Frizerski Salon Elegance </q-toolbar-title>
      </q-toolbar>
    </q-header>

    <!-- DRAWER / SIDE MENU -->
    <q-drawer show-if-above v-model="leftDrawerOpen" side="left" bordered>
      <q-list>
        <q-item-label header class="text-grey-8 text-weight-bold"> Navigacija </q-item-label>

        <q-item to="/home" clickable v-ripple>
          <q-item-section avatar>
            <q-icon name="home" />
          </q-item-section>
          <q-item-section> Home </q-item-section>
        </q-item>

        <q-item to="/rezervacija" clickable v-ripple v-if="true">
          <q-item-section avatar>
            <q-icon name="event" />
          </q-item-section>
          <q-item-section> Rezervacija </q-item-section>
        </q-item>

        <!-- Dodaj više stavki po potrebi -->
      </q-list>
    </q-drawer>

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
import { ref } from 'vue'

const leftDrawerOpen = ref(false)
</script>
