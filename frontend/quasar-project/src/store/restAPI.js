  /* eslint-disable */

  import axios from 'axios';

export function getAPI(endpoint, params = {}, throwError = false) {
  const url = `http://localhost:8080/api/public`

  return axios
    .get(url, {
      params,
      headers: getTokenHeaders()
    })
    .then(res => res.data)
    .catch(error => {
      if (!throwError) {
        if (error.response && [401, 403].includes(error.response.status)) {
          // U jednostavnoj verziji, samo čistimo token i bacamo grešku
          localStorage.removeItem('token')
        }
      }
      throw error
    })
}
/**
export function postAPI(endpoint, data = {}, throwError = false, extraHeaders = {}) {
  console.log('ušao sam!')
  const url = 'http://localhost:8080/api/public'
  const headers = {

  }

  return axios
    .post(url, data, {
      headers
    })
    .then(res => res.data)
    .catch(error => {
      console.log(error)
      if (!throwError) {
        if (error.response && [401, 403].includes(error.response.status)) {
          localStorage.removeItem('token')
        }
      }
      throw error
    })
}
*/



export function postAPI(endpoint, data = {}, throwError = false) {
  console.log('ušao sam!');

  const url = `http://localhost:8080/api/public/${endpoint}`;

  return axios
    .post(url, data)
    .then(res => {
      console.log('Response:', res.data);
      return res.data;
    })
    .catch(error => {
      console.log('Error:', error);
      if (!throwError) {
        if (error.response && [401, 403].includes(error.response.status)) {
          console.log('Unauthorized or forbidden, removing token');
          localStorage.removeItem('token');
        }
      }
      throw error; // Rethrow the error for further handling
    });
}


