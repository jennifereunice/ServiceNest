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
