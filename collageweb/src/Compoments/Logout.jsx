import React from "react";
import { useAuth } from "../Authrozation/AuthContext";

const Logout = () => {
  const { logout } = useAuth();
  return (
    <div>
      <div className="logout-container">
        <button
          onClick={() => {
            logout();
            alert(`logut sucessful `);
          }}
          className="logout-button"
        >
          Logout
        </button>
      </div>
    </div>
  );
};

export default Logout;
