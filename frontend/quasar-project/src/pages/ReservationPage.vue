<template>
  <q-page class="q-pa-md">
    <q-card flat bordered class="q-pa-lg q-mx-auto" style="max-width: 500px">
      <q-card-section>
        <div class="text-h6 text-primary">Rezervacija termina</div>
      </q-card-section>

      <q-card-section class="q-gutter-md">
        <q-select
          filled
          v-model="selectedService"
          :options="services"
          label="Odaberi uslugu"
          emit-value
          map-options
        />

        <q-select
          filled
          v-model="selectedStylist"
          :options="stylists"
          label="Odaberi frizera"
          emit-value
          map-options
        />

        <q-date
          v-model="selectedDate"
          mask="YYYY-MM-DD"
          color="primary"
          :options="availableDates"
          label="Odaberi datum"
          today-btn
        />

        <q-select
          filled
          v-model="selectedTime"
          :options="filteredTimes"
          label="Odaberi vrijeme"
          :disable="filteredTimes.length === 0"
        />

        <div v-if="filteredTimes.length === 0 && selectedService && selectedStylist && selectedDate" class="text-red q-mt-sm">
          Sva vremena su zauzeta za odabrani dan!
        </div>

        <q-btn
          color="primary"
          label="Rezerviraj"
          @click="submitReservation"
          :disable="!formIsValid || loading"
          class="full-width"
        />

        <div class="q-mt-md">
          <b>DEBUG:</b>
          <pre>
    Service: {{ selectedService }}
    Stylist: {{ selectedStylist }}
    Date: {{ selectedDate }}
    Time: {{ selectedTime }}
  </pre>
        </div>

        <q-banner v-if="successMessage" class="q-mt-md bg-green-2 text-green-9" dense>
          {{ successMessage }}
        </q-banner>
        <q-banner v-if="errorMessage" class="q-mt-md bg-red-2 text-red-9" dense>
          {{ errorMessage }}
        </q-banner>
      </q-card-section>
    </q-card>
  </q-page>
</template>

<script setup>
import { ref, computed, onMounted, watch } from 'vue'
import { api } from 'boot/axios'

const services = ref([])
const stylists = ref([])

onMounted(async () => {
  try {
    // Dohvati usluge
    const resUsluge = await api.get('/api/private/usluge')
    services.value = resUsluge.data.map(usluga => ({
      label: usluga.naziv,
      value: usluga.uslugaId
    }))

    // Dohvati frizere
    const resFrizeri = await api.get('/api/private/frizeri')
    stylists.value = resFrizeri.data.map(frizer => ({
      label: frizer.korisnik
        ? `${frizer.korisnik.ime} ${frizer.korisnik.prezime}`
        : frizer.frizerId,
      value: frizer.frizerId
    }))
  } catch (e) {
    if (e.response) {
      console.error('Status:', e.response.status)
      console.error('Data:', e.response.data)
    } else {
      console.error('Greška:', e)
    }
  }
})

// Nedjeljom (0) ne radi
const availableDates = (date) => {
  const day = new Date(date).getDay()
  return day !== 0
}

const availableTimes = [
  '09:00', '10:00', '11:00', '12:00', '13:00',
  '14:00', '15:00', '16:00', '17:00', '18:00'
]

const takenTimes = ref([])

const selectedService = ref(null)
const selectedStylist = ref(null)
const selectedDate = ref(null)
const selectedTime = ref(null)
const loading = ref(false)
const errorMessage = ref('')
const successMessage = ref('')

// Dohvaća zauzeta vremena kad se promijeni usluga, frizer ili datum
watch([selectedService, selectedStylist, selectedDate], async ([uslugaId, frizerId, datum]) => {
  if (uslugaId && frizerId && datum) {
    try {
      const res = await api.get('/api/private/termini/zauzeti', {
        params: { frizerId, uslugaId, datum }
      })
       console.log('zauzeta vremena iz backend-a:', res.data)
      takenTimes.value = res.data || []
      if (takenTimes.value.includes(selectedTime.value)) {
        selectedTime.value = null
      }
    } catch {
      
      takenTimes.value = []
    }
  } else {
    takenTimes.value = []
  }
})

// Samo slobodna vremena
const filteredTimes = computed(() =>
  availableTimes.filter(t => !takenTimes.value.includes(t))
)

const formIsValid = computed(() =>
  selectedService.value &&
  selectedStylist.value &&
  selectedDate.value &&
  selectedTime.value
)

async function submitReservation() {
  // Debug ispis
  console.log(
    'Selected values:',
    selectedService.value,
    selectedStylist.value,
    selectedDate.value,
    selectedTime.value
  )

  errorMessage.value = ''
  successMessage.value = ''
  loading.value = true

  const uslugaId = selectedService.value
  const frizerId = selectedStylist.value
  const url = `/api/private/termin/rezerviraj/usluga/${uslugaId}/frizer/${frizerId}`

  const dataToSend = {
    datumTermina: selectedDate.value,
    vrijeme: selectedTime.value
  }

  console.log('Request payload:', dataToSend)

  try {
    await api.post(url, dataToSend)
    successMessage.value = 'Rezervacija je uspješno poslana!'
    // Resetiraj formu
    selectedService.value = null
    selectedStylist.value = null
    selectedDate.value = null
    selectedTime.value = null
    takenTimes.value = []
  } catch (err) {
    errorMessage.value =
      (err.response && err.response.data && err.response.data.message) ||
      'Greška prilikom slanja rezervacije. Pokušaj kasnije.'
  } finally {
    loading.value = false
  }
}
</script>
