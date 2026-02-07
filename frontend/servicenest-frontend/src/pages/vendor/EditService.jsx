// src/pages/vendor/EditService.jsx
import React, { useState } from "react";
import { updateVendorService } from "../../api/VendorService";
import { useParams, useNavigate } from "react-router-dom";

const EditService = () => {
  const { id } = useParams();
  const navigate = useNavigate();
  const [form, setForm] = useState({});

  const handleSubmit = async (e) => {
    e.preventDefault();
    await updateVendorService(id, form);
    navigate("/vendor/dashboard/services");
  };

  return (
    <form onSubmit={handleSubmit}>
      <h2>Edit Service</h2>

      <input placeholder="Title"
        onChange={e => setForm({ ...form, title: e.target.value })} />

      <input placeholder="Price"
        onChange={e => setForm({ ...form, price: e.target.value })} />

      <select onChange={e => setForm({ ...form, available: e.target.value === "true" })}>
        <option value="true">Available</option>
        <option value="false">Not Available</option>
      </select>

      <button>Update</button>
    </form>
  );
};

export default EditService;
