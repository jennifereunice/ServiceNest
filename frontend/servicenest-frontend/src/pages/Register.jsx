// 
import React, { useState } from "react";
import { register } from "../api/auth";
import { useNavigate } from "react-router-dom";

const Register = () => {
  const [form, setForm] = useState({ name: "", email: "", password: "", role: "USER" });
  const navigate = useNavigate();

  const handleChange = (e) => setForm({ ...form, [e.target.name]: e.target.value });

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      const res = await register(form);
      localStorage.setItem("userId", res.data.id);
      localStorage.setItem("role", res.data.role);
      alert(res.data.message);
      if (form.role === "VENDOR") navigate("/vendor/complete-profile");
      else navigate("/login");
    } catch (err) {
      alert(err.response?.data?.message || "Error registering");
    }
  };

  return (
    <form onSubmit={handleSubmit}>
      <input name="name" placeholder="Name" onChange={handleChange} required />
      <input name="email" placeholder="Email" type="email" onChange={handleChange} required />
      <input name="password" placeholder="Password" type="password" onChange={handleChange} required />
      <select name="role" value={form.role} onChange={handleChange}>
        <option value="USER">User</option>
        <option value="VENDOR">Vendor</option>
      </select>
      <button type="submit">Register</button>
    </form>
  );
};

export default Register;
