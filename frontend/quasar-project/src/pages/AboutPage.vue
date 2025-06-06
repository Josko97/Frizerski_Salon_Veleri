<template>
  <q-page class="q-pa-md bg-grey-1">
    <h1 class="text-h4 text-primary q-mb-lg text-center fancy-title">
      O nama
    </h1>

    <q-img
      src="../assets/frizerski.jpg"
      alt="Frizerski salon"
      class="q-mb-lg"
      style="
        max-width: 35%;
        border-radius: 16px;
        box-shadow: 0 4px 10px rgba(0, 0, 0, 0.2);
        display: block;
        margin: 0 auto;
        animation: fadeIn 1s ease-in-out;
      "
    />

    <div class="text-body1 text-grey-8 q-mb-lg text-center fancy-text bordered-section">
      <h2 class="text-h5 text-primary q-mb-md">
        <span class="icon">🌟</span> Naša vizija
      </h2>
      <p>
        Frizerski salon Elegance već dugi niz godina pruža profesionalne frizerske usluge
        za muškarce i žene. Naš cilj je istaknuti prirodnu ljepotu vaše kose koristeći
        najnovije tehnike i visokokvalitetne proizvode.
      </p>
    </div>

    <div class="text-body1 text-grey-8 q-mb-lg text-center fancy-text bordered-section">
      <h2 class="text-h5 text-primary q-mb-md">
        <span class="icon">💇‍♀️</span> Zašto odabrati nas?
      </h2>
      <p>
        Svakom klijentu pristupamo individualno i trudimo se stvoriti frizuru koja
        odgovara njihovom stilu i osobnosti. Naš tim stručnjaka kontinuirano se educira
        kako bi bio u korak s najnovijim trendovima.
      </p>
      <ul class="q-my-md text-center" style="list-style-type: none; padding: 0;">
        <li>✨ Korištenje najkvalitetnijih proizvoda</li>
        <li>✨ Ugodna i opuštajuća atmosfera</li>
        <li>✨ Moderne tehnike šišanja i bojanja</li>
      </ul>
    </div>

    <div class="text-body1 text-grey-8 q-mb-lg text-center fancy-text bordered-section">
      <h2 class="text-h5 text-primary q-mb-md">
        <span class="icon">📍</span> Posjetite nas!
      </h2>
      <p>
        Naš salon je mjesto gdje se ljepota i stil susreću. Bilo da tražite klasičnu
        frizuru, modernu promjenu ili osvježenje boje, mi smo ovdje za vas. Posjetite nas
        na našoj adresi i uvjerite se zašto smo vodeći frizerski salon u gradu!
      </p>
    </div>
  </q-page>
</template>

<script setup>
</script>

<style>
.fancy-title {
  font-family: 'Playfair Display', serif;
  font-weight: bold;
  text-transform: uppercase;
}

.fancy-text {
  font-family: 'Roboto', sans-serif;
  font-size: 1.1rem;
  line-height: 1.6;
  color: #555;
  text-align: center;
}

.bordered-section {
  border-radius: 10px;
  padding: 1.5rem;
  margin-bottom: 1.5rem;
  background-color: #fff; /* Bijela podloga */
  box-shadow: 0 4px 12px rgba(0, 0, 0, 0.15); /* Sjena */
  animation: slideIn 0.8s ease-in-out; /* Animacija dolaska */
}

.icon {
  font-size: 1.5rem;
  margin-right: 0.5rem;
}

@keyframes slideIn {
  from {
    transform: translateY(30px);
    opacity: 0;
  }
  to {
    transform: translateY(0);
    opacity: 1;
  }
}

@keyframes fadeIn {
  from {
    opacity: 0;
  }
  to {
    opacity: 1;
  }
}
</style>
