import React from "react";
import { Routes, Route } from "react-router-dom";

import Login from "./pages/Login";
import Register from "./pages/Register";
import VendorCompleteProfile from "./pages/VendorCompleteProfile";
import AdminDashboard from "./pages/AdminDashboard";

import ProtectedRoute from "./components/ProtectedRoute";
import VendorRoutes from "./routes/VendorRoutes"; // ✅ IMPORTANT
import UserRoutes from "./routes/UserRoutes";

function App() {
  return (
    <Routes>
      {/* Public */}
      <Route path="/login" element={<Login />} />
      <Route path="/register" element={<Register />} />
      <Route path="/vendor/complete-profile" element={<VendorCompleteProfile />} />

      {/* Vendor (ALL vendor pages here) */}
      <Route
        path="/vendor/*"
        element={
          <ProtectedRoute role="VENDOR">
            <VendorRoutes />
          </ProtectedRoute>
        }
      />

      {/* Admin */}
      <Route
        path="/admin/dashboard"
        element={
          <ProtectedRoute role="ADMIN">
            <AdminDashboard />
          </ProtectedRoute>
        }
      />

      {/* User */}
      <Route
        path="/user/*"
        element={
          <ProtectedRoute role="USER">
            <UserRoutes />
          </ProtectedRoute>
        }
      />
    </Routes>
  );
}

export default App;
