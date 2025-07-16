<template>
    <q-page class="q-pa-lg flex flex-center">
        <q-card style="max-width: 600px; width: 100%;" class="q-pa-xl shadow-4">
            <q-card-section>
                <div class="text-h4 text-center q-mb-lg">Kontaktiraj nas</div>
            </q-card-section>
            <q-form @submit.prevent="posaljiFormu" ref="kontaktForm">
                <q-card-section class="q-gutter-lg">
                    <q-input v-model="form.ime" label="Ime" :rules="[val => !!val || 'Ime je obavezno']" lazy-rules
                        outlined clearable class="q-pa-sm" input-class="text-lg" />
                    <q-input v-model="form.email" label="Email" type="email" :rules="[
                        val => !!val || 'Email je obavezan',
                        val => /.+@.+\..+/.test(val) || 'Email nije ispravan',
                    ]" lazy-rules outlined clearable class="q-pa-sm" input-class="text-lg" />
                    <q-input v-model="form.poruka" label="Poruka" type="textarea"
                        :rules="[val => !!val || 'Poruka je obavezna', val => !val || val.length <= 500 || 'Poruka smije imati najviše 500 znakova']"
                        outlined lazy-rules clearable autogrow :rows="8" :max-rows="15" class="q-pa-sm"
                        input-class="my-textarea" />
                </q-card-section>
                <q-card-actions align="right">
                    <q-btn label="Pošalji" color="primary" type="submit" :loading="loading" :disable="loading" size="lg"
                        class="q-mt-md" />
                </q-card-actions>
            </q-form>
            <q-card-section v-if="obavijest" class="q-pa-none">
                <q-banner class="q-mt-sm"
                    :class="{ 'bg-positive text-white': uspjeh, 'bg-negative text-white': !uspjeh }" dense>
                    {{ obavijest }}
                </q-banner>
            </q-card-section>
        </q-card>
    </q-page>
</template>


<script setup>
import { ref } from 'vue'
import { api } from 'boot/axios'


const form = ref({
    ime: '',
    email: '',
    poruka: ''
})

const loading = ref(false)
const obavijest = ref('')
const uspjeh = ref(false)
const kontaktForm = ref(null)

async function posaljiFormu() {
    obavijest.value = ''
    uspjeh.value = false
    loading.value = true
    try {
        await kontaktForm.value.validate()
        await api.post('/api/public/kontakt', {
            ime: form.value.ime,
            email: form.value.email,
            poruka: form.value.poruka
        })
        obavijest.value = 'Poruka je uspješno poslana!'
        uspjeh.value = true
        form.value.ime = ''
        form.value.email = ''
        form.value.poruka = ''
        kontaktForm.value.resetValidation()
    } catch {
        obavijest.value = 'Došlo je do pogreške. Pokušajte ponovno.'
        uspjeh.value = false
    } finally {
        loading.value = false
    }
}
</script>
