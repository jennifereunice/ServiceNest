import React, { useEffect, useState } from "react";
import { getVendorServices } from "../../api/VendorService";

const MyServices = () => {
  const [services, setServices] = useState([]);
  const userId = localStorage.getItem("userId");

  useEffect(() => {
    loadServices();
  }, []);

  const loadServices = async () => {
    try {
      const res = await getVendorServices(userId);
      setServices(res.data);
    } catch (err) {
      console.error(err);
      alert("Failed to load services");
    }
  };

  return (
    <div>
      <h2>My Services</h2>

      {services.length === 0 && <p>No services added yet</p>}

      {services.map((s) => (
        <div key={s.id} style={{ border: "1px solid #ccc", margin: 10, padding: 10 }}>
          <h3>{s.title}</h3>
          <p>{s.description}</p>
          <p><b>Category:</b> {s.category}</p>
          <p><b>Price:</b> ₹{s.price}</p>
          <p><b>Status:</b> {s.available ? "Available" : "Unavailable"}</p>
        </div>
      ))}
    </div>
  );
};

export default MyServices;
