<template>
  <q-page class="q-pa-md bg-grey-1">
    <h1 class="text-h4 text-primary q-mb-lg text-center fancy-title">O nama</h1>

    <q-img
      src="../assets/frizerski.jpg"
      alt="Frizerski salon"
      class="q-mb-lg"
      style="max-width: 35%; border-radius: 16px; box-shadow: 0 4px 10px rgba(0, 0, 0, 0.2); display: block; margin: 0 auto;"
    />

    <div class="text-body1 text-grey-8 q-mb-lg text-center fancy-text">
      <h2 class="text-h5 text-primary q-mb-md">Naša vizija</h2>
      <p>
        Frizerski salon Elegance već dugi niz godina pruža profesionalne frizerske usluge
        za muškarce i žene. Naš cilj je istaknuti prirodnu ljepotu vaše kose koristeći
        najnovije tehnike i visokokvalitetne proizvode.
      </p>
    </div>

    <div class="text-body1 text-grey-8 q-mb-lg text-center fancy-text">
      <h2 class="text-h5 text-primary q-mb-md">Zašto odabrati nas?</h2>
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

    <div class="text-body1 text-grey-8 q-mb-lg text-center fancy-text">
      <h2 class="text-h5 text-primary q-mb-md">Posjetite nas!</h2>
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
}

.fancy-text h2 {
  font-family: 'Playfair Display', serif;
  font-weight: bold;
  color: #1e88e5;
}

ul li {
  margin: 0.5rem 0;
}
</style>
