import React, { useEffect, useState } from "react";
import axios from "axios";

const UserProfile = () => {
  const [user, setUser] = useState({});

  useEffect(() => {
    fetchProfile();
  }, []);

  const fetchProfile = async () => {
    try {
      const res = await axios.get(
        "http://localhost:8080/api/users/me",
        {
          headers: {
            Authorization: "Bearer " + localStorage.getItem("token"),
          },
        }
      );
      setUser(res.data);
    } catch (error) {
      console.error("Error fetching profile", error);
    }
  };

  return (
    <div>
      <h2>My Profile</h2>

      <div
        style={{
          background: "white",
          padding: "20px",
          borderRadius: "8px",
        }}
      >
        <p><b>Name:</b> {user.name}</p>
        <p><b>Email:</b> {user.email}</p>
        <p><b>Role:</b> {user.role}</p>
      </div>
    </div>
  );
};

export default UserProfile;
