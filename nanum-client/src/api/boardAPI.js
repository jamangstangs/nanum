import api from "./baseAPI";

export const getBoard = async () => {
  const response = await api.get(`main`);
  return response;
};

export const getMyBoard = async () => {
  const response = await api.get(`boards/me`);
  return response;
};

export const getHeartBoard = async () => {
  const response = await api.get(`boards/me/heart`);
  return response;
};

export const getDetailBoard = async (boardId) => {
  const response = await api.get(`boards/${boardId}/details`);
  return response;
};

export const createBoard = async (request) => {
  const response = await api.post(`boards`, request);
  return response;
};

export const completeBoard = async (boardId) => {
  const response = await api.put(`boards/${boardId}/complete`);
  return response;
};
