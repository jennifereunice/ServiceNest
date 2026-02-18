import React from "react";
import { Routes, Route, Navigate } from "react-router-dom";

import UserDashboard from "../pages/user/UserDashboard";
import BrowseServices from "../pages/user/BrowseServices";
import MyRequests from "../pages/user/MyRequests";
import UserProfile from "../pages/user/UserProfile";

const UserRoutes = () => {
  return (
    <Routes>
      <Route path="dashboard" element={<UserDashboard />}>
        <Route index element={<Navigate to="services" replace />} />
        <Route path="services" element={<BrowseServices />} />
        <Route path="requests" element={<MyRequests />} />
        <Route path="profile" element={<UserProfile />} />
      </Route>
    </Routes>
  );
};

export default UserRoutes;
