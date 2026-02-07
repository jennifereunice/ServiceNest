import React from "react";
import { Link, Outlet } from "react-router-dom";

const VendorDashboard = () => {
  return (
    <div style={{ display: "flex", minHeight: "100vh" }}>
      
      {/* Sidebar */}
      <div style={{ width: "220px", padding: "20px", borderRight: "1px solid #ccc", backgroundColor: "#f5f5f5" }}>
        <h3>Vendor Panel</h3>

        <ul style={{ listStyle: "none", padding: 0 }}>
          <li style={{ margin: "10px 0" }}>
            <Link to="/vendor/dashboard/services">My Services</Link>
          </li>
          <li style={{ margin: "10px 0" }}>
            <Link to="/vendor/dashboard/services/add">Add Service</Link>
          </li>
          <li style={{ margin: "10px 0" }}>
            <Link to="/vendor/dashboard/requests">Service Requests</Link>
          </li>
          <li style={{ margin: "10px 0" }}>
            <Link to="/vendor/dashboard/profile">Profile</Link>
          </li>
        </ul>
      </div>

      {/* Page Content */}
      <div style={{ flex: 1, padding: "20px" }}>
        <Outlet />
      </div>

    </div>
  );
};

export default VendorDashboard;
