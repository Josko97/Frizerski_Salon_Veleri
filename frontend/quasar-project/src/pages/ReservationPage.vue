<template>
  <q-page
    class="row justify-center"
    style="
      min-height: 100vh;
      background: url('../assets/background.jpg') no-repeat center center fixed;
      background-size: cover;
      padding: 20px;
    "
  >
    <q-card
      class="shadow-2"
      style="
        width: 600px;
        max-width: 95%;
        padding: 24px;
        border-radius: 12px;
        background: linear-gradient(145deg, rgba(255,255,255,0.95), rgba(240,240,240,0.9));
        backdrop-filter: blur(10px);
      "
    >
      <div class="text-center q-mb-lg">
        <img
          src="../assets/logo.png"
          alt="Salon Logo"
          style="
            width: 80px;
            border-radius: 50%;
            margin-bottom: 16px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
          "
        />
        <h1 class="text-h5 text-primary">Rezervacija termina</h1>
        <p class="text-subtitle1 text-grey-7">Odaberite željeni termin</p>
      </div>

      <q-form class="q-gutter-lg q-form">
        <q-select
          filled
          v-model="selectedService"
          :options="services"
          label="Odaberi uslugu"
          emit-value
          map-options
          class="gradient-input q-mb-xl"
        />

        <q-select
          filled
          v-model="selectedStylist"
          :options="stylists"
          label="Odaberi frizera"
          emit-value
          map-options
          class="gradient-input q-mb-xl"
        />

        <q-date
          v-model="selectedDate"
          mask="YYYY-MM-DD"
          color="primary"
          :options="availableDates"
          label="Odaberi datum"
          today-btn
          class="gradient-date q-mb-xl"
        />

        <q-select
          filled
          v-model="selectedTime"
          :options="filteredTimes"
          label="Odaberi vrijeme"
          :disable="filteredTimes.length === 0"
          class="gradient-input q-mb-xl"
        />

        <div
          v-if="filteredTimes.length === 0 && selectedService && selectedStylist && selectedDate"
          class="text-negative q-mt-sm"
        >
          Sva vremena su zauzeta za odabrani dan!
        </div>

        <div class="q-mt-xl text-center">
          <q-btn
            label="Rezerviraj"
            color="primary"
            @click="submitReservation"
            :disable="!formIsValid || loading"
            class="shadow-2 hover-grow"
            :loading="loading"
            style="
              border-radius: 8px;
              width: calc(100% - 16px);
              max-width: 300px;
              margin: 0 auto;
            "
          />
        </div>

        <q-banner v-if="successMessage" class="q-mt-lg bg-green-2 text-green-9" dense>
          {{ successMessage }}
        </q-banner>
        <q-banner v-if="errorMessage" class="q-mt-lg bg-red-2 text-red-9" dense>
          {{ errorMessage }}
        </q-banner>

        <div class="q-mt-md">
          <b>DEBUG:</b>
          <pre>
Service: {{ selectedService }}
Stylist: {{ selectedStylist }}
Date: {{ selectedDate }}
Time: {{ selectedTime }}
          </pre>
        </div>
      </q-form>
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
    const resUsluge = await api.get('/api/private/usluge')
    services.value = resUsluge.data.map(usluga => ({
      label: usluga.naziv,
      value: usluga.uslugaId
    }))

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

<style scoped>
.q-form {
  width: 100%;
  max-width: 600px;
  margin: 0 auto;
  padding-left: 0;
  padding-right: 0;
}

.gradient-input {
  background: linear-gradient(145deg, #f0f0f0, #e0e0e0);
  box-shadow: 0 4px 6px rgba(0, 0, 0, 0.08);
  border-radius: 8px;
  width: 100%;
  margin-left: 0 !important;
  margin-right: 0 !important;
  padding-left: 12px;
  padding-right: 12px;
  box-sizing: border-box;
}

.gradient-date {
  background: linear-gradient(145deg, #f7f7f7, #dcdcdc);
  box-shadow: 0 6px 12px rgba(0, 0, 0, 0.1);
  border-radius: 8px;
  width: 100%;
  margin-left: 0 !important;
  margin-right: 0 !important;
  padding-left: 12px;
  padding-right: 12px;
  box-sizing: border-box;
  display: block;
  margin-left: auto;
  margin-right: auto;
}

.text-negative {
  color: #e53935;
}

.hover-grow:hover {
  transform: scale(1.05);
  transition: transform 0.2s ease-in-out;
}
</style>
