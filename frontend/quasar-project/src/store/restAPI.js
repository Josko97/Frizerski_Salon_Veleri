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

export function postAPI(endpoint, data = {}, throwError = false, extraHeaders = {}) {
    const url = `http://localhost:8080/api/public`
  const headers = {
    ...getTokenHeaders(),
    ...extraHeaders
  }

  return axios
    .post(url, data, {
      headers
    })
    .then(res => res.data)
    .catch(error => {
      if (!throwError) {
        if (error.response && [401, 403].includes(error.response.status)) {
          localStorage.removeItem('token')
        }
      }
      throw error
    })
}


