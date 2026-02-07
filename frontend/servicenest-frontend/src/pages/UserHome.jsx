import React, { useEffect, useState } from "react";
import { getAllServices, getUserRequests } from "../api/UserService";

const UserHome = () => {
  const [activeTab, setActiveTab] = useState("SERVICES");
  const [services, setServices] = useState([]);
  const [requests, setRequests] = useState([]);

  const user = JSON.parse(localStorage.getItem("user"));

  useEffect(() => {
    loadServices();
    loadRequests();
  }, []);

  const loadServices = async () => {
    const res = await getAllServices();
    setServices(res.data);
  };

  const loadRequests = async () => {
    const res = await getUserRequests();
    setRequests(res.data);
  };

  return (
    <div style={{ display: "flex", padding: "20px" }}>
      
      {/* SIDEBAR */}
      <div style={{ width: "20%", marginRight: "20px" }}>
        <h2>Menu</h2>
        <ul style={{ cursor: "pointer" }}>
          <li onClick={() => setActiveTab("SERVICES")}>Browse Services</li>
          <li onClick={() => setActiveTab("REQUESTS")}>My Requests</li>
          <li onClick={() => setActiveTab("PROFILE")}>Profile</li>
        </ul>
      </div>

      {/* MAIN CONTENT */}
      <div style={{ width: "80%" }}>
        <h1>Welcome, {user?.name}</h1>

        {/* 🔹 SERVICES */}
        {activeTab === "SERVICES" && (
          <section>
            <h2>Available Services</h2>

            {services.map((s) => (
              <div key={s.id} style={{ border: "1px solid #ccc", padding: 10, margin: 10 }}>
                <h3>{s.title}</h3>
                <p>{s.description}</p>
                <p>
                  Vendor: {s.vendor?.businessName} | ₹{s.price} | {s.category}
                </p>
                <button>Request Service</button>
              </div>
            ))}
          </section>
        )}

        {/* 🔹 REQUESTS */}
        {activeTab === "REQUESTS" && (
          <section>
            <h2>My Requests</h2>

            {requests.map((r) => (
              <div key={r.id} style={{ border: "1px solid #eee", padding: 10, margin: 10 }}>
                <p><b>Service:</b> {r.service?.title}</p>
                <p><b>Status:</b> {r.status}</p>
                <p><b>Vendor:</b> {r.vendor?.businessName}</p>
              </div>
            ))}
          </section>
        )}

        {/* 🔹 PROFILE */}
        {activeTab === "PROFILE" && (
          <section>
            <h2>My Profile</h2>
            <p><b>Name:</b> {user?.name}</p>
            <p><b>Email:</b> {user?.email}</p>
            <p><b>Phone:</b> {user?.phone}</p>
            <p><b>Role:</b> {user?.role}</p>
          </section>
        )}

      </div>
    </div>
  );
};

export default UserHome;
