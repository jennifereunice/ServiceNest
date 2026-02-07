import axios from "axios";

const API_BASE = "http://localhost:8080"; // change to your backend

// Register user/vendor
export const register = (data) => axios.post(`${API_BASE}/auth/register`, data);

// Login
export const login = (data) => axios.post(`${API_BASE}/auth/login`, data);

// Complete vendor profile
export const completeVendorProfile = (vendorId, data, token) =>
  axios.put(`${API_BASE}/vendor/${vendorId}/profile`, data, {
    headers: { Authorization: `Bearer ${token}` },
  });

// Admin approve/reject
export const approveVendor = (vendorId, token) =>
  axios.put(`${API_BASE}/admin/vendor/${vendorId}/approve`, {}, {
    headers: { Authorization: `Bearer ${token}` },
  });

export const rejectVendor = (vendorId, token) =>
  axios.put(`${API_BASE}/admin/vendor/${vendorId}/reject`, {}, {
    headers: { Authorization: `Bearer ${token}` },
  });
