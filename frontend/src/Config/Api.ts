import axios from 'axios';

// Backend ka address (local computer par)
export const API_URL = "http://localhost:5454";

export const api = axios.create({
  baseURL: API_URL,
  headers: {
    'Content-Type': 'application/json',
  },
});