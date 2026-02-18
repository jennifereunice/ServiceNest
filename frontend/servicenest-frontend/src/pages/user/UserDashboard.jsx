import React from "react";
import { Outlet, Link, useLocation } from "react-router-dom";

const UserDashboard = () => {
  const location = useLocation();

  const isActive = (path) => {
    return location.pathname.includes(path);
  };

  return (
    <div style={{ display: "flex", minHeight: "100vh" }}>
      
      {/* Sidebar */}
      <div
        style={{
          width: "220px",
          background: "#1e293b",
          color: "white",
          padding: "20px",
        }}
      >
        <h2 style={{ marginBottom: "30px" }}>User Panel</h2>

        <ul style={{ listStyle: "none", padding: 0 }}>
          <li style={{ marginBottom: "15px" }}>
            <Link
              to="services"
              style={{
                color: isActive("services") ? "#38bdf8" : "white",
                textDecoration: "none",
              }}
            >
              Browse Services
            </Link>
          </li>

          <li style={{ marginBottom: "15px" }}>
            <Link
              to="requests"
              style={{
                color: isActive("requests") ? "#38bdf8" : "white",
                textDecoration: "none",
              }}
            >
              My Requests
            </Link>
          </li>

          <li>
            <Link
              to="profile"
              style={{
                color: isActive("profile") ? "#38bdf8" : "white",
                textDecoration: "none",
              }}
            >
              Profile
            </Link>
          </li>
        </ul>
      </div>

      {/* Main Content */}
      <div
        style={{
          flex: 1,
          background: "#f1f5f9",
          padding: "30px",
        }}
      >
        <Outlet />
      </div>
    </div>
  );
};

export default UserDashboard;
