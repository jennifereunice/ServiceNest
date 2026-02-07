import React from "react";
import { Navigate } from "react-router-dom";

const ProtectedRoute = ({ children, role }) => {
  const token = localStorage.getItem("token");
  const userRole = localStorage.getItem("role");
  const status = localStorage.getItem("status");

  if (!token) return <Navigate to="/login" />;

  if (userRole !== role) return <Navigate to="/login" />;

  // Vendor not approved yet
  if (role === "VENDOR" && status !== "APPROVED") {
    return <div>Waiting for admin approval</div>;
  }

  return children;
};

export default ProtectedRoute;
