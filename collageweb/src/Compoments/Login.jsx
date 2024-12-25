import React, { useState } from "react";
import "../Styles/Login.css";
import BaseUrl from "../URLS/BaseUrl";
import { useAuth } from "../Authrozation/AuthContext";
import { useNavigate } from "react-router-dom";

const Login = () => {
  const [username, setUsername] = useState();
  const [password, setPassword] = useState();
  const { saveToken } = useAuth();
  const navagatie = useNavigate();
  const handleSubmit = async (e) => {
    e.preventDefault();
    await BaseUrl.post(`/login`, {
      username: username,
      password: password,
    })
      .then((res) => {
        saveToken(res.data.token);

        if (res.data.role === `ADMIN`)
          navagatie(`/welcome/${res.data.username}`);
        else navagatie("/stdDet");
      })
      .catch((res) => {
        if (res.response) {
          if (res.response.status === 404) {
            alert("User not found");
          } else if (res.response.status === 401) {
            alert(
              "Invalid credentials, please check your username or password."
            );
          } else {
            console.log(res.error);
          }
        } else alert("Network error, please try again later.");
      });
  };

  return (
    <>
      <div className="grid">
        <form className="form" onSubmit={handleSubmit}>
          <h1>Login</h1>
          <label>Username</label>
          <input
            type="text"
            onChange={(e) => setUsername(e.target.value)}
            required
          />
          <label>Password</label>
          <input
            type="password"
            onChange={(e) => setPassword(e.target.value)}
            required
          />
          <button type="submit">Login</button>
        </form>
      </div>
    </>
  );
};

export default Login;
