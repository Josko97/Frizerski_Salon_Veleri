import { defineStore } from 'pinia';
import axios from 'axios';

export const useUsersStore = defineStore('users', {
  state: () => ({
    usersSearchResult: [],
    userGetResult: {
      branches: []
    },
    user: null,
    loading: false,
    status: '',
    statusDescription: '',
    statusMessage: '',
    loadingEmails: false,
    emails: []
  }),
  actions: {
    async registracija(payload) {
      console.log('usao sam')
      try {
        this.loading = true;
        const korisnik = {
          ime: payload.ime,
          prezime: payload.prezime,
          email: payload.email,
          lozinka: payload.lozinka
        };
        const url = `http://localhost:8080/api/public/auth/register`;
       // const response = await postAPI('auth/register', korisnik);
       const response = axios
         .post(url, korisnik)
         .then(res => {
           console.log('Response:', res.data);
           return res.data;
         })
         .catch(error => {
           console.log('Error:', error);

             if (error.response && [401, 403].includes(error.response.status)) {
               console.log('Unauthorized or forbidden, removing token');
               localStorage.removeItem('token');

           }
           throw error; // Rethrow the error for further handling
         });

        this.status = response.status;
        this.statusDescription = response.description ?? '';
        this.statusMessage = response.message ?? '';
      } catch (error) {
        this.status = 'ERROR';
        this.statusMessage = error.message ?? 'Greška prilikom registracije';
        this.statusDescription = error.description ?? 'Nema dodatnih detalja';
      } finally {
        this.loading = false;
      }
    },
    async login(payload) {
      console.log('Pokušaj prijave:', payload);
      try {
        this.loading = true;

        const korisnik = {
          email: payload.email,
          lozinka: payload.lozinka
        };
        const url = `http://localhost:8080/api/public/auth/login`;

        const response = await axios
          .post(url, korisnik)
          .then(res => {
            console.log('Odgovor sa servera:', res.data);
            return res.data;
          })
          .catch(error => {
            console.error('Greška kod prijave:', error);
            if (error.response && [401, 403].includes(error.response.status)) {
              console.log('Unauthorized ili forbidden, brisanje tokena');
              localStorage.removeItem('token');
            }
            throw error; // Ponovno bacanje greške za daljnje rukovanje
          });

        // Pretpostavka: backend vraća token i korisnika
        this.token = response.token;
        this.user = response.user;

        // Spremanje tokena u localStorage
        localStorage.setItem('token', response.token);

        this.status = 'SUCCESS';
        this.statusMessage = 'Prijava uspješna.';
        console.log('Prijava uspješna:', response);
      } catch (error) {
        this.status = 'ERROR';
        this.statusMessage = error.message || 'Greška kod prijave.';
        this.statusDescription = error.description || 'Nema dodatnih informacija.';
        throw error;
      } finally {
        this.loading = false;
      }
    },

    // Other actions...
  }
});
