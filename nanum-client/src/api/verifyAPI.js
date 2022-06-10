import api from "./baseAPI";

export const createSignUpVerificationCode = async (request) => {
  const response = await api.post(`sign-up/verifications`, request);
  return response;
};

export const confirmSignUpVerificationCode = async (request) => {
  const response = await api.post(`sign-up/confirm`, request);
  return response;
};
