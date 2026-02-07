import axios from "axios";

const API = axios.create({
  baseURL: "http://localhost:8080",
});

// Attach JWT
API.interceptors.request.use((req) => {
  const token = localStorage.getItem("token");
  if (token) {
    req.headers.Authorization = `Bearer ${token}`;
  }
  return req;
});

export const getVendorServices = (userId) =>
  API.get(`/vendor/services/${userId}`);

export const addVendorService = (userId, service) =>
  API.post(`/vendor/services/${userId}`, service);



// export const addVendorService = (data) =>
//   API.post("/vendor/services", data);

export const updateVendorService = (id, data) =>
  API.put(`/vendor/services/${id}`, data);
