<template>
  <div class="row" style="min-height: 100vh;">
    <!-- Sidebar kao drawer, nema q-layout! -->
    <q-drawer show-if-above side="left" width="200" bordered :mini="miniState" @mouseenter="miniState = false"
      @mouseleave="miniState = true" class="bg-lime-1 admin-drawer">
      <q-list>
        <q-item v-for="item in menu" :key="item.section" clickable v-ripple :active="selected === item.section"
          @click="selected = item.section">
          <q-item-section avatar>
            <q-icon :name="item.icon" />
          </q-item-section>
          <q-item-section>
            {{ item.label }}
          </q-item-section>
        </q-item>
      </q-list>
    </q-drawer>

    <div class="col">
      <q-page class="q-pa-xl admin-content">
        <div v-if="selected === 'users'">
          <div class="text-h4 text-primary q-mb-lg">Popis korisnika</div>
          <q-table :rows="users" :columns="userColumns" row-key="korisnikId" flat bordered :loading="loadingUsers"
            :pagination="{ rowsPerPage: 10 }" class="q-mb-md" no-data-label="Nema korisnika"
            no-results-label="Nema rezultata" style="max-width:1000px">
            <template v-slot:body-cell-roles="props">
              <q-td>
                {{ props.row.rola || props.row.role?.name || '' }}
              </q-td>
            </template>
          </q-table>
        </div>
        <div v-else-if="selected === 'appointments'">
          <div class="text-h4 text-primary q-mb-lg">Popis Termina</div>
          <q-table :rows="appointments" :columns="appointmentColumns" row-key="terminId" flat bordered
            :loading="loadingAppointments" :pagination="{ rowsPerPage: 10 }" no-data-label="Nema termina"
            no-results-label="Nema rezultata" style="max-width:1000px">
            <template v-slot:body-cell-frizer="props">
              <q-td>{{ props.row.frizerIme }} {{ props.row.frizerPrezime }}</q-td>
            </template>
            <template v-slot:body-cell-klijent="props">
              <q-td>{{ props.row.korisnikIme }} {{ props.row.korisnikPrezime }}</q-td>
            </template>
          </q-table>

        </div>
        <div v-else-if="selected === 'services'">
          <div class="text-h4 text-primary q-mb-lg">Popis Usluga</div>
          <q-table :rows="services" :columns="serviceColumns" row-key="uslugaId" flat bordered
            :loading="loadingServices" :pagination="{ rowsPerPage: 10 }" class="q-mb-md" no-data-label="Nema usluga"
            no-results-label="Nema rezultata" style="max-width:900px" />
        </div>
      </q-page>
    </div>
  </div>
</template>

<script setup>

import { ref, onMounted, watch } from 'vue'
import { api } from 'boot/axios' // ako imaš api helper

const miniState = ref(false)
const selected = ref('users')
const menu = [
  { section: 'users', label: 'Korisnici', icon: 'person' },
  { section: 'appointments', label: 'Termini', icon: 'event' },
  { section: 'services', label: 'Usluge', icon: 'content_cut' }
]



const users = ref([])
const loadingUsers = ref(false)

const userColumns = [
  { name: 'korisnikId', label: 'ID', align: 'left', field: 'korisnikId', sortable: true },
  { name: 'username', label: 'Korisničko ime', align: 'left', field: 'username', sortable: true },
  { name: 'ime', label: 'Ime', align: 'left', field: 'ime', sortable: true },
  { name: 'prezime', label: 'Prezime', align: 'left', field: 'prezime', sortable: true },
  { name: 'email', label: 'E-mail', align: 'left', field: 'email', sortable: true },
  { name: 'enabled', label: 'Status', align: 'center', field: 'enabled', sortable: true },
  { name: 'role', label: 'Uloga', align: 'left', field: row => (row.role ? row.role.nazivUloge : ''), sortable: true },
  // Dodaj dalje što ti treba (npr. createdDate, twoFactor, locked...)
]


onMounted(fetchUsers)

function fetchUsers() {
  loadingUsers.value = true
  api.get('/api/private/admin/lista-korisnika')
    .then(res => { users.value = res.data })
    .catch(() => { users.value = [] })
    .finally(() => { loadingUsers.value = false })
}

const appointmentColumns = [
  { name: 'terminId', label: 'ID', align: 'left', field: 'terminId', sortable: true },
  { name: 'datumTermina', label: 'Datum', align: 'left', field: 'datumTermina', sortable: true },
  { name: 'vrijeme', label: 'Vrijeme', align: 'left', field: 'vrijeme', sortable: true },
  { name: 'uslugaNaziv', label: 'Usluga', align: 'left', field: 'uslugaNaziv', sortable: true },
  { name: 'frizer', label: 'Frizer', align: 'left', field: row => `${row.frizerIme} ${row.frizerPrezime}`, sortable: false },
  { name: 'klijent', label: 'Klijent', align: 'left', field: row => `${row.korisnikIme} ${row.korisnikPrezime}`, sortable: false },
]
const appointments = ref([])
const loadingAppointments = ref(false)

async function fetchAppointments() {
  loadingAppointments.value = true
  api.get('/api/private/admin/lista-termina')
    .then(res => { appointments.value = res.data })
    .catch(() => { appointments.value = [] })
    .finally(() => { loadingAppointments.value = false })
}

watch(selected, val => {
  if (val === 'appointments') fetchAppointments()
})

const services = ref([])
const loadingServices = ref(false)

const serviceColumns = [
  { name: 'uslugaId', label: 'ID', align: 'left', field: 'uslugaId', sortable: true },
  { name: 'naziv', label: 'Naziv usluge', align: 'left', field: 'naziv', sortable: true },
  { name: 'cijena', label: 'Cijena (EUR)', align: 'right', field: 'cijena', sortable: true, format: val => val ? val.toFixed(2) : '-' },
  { name: 'trajanje', label: 'Trajanje (min)', align: 'right', field: 'trajanje', sortable: true }
]

function fetchServices() {
  loadingServices.value = true
  api.get('/api/private/usluge')
    .then(res => { services.value = res.data })
    .catch(() => { services.value = [] })
    .finally(() => { loadingServices.value = false })
}

watch(selected, val => {
  if (val === 'appointments') fetchAppointments()
  if (val === 'services') fetchServices()
})



</script>

<style scoped>
.admin-content {
  margin-top: 48px;
  /* Dovoljno da se ne zalijepi uz header */
  margin-left: 24px;
  /* Razmak od sidebar-a */
  max-width: 1200px;
  /* (Opcionalno) Ograniči širinu sadržaja */
  min-height: 400px;
  /* Da ne bude “skroz malo” */
  background: rgba(255, 255, 255, 0.87);
  /* Opcionalno bijela podloga, blur */
  border-radius: 12px;
  /* Za rounded izgled */
  box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.04);
}

.admin-drawer {
  min-height: calc(100vh - 50px);
  /* prilagodi visinu headera ako treba */
  margin-top: 0;
}
</style>
