import api from "./baseAPI";

export const getHeart = async (boardId) => {
  const response = await api.get(`boards/${boardId}/heart`);
  return response;
};

export const createHeart = async (boardId) => {
  const response = await api.post(`boards/${boardId}/heart`);
  return response;
};
