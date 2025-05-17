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

        <q-select filled v-model="selectedTime" :options="availableTimes" label="Odaberi vrijeme" />

        <q-btn
          color="primary"
          label="Rezerviraj"
          @click="submitReservation"
          :disable="!formIsValid"
          class="full-width"
        />
      </q-card-section>
    </q-card>
  </q-page>
</template>

<script setup>
import { ref, computed } from 'vue'

const services = [
  { label: 'Muško šišanje', value: 'musko' },
  { label: 'Žensko šišanje', value: 'zensko' },
  { label: 'Bojanje kose', value: 'bojanje' },
]

const stylists = [
  { label: 'Ana', value: 'ana' },
  { label: 'Ivan', value: 'ivan' },
  { label: 'Marija', value: 'marija' },
]

const availableDates = (date) => {
  const day = new Date(date).getDay()
  return day !== 0
}

const availableTimes = ['09:00', '10:00', '11:00', '13:00', '14:00', '15:00', '17:00', '18:00']

const selectedService = ref(null)
const selectedStylist = ref(null)
const selectedDate = ref(null)
const selectedTime = ref(null)

const formIsValid = computed(
  () => selectedService.value && selectedStylist.value && selectedDate.value && selectedTime.value,
)

function submitReservation() {
  alert(
    `Rezervacija:\nUsluga: ${selectedService.value}\nFrizer: ${selectedStylist.value}\nDatum: ${selectedDate.value}\nVrijeme: ${selectedTime.value}`,
  )
}
</script>
