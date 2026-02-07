import React, { useState, useEffect } from "react";
import axios from "axios";
import { useNavigate } from "react-router-dom";

const VendorCompleteProfile = () => {
  const navigate = useNavigate();
  
  // Store form data
  const [form, setForm] = useState({
    businessName: "",
    serviceType: "",
    govtIdNumber: "",
    address: "",
    city: "",
    experienceYears: "",
  });

  const token = localStorage.getItem("token");
  const userId = localStorage.getItem("userId"); // Logged-in user ID

  // Handle form input changes
  const handleChange = (e) => {
    const { name, value } = e.target;
    setForm({ ...form, [name]: value });
  };

  // Submit vendor details
  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
     await axios.post(
  `http://localhost:8080/auth/vendor/${userId}/details`,
    form
    );

      alert("Profile submitted successfully. Waiting for admin approval.");
      navigate("/login"); // Redirect to login or dashboard depending on flow
    } catch (err) {
      console.error(err);
      alert(err.response?.data?.message || "Error submitting profile");
    }
  };

  return (
    <div style={{ maxWidth: "500px", margin: "0 auto", padding: "2rem" }}>
      <h2>Complete Vendor Profile</h2>
      <form onSubmit={handleSubmit} style={{ display: "flex", flexDirection: "column", gap: "1rem" }}>
        <input
          type="text"
          name="businessName"
          placeholder="Business Name"
          value={form.businessName}
          onChange={handleChange}
          required
        />
        <input
          type="text"
          name="serviceType"
          placeholder="Service Type (e.g., Plumber, Electrician)"
          value={form.serviceType}
          onChange={handleChange}
          required
        />
        <input
          type="text"
          name="govtIdNumber"
          placeholder="Government ID Number"
          value={form.govtIdNumber}
          onChange={handleChange}
        />
        <input
          type="text"
          name="address"
          placeholder="Address"
          value={form.address}
          onChange={handleChange}
          required
        />
        <input
          type="text"
          name="city"
          placeholder="City"
          value={form.city}
          onChange={handleChange}
          required
        />
        <input
          type="number"
          name="experienceYears"
          placeholder="Experience in Years"
          value={form.experienceYears}
          onChange={handleChange}
          min="0"
        />
        <button type="submit">Submit Profile</button>
      </form>
    </div>
  );
};

export default VendorCompleteProfile;
