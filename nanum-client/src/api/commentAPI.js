import api from "./baseAPI";

export const getComment = async (boardId) => {
  const response = await api.get(`boards/${boardId}/comments`);
  return response;
};

export const postComment = async (boardId, request) => {
  const response = await api.post(`boards/${boardId}/comments`, request);
  return response;
};
