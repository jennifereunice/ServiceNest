import API from "./api";

// GET vendor profile
export const getVendorProfile = () => {
  return API.get("/vendor/profile");
};

// UPDATE vendor profile
export const updateVendorProfile = (profile) => {
  return API.put("/vendor/profile", profile);
};
