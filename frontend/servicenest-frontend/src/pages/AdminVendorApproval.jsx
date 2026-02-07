import React, { useEffect, useState } from "react";
import {
  getPendingVendors,
  approveVendor,
  rejectVendor
} from "../api/admin";

const AdminVendorApproval = () => {
  const [vendors, setVendors] = useState([]);

  const loadVendors = async () => {
    const res = await getPendingVendors();
    setVendors(res.data);
  };

  useEffect(() => {
    loadVendors();
  }, []);

  const handleApprove = async (vendorId) => {
    await approveVendor(vendorId);
    loadVendors(); // refresh list
  };

  const handleReject = async (vendorId) => {
    await rejectVendor(vendorId);
    loadVendors();
  };

  return (
    <div>
      <h2>Pending Vendor Approvals</h2>

      {vendors.length === 0 && <p>No pending vendors</p>}

      {vendors.map(v => (
        <div key={v.id} style={{ border: "1px solid #ccc", margin: 10, padding: 10 }}>
          <p><b>Name:</b> {v.user.name}</p>
          <p><b>Email:</b> {v.user.email}</p>
          <p><b>Service:</b> {v.serviceType}</p>
          <p><b>City:</b> {v.city}</p>
          <p><b>Experience:</b> {v.experienceYears} years</p>

          <button onClick={() => handleApprove(v.id)}>Approve</button>
          <button onClick={() => handleReject(v.id)}>Reject</button>
        </div>
      ))}
    </div>
  );
};

export default AdminVendorApproval;
