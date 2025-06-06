<template>
  <q-page class="q-pa-md" style="background: linear-gradient(to bottom, #f8f9fa, #e9ecef);">
    <!-- Hero sekcija -->
    <div class="text-center q-mb-xl" style="background: #ffffff; padding: 20px; border-radius: 8px; box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);">
      <img
        src="../assets/logo.png"
        alt="Salon Logo"
        class="q-mb-md"
        style="width: 150px; border-radius: 50%; box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);"
      />
      <h1 class="text-h3 text-primary font-bold">Frizerski Salon Elegance</h1>
      <p class="text-subtitle1 text-grey-7">Vaša kosa. Naš stil.</p>
      <q-btn
        color="primary"
        label="Rezerviraj termin"
        class="q-mt-md rounded-lg"
        to="/rezervacija"
      />
    </div>

    <q-separator class="q-my-md" />

    <!-- Usluge -->
    <div class="q-mb-xl text-center" style="background: #ffffff; padding: 20px; border-radius: 8px; box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);">
      <h2 class="text-h5 q-mb-md text-primary font-bold">Naše usluge</h2>
      <div class="row justify-center q-col-gutter-md">
        <q-card class="col-xs-12 col-sm-6 col-md-4 q-pa-md" flat bordered>
          <q-card-section>
            <div class="text-h6 font-bold">Muško šišanje</div>
            <div class="text-caption text-grey">Profesionalno šišanje za muškarce</div>
          </q-card-section>
        </q-card>
        <q-card class="col-xs-12 col-sm-6 col-md-4 q-pa-md" flat bordered>
          <q-card-section>
            <div class="text-h6 font-bold">Žensko šišanje</div>
            <div class="text-caption text-grey">Kreativne frizure i njegovanje</div>
          </q-card-section>
        </q-card>
        <q-card class="col-xs-12 col-sm-6 col-md-4 q-pa-md" flat bordered>
          <q-card-section>
            <div class="text-h6 font-bold">Bojanje kose</div>
            <div class="text-caption text-grey">Moderne boje i tehnike</div>
          </q-card-section>
        </q-card>
      </div>
    </div>

    <q-separator class="q-my-md" />

    <!-- Galerija -->
    <div class="q-mb-md" style="background: #ffffff; padding: 20px; border-radius: 8px; box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);">
      <h2 class="text-h5 q-mb-md text-primary text-center font-bold">Galerija</h2>
      <div class="row justify-center q-gutter-md">
        <q-img
          src="../assets/musko.jpg"
          alt="Muško šišanje"
          class="col-xs-12 col-sm-4"
          style="width: 150px; border-radius: 8px; box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);"
        />
        <q-img
          src="../assets/zensko.jpg"
          alt="Žensko šišanje"
          class="col-xs-12 col-sm-4"
          style="width: 150px; border-radius: 8px; box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);"
        />
        <q-img
          src="../assets/bojanje.jpg"
          alt="Bojanje kose"
          class="col-xs-12 col-sm-4"
          style="width: 150px; border-radius: 8px; box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);"
        />
      </div>
    </div>

    <q-separator class="q-my-md" />

    <!-- Kontakt -->
    <div class="text-center" style="background: #ffffff; padding: 20px; border-radius: 8px; box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);">
      <h2 class="text-h5 text-primary font-bold">Kontakt</h2>
      <div class="q-mt-sm text-grey-8">
        <p><q-icon name="place" class="q-mr-sm" /> Adresa: Ulica Frizera 1, Rijeka</p>
        <p><q-icon name="call" class="q-mr-sm" /> Telefon: 091 123 4567</p>
        <p><q-icon name="email" class="q-mr-sm" /> Email: info@elegance.hr</p>
      </div>
    </div>
  </q-page>
</template>

