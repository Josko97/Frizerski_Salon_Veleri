<template>
  <router-view />
</template>

<script>
import { defineComponent } from 'vue';

export default defineComponent({
  name: 'App'
});

import { onMounted, getCurrentInstance } from 'vue'

onMounted(async () => {
  const { proxy } = getCurrentInstance()
  try {
    const res = await proxy.$api.get('/api/public/csrf-token')
    // Spremi token u localStorage ili u memory (ovisno što želiš)
    localStorage.setItem('csrfToken', res.data.token)
    // Ako koristiš Pinia ili store, možeš spremiti i tamo
  } catch (err) {
    console.error('Ne mogu dohvatiti CSRF token:', err)
  }
})

</script>
