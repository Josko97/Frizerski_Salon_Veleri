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
        background: linear-gradient(145deg, rgba(255, 255, 255, 0.95), rgba(240, 240, 240, 0.9));
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
          :options="isValidDate"
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
            style="border-radius: 8px; width: calc(100% - 16px); max-width: 300px; margin: 0 auto"
          />
        </div>

        <q-banner v-if="successMessage" class="q-mt-lg bg-green-2 text-green-9" dense>
          {{ successMessage }}
        </q-banner>
        <q-banner v-if="errorMessage" class="q-mt-lg bg-red-2 text-red-9" dense>
          {{ errorMessage }}
        </q-banner>
      </q-form>
    </q-card>
  </q-page>
</template>

<script setup>
import { ref, computed, onMounted, watch } from 'vue'
import { api } from 'boot/axios'

// Helper funkcije
function generateTimes(start, end, stepMinutes) {
  const result = []
  let [h, m] = start.split(':').map(Number)
  const [endH, endM] = end.split(':').map(Number)
  while (h < endH || (h === endH && m <= endM)) {
    result.push(
      h.toString().padStart(2, '0') + ':' + m.toString().padStart(2, '0')
    )
    m += stepMinutes
    if (m >= 60) {
      m -= 60
      h += 1
    }
  }
  return result
}

function addMinutes(time, mins) {
 const [h, m] = time.split(':').map(Number)
  const date = new Date(0, 0, 0, h, m)
  date.setMinutes(date.getMinutes() + mins)
  return date.toTimeString().slice(0,5)
}

function isSlotFree(candidateStart, trajanjeNoveUsluge, zauzeti) {
 if (!candidateStart) return true
  const candidateEnd = addMinutes(candidateStart, trajanjeNoveUsluge+5)
  for (const t of zauzeti) {
    const takenStart = t.vrijeme
    const takenEnd = addMinutes(takenStart, t.trajanje)
    // NOVI TERMIN JE SLOBODAN ako: kandidat ne preklapa ni s jednim postojećim
    if (candidateStart < takenEnd && candidateEnd > takenStart) {
      return false
    }
  }
  return true
}

const services = ref([])
const stylists = ref([])

onMounted(async () => {
  try {
    const resUsluge = await api.get('/api/private/usluge')
    services.value = resUsluge.data.map((usluga) => ({
      label: usluga.naziv,
      value: usluga.uslugaId,
      trajanje: usluga.trajanje // u minutama
    }))

    const resFrizeri = await api.get('/api/private/frizeri')
    stylists.value = resFrizeri.data.map((frizer) => ({
      label: frizer.korisnik
        ? `${frizer.korisnik.ime} ${frizer.korisnik.prezime}`
        : frizer.frizerId,
      value: frizer.frizerId,
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

const selectedService = ref(null)
const selectedStylist = ref(null)
const selectedDate = ref(null)
const selectedTime = ref(null)
const takenTimes = ref([])
const loading = ref(false)
const errorMessage = ref('')
const successMessage = ref('')

const selectedServiceObj = computed(() =>
  services.value.find(u => u.value === selectedService.value)
)
const stepMinutes = computed(() =>
  selectedServiceObj.value?.trajanje || 15 // default 15 min ako ništa nije odabrano
)
const availableTimes = computed(() =>
  generateTimes('08:00', '14:00', stepMinutes.value)
)

watch([selectedService, selectedStylist, selectedDate], async ([uslugaId, frizerId, datum]) => {
  if (uslugaId && frizerId && datum) {
    try {
      const res = await api.get('/api/private/termini/zauzeti', {
        params: { frizerId, datum },
      })
      takenTimes.value = res.data || []
      console.log('ZAUZETI TERMINI iz backend-a:', takenTimes.value) 
      if (!isSlotFree(selectedTime.value, stepMinutes.value, takenTimes.value)) {
        selectedTime.value = null
      }
    } catch {
      takenTimes.value = []
    }
  } else {
    takenTimes.value = []
  }
})

const filteredTimes = computed(() => {
  if (!selectedServiceObj.value) return []
  const trajanje = selectedServiceObj.value.trajanje
  return availableTimes.value.filter(slot =>
    isSlotFree(slot, trajanje, takenTimes.value)
  )
})

const formIsValid = computed(
  () => selectedService.value && selectedStylist.value && selectedDate.value && selectedTime.value,
)

const isValidDate = (date) => {
  const today = new Date()
  today.setHours(0, 0, 0, 0)
  const selected = new Date(date)
  selected.setHours(0, 0, 0, 0)
  const isFutureOrToday = selected >= today
  const notSunday = selected.getDay() !== 0
  return isFutureOrToday && notSunday
}

async function submitReservation() {
  errorMessage.value = ''
  successMessage.value = ''
  loading.value = true

  const uslugaId = selectedService.value
  const frizerId = selectedStylist.value
  const url = `/api/private/termin/rezerviraj/usluga/${uslugaId}/frizer/${frizerId}`

  const dataToSend = {
    datumTermina: selectedDate.value,
    vrijeme: selectedTime.value,
  }

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
