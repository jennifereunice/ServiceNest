import React, { useEffect, useState } from "react";
import { getUserRequests, deleteRequest } from "../../api/UserService";

const MyRequests = () => {
  const [requests, setRequests] = useState([]);

  useEffect(() => {
    fetchRequests();
  }, []);

  const fetchRequests = async () => {
    try {
      const res = await getUserRequests();
      setRequests(res.data);
    } catch (error) {
      console.error("Error fetching requests", error);
    }
  };

  const handleDelete = async (id) => {
    try {
      await deleteRequest(id);
      fetchRequests(); // 🔥 Refresh list after delete
    } catch (error) {
      console.error("Error deleting request", error);
    }
  };

  return (
    <div>
      <h2>My Requests</h2>

      {requests.map((req) => (
        <div key={req.id} style={{border:"1px solid gray", margin:"10px", padding:"10px"}}>
          <h3>{req.service?.title}</h3>
          <p>Status: {req.status}</p>

          <button onClick={() => handleDelete(req.id)}>
            Delete
          </button>
        </div>
      ))}
    </div>
  );
};

export default MyRequests;
