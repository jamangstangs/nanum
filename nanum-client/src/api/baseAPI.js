import axios from "axios";

const API_URL = process.env.REACT_APP_API_URL;

const config = {
  baseURL: API_URL,
  timeout: 10000,
};

const api = axios.create(config);

export default api;
