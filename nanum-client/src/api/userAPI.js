import api from "./baseAPI";

export const signup = async (request) => {
  const response = await api.post(`signups`, request);
  return response;
};

export const signin = async (request) => {
  const response = await api.post(`login`, request);
  return response;
};
