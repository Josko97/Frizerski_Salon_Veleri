import { postAPI,getAPI } from '../store/restAPI.js';
import { defineStore } from 'pinia';



export default defineStore('users', {
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
    async search(payload) {
      try {
        this.loading = true;
        const response = await getAPI('auth/users/', {
          search: payload.search
        });
        this.usersSearchResult = response;
      } finally {
        this.loading = false;
      }
    },

    async deleteUser(payload) {
      try {
        this.loading = true;
        const response = await postAPI(`auth/users/${payload.id}`);
        this.status = response.status;
        this.statusDescription = response.description;
        this.statusMessage = response.message;
      } finally {
        this.loading = false;
      }
    },

    async registracija(payload) {
      try {
        this.loading = true;
        const korisnik = {
          ime: payload.ime,
          prezime: payload.prezime,
          email: payload.email,
          lozinka: payload.lozinka
        };

        const response = await postAPI('auth/register', korisnik);

        this.status = response.status;
        this.statusDescription = response.description ?? '';
        this.statusMessage = response.message ?? '';
      } catch (error) {
        this.status = 'ERROR';
        this.statusMessage = error;
        this.statusDescription = error;
      } finally {
        this.loading = false;
      }
    },

    // Other actions...
  }
});
