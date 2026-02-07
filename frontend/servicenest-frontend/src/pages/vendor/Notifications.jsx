// src/pages/vendor/Notifications.jsx
import React, { useEffect, useState } from "react";
import axios from "axios";

const Notifications = () => {
  const [list, setList] = useState([]);

  useEffect(() => {
    axios.get("http://localhost:8080/api/vendor/notifications", { withCredentials: true })
      .then(res => setList(res.data));
  }, []);

  return (
    <div>
      <h2>Notifications</h2>

      {list.map(n => (
        <div key={n.id} style={{ background: n.readStatus ? "#eee" : "#dff" }}>
          {n.message}
        </div>
      ))}
    </div>
  );
};

export default Notifications;
