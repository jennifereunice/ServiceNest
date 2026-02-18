import React, { useEffect, useState } from "react";
import { getAllServices, createServiceRequest } from "../../api/UserService";

const BrowseServices = () => {
  const [services, setServices] = useState([]);
  const [filteredServices, setFilteredServices] = useState([]);
  const [filter, setFilter] = useState("");

  // Fetch services on mount
  useEffect(() => {
    fetchServices();
  }, []);

  const fetchServices = async () => {
    try {
      const res = await getAllServices(); // API returns DTO with vendor info + request info
      setServices(res.data);
      setFilteredServices(res.data);
    } catch (error) {
      console.error("Error fetching services", error);
      alert("Failed to load services");
    }
  };

  // Handle service request
  const handleRequest = async (serviceId) => {
    try {
      await createServiceRequest(serviceId);
      alert("Request sent successfully!");
      fetchServices(); // refresh services to update request status
    } catch (error) {
      console.error(error);
      alert(
        error.response?.data?.message || "Failed to send request"
      );
    }
  };

  // Filter services by category
  useEffect(() => {
    if (!filter) {
      setFilteredServices(services);
    } else {
      const filtered = services.filter((service) =>
        service.category?.toLowerCase().includes(filter.toLowerCase())
      );
      setFilteredServices(filtered);
    }
  }, [filter, services]);

  return (
    <div style={{ maxWidth: "800px", margin: "0 auto" }}>
      <h2>Browse Services</h2>

      <input
        type="text"
        placeholder="Filter by category..."
        value={filter}
        onChange={(e) => setFilter(e.target.value)}
        style={{ padding: "8px", width: "100%", marginBottom: "20px" }}
      />
{filteredServices.map((service) => (
  <div
    key={service.id} // using VendorService ID
    style={{
      marginBottom: "20px",
      padding: "15px",
      border: "1px solid #ccc",
      borderRadius: "5px",
    }}
  >
    <h3>{service.title}</h3>
    <p><strong>Category:</strong> {service.category}</p>
    <p><strong>Price:</strong> ₹{service.price}</p>

    {/* Vendor Info */}
    {service.vendor && (
      <>
        <p><strong>Provider:</strong> {service.vendor.businessName}</p>
        <p><strong>City:</strong> {service.vendor.city}</p>
      </>
    )}
    
    <p><strong>Availability:</strong> {service.available ? "Yes" : "No"}</p>

    {/* Request button */}
    <button
      onClick={() => handleRequest(service.id)}
      disabled={!service.available}
      style={{
        padding: "8px 12px",
        backgroundColor: service.available ? "#4CAF50" : "#ccc",
        color: "#fff",
        border: "none",
        borderRadius: "4px",
        cursor: service.available ? "pointer" : "not-allowed",
      }}
    >
      Request Service
    </button>
  </div>
))}

    </div>
  );
};

export default BrowseServices;
