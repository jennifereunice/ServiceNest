import React, { useEffect, useState } from "react";
import axios from "axios";

const AdminDashboard = () => {
  const [vendors, setVendors] = useState([]);
  const token = localStorage.getItem("token");

  if (!token) {
    console.error("No JWT token found. Please login.");
    return null;
  }

  // Fetch pending vendors
  useEffect(() => {
    const fetchPendingVendors = async () => {
      try {
        const res = await axios.get(
          "http://localhost:8080/admin/vendors/pending",
          { headers: { Authorization: `Bearer ${token}` } }
        );
        console.log("Pending vendors:", res.data); // Debug
        setVendors(res.data);
      } catch (err) {
        console.error("Error fetching pending vendors:", err);
      }
    };
    fetchPendingVendors();
  }, [token]);

  // Approve vendor
  const handleApprove = async (vendorId) => {
    try {
      await axios.put(
        `http://localhost:8080/admin/vendors/${vendorId}/approve`,
        {},
        { headers: { Authorization: `Bearer ${token}` } }
      );
      // Remove approved vendor from state
      setVendors(vendors.filter((v) => v.vendorId !== vendorId));
    } catch (err) {
      console.error("Error approving vendor:", err);
      alert("Failed to approve vendor");
    }
  };

  // Reject vendor
  const handleReject = async (vendorId) => {
    try {
      await axios.put(
        `http://localhost:8080/admin/vendors/${vendorId}/reject`,
        {},
        { headers: { Authorization: `Bearer ${token}` } }
      );
      // Remove rejected vendor from state
      setVendors(vendors.filter((v) => v.vendorId !== vendorId));
    } catch (err) {
      console.error("Error rejecting vendor:", err);
      alert("Failed to reject vendor");
    }
  };

  return (
    <div>
      <h1>Pending Vendors</h1>
      {vendors.length === 0 && <p>No pending vendors</p>}
      <ul>
        {vendors.map((v) => (
          <li key={v.vendorId}>
            <strong>{v.businessName}</strong> ({v.serviceType}) - {v.city}
            <br />
            Experience: {v.experienceYears} years | Address: {v.address} | Govt ID: {v.govtIdNumber}
            <br />
            <button onClick={() => handleApprove(v.vendorId)}>Approve</button>
            <button onClick={() => handleReject(v.vendorId)}>Reject</button>
          </li>
        ))}
      </ul>
    </div>
  );
};

export default AdminDashboard;
