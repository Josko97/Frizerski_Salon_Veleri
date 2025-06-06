<template>
  <q-page class="q-pa-md bg-gradient">
    <div class="text-center q-mb-xl">
      <h1 class="text-h4 main-title">Rezervirajte svoj termin</h1>
      <p class="text-body1 main-subtitle">
        Uživajte u personaliziranoj usluzi. Brzo i jednostavno rezervirajte svoj termin!
      </p>
    </div>

    <q-card flat bordered class="q-pa-lg card-container shadow-card">
      <q-card-section>
        <div class="text-center">
          <h2 class="text-h6 section-title">Odaberite svoje preferencije</h2>
        </div>
      </q-card-section>

      <q-card-section class="form-section">
        <q-select
          filled
          v-model="selectedService"
          :options="services"
          label="Odaberi uslugu"
          emit-value
          map-options
          class="form-item"
        />

        <q-select
          filled
          v-model="selectedStylist"
          :options="stylists"
          label="Odaberi frizera"
          emit-value
          map-options
          class="form-item"
        />

        <q-date
          v-model="selectedDate"
          mask="YYYY-MM-DD"
          color="primary"
          :options="availableDates"
          label="Odaberi datum"
          today-btn
          bordered
          class="form-item"
        />

        <q-select
          filled
          v-model="selectedTime"
          :options="availableTimes"
          label="Odaberi vrijeme"
          class="form-item"
        />
      </q-card-section>

      <q-card-section class="text-center q-mt-md">
        <q-btn
          unelevated
          color="primary"
          label="Rezerviraj"
          @click="submitReservation"
          :disable="!formIsValid"
          class="action-button"
        />
      </q-card-section>
    </q-card>
  </q-page>
</template>

<script setup>
import { ref, computed } from 'vue';

const services = [
  { label: 'Muško šišanje', value: 'musko' },
  { label: 'Žensko šišanje', value: 'zensko' },
  { label: 'Bojanje kose', value: 'bojanje' },
];

const stylists = [
  { label: 'Ana', value: 'ana' },
  { label: 'Ivan', value: 'ivan' },
  { label: 'Marija', value: 'marija' },
];

const availableDates = (date) => {
  const day = new Date(date).getDay();
  return day !== 0; // Disable Sundays
};

const availableTimes = ['09:00', '10:00', '11:00', '13:00', '14:00', '15:00', '17:00', '18:00'];

const selectedService = ref(null);
const selectedStylist = ref(null);
const selectedDate = ref(null);
const selectedTime = ref(null);

const formIsValid = computed(
  () =>
    selectedService.value && selectedStylist.value && selectedDate.value && selectedTime.value
);

function submitReservation() {
  alert(
    `Rezervacija:\nUsluga: ${selectedService.value}\nFrizer: ${selectedStylist.value}\nDatum: ${selectedDate.value}\nVrijeme: ${selectedTime.value}`
  );
}
</script>

<style>
.bg-gradient {
  background: linear-gradient(120deg, #ffffff, #f3f4f6);
  min-height: 100vh;
  padding-top: 2rem;
}

.card-container {
  max-width: 700px;
  margin: 0 auto;
  background: #fff;
  border-radius: 16px;
}

.shadow-card {
  box-shadow: 0 6px 18px rgba(0, 0, 0, 0.1);
}

.main-title {
  color: #2c3e50;
  font-weight: bold;
}

.main-subtitle {
  color: #7f8c8d;
}

.section-title {
  color: #3498db;
  font-weight: bold;
}

.form-section {
  display: grid;
  gap: 1rem;
  margin-top: 1rem;
}

.form-item {
  width: 100%;
}

.action-button {
  width: 50%;
  margin: 0 auto;
  font-weight: bold;
  transition: background-color 0.3s ease;
}

.action-button:hover {
  background-color: #1e88e5;
}
</style>
