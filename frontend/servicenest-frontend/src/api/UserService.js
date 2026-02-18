import API from "./api";

/**
 * Get all available services (for users)
 */
export const getAllServices = () => {
  return API.get("/user/services");
};

/**
 * Get logged-in user's service requests
 */
export const getUserRequests = () => {
  return API.get("/user/requests");
};

/**
 * Create request for a service
 */
export const createServiceRequest = (serviceId) => {
  return API.post(`/user/requests/${serviceId}`);
};

export const deleteRequest = (id) => {
  return API.delete(`/user/requests/${id}`);
};


