import React from "react";

const RequestCard = ({ req, status, onAccept, onReject, onComplete }) => {
  return (
    <div
      style={{
        border: "1px solid #ccc",
        padding: 15,
        marginBottom: 10,
        borderRadius: 6,
      }}
    >
      <p><b>Customer:</b> {req.user?.name}</p>
      <p><b>Service:</b> {req.service?.title}</p>
      <p><b>Category:</b> {req.service?.category}</p>
      <p><b>Price:</b> ₹{req.service?.price}</p>
      <p><b>Address:</b> {req.address}</p>
      <p><b>Scheduled At:</b> {req.scheduledAt}</p>

      {status === "PENDING" && (
        <>
          <button onClick={() => onAccept(req.id)}>Accept</button>
          <button onClick={() => onReject(req.id)} style={{ marginLeft: 10 }}>
            Reject
          </button>
        </>
      )}

      {status === "ACCEPTED" && (
        <button onClick={() => onComplete(req.id)}>Mark Completed</button>
      )}
    </div>
  );
};

export default RequestCard;
