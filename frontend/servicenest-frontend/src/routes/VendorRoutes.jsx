import React from "react";
import { Routes, Route, Navigate } from "react-router-dom";

import VendorDashboard from "../pages/vendor/VendorDashboard";
import MyServices from "../pages/vendor/MyServices";
import AddService from "../pages/vendor/AddService";
import EditService from "../pages/vendor/EditService";
import ServiceRequests from "../pages/vendor/ServiceRequests";
import VendorProfile from "../pages/vendor/VendorProfile";

const VendorRoutes = () => {
  return (
    <Routes>
      {/* Layout route */}
      <Route path="dashboard" element={<VendorDashboard />}>
        {/* Default page */}
        <Route index element={<Navigate to="services" replace />} />

        {/* Vendor pages */}
        <Route path="services" element={<MyServices />} />
        <Route path="services/add" element={<AddService />} />
        <Route path="services/edit/:serviceId" element={<EditService />} />
        <Route path="requests" element={<ServiceRequests />} />
        <Route path="profile" element={<VendorProfile />} />
      </Route>
    </Routes>
  );
};

export default VendorRoutes;
