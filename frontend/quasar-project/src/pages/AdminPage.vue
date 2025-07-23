<template>
  <div class="row" style="min-height: 100vh;">
    <!-- Sidebar -->
    <q-drawer show-if-above side="left" width="200" bordered :mini="miniState" @mouseenter="miniState = false"
      @mouseleave="miniState = true" class="bg-lime-1 admin-drawer">
      <q-toolbar class="bg-lime-2 text-primary">
        <q-toolbar-title v-if="!miniState" class="text-subtitle2">Admin Panel</q-toolbar-title>
        <q-btn dense flat icon="menu" @click="miniState = !miniState" />
      </q-toolbar>
      <q-list>
        <q-item v-for="item in menu" :key="item.section" clickable v-ripple :active="selected === item.section"
          @click="selected = item.section" active-class="bg-lime-3 text-primary">
          <q-item-section avatar>
            <q-icon :name="item.icon" />
          </q-item-section>
          <q-item-section v-if="!miniState">
            {{ item.label }}
          </q-item-section>
        </q-item>
      </q-list>
    </q-drawer>

    <!-- Main Content -->
    <div class="col">
      <q-page class="q-pa-xl admin-content">
        <transition name="fade" mode="out-in">
          <div :key="selected">
            <!-- Users -->
            <div v-if="selected === 'users'">
              <div class="text-h4 text-primary text-weight-medium q-mb-lg">Popis korisnika</div>
              <q-table
                :rows="users"
                :columns="userColumns"
                row-key="korisnikId"
                flat bordered square dense
                :loading="loadingUsers"
                separator="cell"
                :pagination="{ rowsPerPage: 10 }"
                no-data-label="Nema korisnika"
                no-results-label="Nema rezultata"
                class="q-mb-md"
                style="max-width:1000px"
              >
                <template v-slot:body-cell-enabled="props">
                  <q-td class="text-center">
                    <q-badge :color="props.row.enabled ? 'positive' : 'negative'" :label="props.row.enabled ? 'Aktivan' : 'Neaktivan'" outline />
                  </q-td>
                </template>
                <template v-slot:body-cell-roles="props">
                  <q-td>
                    {{ props.row.rola || props.row.role?.name || '' }}
                  </q-td>
                </template>
                <template v-slot:body-cell-actions="props">
                  <q-td class="text-center">
                    <q-btn size="sm" color="primary" flat icon="edit" @click="editItem(props.row)" />
                    <q-btn size="sm" color="negative" flat icon="delete" @click="deleteItem(props.row)" />
                  </q-td>
                </template>
              </q-table>
            </div>

            <!-- Appointments -->
            <div v-else-if="selected === 'appointments'">
              <div class="text-h4 text-primary text-weight-medium q-mb-lg">Popis Termina</div>
              <q-table
                :rows="appointments"
                :columns="appointmentColumns"
                row-key="terminId"
                flat bordered square dense
                :loading="loadingAppointments"
                separator="cell"
                :pagination="{ rowsPerPage: 10 }"
                no-data-label="Nema termina"
                no-results-label="Nema rezultata"
                style="max-width:1000px"
              >
                <template v-slot:body-cell-frizer="props">
                  <q-td>{{ props.row.frizerIme }} {{ props.row.frizerPrezime }}</q-td>
                </template>
                <template v-slot:body-cell-klijent="props">
                  <q-td>{{ props.row.korisnikIme }} {{ props.row.korisnikPrezime }}</q-td>
                </template>
              </q-table>
            </div>

            <!-- Services -->
            <div v-else-if="selected === 'services'">
              <div class="text-h4 text-primary text-weight-medium q-mb-lg">Popis Usluga</div>
              <q-table
                :rows="services"
                :columns="serviceColumns"
                row-key="uslugaId"
                flat bordered square dense
                :loading="loadingServices"
                separator="cell"
                :pagination="{ rowsPerPage: 10 }"
                no-data-label="Nema usluga"
                no-results-label="Nema rezultata"
                style="max-width:900px"
              />
            </div>
          </div>
        </transition>
      </q-page>
    </div>
  </div>
</template>

<script setup>
import { ref, onMounted, watch } from 'vue'
import { api } from 'boot/axios'

const miniState = ref(false)
const selected = ref('users')
const menu = [
  { section: 'users', label: 'Korisnici', icon: 'person' },
  { section: 'appointments', label: 'Termini', icon: 'event' },
  { section: 'services', label: 'Usluge', icon: 'content_cut' }
]

// Users
const users = ref([])
const loadingUsers = ref(false)
const userColumns = [
  { name: 'korisnikId', label: 'ID', align: 'left', field: 'korisnikId', sortable: true },
  { name: 'username', label: 'Korisničko ime', align: 'left', field: 'username', sortable: true },
  { name: 'ime', label: 'Ime', align: 'left', field: 'ime', sortable: true },
  { name: 'prezime', label: 'Prezime', align: 'left', field: 'prezime', sortable: true },
  { name: 'email', label: 'E-mail', align: 'left', field: 'email', sortable: true },
  { name: 'enabled', label: 'Status', align: 'center', field: 'enabled', sortable: true },
  { name: 'role', label: 'Uloga', align: 'left', field: row => row.role?.nazivUloge || '', sortable: true },
  { name: 'actions', label: 'Akcije', align: 'center', field: 'actions', sortable: false }
]
function fetchUsers() {
  loadingUsers.value = true
  api.get('/api/private/admin/lista-korisnika')
    .then(res => { users.value = res.data })
    .catch(() => { users.value = [] })
    .finally(() => { loadingUsers.value = false })
}

// Appointments
const appointments = ref([])
const loadingAppointments = ref(false)
const appointmentColumns = [
  { name: 'terminId', label: 'ID', align: 'left', field: 'terminId', sortable: true },
  { name: 'datumTermina', label: 'Datum', align: 'left', field: 'datumTermina', sortable: true },
  { name: 'vrijeme', label: 'Vrijeme', align: 'left', field: 'vrijeme', sortable: true },
  { name: 'uslugaNaziv', label: 'Usluga', align: 'left', field: 'uslugaNaziv', sortable: true },
  { name: 'frizer', label: 'Frizer', align: 'left', field: row => `${row.frizerIme} ${row.frizerPrezime}`, sortable: false },
  { name: 'klijent', label: 'Klijent', align: 'left', field: row => `${row.korisnikIme} ${row.korisnikPrezime}`, sortable: false }
]
function fetchAppointments() {
  loadingAppointments.value = true
  api.get('/api/private/admin/lista-termina')
    .then(res => { appointments.value = res.data })
    .catch(() => { appointments.value = [] })
    .finally(() => { loadingAppointments.value = false })
}

// Services
const services = ref([])
const loadingServices = ref(false)
const serviceColumns = [
  { name: 'uslugaId', label: 'ID', align: 'left', field: 'uslugaId', sortable: true },
  { name: 'naziv', label: 'Naziv usluge', align: 'left', field: 'naziv', sortable: true },
  { name: 'cijena', label: 'Cijena (EUR)', align: 'right', field: 'cijena', sortable: true, format: val => val?.toFixed(2) || '-' },
  { name: 'trajanje', label: 'Trajanje (min)', align: 'right', field: 'trajanje', sortable: true }
]
function fetchServices() {
  loadingServices.value = true
  api.get('/api/private/usluge')
    .then(res => { services.value = res.data })
    .catch(() => { services.value = [] })
    .finally(() => { loadingServices.value = false })
}

// Watchers
onMounted(fetchUsers)
watch(selected, val => {
  if (val === 'appointments') fetchAppointments()
  if (val === 'services') fetchServices()
})

// Dummy handlers
function editItem(row) {
  console.log('Edit', row)
}
function deleteItem(row) {
  console.log('Delete', row)
}
</script>

<style scoped>
.admin-content {
  margin-top: 48px;
  margin-left: 24px;
  max-width: 1200px;
  min-height: 400px;
  background: rgba(255, 255, 255, 0.87);
  border-radius: 12px;
  box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.04);
}

.admin-drawer {
  min-height: calc(100vh - 50px);
  margin-top: 0;
}

.q-table tbody tr:hover {
  background-color: #f5f5f5;
  cursor: pointer;
}

/* Fade animation */
.fade-enter-active, .fade-leave-active {
  transition: opacity 0.3s ease;
}
.fade-enter-from, .fade-leave-to {
  opacity: 0;
}
</style>
