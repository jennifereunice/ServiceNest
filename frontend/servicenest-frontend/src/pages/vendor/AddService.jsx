import React, { useState } from "react";
import { addVendorService } from "../../api/VendorService";
import { useNavigate } from "react-router-dom";

const AddService = () => {
  const [form, setForm] = useState({
    title: "",
    description: "",
    price: "",
    category: "",
  });

  const navigate = useNavigate();
  const userId = localStorage.getItem("userId");

  const handleChange = (e) =>
    setForm({ ...form, [e.target.name]: e.target.value });

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      await addVendorService(userId, form);
      alert("Service added successfully");
      navigate("/vendor/services");
    } catch (err) {
      console.error(err);
      alert("Failed to add service");
    }
  };

  return (
    <div>
      <h2>Add New Service</h2>

      <form onSubmit={handleSubmit}>
        <input
          name="title"
          placeholder="Service Title"
          onChange={handleChange}
          required
        /><br />

        <textarea
          name="description"
          placeholder="Description"
          onChange={handleChange}
        /><br />

        <input
          name="category"
          placeholder="Category"
          onChange={handleChange}
          required
        /><br />

        <input
          name="price"
          type="number"
          placeholder="Price"
          onChange={handleChange}
          required
        /><br />

        <button type="submit">Add Service</button>
      </form>
    </div>
  );
};

export default AddService;
