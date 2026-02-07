import axios from "axios";

const BASE = "http://localhost:8080/admin/vendors";

// View pending vendors
export const getPendingVendors = () =>
  axios.get(`${BASE}/pending`);

// Approve vendor
export const approveVendor = (vendorId) =>
  axios.put(`${BASE}/${vendorId}/approve`);

// Reject vendor
export const rejectVendor = (vendorId) =>
  axios.put(`${BASE}/${vendorId}/reject`);
