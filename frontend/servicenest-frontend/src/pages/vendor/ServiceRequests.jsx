import React, { useEffect, useState } from "react";
import {
  getRequests,
  acceptRequest,
  rejectRequest,
  completeRequest,
} from "../../api/VendorRequests";
import RequestCard from "./RequestCard";

const ServiceRequests = () => {
  const vendorId = localStorage.getItem("userId");
  const [status, setStatus] = useState("PENDING");
  const [requests, setRequests] = useState([]);

  useEffect(() => {
    loadRequests();
  }, [status]);

  const loadRequests = async () => {
    try {
      const res = await getRequests(vendorId, status);
      setRequests(res.data);
    } catch (err) {
      console.error(err);
      alert("Failed to load requests");
    }
  };

  const handleAccept = async (id) => {
    await acceptRequest(id);
    loadRequests();
  };

  const handleReject = async (id) => {
    await rejectRequest(id);
    loadRequests();
  };

  const handleComplete = async (id) => {
    await completeRequest(id);
    loadRequests();
  };

  return (
    <div>
      <h2>Service Requests</h2>

      {/* Tabs */}
      <div style={{ marginBottom: 20 }}>
        {["PENDING", "ACCEPTED", "COMPLETED"].map((s) => (
          <button
            key={s}
            onClick={() => setStatus(s)}
            style={{
              marginRight: 10,
              fontWeight: status === s ? "bold" : "normal",
            }}
          >
            {s}
          </button>
        ))}
      </div>

      {requests.length === 0 && <p>No requests</p>}

      {requests.map((req) => (
        <RequestCard
          key={req.id}
          req={req}
          status={status}
          onAccept={handleAccept}
          onReject={handleReject}
          onComplete={handleComplete}
        />
      ))}
    </div>
  );
};

export default ServiceRequests;
