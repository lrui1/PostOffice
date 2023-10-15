import axios from 'axios'
const _axios = axios.create({
  baseURL: import.meta.env.VITE_BACKEND_API_BASE_URL,
  withCredentials: true
})

export default _axios