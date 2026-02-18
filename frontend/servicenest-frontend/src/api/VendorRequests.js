import axios from "axios";

const API = axios.create({
  baseURL: "http://localhost:8080",
});

API.interceptors.request.use((req) => {
  const token = localStorage.getItem("token");
  if (token) {
    req.headers.Authorization = `Bearer ${token}`;
  }
  return req;
});

export const getRequests = (vendorId, status) =>
  API.get(`/vendor/requests/${status}`, { params: { vendorUserId: vendorId } });


export const acceptRequest = (id) =>
  API.put(`/vendor/requests/${id}/accept`);

export const rejectRequest = (id) =>
  API.put(`/vendor/requests/${id}/reject`);

export const completeRequest = (id) =>
  API.put(`/vendor/requests/${id}/complete`);
